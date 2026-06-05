package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupCompass extends Pickup {
    private Treasure compassType;

    public PickupCompass(Treasure compassType) {
        super();

        this.compassType = compassType;

        switch(compassType) {
        case COMPASS_1:
            setImage(World.images.getCompass(1));
            break;
        case COMPASS_2:
            setImage(World.images.getCompass(2));
            break;
        case COMPASS_3:
            setImage(World.images.getCompass(3));
            break;
        case COMPASS_4:
            setImage(World.images.getCompass(4));
            break;
        case COMPASS_5:
            setImage(World.images.getCompass(5));
            break;
        case COMPASS_6:
            setImage(World.images.getCompass(6));
            break;
        case COMPASS_7:
            setImage(World.images.getCompass(7));
            break;
        default:
            throw new IllegalStateException(this.getClass().getSimpleName() + ": no compass type given");
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
        game.addTreasure(compassType, true);
        this.setState(State.DEAD);
    }
}
