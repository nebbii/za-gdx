package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Rectangle implements Actor {
    protected boolean solid;
    protected float searchSpeed = 80f;
    protected float fightingSpeed = 140f;
    protected float hurtDuration = 0f;
    protected float searchDuration = 0f;
    protected State state;
    protected ActorType type;

    protected Rectangle alertBox;
    protected Direction direction;
    protected EnemyState enemyState;

    public enum Direction {
        LEFT,
        DOWN,
        UP,
        RIGHT
    }

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

        float deltaTime = Gdx.graphics.getDeltaTime();

        hurtDuration = Math.max(0f, hurtDuration - deltaTime);

        /*
        switch(enemyState) {
            case FIGHTING:
                break;
            case SEARCHING:
                break;
            default:
                break;
        }
        */
    }

    public void draw(SpriteBatch batch) {
        /*
        if (getState() != State.ACTIVE) return;
        */
    }

    public void onHit(float hitstun) {
        if (hurtDuration > 0) return;
        hurtDuration += hitstun;
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
        searchDuration = MathUtils.random(0.5f, 4.0f);
    }

    public void checkAndSetRandomDirection() {
        searchDuration -= Gdx.graphics.getDeltaTime();;

        if (searchDuration <= 0f) {
            setDirection(getRandomDirection());
            resetDirectionTimer();
        }
    }

    public Direction getRandomDirection() {
        return Direction.values()[MathUtils.random(Direction.values().length - 1)];
    }

    public Direction getDirection() {
        return direction;
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
}
