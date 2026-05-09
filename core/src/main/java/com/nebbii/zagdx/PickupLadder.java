package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupLadder extends Pickup {
    private Texture image;

    public PickupLadder() {
        super();

        setImage(World.images.getLadder());

        baseOffsetX = -2;
        baseOffsetY = 2;

        setWidth(24);
        setHeight(29);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);
        game.addTreasure(Treasure.LADDER);
        this.setState(State.DEAD);
    }
}
