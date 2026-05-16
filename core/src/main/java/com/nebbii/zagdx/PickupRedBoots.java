package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupRedBoots extends Pickup {
    private Texture image;

    public PickupRedBoots() {
        super();
        setImage(World.images.getRedBoots());

        baseOffsetX = -2;
        baseOffsetY = 0;

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
        super.onPickup(game);
        game.addTreasure(Treasure.RED_BOOTS, true);
        this.setState(State.DEAD);
    }
}
