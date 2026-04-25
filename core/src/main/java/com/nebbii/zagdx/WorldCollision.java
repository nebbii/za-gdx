package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ShortArray;
import com.nebbii.zagdx.Enemy.EnemyState;
import com.nebbii.zagdx.GameManager.GameState;

/*
 * Handling all collision in the world, knows all actors and map objects
 */
public class WorldCollision {
    private static final int MAX_PUSH_ITERATIONS = 8; // how many polygon nudges per frame

    private MapManager map;
    private GameManager game;

    private MapLayerObjects collision;
    private MapLayerObjects special;
    public ArrayList<Actor> actors;

    private final EarClippingTriangulator triangulator = new EarClippingTriangulator();

    public WorldCollision(MapManager map, GameManager game) {
        this.map = map;
        this.game = game;
        this.collision = map.collision;
        this.special = map.special;
        this.actors = map.actors;
    }

    public void logic() {
        collideActorsWithCollision();
        collideZeldaWithEnemies();
        collideZeldaWithPickups();
        collideProjectilesWithEnemies();
        checkOverlapAlertBoxes();
        collideEnemiesWithWorldBorders();
    }

    private void collideZeldaWithPickups() {
        for (Actor pickup : actors) {
            if (!(pickup instanceof Pickup) || !pickup.isActive()) continue;

            if (pickup.getHitbox().overlaps(map.getZelda().getHitbox())) {
                ((Pickup) pickup).onPickup(game);
            }
        }
    }

    private void collideZeldaWithEnemies() {
        for (Actor actor : actors) {
            if (!(actor instanceof Enemy) || !actor.isActive()) continue;

            Enemy enemy = (Enemy) actor;

            if (enemy.getHitbox().overlaps(map.getZelda().getHitbox())) {
                map.getZelda().onHit(enemy.getHitDamage());
            }
        }
    }

    private void collideProjectilesWithEnemies() {
        for (Actor overlapper : actors) {
            if (!overlapper.isActive()) continue;
            if (!(overlapper instanceof Enemy)) continue;

            Enemy enemy = (Enemy) overlapper;

            for (Actor overlapee : actors) {
                if (!overlapee.isActive()) continue;
                if (!(overlapee instanceof ZeldaAction)) continue;

                ZeldaAction projectile = (ZeldaAction) overlapee;

                if (enemy.getHitbox().overlaps(projectile.getHitbox())) {
                    enemy.setHurtDirection(projectile.getDirection());
                    enemy.onHit(projectile.getDamage(), 0.2f);
                }
            }
        }
    }

    private void collideActorsWithCollision() {
        List<PolygonMapObject> collisionObjects = this.collision.getPolygonObjects();
        List<PolygonMapObject> specialObjects = this.special.getPolygonObjects();

        for (Actor actor : actors) {
            if (!actor.isSolid()) continue;

            Rectangle actorRectangle = actor.getCollisionBox();

            for (PolygonMapObject collisionObject : collisionObjects) {
                Polygon polygon = collisionObject.getPolygon();

                // cheap overlap check
                if (!actorRectangle.overlaps(polygon.getBoundingRectangle())) {
                    continue;
                }

                resolveRectangleVsPolygon(actorRectangle, polygon);
                if (actor instanceof Enemy) {
                    Enemy enemy = (Enemy) actor;
                    enemy.setDirection(enemy.getRandomDirection());
                }
            }

            for (PolygonMapObject specialObject : specialObjects) {
                Polygon polygon = specialObject.getPolygon();

                // cheap overlap check
                if (!actorRectangle.overlaps(polygon.getBoundingRectangle())) {
                    continue;
                }

                if (actor instanceof Enemy) {
                    Enemy enemy = (Enemy) actor;

                    resolveRectangleVsPolygon(actorRectangle, polygon);
                    enemy.setDirection(enemy.getRandomDirection());
                }
                else if (actor instanceof Zelda) {
                    collideZeldaWithSpecial(specialObject, (Zelda) actor);
                }
            }
        }
    }

