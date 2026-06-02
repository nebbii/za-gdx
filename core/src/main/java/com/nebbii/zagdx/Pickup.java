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

    public Pickup() {
        setType(ActorType.PICKUP);
        setState(State.PENDING);
        this.drawOrder = 1;
        this.duration = 0;
        this.spawnerParent = null;
        this.purchasable = false;
        this.price = 0;
        this.priceImage = null;
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
            float drawX = getX() + offsetX + baseOffsetX;
            float drawY = getY() + offsetY + baseOffsetY;

            batch.draw(getImage(), drawX, drawY, getWidth(), getHeight());

            if (hasPriceImage()) {
                if (shouldDrawPrice()) {
                    Texture priceImage = getPriceImage();
                    float priceImageX = drawX + (getWidth() - priceImage.getWidth()) / 2f;

                    batch.draw(priceImage, priceImageX, drawY, priceImage.getWidth(), priceImage.getHeight());
                }
            }
            else {
                drawPrice(batch, drawX, drawY + getHeight() / 3, getWidth(), getHeight());
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
        float offsetY = bounce * 10f;

        batch.draw(getImage(), getX(), getY() + offsetY, getWidth(), getHeight());
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
        Gdx.app.log(this.getClass().getSimpleName(), "Storing pickup in save");

        map.addNewActor(new SpriteSparkle(getCenterPointX(), getCenterPointY()));

        if (spawnerParent != null) {
            map.getSaveManager().addLocationEntry(spawnerParent.getLocationEntry(), "spawned");
        }
        else if (getLocationEntry() != null) {
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

    boolean shouldDrawPrice() {
        return shouldDrawPrice(duration);
    }

    boolean shouldDrawPrice(float elapsedTime) {
        if (!isPurchasable()) {
            return false;
        }

        if (!isOverlappingZelda()) {
            return false;
        }

        return elapsedTime % 1f < 0.5f;
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

    public Texture getPriceImage() {
        if (priceImage == null) {
            return getImage();
        }

        return priceImage;
    }

    public void setPriceImage(Texture priceImage) {
        this.priceImage = priceImage;
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
}
