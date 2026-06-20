package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupDagger extends Pickup {
    public PickupDagger() {
        super();

        setImage(World.images.getDagger());

        baseOffsetX = -2;
        baseOffsetY = 2;

        setWidth(31);
        setHeight(31);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);
        game.addWeapon(Weapon.DAGGER, true);
        this.setState(State.DEAD);
    }
}
