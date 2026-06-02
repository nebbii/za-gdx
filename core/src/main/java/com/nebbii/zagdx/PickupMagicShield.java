package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupMagicShield extends Pickup {
    public PickupMagicShield() {
        super();

        setImage(World.images.getMagicShield());
        setPriceImage(World.images.getMagicShieldPrice());

        setWidth(43);
        setHeight(43);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);
        game.addTreasure(Treasure.MAGIC_SHIELD, true);
        this.setState(State.DEAD);
    }
}
