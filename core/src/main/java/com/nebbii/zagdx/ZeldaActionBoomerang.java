package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.animation.ActionBoomerangAnimation;

public class ZeldaActionBoomerang extends ZeldaAction {
    public ActionBoomerangAnimation animation;

    int[] defaultOffset;

	public ZeldaActionBoomerang(Zelda zelda, float x, float y) {
		super(zelda, x, y);
        damage = 20f; // TODO: scale with amount of shrines cleared/celestial stones obtained

        this.animation = new ActionBoomerangAnimation();

        setWidth(31);
        setHeight(31);

        switch(zelda.getDirection()) {
        case LEFT:
            setX(zelda.getCenterPointX() - zelda.getWidth() / 2 - this.getWidth());
            setY(zelda.getY());
            setDirection(Direction.LEFT);
            break;
        case DOWN:
            setX(zelda.getCenterPointX() - this.getWidth() / 2);
            setY(zelda.getY() - this.getHeight() / 2);
            setDirection(Direction.DOWN);
            break;
        case UP:
            setX(zelda.getCenterPointX() - this.getWidth() / 2);
            setY(zelda.getCenterPointY() + zelda.getHeight() / 2);
            setDirection(Direction.UP);
            break;
        case RIGHT:
            setX(zelda.getCenterPointX() + zelda.getWidth() / 2);
            setY(zelda.getY());
            setDirection(Direction.RIGHT);
            break;
        default:
            break;
        }
	}

	public void logic() {
        super.logic();

        switch(getDirection()) {
			case LEFT:
				break;
			case DOWN:
				break;
			case UP:
				break;
			case RIGHT:
				break;
			default:
                throw new IllegalStateException(getClass().getSimpleName() + "Unhandled direction somehow?!");
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), this.getX() + animation.getX(), this.getY() + animation.getY());
    }
}
