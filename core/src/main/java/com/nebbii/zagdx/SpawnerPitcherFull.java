package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SpawnerPitcherFull extends Spawner {

    public SpawnerPitcherFull() {
        super();
    }

    public void logic() {
        super.logic();
    }

    public void activate(GameManager game) {
        if (!isActive()) return;

        PickupPitcherFull pitcher = new PickupPitcherFull();
        pitcher.setPosition(this.getX(), this.getY());
        Gdx.app.log("SpawnerPitcherFull", "item placed");

        map.addActor(pitcher);
        game.removeTreasure(Treasure.PITCHER_EMPTY);
        setState(State.DEAD);
    }
}
