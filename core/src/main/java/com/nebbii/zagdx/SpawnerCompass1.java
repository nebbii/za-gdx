package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class SpawnerCompass1 extends Spawner {

    public SpawnerCompass1() {
        super();
    }

    public void logic() {
        super.logic();
        if (!isActive()) return;

        if (!map.activeActorsContain(Enemy.class)) {
            activate();
        }
    }

    @Override
    public void activate() {
        if (!isActive()) return;

        PickupCompass1 pickup = new PickupCompass1();
        pickup.setPosition(this.getX(), this.getY());
        Gdx.app.log(this.getClass().getSimpleName(), "item placed");

        map.addNewPickupWithParent(pickup, this);
        setState(State.DEAD);
    }
}
