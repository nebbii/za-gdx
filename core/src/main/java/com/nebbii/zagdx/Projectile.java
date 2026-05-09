package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Projectile extends Rectangle implements Actor {
    protected boolean solid = false;

    protected MapManager map;
    private String locationEntry;
    protected int damage;
    protected int bonusDamage;
    protected float stateTime;
    protected float duration;
    protected State state;
    protected Direction direction;

    private ActorType type;
    private int drawOrder;

    public Projectile(Actor actor, float x, float y) {
        setState(State.ACTIVE);
        setType(ActorType.PROJECTILE);
        this.drawOrder = 3;
        this.damage = 0;
        this.bonusDamage = 0;
    }

    @Override
    public void logic() {
        if (isDead()) return;
    }

    @Override
    public void draw(SpriteBatch batch) {
    }

    public void onHit() {
        setState(State.DEAD);
    }

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

    public String[] getWeaknesses() {
        return new String[] {};
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }

    public void setBonusDamage(int bonusDamage) {
        this.bonusDamage = bonusDamage;
    }

    public int getDefense() {
        return 0;
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
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    public MapManager getMap() {
        return map;
    }

    public void setMap(MapManager map) {
        this.map = map;
    }

    @Override
    public String getLocationEntry() {
        return locationEntry;
    }

    @Override
    public void setLocationEntry(String locationEntry) {
        this.locationEntry = locationEntry;
    }
}
