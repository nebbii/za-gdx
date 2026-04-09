package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Rectangle implements Actor {
    private boolean solid;
    protected State state;
    protected EnemyState enemyState;
    protected ActorType type;
    protected Rectangle alertBox;

    public enum EnemyState {
        SEARCHING,
        FIGHTING
    }

    public Enemy(ActorType actorType, boolean solid) {
        setWidth(32);
        setHeight(32);
        setState(State.IDLE);
        setType(actorType);
        this.solid = solid;

        this.alertBox = new Rectangle(0, 0, 32, 32);
    }

    public void logic() {
        if (getState() != State.ACTIVE) return;
        /*
        switch(enemyState) {
            case FIGHTING:
                break;
            case SEARCHING:
                break;
            default:
                break;
        }
        */
    }

    public void draw(SpriteBatch batch) {
        if (getState() != State.ACTIVE) return;
    }

    public Rectangle getHitbox() {
        return this;
    }

    public Rectangle getAlertBox() {
        return alertBox;
    }

    public Rectangle getCollisionBox() {
        return this;
    }

    public float getCenterPointX() {
        return getX() + getWidth() / 2;
    }

    public float getCenterPointY() {
        return getY() + getHeight() / 2;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isActive() {
        return getState() == State.ACTIVE;
    }

    public boolean isIdle() {
        return getState() == State.IDLE;
    }

    public boolean isDead() {
        return getState() == State.DEAD;
    }

    public ActorType getType() {
        return type;
    }

    public void setType(ActorType type) {
        this.type = type;
    }

    public boolean isSolid() {
        return solid;
    }
}
