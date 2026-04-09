package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.Enemy.EnemyState;

public class EnemyTumblebot extends Enemy {
    //public EnemyTumblebot animation;

    public EnemyTumblebot() {
        super(ActorType.FRIENDLY, false);
        //this.animation = new EnemyTumblebot(this);

        this.enemyState = EnemyState.SEARCHING;
    }

    @Override
    public void logic() {
        super.logic();

        switch(enemyState) {
            case FIGHTING:
                break;
            case SEARCHING:
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        //batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
