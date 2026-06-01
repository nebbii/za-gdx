
package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class TestWeapon implements Actor {
    @Override
    public int getDamage() {
        return 10;
    }

    @Override
    public Array<String> getWeaknesses() {
        return new Array<>();
    }

    @Override
    public int getBonusDamage() {
        return 0;
    }

    @Override
    public int getDefense() {
        return 0;
    }

    @Override
    public void logic() {
    }

    @Override
    public void draw(SpriteBatch batch) {
    }

    @Override
    public int getDrawOrder() {
        return 0;
    }

    @Override
    public Rectangle getCollisionBox() {
        return null;
    }

    @Override
    public Rectangle getHitbox() {
        return null;
    }

    @Override
    public float getCenterPointX() {
        return 0;
    }

    @Override
    public float getCenterPointY() {
        return 0;
    }

    @Override
    public State getState() {
        return null;
    }

    @Override
    public void setState(State state) {
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public ActorType getType() {
        return null;
    }

    @Override
    public void setType(ActorType type) {
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public MapManager getMap() {
        return null;
    }

    @Override
    public void setMap(MapManager map) {
    }

    @Override
    public String getLocationEntry() {
        return null;
    }

    @Override
    public void setLocationEntry(String locationEntry) {
    }
}
