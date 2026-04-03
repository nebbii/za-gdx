package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ZeldaActionWand extends ZeldaAction {
	public ZeldaActionWand(Zelda zelda, float x, float y) {
		super(zelda, x, y);
        switch(zelda.getAnimState()) {
        case ATTACKLEFT:
            setWidth(48);
            setHeight(48);
            setX(zelda.getCenterPointX() - zelda.getWidth() / 2 - this.getWidth());
            setY(zelda.getCenterPointY() - this.getHeight() / 6);
            break;
        case ATTACKDOWN:
            setWidth(64);
            setHeight(44);
            setX(zelda.getCenterPointX() - this.getWidth() / 2);
            setY(zelda.getY() - this.getHeight() + zelda.getHeight() + zelda.getHeight() / 2);
            break;
        case ATTACKUP:
            setWidth(64);
            setHeight(44);
            setX(zelda.getCenterPointX() - this.getWidth() / 2);
            setY(zelda.getCenterPointY() + zelda.getHeight() / 2);
            break;
        case ATTACKRIGHT:
            setWidth(48);
            setHeight(48);
            setX(zelda.getCenterPointX() + zelda.getWidth() / 2);
            setY(zelda.getCenterPointY() - this.getHeight() / 6);
            break;
        default:
            throw new IllegalStateException("ZeldaActionWand(): Unhandled zelda animation state: " + zelda.getAnimState());
        }

        duration = 0.15f;
	}

	public void logic() {
        super.logic();
    }
}
