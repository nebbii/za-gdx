package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyWizzrobeAnimation;

/*
enemy.wizzrobe:
    [health=280, damage=45, defense=35, bonusDamage=70]
    NOTE: weakToSpell_raw was "LeatherBook", which has no matching ZeldaAction* class in this
    codebase; returning an empty weakness list until that item/action exists.
*/
// TODO: verify weakness once a LeatherBook-equivalent ZeldaAction class exists
public class EnemyWizzrobe extends Enemy {
    public EnemyWizzrobeAnimation animation;

    public EnemyWizzrobe() {
        super(ActorType.ENEMY, true);
        setWidth(64);
        setHeight(54);
        setHealth(280);
        setDamage(45);
        setDefense(35);
        setBonusDamage(70);

        this.animation = new EnemyWizzrobeAnimation(this);

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
