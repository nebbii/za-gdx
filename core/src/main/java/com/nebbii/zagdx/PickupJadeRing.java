package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupJadeRing extends Pickup {
    public PickupJadeRing() {
        super();
        setImage(World.images.getJadeRing());

        baseOffsetX = -2;
        baseOffsetY = 2;

        setWidth(31);
        setHeight(23);
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
        game.addWeapon(Weapon.JADE_RING, true);
        this.setState(State.DEAD);
    }
}
