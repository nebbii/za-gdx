package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

class TestSardakRed extends Enemy {
    public TestSardakRed() {
        super(ActorType.ENEMY, true);

        setHealth(280);
        setDamage(50);
        setDefense(30);
        setBonusDamage(70);
    }

    @Override
    public Array<String> getWeaknesses() {
        return Array.with("TestZeldaActionJadeRing");
    }

    @Override
    public void logic() {
    }

    @Override
    public void draw(SpriteBatch batch) {
    }
}
