package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Rectangle implements Actor {
    protected boolean solid;

    protected float health;

    protected float searchSpeed;
    protected float fightingSpeed;
    protected float searchDuration;
    protected float searchDurationCap;
    protected float targetX;
    protected float targetY;

    protected float hurtDuration;
    protected Direction hurtDirection;

    protected State state;
    protected ActorType type;

    protected Rectangle alertBox;
    protected Direction direction;
    protected EnemyState enemyState;

    public enum EnemyState {
        SEARCHING,
        FIGHTING
    }

    public Enemy(ActorType actorType, boolean solid) {
        setWidth(32);
        setHeight(32);
        setState(State.IDLE);
        setType(actorType);
        this.solid = solid;

        this.direction = getRandomDirection();
        resetDirectionTimer();
        setEnemyState(EnemyState.SEARCHING);

        this.alertBox = new Rectangle();
        this.alertBox.setWidth(150);
        this.alertBox.setHeight(150);
    }

    public void logic() {
        if (getState() != State.ACTIVE) return;
        if (health <= 0) onDeath();

        float deltaTime = Gdx.graphics.getDeltaTime();

        hurtDuration = Math.max(0f, hurtDuration - deltaTime);

        if (hurtDuration > 0) {
            movePushback();
        }
        else {
            switch(enemyState) {
                case SEARCHING:
                    searchDurationCap = 4.0f;
                    refreshDirection();
                    move();
                    break;
                case FIGHTING:
                    searchDurationCap = 0.5f;
                    refreshDirection();
                    move();
                    break;
                default:
                    break;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        /*
        if (hurtDuration > 0) drawFlashOverlay(batch);
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
        if (hurtDuration > 0) endDrawFlashOverlay(batch);
        */
    }

    protected void move() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        switch(getDirection()) {
            case LEFT:
                setX(getX() - searchSpeed * deltaTime);
                break;
            case DOWN:
                setY(getY() - searchSpeed * deltaTime);
                break;
            case UP:
                setY(getY() + searchSpeed * deltaTime);
                break;
            case RIGHT:
                setX(getX() + searchSpeed * deltaTime);
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

    public void onHit(float damage, float hitstun) {
        if (hurtDuration > 0) return;
        hurtDuration += hitstun;
        health -= damage;
    }

    public void onDeath() {
        // TODO: death animation
        setState(State.DEAD);
    }

    public void drawFlashOverlay(SpriteBatch batch) {
        Color currentColor;
        float t = hurtDuration % 0.12f;

        if (t < 0.04f) {
            currentColor = Color.YELLOW;
        } else if (t < 0.08f) {
            currentColor = Color.GRAY;
        } else {
            currentColor = Color.RED;
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
            case SEARCHING:
                setDirection(getRandomDirection());
                break;
            case FIGHTING:
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

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
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

    public float getSearchSpeed() {
        return searchSpeed;
    }

    public void setSearchSpeed(float searchSpeed) {
        this.searchSpeed = searchSpeed;
    }
}
