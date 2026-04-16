package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ZeldaAction extends Rectangle implements Actor {
    protected final boolean solid = false;

    protected Zelda zelda;
    protected float damage = 0f;
    protected float stateTime;
    protected float duration;
    protected State state;

    private ActorType type;

    public ZeldaAction(Zelda zelda, float x, float y) {
        this.zelda = zelda;
        setState(State.ACTIVE);
        setType(ActorType.PROJECTILE);
        stateTime = 0f;
        duration = 0.5f;
    }

    @Override
    public void logic() {
        if (isDead()) return;
        if (stateTime >= duration) setState(State.DEAD);
        stateTime += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void draw(SpriteBatch batch) {
    }

    public Rectangle getHitbox() {
        return this;
    }

    @Override
    public Rectangle getCollisionBox() {
        return this;
    }

    public float getCenterPointX() {
        return getX() + getWidth() / 2;
    }

    public float getCenterPointY() {
        return getY() + getHeight() / 2;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    public Direction getDirection() {
        return Direction.DOWN;
    }

    public boolean isActive() {
        return getState() == State.ACTIVE;
    }

    public boolean isDead() {
        return getState() == State.DEAD;
    }

    public boolean isSolid() {
        return solid;
    }

    public ActorType getType() {
        return type;
    }

    public void setType(ActorType type) {
        this.type = type;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}
