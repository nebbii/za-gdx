package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class SpawnerTektiteAmbush extends Spawner {

    public SpawnerTektiteAmbush() {
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

        EnemyTektite tektite = new EnemyTektite();
        tektite.setX(this.getX());
        tektite.setY(this.getY());
        tektite.setState(State.ACTIVE);
        map.addNewActor(tektite);

        setState(State.DEAD);
    }
}
