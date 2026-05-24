package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class SpawnerLadder extends Spawner {

    public SpawnerLadder() {
        super();
    }

    public void logic() {
        super.logic();
        if (!isActive()) return;
    }

    /* uhh maybe in randomizer you know */
    public void activate() {
        if (!isActive()) return;

        Gdx.app.log(this.getClass().getSimpleName(), "ladder placed");
        map.getSaveManager().addLocationEntry(getLocationEntry(), "placed");

        setState(State.DEAD);
    }

    public void activate(GameManager game) {
        if (!isActive()) return;

        Gdx.app.log(this.getClass().getSimpleName(), "ladder placed");
        game.removeTreasure(Treasure.LADDER, true);
        map.getSaveManager().addLocationEntry(getLocationEntry(), "placed");

        setState(State.DEAD);
    }
}
