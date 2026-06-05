package com.nebbii.zagdx;

import com.badlogic.gdx.maps.objects.PolygonMapObject;

public class SpecialCollisionHandler {
    private final MapManager map;
    private final GameManager game;
    private final PolygonCollisionResolver polygonResolver;

    public SpecialCollisionHandler(
        MapManager map,
        GameManager game,
        PolygonCollisionResolver polygonResolver
    ) {
        this.map = map;
        this.game = game;
        this.polygonResolver = polygonResolver;
    }

    public void collideZeldaWithSpecial(PolygonMapObject polygonObject, Zelda zelda) {
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
        case "overworld_ambush_cave_entrance":
            map.updateSpawnLocation("overworld_ambush_cave_exit");
            game.initializeFadeWarp();
            break;
        case "overworld_ambush_cave_exit":
            map.updateSpawnLocation("overworld_ambush_cave_entrance");
            game.initializeFadeWarp();
            break;
        /* Overworld specials */
        case "overworld_andor":
            if (!game.getTreasures().contains(Treasure.RED_BOOTS)) {
                polygonResolver.resolveRectangleVsPolygon(zelda.getCollisionBox(), polygonObject.getPolygon());
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
}
