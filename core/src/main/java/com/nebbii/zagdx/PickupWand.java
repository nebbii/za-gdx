package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupWand extends Pickup {
    public PickupWand() {
        super();

        setImage(World.images.getWand());

        setWidth(31);
        setHeight(31);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getImage(), getX(), getY(), getWidth(), getHeight());
    }

    public void onPickup(GameManager game) {
        game.addWeapon(Weapon.WAND);
        game.getZelda().setCurrentItem(Weapon.WAND);
        this.setState(State.DEAD);
    }
}
