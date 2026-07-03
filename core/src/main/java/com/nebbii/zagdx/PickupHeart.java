package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupHeart extends Pickup {
    private boolean expires;

    public PickupHeart(boolean expires) {
        super();

        setImage(World.images.getHeart());

        setWidth(11);
        setHeight(11);
    }

    public void logic() {
        super.logic();

        if (expires && getDuration() > 6) {
            map.addNewActor(new SpriteSparkle(getCenterPointX(), getCenterPointY()));
            setState(State.DEAD);
        }
    }

    public void draw(SpriteBatch batch) {
        if (!expires || !(getDuration() > 5 && getDuration() % 0.16f > 0.08f)) {
            super.draw(batch);
        }
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);

        game.getZelda().increaseHealth(20);
        this.setState(State.DEAD);
    }
}
