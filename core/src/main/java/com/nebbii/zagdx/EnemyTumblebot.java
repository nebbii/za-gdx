package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.EnemyTumblebotAnimation;

public class EnemyTumblebot extends Enemy {
    public EnemyTumblebotAnimation animation;

    public EnemyTumblebot() {
        super(ActorType.FRIENDLY, true);
        setHeight(26);
        setHealth(60);
        setHitDamage(20);

        this.animation = new EnemyTumblebotAnimation(this);

        this.enemyState = EnemyState.SEARCHING;
    }

    @Override
    public void logic() {
        super.logic();

        switch(enemyState) {
            case SEARCHING:
                setSearchSpeed(80f);
                break;
            case FIGHTING:
                setSearchSpeed(110f);
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
