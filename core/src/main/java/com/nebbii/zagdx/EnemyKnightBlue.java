package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyKnightBlueAnimation;

// TODO: Set actual original game accurate values
public class EnemyKnightBlue extends Enemy {
    public EnemyKnightBlueAnimation animation;

    /*
    enemy.knight.blue:
        [cast.json's weakToSpell field reported Hourglass, but the CD-i Interactief
        guide's List of Foes states the Blue Knight's weakness is the Broadsword (same
        as the Green Knight). No ZeldaActionBroadsword-equivalent class exists yet in
        this codebase, so returning empty until that item is implemented.]
    */
    public EnemyKnightBlue() {
        super(ActorType.ENEMY, true);
        setWidth(116);
        setHeight(102);
        setHealth(280);
        setDamage(73);
        setDefense(35);
        setBonusDamage(70);

        this.animation = new EnemyKnightBlueAnimation(this);

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
