package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyKelpiAnimation;

// TODO: Set actual original game accurate values
public class EnemyKelpi extends Enemy {
    public EnemyKelpiAnimation animation;

    public EnemyKelpi() {
        super(ActorType.ENEMY, true);
        setWidth(72);
        setHeight(63);
        setHealth(280);
        setDamage(69);
        setDefense(34);
        setBonusDamage(70);

        this.animation = new EnemyKelpiAnimation(this);

        this.enemyState = EnemyState.SEARCH;
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

    // TODO: CD-i Interactief guide states Kelpi is killed with the Turquoise Ring, not
    // Boomerang (which is what cast.json's weakToSpell field reported). No
    // ZeldaActionTurquoiseRing class exists yet in this codebase, so returning empty
    // until that item is implemented, rather than shipping a confirmed-wrong weakness.
    @Override
    public Array<String> getWeaknesses() {
        return new Array<String>();
    }
}
