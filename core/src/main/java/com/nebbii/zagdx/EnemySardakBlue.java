package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemySardakBlueAnimation;

// TODO: Set actual original game accurate values
public class EnemySardakBlue extends EnemySardak {
    public EnemySardakBlueAnimation animation;

    /*
    enemy.sardak.blue:
        [health=80, damage=50, defense=30, weakness=JadeRing, bonusDamage=70]
    */
    public EnemySardakBlue() {
        super();
        setHealth(80);
        setDamage(50);
        setDefense(30);
        setBonusDamage(70);

        this.animation = new EnemySardakBlueAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();

        switch(enemyState) {
            case SEARCH:
                setSpeed(80f);
                break;
            case FIGHT:
                setSpeed(110f);
                break;
            default:
        }
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
