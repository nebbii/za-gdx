package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SpawnerVialOfWind extends Spawner {

    public SpawnerVialOfWind() {
        super();
    }

    public void logic() {
        super.logic();
    }

    public void activate(GameManager game) {
        if (!isActive()) return;

        PickupVialOfWind vialOfWind = new PickupVialOfWind();
        vialOfWind.setPosition(this.getX(), this.getY());

        Gdx.app.log("SpawnerVialOfWind", "item placed");

        map.addActor(vialOfWind);
        game.removeTreasure(Treasure.PITCHER_FULL);
        setState(State.DEAD);
    }
}
