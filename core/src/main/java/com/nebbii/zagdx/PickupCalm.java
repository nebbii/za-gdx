package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupCalm extends Pickup {
    public PickupCalm() {
        super();

        setImage(World.images.getCalm());
        setPriceImage(World.images.getCalmPrice());

        setWidth(35);
        setHeight(40);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);
        game.addWeapon(Weapon.CALM, true);
        this.setState(State.DEAD);
    }
}