    private void collideZeldaWithSpecial(PolygonMapObject polygonObject, Zelda zelda) {
        switch(polygonObject.getName()) {
        case "andor":
            if(!game.getTreasures().contains(Treasure.RED_BOOTS)) {
                resolveRectangleVsPolygon(zelda, polygonObject.getPolygon());
            }
            break;
        case "entrance_earth":
            game.setNextMap("shrine_of_earth");
            game.initializeFadeWarp();
            break;
        default:
            resolveRectangleVsPolygon(zelda, polygonObject.getPolygon());
        }
    }

    private void collideEnemiesWithWorldBorders() {
        Rectangle[] borders = game.getWorld().getWorldBorders();

        Rectangle leftBorder = borders[0];
        Rectangle rightBorder = borders[1];
        Rectangle bottomBorder = borders[2];
        Rectangle topBorder = borders[3];

        for (Actor actor : actors) {
            if (!(actor instanceof Enemy)) continue;
            if (!actor.isActive()) continue;

            Enemy enemy = (Enemy) actor;

            Rectangle enemyCollision = (Enemy) actor.getCollisionBox();

            boolean bounced = false;

            if (leftBorder.overlaps(enemyCollision)) {
                enemy.setX(leftBorder.x + leftBorder.width + 1f);
                bounced = true;
            } else if (rightBorder.overlaps(enemyCollision)) {
                enemy.setX(rightBorder.x - enemyCollision.width - 1f);
                bounced = true;
            }

            if (bottomBorder.overlaps(enemyCollision)) {
                enemy.setY(bottomBorder.y + bottomBorder.height + 1f);
                bounced = true;
            } else if (topBorder.overlaps(enemyCollision)) {
                enemy.setY(topBorder.y - enemyCollision.height - 1f);
                bounced = true;
            }

            if (bounced) {
                enemy.setDirection(enemy.getRandomDirection());
            }
        }
    }

    private void checkOverlapAlertBoxes() {
        for (Actor actor : actors) {
            if (!actor.isActive()) continue;
            if (!(actor instanceof Enemy)) continue;

            Enemy enemy = (Enemy) actor;
            Zelda zelda = map.getZelda();

            if (enemy.getAlertBox().overlaps(zelda.getHitbox())) {
                enemy.setTargetX(zelda.getCenterPointX());
                enemy.setTargetY(zelda.getCenterPointY());

                if (enemy.getEnemyState() == EnemyState.SEARCHING) {
                    enemy.setEnemyState(EnemyState.FIGHTING);
                    enemy.changeDirectionTowardsTarget();
                    Gdx.app.log("WorldCollision", actor.getClass() + " is aggro'd!");
                }
            }
            else {
                if (enemy.getEnemyState() == EnemyState.FIGHTING) {
                    enemy.setEnemyState(EnemyState.SEARCHING);
                    Gdx.app.log("WorldCollision", actor.getClass() + " went back to searching...");
                }
            }
        }
    }

    /**
     * @nebbii: Resolve rectangle against a polygon that may be convex or concave.
     * Concave polygons are triangulated into convex pieces, then all overlapping
     * triangles are resolved in repeated passes.
     */
    private void resolveRectangleVsPolygon(Rectangle movingRectangle, Polygon solidPolygon) {
        float[] polyVertices = solidPolygon.getTransformedVertices();

        if (polyVertices.length < 6) return;

        // Triangle case: already convex
        if (polyVertices.length == 6) {
            for (int pass = 0; pass < MAX_PUSH_ITERATIONS; pass++) {
                Vector2 push = computePushOutVectorConvex(movingRectangle, polyVertices);

                if (push.len2() <= 0.0001f) break;

                movingRectangle.x += push.x;
                movingRectangle.y += push.y;
            }

            return;
        }

        ShortArray triangleIndices = triangulator.computeTriangles(polyVertices);

        for (int pass = 0; pass < MAX_PUSH_ITERATIONS; pass++) {
            boolean moved = false;

            for (int i = 0; i < triangleIndices.size; i += 3) {
                int i0 = triangleIndices.get(i) * 2;
                int i1 = triangleIndices.get(i + 1) * 2;
                int i2 = triangleIndices.get(i + 2) * 2;

                float[] triangleVertices = new float[] {
                    polyVertices[i0], polyVertices[i0 + 1],
                    polyVertices[i1], polyVertices[i1 + 1],
                    polyVertices[i2], polyVertices[i2 + 1]
                };

                Vector2 push = computePushOutVectorConvex(movingRectangle, triangleVertices);

                if (push.len2() > 0.0001f) {
                    movingRectangle.x += push.x;
                    movingRectangle.y += push.y;
                    moved = true;
                }
            }

            if (!moved) break;
        }
    }

