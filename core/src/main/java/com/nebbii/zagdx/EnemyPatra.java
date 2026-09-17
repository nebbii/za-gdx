package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyPatraAnimation;

// TODO: Set actual original game accurate values
public class EnemyPatra extends Enemy {
    public EnemyPatraAnimation animation;

    /*
    enemy.patra:
        [health=30, damage=37, defense=5, weakness=Wand]
    */
    public EnemyPatra() {
        super(ActorType.ENEMY, false);
        setWidth(72);
        setHeight(41);
        setHealth(30);
        setDamage(37);
        setDefense(5);

        this.animation = new EnemyPatraAnimation(this);

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
