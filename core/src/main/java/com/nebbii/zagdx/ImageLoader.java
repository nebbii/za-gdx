package com.nebbii.zagdx;

import java.util.EnumMap;

import com.badlogic.gdx.graphics.Texture;

public class ImageLoader {
    private Texture none;

    public enum ZeldaAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT,
        ATTACKUP,
        ATTACKRIGHT,
        ATTACKDOWN,
        ATTACKLEFT,
        GAMEOVER
    }

    /* Enemies */
    public enum EnemyGoriyaAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyTumblebotAnimationGroup {
        ROLLUP,
        ROLLRIGHT,
        ROLLDOWN,
        ROLLLEFT
    }

    public enum EnemyMobyAnimationGroup {
        FLYUP,
        FLYRIGHT,
        FLYDOWN,
        FLYLEFT
    }

    public enum EnemyMoblinAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyDeelerAnimationGroup {
        WALK
    }

    public enum EnemyTektiteAnimationGroup {
        WALK
    }

    public enum EnemyKeeseAnimationGroup {
        FLY
    }

    public enum EnemyLlortAnimationGroup {
        WALK,
        ATTACK
    }

    public enum EnemySardakRedAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemySardakBlueAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemySardakYellowAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum NpcTalkingChestAnimationGroup {
        IDLE
    }

    public enum SpriteLlortLaserAnimationGroup {
        IDLE
    }

    private EnumMap<ZeldaAnimationGroup, Texture[]> zelda;
    private EnumMap<EnemyGoriyaAnimationGroup, Texture[]> enemyGoriya;
    private EnumMap<EnemyTumblebotAnimationGroup, Texture[]> enemyTumblebot;
    private EnumMap<EnemyMobyAnimationGroup, Texture[]> enemyMoby;
    private EnumMap<EnemyMoblinAnimationGroup, Texture[]> enemyMoblin;
    private EnumMap<EnemyDeelerAnimationGroup, Texture[]> enemyDeeler;
    private EnumMap<EnemyTektiteAnimationGroup, Texture[]> enemyTektite;
    private EnumMap<EnemyKeeseAnimationGroup, Texture[]> enemyKeese;
    private EnumMap<EnemyLlortAnimationGroup, Texture[]> enemyLlort;
    private EnumMap<EnemySardakRedAnimationGroup, Texture[]> enemySardakRed;
    private EnumMap<EnemySardakBlueAnimationGroup, Texture[]> enemySardakBlue;
    private EnumMap<EnemySardakYellowAnimationGroup, Texture[]> enemySardakYellow;

    /* NPCs */
    private Texture[] npcGlebb;
    private EnumMap<NpcTalkingChestAnimationGroup, Texture[]> npcTalkingChest;

    /* Map stuff */
    private Texture spriteLadder;
    private EnumMap<SpriteLlortLaserAnimationGroup, Texture[]> spriteLlortLaser;

    /* Treasures */
    private Texture bone;
    private Texture ladder;
    private Texture pitcherEmpty;
    private Texture pitcherFull;
    private Texture vialOfWind;
    private Texture redBoots;
    private Texture underworldMap1;
    private Texture underworldMap2;
    private Texture underworldMap3;
    private Texture underworldMap4;
    private Texture underworldMap5;
    private Texture underworldMap6;
    private Texture underworldMap7;
    private Texture compass1;
    private Texture compass2;
    private Texture compass3;
    private Texture compass4;
    private Texture compass5;
    private Texture compass6;
    private Texture compass7;
    private Texture celestialStone1;
    private Texture celestialStone2;
    private Texture celestialStone3;
    private Texture celestialStone4;
    private Texture celestialStone5;
    private Texture celestialStone6;
    private Texture celestialStone7;

    /* Weapons */
    private Texture wand;
    private Texture boomerang;
    private Texture jadeRing;

    /* Pickups */
    private Texture rubyBlue;
    private Texture rubyYellow;
    private Texture heart;

    /* HUD */
    private Texture[] hudNumbers;
    private Texture hudHeartEmpty;
    private Texture hudHeartHalf;
    private Texture hudHeartFull;

    /* Projectiles */
    private Texture[] friendlyBoomerang;
    private Texture[] friendlyJadeRing;
    private Texture[] enemyBoomerang;
    private Texture[] enemySpear;

    private Texture itemScreen;

    public ImageLoader() {
        none = new Texture("invisible.png");
        itemScreen = new Texture("dummy-pause-screen.png"); // TODO: swap with real asset

        /* Actors */
        zelda = new EnumMap<>(ZeldaAnimationGroup.class);
        zelda.put(ZeldaAnimationGroup.WALKUP,
            loadTextureArray("export/common/zelda/sprites/group0", 5));
        zelda.put(ZeldaAnimationGroup.WALKRIGHT,
            loadTextureArray("export/common/zelda/sprites/group1", 5));
        zelda.put(ZeldaAnimationGroup.WALKDOWN,
            loadTextureArray("export/common/zelda/sprites/group2", 5));
        zelda.put(ZeldaAnimationGroup.WALKLEFT,
            loadTextureArray("export/common/zelda/sprites/group3", 5));
        zelda.put(ZeldaAnimationGroup.ATTACKUP,
            loadTextureArray("export/common/zelda/sprites/group4", 3));
        zelda.put(ZeldaAnimationGroup.ATTACKRIGHT,
            loadTextureArray("export/common/zelda/sprites/group5", 3));
        zelda.put(ZeldaAnimationGroup.ATTACKDOWN,
            loadTextureArray("export/common/zelda/sprites/group6", 3));
        zelda.put(ZeldaAnimationGroup.ATTACKLEFT,
            loadTextureArray("export/common/zelda/sprites/group7", 3));

        Texture[] zeldaGameover = new Texture[4];
        zeldaGameover[0] = new Texture("export/common/zelda/sprites/group0/sprite0.png");
        zeldaGameover[1] = new Texture("export/common/zelda/sprites/group1/sprite0.png");
        zeldaGameover[2] = new Texture("export/common/zelda/sprites/group2/sprite0.png");
        zeldaGameover[3] = new Texture("export/common/zelda/sprites/group3/sprite0.png");
        zelda.put(ZeldaAnimationGroup.GAMEOVER, zeldaGameover);

        enemyGoriya = new EnumMap<>(EnemyGoriyaAnimationGroup.class);
        enemyGoriya.put(EnemyGoriyaAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/d24/sprites/desc0/group0", 5));
        enemyGoriya.put(EnemyGoriyaAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/d24/sprites/desc0/group1", 5));
        enemyGoriya.put(EnemyGoriyaAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/d24/sprites/desc0/group2", 5));
        enemyGoriya.put(EnemyGoriyaAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/d24/sprites/desc0/group3", 5));

        enemyTumblebot = new EnumMap<>(EnemyTumblebotAnimationGroup.class);
        enemyTumblebot.put(EnemyTumblebotAnimationGroup.ROLLUP,
            loadTextureArray("export/overworld/h21/sprites/desc0/group0", 8));
        enemyTumblebot.put(EnemyTumblebotAnimationGroup.ROLLRIGHT,
            loadTextureArray("export/overworld/h21/sprites/desc0/group1", 8));
        enemyTumblebot.put(EnemyTumblebotAnimationGroup.ROLLDOWN,
            loadTextureArray("export/overworld/h21/sprites/desc0/group2", 8));
        enemyTumblebot.put(EnemyTumblebotAnimationGroup.ROLLLEFT,
            loadTextureArray("export/overworld/h21/sprites/desc0/group3", 8));

        enemyMoby = new EnumMap<>(EnemyMobyAnimationGroup.class);
        enemyMoby.put(EnemyMobyAnimationGroup.FLYUP,
            loadTextureArray("export/overworld/g26/sprites/desc0/group0", 5));
        enemyMoby.put(EnemyMobyAnimationGroup.FLYRIGHT,
            loadTextureArray("export/overworld/g26/sprites/desc0/group1", 5));
        enemyMoby.put(EnemyMobyAnimationGroup.FLYDOWN,
            loadTextureArray("export/overworld/g26/sprites/desc0/group2", 5));
        enemyMoby.put(EnemyMobyAnimationGroup.FLYLEFT,
            loadTextureArray("export/overworld/g26/sprites/desc0/group3", 5));

        enemyMoblin = new EnumMap<>(EnemyMoblinAnimationGroup.class);
        enemyMoblin.put(EnemyMoblinAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/g27/sprites/desc0/group0", 5));
        enemyMoblin.put(EnemyMoblinAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/g27/sprites/desc0/group1", 5));
        enemyMoblin.put(EnemyMoblinAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/g27/sprites/desc0/group2", 5));
        enemyMoblin.put(EnemyMoblinAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/g27/sprites/desc0/group3", 5));

        enemyDeeler = new EnumMap<>(EnemyDeelerAnimationGroup.class);
        enemyDeeler.put(EnemyDeelerAnimationGroup.WALK,
            loadTextureArray("export/overworld/g29/sprites/desc0/group0", 3));

        enemyTektite = new EnumMap<>(EnemyTektiteAnimationGroup.class);
        enemyTektite.put(EnemyTektiteAnimationGroup.WALK,
            loadTextureArray("export/underworld/s118/sprites/desc1/group0", 5));

        enemyKeese = new EnumMap<>(EnemyKeeseAnimationGroup.class);
        enemyKeese.put(EnemyKeeseAnimationGroup.FLY,
            loadTextureArray("export/underworld/s106/sprites/desc0/group0", 5));

        enemyLlort = new EnumMap<>(EnemyLlortAnimationGroup.class);
        enemyLlort.put(EnemyLlortAnimationGroup.WALK,
            loadTextureArray("export/underworld/s121/sprites/desc0/group0", 5));
        enemyLlort.put(EnemyLlortAnimationGroup.ATTACK,
            loadTextureArray("export/underworld/s121/sprites/desc0/group1", 7));

        enemySardakRed = new EnumMap<>(EnemySardakRedAnimationGroup.class);
        enemySardakRed.put(EnemySardakRedAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s107/sprites/desc0/group0", 5));
        enemySardakRed.put(EnemySardakRedAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s107/sprites/desc0/group1", 5));
        enemySardakRed.put(EnemySardakRedAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s107/sprites/desc0/group2", 5));
        enemySardakRed.put(EnemySardakRedAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s107/sprites/desc0/group3", 5));

        enemySardakBlue = new EnumMap<>(EnemySardakBlueAnimationGroup.class);
        enemySardakBlue.put(EnemySardakBlueAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s112/sprites/desc0/group0", 5));
        enemySardakBlue.put(EnemySardakBlueAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s112/sprites/desc0/group1", 5));
        enemySardakBlue.put(EnemySardakBlueAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s112/sprites/desc0/group2", 5));
        enemySardakBlue.put(EnemySardakBlueAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s112/sprites/desc0/group3", 5));

        enemySardakYellow = new EnumMap<>(EnemySardakYellowAnimationGroup.class);
        enemySardakYellow.put(EnemySardakYellowAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s120/sprites/desc0/group0", 5));
        enemySardakYellow.put(EnemySardakYellowAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s120/sprites/desc0/group1", 5));
        enemySardakYellow.put(EnemySardakYellowAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s120/sprites/desc0/group2", 5));
        enemySardakYellow.put(EnemySardakYellowAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s120/sprites/desc0/group3", 5));

        /* NPCs */
        npcGlebb = loadTextureArray("export/overworld/j24/sprites/desc0/group0", 5);

        npcTalkingChest = new EnumMap<>(NpcTalkingChestAnimationGroup.class);
        npcTalkingChest.put(NpcTalkingChestAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s108/sprites/desc0/group0", 4));

        /* Map stuff */
        spriteLadder = new Texture("export/underworld/s102/sprites/desc0/group0/sprite0.png");

        spriteLlortLaser = new EnumMap<>(SpriteLlortLaserAnimationGroup.class);
        spriteLlortLaser.put(SpriteLlortLaserAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s120/sprites/desc2/group0", 4));

        /* Items */
        rubyBlue = new Texture("export/common/hudSprites/3.png");
        rubyYellow = new Texture("export/common/hudSprites/4.png");
        heart = new Texture("export/common/hudSprites/5.png");

        /* HUD */
        hudNumbers = loadTextureArray("export/common/zinitVideo/record3_hud_palette", 10); // path subject to change in the future
        hudHeartEmpty = new Texture("export/common/hudSprites/0.png");
        hudHeartHalf = new Texture("export/common/hudSprites/1.png");
        hudHeartFull = new Texture("export/common/hudSprites/2.png");

        /* Treasures */
        bone = new Texture("export/overworld/k13a/sprites/desc2/group0/sprite0.png");
        ladder = new Texture("export/overworld/f28/sprites/desc0/group0/sprite0.png");
        pitcherEmpty = new Texture("export/overworld/j24/sprites/desc1/group0/sprite0.png");
        pitcherFull = new Texture("export/overworld/e20/sprites/desc0/group0/sprite0.png");
        vialOfWind = new Texture("export/overworld/j24/sprites/desc2/group0/sprite0.png");
        redBoots = new Texture("export/underworld/s116/sprites/desc1/group0/sprite0.png");

        underworldMap1 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png");
        underworldMap2 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image
        underworldMap3 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image
        underworldMap4 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image
        underworldMap5 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image
        underworldMap6 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image
        underworldMap7 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image

        compass1 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png");
        compass2 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image
        compass3 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image
        compass4 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image
        compass5 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image
        compass6 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image
        compass7 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image

        celestialStone1 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png");
        celestialStone2 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image
        celestialStone3 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image
        celestialStone4 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image
        celestialStone5 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image
        celestialStone6 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image
        celestialStone7 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image

        /* Weapons */
        wand = new Texture("export/overworld/h23/sprites/desc0/group0/sprite0.png");
        boomerang = new Texture("export/overworld/d24/sprites/desc2/group0/sprite0.png");
        jadeRing = new Texture("export/underworld/s108/sprites/desc1/group0/sprite0.png");

        /* Projectiles */
        friendlyBoomerang = loadTextureArray("export/common/weapons/Boomerang/group0", 4);
        friendlyJadeRing = new Texture[4];
        friendlyJadeRing[0] = new Texture("export/common/weapons/JadeRing/group0/sprite0.png");
        friendlyJadeRing[1] = new Texture("export/common/weapons/JadeRing/group1/sprite0.png");
        friendlyJadeRing[2] = new Texture("export/common/weapons/JadeRing/group2/sprite0.png");
        friendlyJadeRing[3] = new Texture("export/common/weapons/JadeRing/group3/sprite0.png");
        enemyBoomerang = loadTextureArray("export/overworld/d24/sprites/desc1/group0", 4);
        enemySpear = new Texture[4];
        enemySpear[0] = new Texture("export/underworld/s120/sprites/desc1/group0/sprite0.png");
        enemySpear[1] = new Texture("export/underworld/s120/sprites/desc1/group1/sprite0.png");
        enemySpear[2] = new Texture("export/underworld/s120/sprites/desc1/group2/sprite0.png");
        enemySpear[3] = new Texture("export/underworld/s120/sprites/desc1/group3/sprite0.png");
    }

    public void dispose() {
        none.dispose();
        itemScreen.dispose();

        /* Actors */
        for (Texture[] textures : zelda.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyGoriya.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyTumblebot.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyMoby.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyMoblin.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyDeeler.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyTektite.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyKeese.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyLlort.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySardakRed.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySardakBlue.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySardakYellow.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture texture : npcGlebb) {
            texture.dispose();
        }

        for (Texture[] textures : npcTalkingChest.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        /* Map stuff */
        spriteLadder.dispose();

        for (Texture[] textures : spriteLlortLaser.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        /* Items */
        rubyBlue.dispose();
        rubyYellow.dispose();
        heart.dispose();

        /* Hud */
        for (Texture texture : hudNumbers) {
            if (texture != null) {
                texture.dispose();
            }
        }

        hudHeartEmpty.dispose();
        hudHeartHalf.dispose();
        hudHeartFull.dispose();

        /* Treasures */
        bone.dispose();
        ladder.dispose();
        pitcherEmpty.dispose();
        pitcherFull.dispose();
        vialOfWind.dispose();
        redBoots.dispose();
        jadeRing.dispose();
        underworldMap1.dispose();
        underworldMap2.dispose();
        underworldMap3.dispose();
        underworldMap4.dispose();
        underworldMap5.dispose();
        underworldMap6.dispose();
        underworldMap7.dispose();
        compass1.dispose();
        compass2.dispose();
        compass3.dispose();
        compass4.dispose();
        compass5.dispose();
        compass6.dispose();
        compass7.dispose();
        celestialStone1.dispose();
        celestialStone2.dispose();
        celestialStone3.dispose();
        celestialStone4.dispose();
        celestialStone5.dispose();
        celestialStone6.dispose();
        celestialStone7.dispose();

        /* Weapons */
        wand.dispose();
        boomerang.dispose();
        jadeRing.dispose();

        /* Projectiles */
        for (Texture texture : friendlyBoomerang) {
            if (texture != null) {
                texture.dispose();
            }
        }

        for (Texture texture : friendlyJadeRing) {
            if (texture != null) {
                texture.dispose();
            }
        }

        for (Texture texture : enemyBoomerang) {
            if (texture != null) {
                texture.dispose();
            }
        }

        for (Texture texture : enemySpear) {
            if (texture != null) {
                texture.dispose();
            }
        }
    }

    public Texture getImageByItem(Item item) {
        if (item instanceof Treasure) {
            switch((Treasure) item) {
            case BLACK_ORB:
            case BONE:
                return getBone();
            case CANDLE:
            case CELESTIAL_SIGN_1:
                return getCelestialStone(1);
            case CELESTIAL_SIGN_2:
                return getCelestialStone(2);
            case CELESTIAL_SIGN_3:
                return getCelestialStone(3);
            case CELESTIAL_SIGN_4:
                return getCelestialStone(4);
            case CELESTIAL_SIGN_5:
                return getCelestialStone(5);
            case CELESTIAL_SIGN_6:
                return getCelestialStone(6);
            case CELESTIAL_SIGN_7:
                return getCelestialStone(7);
            case COAL:
                return getLadder();
            case COMPASS_1:
                return getCompass(1);
            case COMPASS_2:
                return getCompass(2);
            case COMPASS_3:
                return getCompass(3);
            case COMPASS_4:
                return getCompass(4);
            case COMPASS_5:
                return getCompass(5);
            case COMPASS_6:
                return getCompass(6);
            case COMPASS_7:
                return getCompass(7);
            case CRYSTALHEART:
            case DIAMOND:
            case FAIRY:
            case FLUTE:
            case GOLDEN_BOOTS:
            case HARP:
            case KEYS:
            case KNIFE:
            case LADDER:
                return getLadder();
            case LIFE_HEART:
            case LIFE_POTION:
            case MAGIC_SHIELD:
            case NONE:
                return getNone();
            case PITCHER_EMPTY:
                return getPitcherEmpty();
            case PITCHER_FULL:
                return getPitcherFull();
            case PLANK:
            case RAFT:
            case RED_BOOTS:
                return getRedBoots();
            case RED_RIBBON:
            case RUG:
            case RUPEES:
            case TICKET:
            case UNDERWORLD_MAP_1:
                return getUnderworldMap(1);
            case UNDERWORLD_MAP_2:
                return getUnderworldMap(2);
            case UNDERWORLD_MAP_3:
                return getUnderworldMap(3);
            case UNDERWORLD_MAP_4:
                return getUnderworldMap(4);
            case UNDERWORLD_MAP_5:
                return getUnderworldMap(5);
            case UNDERWORLD_MAP_6:
                return getUnderworldMap(6);
            case UNDERWORLD_MAP_7:
                return getUnderworldMap(7);
            case VIAL_OF_WIND:
                return getVialOfWind();
            case ZOLA_REPELLENT:
            default:
                throw new IllegalStateException("ImageLoader->getImageByName(): unimplemented treasure");
            }
        } else if (item instanceof Weapon) {
            switch((Weapon) item) {
            case WAND:
                return getWand();
            case BOOMERANG:
                return getBoomerang();
            case BOW_AND_ARROW:
            case BROADSWORD:
            case CALM:
            case DAGGER:
            case FEATHER:
            case FIRESTORM:
            case GOLD_NECKLACE:
            case HAMMER:
            case JADE_AMULET:
            case JADE_RING:
                return getJadeRing();
            case JOUST:
            case NOISE:
            case NONE:
                return getNone();
            case PYROS:
            case RINGS_OF_FIRE:
            case ROAR_STICK:
            case SHORT_AXE:
            case TURQUOISE_RING:
            default:
                throw new IllegalStateException("ImageLoader->getImageByName(): unimplemented weapon");
            }
        }
        return none;
    }

    private Texture[] loadTextureArray(String folderPath, int frameCount) {
        Texture[] textures = new Texture[frameCount];

        for (int i = 0; i < frameCount; i++) {
            textures[i] = new Texture(folderPath + "/sprite" + i + ".png");
        }

        return textures;
    }

    public Texture[] getZeldaAnimation(ZeldaAnimationGroup anim) {
        return zelda.get(anim);
    }

    public Texture[] getEnemyGoriyaAnimation(EnemyGoriyaAnimationGroup anim) {
        return enemyGoriya.get(anim);
    }

    public Texture[] getEnemyTumblebotAnimation(EnemyTumblebotAnimationGroup anim) {
        return enemyTumblebot.get(anim);
    }

    public Texture[] getEnemyMobyAnimation(EnemyMobyAnimationGroup anim) {
        return enemyMoby.get(anim);
    }

    public Texture[] getEnemyMoblinAnimation(EnemyMoblinAnimationGroup anim) {
        return enemyMoblin.get(anim);
    }

    public Texture[] getEnemyDeelerAnimation(EnemyDeelerAnimationGroup anim) {
        return enemyDeeler.get(anim);
    }

    public Texture[] getEnemyTektiteAnimation(EnemyTektiteAnimationGroup anim) {
        return enemyTektite.get(anim);
    }

    public Texture[] getEnemyKeeseAnimation(EnemyKeeseAnimationGroup anim) {
        return enemyKeese.get(anim);
    }

    public Texture[] getEnemyLlortAnimation(EnemyLlortAnimationGroup anim) {
        return enemyLlort.get(anim);
    }

    public Texture[] getEnemySardakRedAnimation(EnemySardakRedAnimationGroup anim) {
        return enemySardakRed.get(anim);
    }

    public Texture[] getEnemySardakBlueAnimation(EnemySardakBlueAnimationGroup anim) {
        return enemySardakBlue.get(anim);
    }

    public Texture[] getEnemySardakYellowAnimation(EnemySardakYellowAnimationGroup anim) {
        return enemySardakYellow.get(anim);
    }

    public Texture getNone() {
        return none;
    }

    public Texture[] getNpcGlebb() {
        return npcGlebb;
    }

    public Texture[] getNpcTalkingChestAnimation(NpcTalkingChestAnimationGroup anim) {
        return npcTalkingChest.get(anim);
    }

    public Texture getRubyBlue() {
        return rubyBlue;
    }

    public Texture getRubyYellow() {
        return rubyYellow;
    }

    public Texture getHeart() {
        return heart;
    }

    public Texture[] getHudNumbers() {
        return hudNumbers;
    }

    public Texture getHudNumber(int number) {
        if (number < 0 || number >= hudNumbers.length) {
            throw new IllegalArgumentException("HUD number out of range: " + number);
        }

        return hudNumbers[number];
    }

    public Texture getHudHeartEmpty() {
        return hudHeartEmpty;
    }

    public Texture getHudHeartHalf() {
        return hudHeartHalf;
    }

    public Texture getHudHeartFull() {
        return hudHeartFull;
    }

    public Texture getWand() {
        return wand;
    }

    public Texture getBoomerang() {
        return boomerang;
    }

    public Texture getBone() {
        return bone;
    }

    public Texture getLadder() {
        return ladder;
    }

    public Texture getSpriteLadder() {
        return spriteLadder;
    }

    public Texture[] getSpriteLlortLaserAnimation(SpriteLlortLaserAnimationGroup anim) {
        return spriteLlortLaser.get(anim);
    }

    public Texture[] getSpriteLlortLaser() {
        return getSpriteLlortLaserAnimation(SpriteLlortLaserAnimationGroup.IDLE);
    }

    public Texture getPitcherEmpty() {
        return pitcherEmpty;
    }

    public Texture getPitcherFull() {
        return pitcherFull;
    }

    public Texture getVialOfWind() {
        return vialOfWind;
    }

    public Texture getItemScreen() {
      return itemScreen;
    }

    public Texture[] getFriendlyBoomerang() {
        return friendlyBoomerang;
    }

    public Texture[] getFriendlyJadeRing() {
        return friendlyJadeRing;
    }

    public Texture[] getEnemyBoomerang() {
        return enemyBoomerang;
    }

    public Texture[] getEnemySpear() {
        return enemySpear;
    }

    public Texture getJadeRing() {
        return jadeRing;
    }

    public Texture getRedBoots() {
        return redBoots;
    }

    public Texture getCelestialStone(int index) {
        switch(index) {
        case 1:
            return celestialStone1;
        case 2:
            return celestialStone2;
        case 3:
            return celestialStone3;
        case 4:
            return celestialStone4;
        case 5:
            return celestialStone5;
        case 6:
            return celestialStone6;
        case 7:
            return celestialStone7;
        default:
            throw new IllegalStateException(this.getClass().getSimpleName() + ": celestial stone out of index");
        }
    }

    public Texture getCompass(int index) {
        switch(index) {
        case 1:
            return compass1;
        case 2:
            return compass2;
        case 3:
            return compass3;
        case 4:
            return compass4;
        case 5:
            return compass5;
        case 6:
            return compass6;
        case 7:
            return compass7;
        default:
            throw new IllegalStateException(this.getClass().getSimpleName() + ": compass out of index");
        }
    }

    public Texture getUnderworldMap(int index) {
        switch(index) {
        case 1:
            return underworldMap1;
        case 2:
            return underworldMap2;
        case 3:
            return underworldMap3;
        case 4:
            return underworldMap4;
        case 5:
            return underworldMap5;
        case 6:
            return underworldMap6;
        case 7:
            return underworldMap7;
        default:
            throw new IllegalStateException(this.getClass().getSimpleName() + ": underworld map out of index");
        }
    }
}
