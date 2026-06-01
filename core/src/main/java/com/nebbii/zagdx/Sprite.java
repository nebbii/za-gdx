package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Sprite extends Rectangle implements Actor {
    protected boolean solid;
    protected MapManager map;
    protected String locationEntry;
    private Texture image;

    protected State state;
    protected ActorType type;
    protected int drawOrder;
    protected Direction direction;
    protected int damage;
    protected int bonusDamage;
    protected int defense;

    public Sprite(ActorType actorType, boolean solid) {
        setWidth(32);
        setHeight(32);
        setBonusDamage(0);
        setDamage(0);
        setDefense(0);
        setState(State.IDLE);
        setType(actorType);
        this.drawOrder = 0;
    }

    public void logic() {
        if (!isActive()) return;
    }

    public void draw(SpriteBatch batch) {
    }

    @Override
    public int getDrawOrder() {
        return drawOrder;
    }

    protected void move() {
    }

    public Rectangle getHitbox() {
        return this;
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

    public Array<String> getWeaknesses() {
        return new Array<>();
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

    public Direction getDirection() {
        return direction;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
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

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

}
