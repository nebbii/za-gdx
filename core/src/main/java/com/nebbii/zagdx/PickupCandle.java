package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupCandle extends Pickup {
    public PickupCandle() {
        super();

        setImage(World.images.getCandle(), -4, 0);
        //setPriceImage(World.images.getCandlePrice());

        setWidth(27);
        setHeight(42);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);
        game.addTreasure(Treasure.CANDLE, true);
        this.setState(State.DEAD);
    }
}
