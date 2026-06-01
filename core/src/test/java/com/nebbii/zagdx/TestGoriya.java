package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestGoriya extends Enemy {
    public TestGoriya() {
        super(ActorType.ENEMY, true);

        setHealth(60);
        setDamage(45);
        setBonusDamage(5);
        setDefense(10);
    }

    @Override
    public void logic() {
    }

    @Override
    public void draw(SpriteBatch batch) {
    }
}
