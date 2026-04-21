package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Spawner extends Rectangle implements Actor {
    private State state;
    private ActorType type;
    private int drawOrder;
    private boolean solid = false;

    public Spawner() {
        setWidth(1);
        setHeight(1);
        setState(State.IDLE);
        setType(ActorType.SPAWNER);
        this.drawOrder = 0;
    }

    @Override
    public void logic() {
    }

    @Override
    public void draw(SpriteBatch batch) {}

    @Override
    public int getDrawOrder() {
        return drawOrder;
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
        return this.state;
    }

    @Override
    public void setState(State state) {
        //Gdx.app.log("Spawner", "Updating state: " + state);
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

    @Override
    public ActorType getType() {
        return type;
    }

    @Override
    public void setType(ActorType type) {
        this.type = type;
    }

    @Override
    public boolean isSolid() {
        return solid;
    }
}
