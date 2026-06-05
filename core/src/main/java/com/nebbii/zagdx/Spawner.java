package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Spawner extends Rectangle implements Actor {
    private State state;
    private ActorType type;
    private int drawOrder;
    private boolean solid = false;
    protected MapManager map;
    private String locationEntry;
    private String pickupType;

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

    public void activate() {}

    protected Pickup createPickup() {
        if (pickupType == null || pickupType.trim().isEmpty()) {
            throw new IllegalStateException(getClass().getSimpleName() + " requires pickupType");
        }

        try {
            Class<?> newClass = Class.forName("com.nebbii.zagdx." + pickupType.trim());
            Object object = newClass.getDeclaredConstructor().newInstance();

            if (!(object instanceof Pickup)) {
                throw new IllegalArgumentException(pickupType + " is not a pickup");
            }

            Pickup pickup = (Pickup) object;
            pickup.setPosition(this.getX(), this.getY());
            return pickup;
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create pickup: " + pickupType, e);
        }
    }

    protected void placePickup() {
        Pickup pickup = createPickup();
        logItemPlaced();
        map.addNewActor(pickup);
    }

    protected void placePickupWithParent() {
        Pickup pickup = createPickup();
        logItemPlaced();
        map.addNewPickupWithParent(pickup, this);
    }

    protected void logItemPlaced() {
        if (Gdx.app != null) {
            Gdx.app.log(getClass().getSimpleName(), "item placed");
        }
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

    public String getPickupType() {
        return pickupType;
    }

    public void setPickupType(String pickupType) {
        this.pickupType = pickupType;
    }
}
