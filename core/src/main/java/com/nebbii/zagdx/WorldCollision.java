package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.Enemy.EnemyState;

/*
 * Handling all collision in the world, knows all actors and map objects
 */
public class WorldCollision {
    private MapManager map;
    private GameManager game;

    private MapLayerObjects collision;
    private MapLayerObjects special;
    public ArrayList<Actor> actors;

    private final PolygonCollisionResolver polygonResolver = new PolygonCollisionResolver();
    private final SpecialCollisionHandler specialCollisionHandler;

    public WorldCollision(MapManager map, GameManager game) {
        this.map = map;
        this.game = game;
        this.collision = map.collision;
        this.special = map.special;
        this.actors = map.actors;
        this.specialCollisionHandler = new SpecialCollisionHandler(map, game, polygonResolver);
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

            if (polygonResolver.rectangleOverlapsAnyPolygon(actorRectangle, collisionObjects)
                || polygonResolver.rectangleOverlapsAnyPolygon(actorRectangle, specialObjects)
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

                boolean moved = polygonResolver.resolveRectangleVsPolygon(actorRectangle, polygon);

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

                    boolean moved = polygonResolver.resolveRectangleVsPolygon(actorRectangle, polygon);

                    if (moved) {
                        enemy.setDirection(enemy.getRandomDirection());
                    }
                }
                else if (actor instanceof Zelda) {
                    if (polygonResolver.rectangleOverlapsPolygon(actorRectangle, polygon)) {
                        specialCollisionHandler.collideZeldaWithSpecial(specialObject, (Zelda) actor);
                    }
                }
            }
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

}
