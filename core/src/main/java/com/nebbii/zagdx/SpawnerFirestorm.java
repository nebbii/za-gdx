package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class SpawnerFirestorm extends Spawner {

    public SpawnerFirestorm() {
        super();
    }

    public void logic() {
        super.logic();
        if (!isActive()) return;
    }

    @Override
    public void activate() {
        if (!isActive()) return;

        PickupFirestorm pickup = new PickupFirestorm();
        pickup.setPosition(this.getX(), this.getY());
        Gdx.app.log(this.getClass().getSimpleName(), "item placed");

        map.addNewPickupWithParent(pickup, this);
        setState(State.DEAD);
    }
}
