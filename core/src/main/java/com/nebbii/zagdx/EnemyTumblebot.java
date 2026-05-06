package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.EnemyTumblebotAnimation;

public class EnemyTumblebot extends Enemy {
    public EnemyTumblebotAnimation animation;

    // TODO: Set actual original game accurate values
    public EnemyTumblebot() {
        super(ActorType.ENEMY, true);
        setHeight(26);
        setHealth(60);
        setDamage(57);
        setDefense(10);

        this.animation = new EnemyTumblebotAnimation(this);

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
}
