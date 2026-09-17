package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyTumbleHeadAnimation;

// TODO: Set actual original game accurate values
public class EnemyTumbleHead extends Enemy {
    public EnemyTumbleHeadAnimation animation;

    public EnemyTumbleHead() {
        super(ActorType.ENEMY, true);
        setWidth(60);
        setHeight(43);
        setHealth(60);
        setDamage(40);
        setDefense(0);

        this.animation = new EnemyTumbleHeadAnimation(this);

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
