package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyCrockarockAnimation;

// TODO: Set actual original game accurate values
public class EnemyCrockarock extends Enemy {
    public EnemyCrockarockAnimation animation;

    /*
    enemy.crockarock:
        [health=60, damage=72, defense=38, weakness=Wand]
    */
    public EnemyCrockarock() {
        super(ActorType.ENEMY, true);
        setWidth(48);
        setHeight(38);
        setHealth(60);
        setDamage(72);
        setDefense(38);

        this.animation = new EnemyCrockarockAnimation(this);

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
        return Array.with("ZeldaActionWand");
    }
}
