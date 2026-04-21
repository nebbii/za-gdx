package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Npc extends Rectangle implements Actor {
    private boolean solid;
    protected State state;
    protected NpcState npcState;
    protected ActorType type;
    protected int drawOrder;

    public enum NpcState {
        TALKY,
        TALKING,
        DONE
    }

    public Npc(ActorType actorType, boolean solid) {
        setWidth(32);
        setHeight(32);
        setState(State.IDLE);
        setType(actorType);
        this.drawOrder = 2;
        this.solid = solid;
    }

    public void logic() {
        /*
        if (getState() != State.ACTIVE) return;

        switch(npcState) {
        case TALKY:
            break;
        case TALKING:
            break;
        case DONE:
            break;
        default:
            break;
        }
        */
    }

    public void draw(SpriteBatch batch) {
    }

    @Override
    public int getDrawOrder() {
        return drawOrder;
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

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Direction getDirection() {
        return Direction.DOWN;
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
}
