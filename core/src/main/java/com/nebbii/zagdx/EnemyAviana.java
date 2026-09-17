package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyAvianaAnimation;

// TODO: Set actual original game accurate values
public class EnemyAviana extends Enemy {
    public EnemyAvianaAnimation animation;

    /*
    enemy.aviana:
        [health=60, damage=60, defense=24, weakness=none (weakToSpell_raw=RingsOfFire, not mapped)]
    */
    public EnemyAviana() {
        super(ActorType.ENEMY, false);
        setWidth(152);
        setHeight(101);
        setHealth(60);
        setDamage(60);
        setDefense(24);

        this.animation = new EnemyAvianaAnimation(this);

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
