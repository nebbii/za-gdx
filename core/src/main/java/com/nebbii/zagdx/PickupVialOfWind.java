package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupVialOfWind extends Pickup {
    private Texture image;

    public PickupVialOfWind() {
        super();
        setImage(World.images.getVialOfWind());

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
        game.addTreasure(Treasure.VIAL_OF_WIND);
        this.setState(State.DEAD);
    }
}
