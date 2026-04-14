package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupHeart extends Pickup {
    public PickupHeart() {
        super();

        setImage(World.images.getHeart());

        setWidth(6);
        setHeight(8);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void onPickup(GameManager game) {
        game.getZelda().increaseHealth(20);
        this.setState(State.DEAD);
    }
}
