package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface Actor {
    void logic();

    void draw(SpriteBatch batch);

    Rectangle getCollisionBox();
    Rectangle getHitbox();
    float getCenterPointX();
    float getCenterPointY();

    State getState();
    void setState(State state);

    Direction getDirection();

    boolean isActive();
    boolean isDead();

    ActorType getType();
    void setType(ActorType type);

    boolean isSolid();
}
