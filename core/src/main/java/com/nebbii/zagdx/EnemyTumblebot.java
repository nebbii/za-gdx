package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.EnemyTumblebotAnimation;

public class EnemyTumblebot extends Enemy {
    public EnemyTumblebotAnimation animation;

    public EnemyTumblebot() {
        super(ActorType.FRIENDLY, false);
        setHeight(26);

        this.animation = new EnemyTumblebotAnimation(this);

        this.enemyState = EnemyState.SEARCHING;
    }

    @Override
    public void logic() {
        super.logic();
        if (getState() != State.ACTIVE) return;

        switch(enemyState) {
            case SEARCHING:
                checkAndSetRandomDirection();
                move();
                break;
            case FIGHTING:
                move();
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (getState() != State.ACTIVE) return;

        if (hurtDuration > 0) drawFlashOverlay(batch);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (hurtDuration > 0) endDrawFlashOverlay(batch);
    }

    private void move() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        switch(getDirection()) {
            case LEFT:
                setX(getX() - searchSpeed * deltaTime);
                break;
            case DOWN:
                setY(getY() - searchSpeed * deltaTime);
                break;
            case UP:
                setY(getY() + searchSpeed * deltaTime);
                break;
            case RIGHT:
                setX(getX() + searchSpeed * deltaTime);
                break;
            default:
                throw new IllegalStateException("EnemyTumblebot->moveSearch(): Unhandled movement state" + getDirection());
        }
    }
}
