package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public interface Actor {
    void logic();

    void draw(SpriteBatch batch);
    int getDrawOrder();

    Rectangle getCollisionBox(); // main rectangle, use instead of getRectangle()
    Rectangle getHitbox();
    float getCenterPointX();
    float getCenterPointY();

    Array<String> getWeaknesses();
    int getDamage();
    int getBonusDamage();
    int getDefense();

    State getState();
    void setState(State state);

    Direction getDirection();

    boolean isActive();
    boolean isDead();

    ActorType getType();
    void setType(ActorType type);

    boolean isSolid();

    MapManager getMap();
    void setMap(MapManager map);

    String getLocationEntry();
    void setLocationEntry(String locationEntry);
}