    /**
     * SAT push-out for rectangle vs convex shapes (https://dyn4j.org/2010/01/sat/)
     */
    private static Vector2 computePushOutVectorConvex(Rectangle movingRectangle, float[] polyVertices) {
        Vector2 mtv = new Vector2();

        float[] rectangleVertices = new float[] {
            movingRectangle.x, movingRectangle.y,
            movingRectangle.x + movingRectangle.width, movingRectangle.y,
            movingRectangle.x + movingRectangle.width, movingRectangle.y + movingRectangle.height,
            movingRectangle.x, movingRectangle.y + movingRectangle.height
        };

        if (polyVertices.length < 6) {
            return mtv.set(0f, 0f);
        }

        float smallestOverlap = Float.POSITIVE_INFINITY;
        Vector2 smallestAxis = new Vector2();

        // Polygon edge normals
        for (int i = 0; i < polyVertices.length; i += 2) {
            int next = (i + 2) % polyVertices.length;

            float x1 = polyVertices[i];
            float y1 = polyVertices[i + 1];
            float x2 = polyVertices[next];
            float y2 = polyVertices[next + 1];

            float edgeX = x2 - x1;
            float edgeY = y2 - y1;

            if (edgeX == 0f && edgeY == 0f) continue;

            Vector2 axis = new Vector2(-edgeY, edgeX).nor();

            float overlap = getOverlapOnAxis(rectangleVertices, polyVertices, axis);

            if (overlap <= 0f) {
                return mtv.set(0f, 0f);
            }

            if (overlap < smallestOverlap) {
                smallestOverlap = overlap;
                smallestAxis.set(axis);
            }
        }

        // Rectangle axes
        Vector2[] rectangleAxes = {
            new Vector2(1f, 0f),
            new Vector2(0f, 1f)
        };

        for (Vector2 axis : rectangleAxes) {
            float overlap = getOverlapOnAxis(rectangleVertices, polyVertices, axis);
            if (overlap <= 0f) {
                return mtv.set(0f, 0f);
            }

            if (overlap < smallestOverlap) {
                smallestOverlap = overlap;
                smallestAxis.set(axis);
            }
        }

        // Make sure push points from polygon outward toward the rectangle
        Vector2 rectangleCenter = new Vector2(
            movingRectangle.x + movingRectangle.width * 0.5f,
            movingRectangle.y + movingRectangle.height * 0.5f
        );

        Vector2 polygonCenter = computePolygonCenter(polyVertices);
        Vector2 centerDelta = new Vector2(rectangleCenter).sub(polygonCenter);

        if (centerDelta.dot(smallestAxis) < 0f) {
            smallestAxis.scl(-1f);
        }

        return mtv.set(smallestAxis).scl(smallestOverlap);
    }

    private static float getOverlapOnAxis(float[] rectangleVertices, float[] polyVertices, Vector2 axis) {
        float[] rectProjection = projectVertices(rectangleVertices, axis);
        float[] polyProjection = projectVertices(polyVertices, axis);

        float minA = rectProjection[0];
        float maxA = rectProjection[1];
        float minB = polyProjection[0];
        float maxB = polyProjection[1];

        return Math.min(maxA, maxB) - Math.max(minA, minB);
    }

    private static float[] projectVertices(float[] vertices, Vector2 axis) {
        float min = Float.POSITIVE_INFINITY;
        float max = Float.NEGATIVE_INFINITY;

        for (int i = 0; i < vertices.length; i += 2) {
            float projection = vertices[i] * axis.x + vertices[i + 1] * axis.y;
            if (projection < min) min = projection;
            if (projection > max) max = projection;
        }

        return new float[] { min, max };
    }

    private static Vector2 computePolygonCenter(float[] vertices) {
        float sumX = 0f;
        float sumY = 0f;
        int count = vertices.length / 2;

        for (int i = 0; i < vertices.length; i += 2) {
            sumX += vertices[i];
            sumY += vertices[i + 1];
        }

        return new Vector2(sumX / count, sumY / count);
    }
}
