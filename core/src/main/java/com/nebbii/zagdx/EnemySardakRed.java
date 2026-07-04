package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemySardakRedAnimation;

// TODO: Set actual original game accurate values
public class EnemySardakRed extends EnemySardak {
    public EnemySardakRedAnimation animation;

    /*
    enemy.sardak.red:
        [health=280, damage=50, defense=30, weakness=JadeRing, bonusDamage=70]
    */
    public EnemySardakRed() {
        super();
        setHealth(280);
        setDamage(50);
        setDefense(30);
        setBonusDamage(70);
        setVoiceLine(World.sounds.getEnemySardakRedLine0());

        this.animation = new EnemySardakRedAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
    }

    @Override
    public Array<String> getWeaknesses() {
        return Array.with("ZeldaActionJadeRing");
    }
}
