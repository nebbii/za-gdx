package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class SpawnerBoomerang extends Spawner {

    public SpawnerBoomerang() {
        super();
    }

    public void logic() {
        super.logic();
        if (!isActive()) return;

        if (!map.activeActorsContain(EnemyGoriya.class)) {
            activate();
        }
    }

    public void activate() {
        if (!isActive()) return;

        PickupBoomerang pickup = new PickupBoomerang();
        pickup.setPosition(this.getX(), this.getY());
        Gdx.app.log(this.getClass().getSimpleName(), "item placed");

        map.addNewActor(pickup);
        setState(State.DEAD);
    }
}
