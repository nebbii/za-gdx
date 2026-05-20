package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupUnderworldMap extends Pickup {
    private Treasure mapType;

    public PickupUnderworldMap(Treasure mapType) {
        super();

        this.mapType = mapType;

        switch(mapType) {
        case UNDERWORLD_MAP_1:
            setImage(World.images.getUnderworldMap(1));
            break;
        case UNDERWORLD_MAP_2:
            setImage(World.images.getUnderworldMap(2));
            break;
        case UNDERWORLD_MAP_3:
            setImage(World.images.getUnderworldMap(3));
            break;
        case UNDERWORLD_MAP_4:
            setImage(World.images.getUnderworldMap(4));
            break;
        case UNDERWORLD_MAP_5:
            setImage(World.images.getUnderworldMap(5));
            break;
        case UNDERWORLD_MAP_6:
            setImage(World.images.getUnderworldMap(6));
            break;
        case UNDERWORLD_MAP_7:
            setImage(World.images.getUnderworldMap(7));
            break;
        default:
            throw new IllegalStateException(this.getClass().getSimpleName() + ": no underworld map type given");
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
        game.addTreasure(mapType, true);
        this.setState(State.DEAD);
    }
}
