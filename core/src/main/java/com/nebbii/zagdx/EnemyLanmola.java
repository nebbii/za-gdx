package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyLanmolaAnimation;

// TODO: Set actual original game accurate values
public class EnemyLanmola extends Enemy {
    public EnemyLanmolaAnimation animation;

    /*
    enemy.lanmola:
        [health=60, damage=68, defense=20, weakness=Wand]
    */
    public EnemyLanmola() {
        super(ActorType.ENEMY, true);
        setWidth(60);
        setHeight(60);
        setHealth(60);
        setDamage(68);
        setDefense(20);

        this.animation = new EnemyLanmolaAnimation(this);

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
