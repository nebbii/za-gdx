package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemySardakYellowAnimation;

// TODO: Set actual original game accurate values
public class EnemySardakYellow extends EnemySardak {
    public EnemySardakYellowAnimation animation;

    /*
    enemy.sardak.yellow:
        [health=280, damage=50, defense=30, weakness=JadeRing, bonusDamage=70]
    */
    public EnemySardakYellow() {
        super();
        setHealth(280);
        setDamage(50);
        setDefense(30);
        setBonusDamage(70);

        this.animation = new EnemySardakYellowAnimation(this);
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
