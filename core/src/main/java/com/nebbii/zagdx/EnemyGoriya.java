package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.EnemyGoriyaAnimation;

public class EnemyGoriya extends Enemy {
    public EnemyGoriyaAnimation animation;

    // TODO: Set actual original game accurate values
    public EnemyGoriya() {
        super(ActorType.ENEMY, true);
        setWidth(48);
        setHeight(42);
        setHealth(60);
        setHitDamage(20);

        this.animation = new EnemyGoriyaAnimation(this);

        this.enemyState = EnemyState.SEARCHING;
    }

    @Override
    public void logic() {
        super.logic();

        switch(enemyState) {
            case SEARCHING:
                setSpeed(80f);
                break;
            case FIGHTING:
                setSpeed(110f);
                break;
            default:
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (hurtDuration > 0) drawFlashOverlay(batch);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (hurtDuration > 0) endDrawFlashOverlay(batch);
    }
}
