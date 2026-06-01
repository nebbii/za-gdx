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

    public void activate() {
        if (!isActive()) return;

        PickupPitcherFull pitcher = new PickupPitcherFull();
        pitcher.setPosition(this.getX(), this.getY());
        Gdx.app.log(getClass().getSimpleName(), "item placed");

        map.addNewActor(pitcher);
        setState(State.DEAD);
    }

    public void activate(GameManager game) {
        if (!isActive()) return;

        PickupPitcherFull pitcher = new PickupPitcherFull();
        pitcher.setPosition(this.getX(), this.getY());
        Gdx.app.log(getClass().getSimpleName(), "item placed");

        map.addNewActor(pitcher);
        game.removeTreasure(Treasure.PITCHER_EMPTY, true);
        setState(State.DEAD);
    }
}
