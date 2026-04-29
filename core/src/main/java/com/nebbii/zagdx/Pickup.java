package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Pickup extends Rectangle implements Actor {
    private State state;
    private ActorType type;
    private MapManager map;
    private int drawOrder;
    private boolean solid;
    private Texture image;

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

    public void onPickup(GameManager game) {}

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
}
