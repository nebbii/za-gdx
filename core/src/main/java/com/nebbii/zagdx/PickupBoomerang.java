package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupBoomerang extends Pickup {
    private Texture image;

    public PickupBoomerang() {
        super();

        setImage(World.images.getBoomerang());

        baseOffsetX = -2;
        baseOffsetY = 2;

        setWidth(23);
        setHeight(31);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void onPickup(GameManager game) {
        game.addWeapon(Weapon.BOOMERANG);
        this.setState(State.DEAD);
    }
}
