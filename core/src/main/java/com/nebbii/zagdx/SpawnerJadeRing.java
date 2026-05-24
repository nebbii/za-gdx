package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class SpawnerJadeRing extends Spawner {

    public SpawnerJadeRing() {
        super();
    }

    public void logic() {
        super.logic();
        if (!isActive()) return;
    }

    @Override
    public void activate() {
        if (!isActive()) return;

        PickupJadeRing pickup = new PickupJadeRing();
        pickup.setPosition(this.getX(), this.getY());
        Gdx.app.log(this.getClass().getSimpleName(), "item placed");

        map.addNewPickupWithParent(pickup, this);
        setState(State.DEAD);
    }
}
