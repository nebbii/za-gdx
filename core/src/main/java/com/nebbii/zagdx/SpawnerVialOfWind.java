package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class SpawnerVialOfWind extends Spawner {

    public SpawnerVialOfWind() {
        super();
    }

    public void logic() {
        super.logic();
    }

    public void activate() {
        if (!isActive()) return;

        PickupVialOfWind vialOfWind = new PickupVialOfWind();
        vialOfWind.setPosition(this.getX(), this.getY());

        Gdx.app.log(getClass().getSimpleName(), "item placed");

        map.addNewActor(vialOfWind);
        setState(State.DEAD);
    }

    public void activate(GameManager game) {
        if (!isActive()) return;

        PickupVialOfWind pickup = new PickupVialOfWind();
        pickup.setPosition(this.getX(), this.getY());

        Gdx.app.log(getClass().getSimpleName(), "item placed");

        map.addNewPickupWithParent(pickup, this);
        game.removeTreasure(Treasure.PITCHER_FULL, true);
        setState(State.DEAD);
    }
}
