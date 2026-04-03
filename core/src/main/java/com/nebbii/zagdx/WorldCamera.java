package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

/**
 * Tracks zelda throughout the currently loaded world
 */
public class WorldCamera {
    private OrthographicCamera camera;
    private MapManager map;

    private float targetX;
    private float targetY;

    public boolean targetJustUpdated;

	public WorldCamera(OrthographicCamera camera, MapManager map) {
        this.camera = camera;
        this.map = map;
        this.targetJustUpdated = false;
    }

    public void logic() {
        updateTargetToZeldaCell();
        shiftToTarget(targetX, targetY);
        camera.update();
    }

    public void resetPosition() {
        float x = World.getCenterOfCellByX(map.getZelda().getX());
        float y = World.getCenterOfCellByY(map.getZelda().getY());
        setTargetX(x);
        setTargetY(y);

        camera.position.set(x, y, 0);
    }

    // LLM direction math for scrolling the camera to a new spot
    public void shiftToTarget(float targetX, float targetY) {
        Vector2 current = new Vector2(camera.position.x, camera.position.y);
        Vector2 target = new Vector2(targetX, targetY);
        float speed = 300f;

        Vector2 direction = target.sub(current);

        if (direction.len() > 1f) {
            direction.nor().scl(speed * Gdx.graphics.getDeltaTime());
            camera.position.add(direction.x, direction.y, 0);
        } else {
            camera.position.set(targetX, targetY, 0);
        }
    }

    public void updateTargetToZeldaCell() {
        setTargetX(
            World.getCenterOfCellByX(
               map.getZelda().getCenterPointX()));
        setTargetY(
            World.getCenterOfCellByY(
                map.getZelda().getCenterPointY()));
    }

    public boolean isTransitioning() {
        return getTargetX() != camera.position.x
            || getTargetY() != camera.position.y;
    }

    public OrthographicCamera getCamera() {
		return camera;
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

    public int getTargetCellColumn() {
        return World.convertWorldXToCellColumn(getTargetX());
    }

    public int getTargetCellRow() {
        return World.convertWorldYToCellRow(getTargetY());
    }
}
