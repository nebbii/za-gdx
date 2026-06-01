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

/*
 * Handling all collision in the world, knows all actors and map objects
 */
public class WorldCollision {
    private static final int MAX_PUSH_ITERATIONS = 8; // how many polygon nudges per tick
    private static final float MIN_PUSH_LEN2 = 0.000001f;
    private static final float CONVEX_EPSILON = 0.00001f;

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
        collideProjectilesWithEnemies();
        collideProjectilesWithCollision();
        collideActorsWithCollision();
        collideZeldaWithEnemies();
        collideZeldaWithEnemyProjectiles();
        collideZeldaWithPickups();
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
        Zelda zelda = map.getZelda();

        for (Actor actor : actors) {
            if (!(actor instanceof Enemy) || !actor.isActive()) continue;

            Enemy enemy = (Enemy) actor;

            if (enemy.getHitbox().overlaps(zelda.getHitbox())) {
                int damage = game.calculateDamage(enemy, zelda);
                float knockback = game.calculateZeldaKnockback();
                zelda.onHit(damage, knockback);
            }
        }
    }

    private void collideZeldaWithEnemyProjectiles() {
        Zelda zelda = map.getZelda();

        for (Actor actor : actors) {
            if (!(actor instanceof EnemyAction) || !actor.isActive()) continue;

            EnemyAction projectile = (EnemyAction) actor;

            if (projectile.getHitbox().overlaps(zelda.getHitbox())) {
                int damage = game.calculateDamage(projectile, zelda);
                float knockback = game.calculateZeldaKnockback();

                zelda.onHit(damage, knockback);
                projectile.onHit();
            }
        }
    }

    private void collideProjectilesWithEnemies() {
        for (Actor overlapper : actors) {
            if (!(overlapper instanceof Enemy) || !overlapper.isActive()) continue;

            Enemy enemy = (Enemy) overlapper;

            for (Actor overlapee : actors) {
                if (!overlapee.isActive()) continue;
                if (!(overlapee instanceof ZeldaAction)) continue;

                ZeldaAction projectile = (ZeldaAction) overlapee;

                if (enemy.getHitbox().overlaps(projectile.getHitbox())) {
                    int damage = game.calculateDamage(projectile, enemy);
                    float knockback = game.calculateZeldaKnockback();

                    enemy.setHurtDirection(projectile.getDirection());
                    enemy.onHit(damage, knockback);

                    projectile.onHit();
                }
            }
        }
    }

    private void collideProjectilesWithCollision() {
        List<PolygonMapObject> collisionObjects = this.collision.getPolygonObjects();
        List<PolygonMapObject> specialObjects = this.special.getPolygonObjects();

        for (Actor actor : actors) {
            if (!actor.isSolid() || !actor.isActive()) continue;
            if (actor.getType() != ActorType.PROJECTILE) continue;

            Rectangle actorRectangle = actor.getCollisionBox();

            if (rectangleOverlapsAnyPolygon(actorRectangle, collisionObjects)
                || rectangleOverlapsAnyPolygon(actorRectangle, specialObjects)
            ) {
                actor.setState(State.DEAD);
            }
        }
    }

    private void collideActorsWithCollision() {
        List<PolygonMapObject> collisionObjects = this.collision.getPolygonObjects();
        List<PolygonMapObject> specialObjects = this.special.getPolygonObjects();

        for (Actor actor : actors) {
            if (!actor.isSolid() || !actor.isActive()) continue;

            Rectangle actorRectangle = actor.getCollisionBox();

            for (PolygonMapObject collisionObject : collisionObjects) {
                Polygon polygon = collisionObject.getPolygon();

                // cheap bounding-box check
                if (!actorRectangle.overlaps(polygon.getBoundingRectangle())) {
                    continue;
                }

                boolean moved = resolveRectangleVsPolygon(actorRectangle, polygon);

                if (moved && actor instanceof Enemy) {
                    Enemy enemy = (Enemy) actor;
                    enemy.setDirection(enemy.getRandomDirection());
                }
            }

            for (PolygonMapObject specialObject : specialObjects) {
                Polygon polygon = specialObject.getPolygon();

                // cheap bounding-box check
                if (!actorRectangle.overlaps(polygon.getBoundingRectangle())) {
                    continue;
                }

                if (actor instanceof Enemy) {
                    Enemy enemy = (Enemy) actor;

                    boolean moved = resolveRectangleVsPolygon(actorRectangle, polygon);

                    if (moved) {
                        enemy.setDirection(enemy.getRandomDirection());
                    }
                }
                else if (actor instanceof Zelda) {
                    if (rectangleOverlapsPolygon(actorRectangle, polygon)) {
                        collideZeldaWithSpecial(specialObject, (Zelda) actor);
                    }
                }
            }
        }
    }

    private void collideZeldaWithSpecial(PolygonMapObject polygonObject, Zelda zelda) {
        String name = polygonObject.getName();
        SaveManager saveManager = game.getSaveManager();

        if (name == null) {
            name = "";
        }

        switch(name) {
        /* Overworld warps */
        case "overworld_entrance_earth":
            map.updateSpawnLocation("shrine_of_earth_exit_earth");
            game.initializeFadeWarp();
            break;
        case "overworld_moblin_head_inn_entrance":
            map.updateSpawnLocation("overworld_moblin_head_inn_exit");
            game.initializeFadeWarp();
            break;
        case "overworld_moblin_head_inn_exit":
            map.updateSpawnLocation("overworld_moblin_head_inn_entrance");
            game.initializeFadeWarp();
            break;
        /* Overworld specials */
        case "overworld_andor":
            if (game.getTreasures().contains(Treasure.RED_BOOTS)) {
                // do nothing
            }
            break;
        /* Shrine of earth warps */
        case "shrine_of_earth_exit_earth":
            map.updateSpawnLocation("overworld_entrance_earth");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_chest_entrance":
            map.updateSpawnLocation("shrine_of_earth_chest_exit");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_chest_exit":
            map.updateSpawnLocation("shrine_of_earth_chest_entrance");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_blue_1_entrance":
            map.updateSpawnLocation("shrine_of_earth_blue_1_exit");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_blue_1_exit":
            map.updateSpawnLocation("shrine_of_earth_blue_1_entrance");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_blue_2_entrance":
            map.updateSpawnLocation("shrine_of_earth_blue_2_exit");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_blue_2_exit":
            map.updateSpawnLocation("shrine_of_earth_blue_2_entrance");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_blue_3_entrance":
            map.updateSpawnLocation("shrine_of_earth_blue_3_exit");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_blue_3_exit":
            map.updateSpawnLocation("shrine_of_earth_blue_3_entrance");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_cave_1_entrance":
            map.updateSpawnLocation("shrine_of_earth_cave_1_exit");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_cave_1_exit":
            map.updateSpawnLocation("shrine_of_earth_cave_1_entrance");
            game.initializeFadeWarp();
            break;

        /* Shrine of earth specials */
        case "shrine_of_earth_ladder_bridge_1":
        case "shrine_of_earth_ladder_bridge_2":
            if (!saveManager.hasLocationEntry("s102_0")) {
                resolveRectangleVsPolygon(zelda.getCollisionBox(), polygonObject.getPolygon());
            }
            break;
        case "shrine_of_earth_guard_gate":
            if (!(saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakBlue", "permadead")
                && saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakRed", "permadead")
                && saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakYellow", "permadead")))
            {
                resolveRectangleVsPolygon(zelda.getCollisionBox(), polygonObject.getPolygon());
            }
            break;
        case "shrine_of_earth_llort_gate":
            if (!saveManager.hasLocationEntry("s121_0")) {
                resolveRectangleVsPolygon(zelda.getCollisionBox(), polygonObject.getPolygon());
            }
            break;
        case "shrine_of_earth_dark_cave_entrance":
            map.updateSpawnLocation("shrine_of_earth_dark_cave_exit");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_dark_cave_exit":
            map.updateSpawnLocation("shrine_of_earth_dark_cave_entrance");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_llort_warning_entrance":
            map.updateSpawnLocation("shrine_of_earth_llort_warning_exit");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_llort_warning_exit":
            map.updateSpawnLocation("shrine_of_earth_llort_warning_entrance");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_llort_hall_entrance":
            map.updateSpawnLocation("shrine_of_earth_llort_hall_exit");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_llort_hall_exit":
            map.updateSpawnLocation("shrine_of_earth_llort_hall_entrance");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_llort_gate_entrance":
            map.updateSpawnLocation("shrine_of_earth_llort_gate_exit");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_llort_gate_exit":
            map.updateSpawnLocation("shrine_of_earth_llort_gate_entrance");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_celestial_sign_1_entrance":
            map.updateSpawnLocation("shrine_of_earth_celestial_sign_1_exit");
            game.initializeFadeWarp();
            break;
        case "shrine_of_earth_celestial_sign_1_exit":
            map.updateSpawnLocation("shrine_of_earth_celestial_sign_1_entrance");
            game.initializeFadeWarp();
            break;
        default:
            resolveRectangleVsPolygon(zelda.getCollisionBox(), polygonObject.getPolygon());
        }
    }

    private void collideEnemiesWithWorldBorders() {
        Rectangle[] borders = game.getWorld().getWorldBorders();

        Rectangle leftBorder = borders[0];
        Rectangle rightBorder = borders[1];
        Rectangle bottomBorder = borders[2];
        Rectangle topBorder = borders[3];

        for (Actor actor : actors) {
            if (!actor.isActive() || !(actor instanceof Enemy)) continue;

            Enemy enemy = (Enemy) actor;
            Rectangle enemyCollision = enemy.getCollisionBox();

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
            if (!(actor instanceof Enemy) || actor.getType() != ActorType.ENEMY) continue;

            Enemy enemy = (Enemy) actor;
            Zelda zelda = map.getZelda();

            if (enemy.getAlertBox().overlaps(zelda.getHitbox())) {
                enemy.setTargetX(zelda.getCenterPointX());
                enemy.setTargetY(zelda.getCenterPointY());

                if (enemy.getEnemyState() == EnemyState.SEARCH) {
                    enemy.setEnemyState(EnemyState.FIGHT);
                    enemy.changeDirectionTowardsTarget();
                    Gdx.app.log(getClass().getSimpleName(), actor.getClass() + " is aggro'd!");
                }
            }
            else {
                if (enemy.getEnemyState() == EnemyState.FIGHT) {
                    enemy.setEnemyState(EnemyState.SEARCH);
                    Gdx.app.log(getClass().getSimpleName(), actor.getClass() + " went back to searching...");
                }
            }
        }
    }

    private boolean rectangleOverlapsPolygon(Rectangle rectangle, Polygon polygon) {
        if (!rectangle.overlaps(polygon.getBoundingRectangle())) {
            return false;
        }

        float[] polyVertices = polygon.getTransformedVertices();

        if (polyVertices.length < 6) {
            return false;
        }

        if (isConvex(polyVertices)) {
            return rectangleOverlapsConvexVertices(rectangle, polyVertices);
        }

        ShortArray triangleIndices = triangulator.computeTriangles(polyVertices);

        for (int i = 0; i < triangleIndices.size; i += 3) {
            float[] triangleVertices = getTriangleVertices(polyVertices, triangleIndices, i);

            if (rectangleOverlapsConvexVertices(rectangle, triangleVertices)) {
                return true;
            }
        }

        return false;
    }

    private boolean rectangleOverlapsAnyPolygon(Rectangle rectangle, List<PolygonMapObject> polygonObjects) {
        for (PolygonMapObject polygonObject : polygonObjects) {
            Polygon polygon = polygonObject.getPolygon();

            if (!rectangle.overlaps(polygon.getBoundingRectangle())) {
                continue;
            }

            if (rectangleOverlapsPolygon(rectangle, polygon)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @nebbii: Resolve rectangle against a polygon that may be convex or concave.
     * Concave polygons are triangulated into convex pieces, then all overlapping
     * triangles are resolved in repeated passes.
     */
    private boolean resolveRectangleVsPolygon(Rectangle movingRectangle, Polygon solidPolygon) {
        if (!movingRectangle.overlaps(solidPolygon.getBoundingRectangle())) {
            return false;
        }

        float[] polyVertices = solidPolygon.getTransformedVertices();

        if (polyVertices.length < 6) {
            return false;
        }

        if (isConvex(polyVertices)) {
            return resolveRectangleVsConvexVertices(movingRectangle, polyVertices);
        }

        boolean movedAtAll = false;
        ShortArray triangleIndices = triangulator.computeTriangles(polyVertices);

        for (int pass = 0; pass < MAX_PUSH_ITERATIONS; pass++) {
            boolean movedThisPass = false;

            for (int i = 0; i < triangleIndices.size; i += 3) {
                float[] triangleVertices = getTriangleVertices(polyVertices, triangleIndices, i);

                Vector2 push = computePushOutVectorConvex(movingRectangle, triangleVertices);

                if (push.len2() > MIN_PUSH_LEN2) {
                    movingRectangle.x += push.x;
                    movingRectangle.y += push.y;
                    movedThisPass = true;
                    movedAtAll = true;
                }
            }

            if (!movedThisPass) {
                break;
            }
        }

        return movedAtAll;
    }

    private boolean resolveRectangleVsConvexVertices(Rectangle movingRectangle, float[] convexVertices) {
        boolean movedAtAll = false;

        for (int pass = 0; pass < MAX_PUSH_ITERATIONS; pass++) {
            Vector2 push = computePushOutVectorConvex(movingRectangle, convexVertices);

            if (push.len2() <= MIN_PUSH_LEN2) {
                break;
            }

            movingRectangle.x += push.x;
            movingRectangle.y += push.y;
            movedAtAll = true;
        }

        return movedAtAll;
    }

    /**
     * SAT push-out for rectangle vs convex shapes
     */
    private static Vector2 computePushOutVectorConvex(Rectangle movingRectangle, float[] polyVertices) {
        Vector2 mtv = new Vector2();

        if (polyVertices.length < 6) {
            return mtv.set(0f, 0f);
        }

        float[] rectangleVertices = getRectangleVertices(movingRectangle);

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

        if (smallestOverlap == Float.POSITIVE_INFINITY) {
            return mtv.set(0f, 0f);
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

    private static boolean rectangleOverlapsConvexVertices(Rectangle rectangle, float[] convexVertices) {
        if (convexVertices.length < 6) {
            return false;
        }

        float[] rectangleVertices = getRectangleVertices(rectangle);

        if (hasSeparatingAxis(rectangleVertices, rectangleVertices, convexVertices)) {
            return false;
        }

        if (hasSeparatingAxis(convexVertices, rectangleVertices, convexVertices)) {
            return false;
        }

        return true;
    }

    private static boolean hasSeparatingAxis(float[] axisSourceVertices, float[] verticesA, float[] verticesB) {
        for (int i = 0; i < axisSourceVertices.length; i += 2) {
            int next = (i + 2) % axisSourceVertices.length;

            float x1 = axisSourceVertices[i];
            float y1 = axisSourceVertices[i + 1];
            float x2 = axisSourceVertices[next];
            float y2 = axisSourceVertices[next + 1];

            float edgeX = x2 - x1;
            float edgeY = y2 - y1;

            if (edgeX == 0f && edgeY == 0f) {
                continue;
            }

            Vector2 axis = new Vector2(-edgeY, edgeX).nor();
            float overlap = getOverlapOnAxis(verticesA, verticesB, axis);

            if (overlap <= 0f) {
                return true;
            }
        }

        return false;
    }

    private static float getOverlapOnAxis(float[] verticesA, float[] verticesB, Vector2 axis) {
        float[] projectionA = projectVertices(verticesA, axis);
        float[] projectionB = projectVertices(verticesB, axis);

        float minA = projectionA[0];
        float maxA = projectionA[1];
        float minB = projectionB[0];
        float maxB = projectionB[1];

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

    private static float[] getRectangleVertices(Rectangle rectangle) {
        return new float[] {
            rectangle.x, rectangle.y,
            rectangle.x + rectangle.width, rectangle.y,
            rectangle.x + rectangle.width, rectangle.y + rectangle.height,
            rectangle.x, rectangle.y + rectangle.height
        };
    }

    private static float[] getTriangleVertices(float[] vertices, ShortArray triangleIndices, int triangleOffset) {
        int i0 = triangleIndices.get(triangleOffset) * 2;
        int i1 = triangleIndices.get(triangleOffset + 1) * 2;
        int i2 = triangleIndices.get(triangleOffset + 2) * 2;

        return new float[] {
            vertices[i0], vertices[i0 + 1],
            vertices[i1], vertices[i1 + 1],
            vertices[i2], vertices[i2 + 1]
        };
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

    private static boolean isConvex(float[] vertices) {
        int vertexCount = vertices.length / 2;

        if (vertexCount < 3) {
            return false;
        }

        boolean hasPositiveCross = false;
        boolean hasNegativeCross = false;
        boolean hasNonZeroCross = false;

        for (int i = 0; i < vertexCount; i++) {
            int current = i * 2;
            int next = ((i + 1) % vertexCount) * 2;
            int afterNext = ((i + 2) % vertexCount) * 2;

            float currentX = vertices[current];
            float currentY = vertices[current + 1];

            float nextX = vertices[next];
            float nextY = vertices[next + 1];

            float afterNextX = vertices[afterNext];
            float afterNextY = vertices[afterNext + 1];

            float edgeAX = nextX - currentX;
            float edgeAY = nextY - currentY;

            float edgeBX = afterNextX - nextX;
            float edgeBY = afterNextY - nextY;

            float cross = edgeAX * edgeBY - edgeAY * edgeBX;

            if (cross > CONVEX_EPSILON) {
                hasPositiveCross = true;
                hasNonZeroCross = true;
            }
            else if (cross < -CONVEX_EPSILON) {
                hasNegativeCross = true;
                hasNonZeroCross = true;
            }

            if (hasPositiveCross && hasNegativeCross) {
                return false;
            }
        }

        return hasNonZeroCross;
    }
}
