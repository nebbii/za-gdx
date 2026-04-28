package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupPitcherFull extends Pickup {
    public PickupPitcherFull() {
        super();
        setImage(World.images.getPitcherFull());

        baseOffsetX = -2;
        baseOffsetY = 2;

        setWidth(24);
        setHeight(29);
        setState(State.PENDING);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void onPickup(GameManager game) {
        game.addTreasure(Treasure.PITCHER_FULL);
        this.setState(State.DEAD);
    }
}
