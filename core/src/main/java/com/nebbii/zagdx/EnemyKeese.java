package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyKeeseAnimation;

public class EnemyKeese extends Enemy {
    public EnemyKeeseAnimation animation;

    public EnemyKeese() {
        super(ActorType.ENEMY, false);
        setWidth(48);
        setHeight(26);
        setHealth(60);
        setDamage(45);
        setDefense(10);

        this.animation = new EnemyKeeseAnimation(this);

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

    public float getAnimationSpeed() {
        switch(enemyState) {
            case FIGHT:
                return 0.08f;
            case SEARCH:
            default:
                return 0.12f;
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
