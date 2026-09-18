package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyKnightGreenAnimation;

// TODO: Set actual original game accurate values
public class EnemyKnightGreen extends Enemy {
    public EnemyKnightGreenAnimation animation;

    /*
    enemy.knight.green:
        [cast.json's weakToSpell field reported Hourglass, but the CD-i Interactief guide
        explicitly states Zelda "must use her broadsword against the Green Knight."
        No ZeldaActionBroadsword-equivalent class exists yet in this codebase, so
        returning empty until that item is implemented, rather than shipping the
        extractor's unconfirmed value.]
    */
    public EnemyKnightGreen() {
        super(ActorType.ENEMY, true);
        setWidth(116);
        setHeight(102);
        setHealth(280);
        setDamage(73);
        setDefense(35);
        setBonusDamage(70);

        this.animation = new EnemyKnightGreenAnimation(this);

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

    @Override
    public Array<String> getWeaknesses() {
        return new Array<String>();
    }
}
