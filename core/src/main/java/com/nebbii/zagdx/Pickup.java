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
    private Texture priceImage;
    private Spawner spawnerParent;
    private boolean purchasable;
    private int price;
    private boolean overlappingZelda;

    private float duration;

    protected float baseOffsetX; // actor wide
    protected float baseOffsetY;
    protected float offsetX; // animation specific
    protected float offsetY;
    private float priceImageOffsetX;
    private float priceImageOffsetY;

    public Pickup() {
        setType(ActorType.PICKUP);
        setState(State.PENDING);
        this.drawOrder = 1;
        this.duration = 0;
        this.spawnerParent = null;
        this.purchasable = false;
        this.price = 0;
        this.baseOffsetX = 0;
        this.baseOffsetY = 0;
        this.priceImage = null;
        this.priceImageOffsetX = 0;
        this.priceImageOffsetY = 0;
        this.overlappingZelda = false;
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
            float drawX = getImageDrawX();
            float drawY = getImageDrawY();

            if (shouldDrawPrice()) {
                if (hasPriceImage()) {
                    Texture priceImage = getPriceImage();
                    float priceImageX = getPriceImageDrawX(priceImage);
                    float priceImageY = getPriceImageDrawY();

                    batch.draw(priceImage, priceImageX, priceImageY, priceImage.getWidth(), priceImage.getHeight());
                }
                else {
                    batch.draw(getImage(), drawX, drawY, getWidth(), getHeight());
                    drawPrice(batch, drawX, drawY + getHeight() / 3, getWidth(), getHeight());
                }
            }
            else {
                batch.draw(getImage(), drawX, drawY, getWidth(), getHeight());
            }
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
        float bounceOffsetY = bounce * 10f;

        batch.draw(getImage(), getImageDrawX(), getImageDrawY() + bounceOffsetY, getWidth(), getHeight());
    }

    protected float getImageDrawX() {
        return getX() + offsetX + baseOffsetX;
    }

    protected float getImageDrawY() {
        return getY() + offsetY + baseOffsetY;
    }

    protected float getPriceImageDrawX(Texture priceImage) {
        return getX() + priceImageOffsetX + (getWidth() - priceImage.getWidth()) / 2f;
    }

    protected float getPriceImageDrawY() {
        return getY() + priceImageOffsetY;
    }

    protected void drawPrice(SpriteBatch batch, float x, float y, float width, float height) {
        if (hasPriceImage()) {
            return;
        }

        if (!shouldDrawPrice()) {
            return;
        }

        HudNumberRenderer.drawCentered(batch, getPrice(), 0, x, y, width, height);
    }

    public void onPickup(GameManager game) {
        map.addNewActor(new SpriteSparkle(getCenterPointX(), getCenterPointY()));

        if (spawnerParent != null) {
            Gdx.app.log(this.getClass().getSimpleName(), "Storing spawner pickup in save");
            map.getSaveManager().addLocationEntry(spawnerParent.getLocationEntry(), "spawned");
        }
        else if (getLocationEntry() != null) {
            Gdx.app.log(this.getClass().getSimpleName(), "Storing pickup in save");
            map.getSaveManager().addLocationEntry(getLocationEntry(), "picked_up");
        }
    }

    public boolean tryPickup(GameManager game) {
        if (isPurchasable()) {
            if (game.getRubies() < getPrice()) {
                return false;
            }

            game.decreaseRubies(getPrice(), true);
        }

        onPickup(game);
        return true;
    }

    public boolean shouldDrawPrice() {
        if (!isPurchasable()) {
            return false;
        }

        if (!isOverlappingZelda()) {
            return false;
        }

        return duration % 1f < 0.5f;
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

    public void setImage(Texture image, float offsetX, float offsetY) {
        setImage(image);
        setImageOffset(offsetX, offsetY);
    }

    public void setImageOffset(float offsetX, float offsetY) {
        this.baseOffsetX = offsetX;
        this.baseOffsetY = offsetY;
    }

    public Texture getPriceImage() {
        if (priceImage == null) {
            return getImage();
        }

        return priceImage;
    }

    public void setPriceImage(Texture priceImage) {
        this.priceImage = priceImage;
    }

    public void setPriceImage(Texture priceImage, float offsetX, float offsetY) {
        setPriceImage(priceImage);
        setPriceImageOffset(offsetX, offsetY);
    }

    public void setPriceImageOffset(float offsetX, float offsetY) {
        this.priceImageOffsetX = offsetX;
        this.priceImageOffsetY = offsetY;
    }

    protected boolean hasPriceImage() {
        return getPriceImage() != getImage();
    }

    public boolean isPurchasable() {
        return purchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isOverlappingZelda() {
        return overlappingZelda;
    }

    public void setOverlappingZelda(boolean overlappingZelda) {
        this.overlappingZelda = overlappingZelda;
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

    public float getDuration() {
        return duration;
    }
}
