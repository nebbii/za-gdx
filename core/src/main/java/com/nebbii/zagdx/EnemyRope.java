package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyRopeAnimation;

// TODO: Set actual original game accurate values
public class EnemyRope extends Enemy {
    public EnemyRopeAnimation animation;

    /*
    enemy.rope:
        [health=5, damage=57, defense=500, weakness=Wand]
    */
    public EnemyRope() {
        super(ActorType.ENEMY, true);
        setWidth(76);
        setHeight(57);
        setHealth(5);
        setDamage(57);
        setDefense(500);

        this.animation = new EnemyRopeAnimation(this);

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
