package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class SpawnerUnderworldMap1 extends Spawner {

    public SpawnerUnderworldMap1() {
        super();
    }

    public void logic() {
        super.logic();
        if (!isActive()) return;

        if (!map.activeActorsContain(EnemyTektite.class)) {
            activate();
        }
    }

    public void activate() {
        if (!isActive()) return;

        PickupUnderworldMap1 underworldMap = new PickupUnderworldMap1();
        underworldMap.setPosition(this.getX(), this.getY());

        Gdx.app.log(getClass().getSimpleName(), "item placed");

        map.addNewActor(underworldMap);
        setState(State.DEAD);
    }
}
