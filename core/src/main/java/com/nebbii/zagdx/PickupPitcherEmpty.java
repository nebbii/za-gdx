package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupPitcherEmpty extends Pickup {
    private Texture image;

    public PickupPitcherEmpty() {
        super();

        setImage(World.images.getPitcherEmpty());

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
        Gdx.app.log(this.getClass().getSimpleName(), "picked up and made dead!");
        game.addTreasure(Treasure.PITCHER_EMPTY, true);
        this.setState(State.DEAD);
    }
}
