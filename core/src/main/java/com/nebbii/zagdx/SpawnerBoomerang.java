package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class SpawnerBoomerang extends Spawner {

    public SpawnerBoomerang() {
        super();
    }

    public void logic() {
        super.logic();
    }

    public void activate(MapManager map, GameManager game) {
        if (!isActive()) return;

        PickupBoomerang pickup = new PickupBoomerang();
        pickup.setPosition(this.getX(), this.getY());
        Gdx.app.log(this.getClass().getSimpleName(), "item placed");

        map.addActor(pickup);
        setState(State.DEAD);
    }
}
