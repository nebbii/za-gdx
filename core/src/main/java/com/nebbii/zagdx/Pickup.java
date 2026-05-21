package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Pickup extends Rectangle implements Actor {
    private State state;
    private ActorType type;
    protected MapManager map;
    protected String locationEntry;
    private int drawOrder;
    private boolean solid;
    private Texture image;
    private Spawner spawnerParent;

    private float duration;

    protected float baseOffsetX; // actor wide
    protected float baseOffsetY;
    protected float offsetX; // animation specific
    protected float offsetY;

    public Pickup() {
        setType(ActorType.PICKUP);
        setState(State.PENDING);
        this.drawOrder = 1;
        this.duration = 0;
        this.spawnerParent = null;
    }

    @Override
    public void logic() {
        this.duration += Gdx.graphics.getDeltaTime();

        if (isPending() && duration >= 1f) setState(State.ACTIVE);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(isPending()) {
            drawBounceAnim(batch);
        }
        else {
            batch.draw(getImage(), getX() + offsetX + baseOffsetX, getY() + offsetY + baseOffsetY, getWidth(), getHeight());
        }
    }

    @Override
    public int getDrawOrder() {
        return drawOrder;
    }

    /*
     * simple bounce curve using duration
     */
    public void drawBounceAnim(SpriteBatch batch) {
        float decay = (float) Math.exp(-4 * duration);
        float bounce = (float) Math.pow(Math.abs(Math.sin(8f * Math.PI * duration)), 1.5f) * decay;
        float offsetY = bounce * 10f;

        batch.draw(getImage(), getX(), getY() + offsetY, getWidth(), getHeight());
    }

    public void onPickup(GameManager game) {
        Gdx.app.log(this.getClass().getSimpleName(), "Storing pickup in save");

        if (spawnerParent != null) {
            map.getSaveManager().addLocationEntry(spawnerParent.getLocationEntry(), "spawned");
        }
        else if (getLocationEntry() != null) {
            map.getSaveManager().addLocationEntry(getLocationEntry(), "picked_up");
        }
    }

    @Override
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

    public Array<String> getWeaknesses() {
        return new Array<>();
    }

    public int getDamage() {
        return 0;
    }

    public int getBonusDamage() {
        return 0;
    }

    public int getDefense() {
        return 0;
    }

    @Override
    public State getState() {
        return this.state;
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

    public boolean isPending() {
        return getState() == State.PENDING;
    }

    public boolean isIdle() {
        return getState() == State.IDLE;
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

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
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

    public Spawner getSpawnerParent() {
        return spawnerParent;
    }

    public void setSpawnerParent(Spawner spawnerParent) {
        this.spawnerParent = spawnerParent;
    }
}
