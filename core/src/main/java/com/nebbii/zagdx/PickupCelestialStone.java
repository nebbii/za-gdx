package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupCelestialStone extends Pickup {
    private Treasure celestialStoneType;

    public PickupCelestialStone(Treasure celestialStoneType) {
        super();

        setState(State.ACTIVE);

        this.celestialStoneType = celestialStoneType;

        switch(celestialStoneType) {
        case CELESTIAL_SIGN_1:
            setImage(World.images.getCelestialStone(1));
            break;
        case CELESTIAL_SIGN_2:
            setImage(World.images.getCelestialStone(2));
            break;
        case CELESTIAL_SIGN_3:
            setImage(World.images.getCelestialStone(3));
            break;
        case CELESTIAL_SIGN_4:
            setImage(World.images.getCelestialStone(4));
            break;
        case CELESTIAL_SIGN_5:
            setImage(World.images.getCelestialStone(5));
            break;
        case CELESTIAL_SIGN_6:
            setImage(World.images.getCelestialStone(6));
            break;
        case CELESTIAL_SIGN_7:
            setImage(World.images.getCelestialStone(7));
            break;
        default:
            throw new IllegalStateException(this.getClass().getSimpleName() + ": no celestial stone type given");
        }

        baseOffsetX = 0;
        baseOffsetY = 0;

        setWidth(31);
        setHeight(30);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);
        game.addTreasure(celestialStoneType, true);
        this.setState(State.DEAD);
    }
}
