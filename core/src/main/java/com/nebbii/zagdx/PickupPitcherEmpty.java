package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupPitcherEmpty extends Pickup {
    private Texture image;

    public PickupPitcherEmpty() {
        super();

        setImage(World.images.getPitcherEmpty());

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
        game.addTreasure(Treasure.PITCHER_EMPTY);
        this.setState(State.DEAD);
    }
}
