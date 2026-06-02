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
                polygonResolver.resolveRectangleVsPolygon(zelda.getCollisionBox(), polygonObject.getPolygon());
            }
            break;
        case "shrine_of_earth_guard_gate":
            if (!(saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakBlue", "permadead")
                && saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakRed", "permadead")
                && saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakYellow", "permadead")))
            {
                polygonResolver.resolveRectangleVsPolygon(zelda.getCollisionBox(), polygonObject.getPolygon());
            }
            break;
        case "shrine_of_earth_llort_gate":
            if (!saveManager.hasLocationEntry("s121_0")) {
                polygonResolver.resolveRectangleVsPolygon(zelda.getCollisionBox(), polygonObject.getPolygon());
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
            polygonResolver.resolveRectangleVsPolygon(zelda.getCollisionBox(), polygonObject.getPolygon());
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
