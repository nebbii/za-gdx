package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Rectangle implements Actor {
    protected boolean solid;
    protected MapManager map;
    protected String locationEntry;

    protected int health;
    protected int defense;
    protected int damage;
    protected int bonusDamage;

    protected float speed;
    protected float searchDuration;
    protected float searchDurationCap;
    protected float targetX;
    protected float targetY;

    protected float knockback;
    protected Direction hurtDirection;
    protected boolean hurtWeakness = true;

    protected State state;
    protected ActorType type;
    protected int drawOrder;

    protected Rectangle alertBox;
    protected Direction direction;
    protected EnemyState enemyState;

    public enum EnemyState {
        SEARCH,
        FIGHT,
        STOP
    }

    public Enemy(ActorType actorType, boolean solid) {
        setWidth(32);
        setHeight(32);
        setState(State.IDLE);
        setType(actorType);
        this.drawOrder = 0;
        this.solid = solid;
        this.damage = 0;
        this.defense = 0;
        this.bonusDamage = 0;

        this.direction = getRandomDirection();
        resetDirectionTimer();
        setEnemyState(EnemyState.SEARCH);

        this.alertBox = new Rectangle();
        this.alertBox.setWidth(200);
        this.alertBox.setHeight(150);
    }

    public void logic() {
        if (getState() != State.ACTIVE) return;
        if (health <= 0) onDeath();

        knockback = Math.max(0f, knockback - Gdx.graphics.getDeltaTime());

        if (knockback > 0) {
            if (hurtWeakness) {
                movePushback();
            }
        }
        else {
            switch(enemyState) {
            case SEARCH:
                searchDurationCap = 4.0f;
                refreshDirection();
                move();
                break;
            case FIGHT:
                searchDurationCap = 0.5f;
                refreshDirection();
                move();
                break;
            case STOP:
            default:
                break;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        /*
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
        if (knockback > 0) endDrawFlashOverlay(batch);
        */
    }

    @Override
    public int getDrawOrder() {
        return drawOrder;
    }

    protected void move() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        switch(getDirection()) {
            case LEFT:
                setX(getX() - speed * deltaTime);
                break;
            case DOWN:
                setY(getY() - speed * deltaTime);
                break;
            case UP:
                setY(getY() + speed * deltaTime);
                break;
            case RIGHT:
                setX(getX() + speed * deltaTime);
                break;
            default:
                throw new IllegalStateException(this.getClass() + "->move(): Unhandled movement state" + getDirection());
        }
    }

    protected void movePushback() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        switch(getHurtDirection()) {
            case LEFT:
                setX(getX() - 350f * deltaTime);
                break;
            case DOWN:
                setY(getY() - 350f * deltaTime);
                break;
            case UP:
                setY(getY() + 350f * deltaTime);
                break;
            case RIGHT:
                setX(getX() + 350f * deltaTime);
                break;
            default:
                throw new IllegalStateException(this.getClass() + "->movePushback(): Unhandled movement state" + getDirection());
        }
    }

    public void onHit(int damage, float knockback) {
        if (this.knockback > 0) return;
        decreaseHealth(damage);
        increaseKnockback(knockback);
    }

    public void onDeath() {
        setState(State.DEAD);
        map.getSaveManager().addLocationEntry(locationEntry, "dead");
    }

    public void drawFlashOverlay(SpriteBatch batch, boolean weakness) {
        Color currentColor;
        float t = knockback % 0.12f;

        if (weakness) {
            if (t < 0.04f) {
                currentColor = Color.YELLOW;
            } else if (t < 0.08f) {
                currentColor = Color.GRAY;
            } else {
                currentColor = Color.RED;
            }
        }
        else {
            if (t < 0.04f) {
                currentColor = Color.BLUE;
            } else if (t < 0.08f) {
                currentColor = Color.WHITE;
            } else {
                currentColor = Color.BLUE;
            }
        }

        WorldShaders.beginHitFlashShader(batch, currentColor);
    }

    public void endDrawFlashOverlay(SpriteBatch batch) {
        WorldShaders.endHitFlashShader(batch);
    }

    private void resetDirectionTimer() {
        searchDuration = MathUtils.random(0.5f, searchDurationCap);
    }

    public void refreshDirection() {
        searchDuration -= Gdx.graphics.getDeltaTime();;

        if (searchDuration <= 0f) {
            switch(enemyState) {
            case SEARCH:
                setDirection(getRandomDirection());
                break;
            case FIGHT:
                changeDirectionTowardsTarget();
                break;
            default:
            }

            resetDirectionTimer();
        }
    }

    public void changeDirectionTowardsTarget() {
        float relativeX = targetX - this.x;
        float relativeY = targetY - this.y;

        if (Math.abs(relativeX) > Math.abs(relativeY)) {
            if (relativeX < 0) {
                setDirection(Direction.LEFT);
            }
            else if (relativeX > 0) {
                setDirection(Direction.RIGHT);
            }
        }
        else {
            if (relativeY < 0) {
                setDirection(Direction.DOWN);
            }
            else if (relativeY > 0) {
                setDirection(Direction.UP);
            }
        }
    }

    public Direction getRandomDirection() {
        return Direction.values()[MathUtils.random(Direction.values().length - 1)];
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction getHurtDirection() {
        return hurtDirection;
    }

    public void setHurtDirection(Direction hurtDirection) {
        this.hurtDirection = hurtDirection;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Rectangle getHitbox() {
        return this;
    }

    public Rectangle getAlertBox() {
        alertBox.setX(getCenterPointX()-alertBox.getWidth()/2);
        alertBox.setY(getCenterPointY()-alertBox.getHeight()/2);

        return alertBox;
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

    public EnemyState getEnemyState() {
        return enemyState;
    }

    public void setEnemyState(EnemyState enemyState) {
        this.enemyState = enemyState;
    }

    public boolean isSolid() {
        return solid;
    }

    public void increaseHealth(int amount) {
        setHealth(getHealth() + amount);
    }

    public void decreaseHealth(int amount) {
        Gdx.app.log(this.getClass().getSimpleName(), "decreasing health (" + getHealth() + ") by " + amount);

        setHealth(getHealth() - amount);
    }

    public void increaseKnockback(float amount) {
        this.knockback += amount;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public float getTargetX() {
        return targetX;
    }

    public void setTargetX(float targetX) {
        this.targetX = targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public void setTargetY(float targetY) {
        this.targetY = targetY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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
