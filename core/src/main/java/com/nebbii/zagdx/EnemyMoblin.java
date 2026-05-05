package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.EnemyMoblinAnimation;

public class EnemyMoblin extends Enemy {
    public EnemyMoblinAnimation animation;

    // TODO: Set actual original game accurate values
    public EnemyMoblin() {
        super(ActorType.ENEMY, true);
        setWidth(40);
        setHeight(40);
        setHealth(60);

        this.animation = new EnemyMoblinAnimation(this);

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
        if (knockback > 0) drawFlashOverlay(batch);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
    }
}
