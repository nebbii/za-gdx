package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ZeldaActionWand extends ZeldaAction {
    public ZeldaActionWand(Zelda zelda, float x, float y) {
        super(zelda, x, y);
        damage = 20;
        switch(zelda.getDirection()) {
        case LEFT:
            setWidth(48);
            setHeight(48);
            setX(zelda.getCenterPointX() - zelda.getWidth() / 2 - this.getWidth());
            setY(zelda.getY());
            setDirection(Direction.LEFT);
            break;
        case DOWN:
            setWidth(64);
            setHeight(44);
            setX(zelda.getCenterPointX() - this.getWidth() / 2);
            setY(zelda.getY() - this.getHeight() / 2);
            setDirection(Direction.DOWN);
            break;
        case UP:
            setWidth(64);
            setHeight(44);
            setX(zelda.getCenterPointX() - this.getWidth() / 2);
            setY(zelda.getCenterPointY() + zelda.getHeight() / 2);
            setDirection(Direction.UP);
            break;
        case RIGHT:
            setWidth(48);
            setHeight(48);
            setX(zelda.getCenterPointX() + zelda.getWidth() / 2);
            setY(zelda.getY());
            setDirection(Direction.RIGHT);
            break;
        default:
            throw new IllegalStateException(getClass().getSimpleName() + ": Unhandled zelda animation state: " + zelda.getAnimState());
        }

        stateTime = 0f;
        duration = 0.10f;
    }

    public void logic() {
        super.logic();

        if (stateTime >= duration) setState(State.DEAD);
        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void onHit() {
    }
}
