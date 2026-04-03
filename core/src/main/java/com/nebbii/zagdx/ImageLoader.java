package com.nebbii.zagdx;

import java.util.EnumMap;

import com.badlogic.gdx.graphics.Texture;

public class ImageLoader {
    private Texture none;
    private Texture[] npcGlebb;

    private Texture rubyBlue;
    private Texture heart;

    public enum ZeldaAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT,
        ATTACKUP,
        ATTACKRIGHT,
        ATTACKDOWN,
        ATTACKLEFT
    }

    private EnumMap<ZeldaAnimationGroup, Texture[]> zelda;

    /* Treasures */
    private Texture bone;
    private Texture pitcherEmpty;
    private Texture pitcherFull;
    private Texture vialOfWind;

    /* Weapons */
    private Texture wand;

    private Texture itemScreen;

    public ImageLoader() {
        none = new Texture("invisible.png");
        itemScreen = new Texture("pause-screen.png"); // TODO: swap with real asset

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

        npcGlebb = loadTextureArray("export/overworld/j24/sprites/desc0/group0", 5);

        /* Items */
        rubyBlue = new Texture("export/common/hudSprites/3.png");
        heart = new Texture("export/common/hudSprites/5.png");

        /* Treasures */
        bone = new Texture("export/overworld/k13a/sprites/desc2/group0/sprite0.png");
        pitcherEmpty = new Texture("export/overworld/j24/sprites/desc1/group0/sprite0.png");
        pitcherFull = new Texture("export/overworld/e20/sprites/desc0/group0/sprite0.png");
        vialOfWind = new Texture("export/overworld/j24/sprites/desc2/group0/sprite0.png");

        /* Weapons */
        wand = new Texture("export/overworld/h23/sprites/desc0/group0/sprite0.png");
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

        for (Texture texture : npcGlebb) {
            texture.dispose();
        }

        /* Items */
        rubyBlue.dispose();
        heart.dispose();

        /* Treasures */
        bone.dispose();
        pitcherEmpty.dispose();
        pitcherFull.dispose();
        vialOfWind.dispose();

        /* Weapons */
        wand.dispose();
    }

    public Texture getImageByItem(Item item) {
        if (item instanceof Treasure) {
            switch((Treasure) item) {
            case BLACK_ORB:
            case BONE:
                return getBone();
            case CANDLE:
            case CELESTIAL_SIGN_1:
            case CELESTIAL_SIGN_2:
            case CELESTIAL_SIGN_3:
            case CELESTIAL_SIGN_4:
            case CELESTIAL_SIGN_5:
            case CELESTIAL_SIGN_6:
            case CELESTIAL_SIGN_7:
            case COAL:
            case COMPASS_1:
            case COMPASS_2:
            case COMPASS_3:
            case COMPASS_4:
            case COMPASS_5:
            case COMPASS_6:
            case COMPASS_7:
            case CRYSTALHEART:
            case DIAMOND:
            case FAIRY:
            case FLUTE:
            case GOLDEN_BOOTS:
            case HARP:
            case KEYS:
            case KNIFE:
            case LADDER:
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
            case RED_RIBBON:
            case RUG:
            case RUPEES:
            case TICKET:
            case UNDERWORLD_MAP_1:
            case UNDERWORLD_MAP_2:
            case UNDERWORLD_MAP_3:
            case UNDERWORLD_MAP_4:
            case UNDERWORLD_MAP_5:
            case UNDERWORLD_MAP_6:
            case UNDERWORLD_MAP_7:
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

    public Texture getNone() {
        return none;
    }

    public Texture[] getNpcGlebb() {
        return npcGlebb;
    }

    public Texture getRubyBlue() {
        return rubyBlue;
    }

    public Texture getHeart() {
        return heart;
    }

    public Texture getWand() {
        return wand;
    }

    public Texture getBone() {
        return bone;
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
}
