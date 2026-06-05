package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

class TestZelda implements Actor {
    private final int damage;
    private final Rectangle hitbox = new Rectangle(0f, 0f, 24f, 36f);

    public TestZelda(int damage) {
        this.damage = damage;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public Direction getDirection() {
        return Direction.RIGHT;
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public float getCenterPointX() {
        return hitbox.x + hitbox.width / 2f;
    }

    @Override
    public float getCenterPointY() {
        return hitbox.y + hitbox.height / 2f;
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
        return hitbox;
    }

    @Override
    public State getState() {
        return State.ACTIVE;
    }

    @Override
    public void setState(State state) {
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public ActorType getType() {
        return ActorType.PLAYER;
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