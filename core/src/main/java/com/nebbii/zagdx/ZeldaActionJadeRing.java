package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ZeldaActionJadeRing extends ZeldaAction {
    private Rectangle hitbox;

    public ZeldaActionJadeRing(Actor actor, float x, float y) {
        super(actor, x, y);

        duration = 0.30f;
        solid = false;

        setWidth(48);
        setHeight(48);
        setDamage(actor.getDamage());
        setDirection(actor.getDirection());

        hitbox = new Rectangle();
        hitbox.setWidth(48);
        hitbox.setHeight(48);

        switch(actor.getDirection()) {
        case LEFT:
            setX(actor.getCenterPointX() - actor.getHitbox().getWidth() / 2 - getWidth());
            setY(actor.getCenterPointY() - getHeight() / 2);
            break;
        case DOWN:
            setX(actor.getCenterPointX() - getWidth() / 2);
            setY(actor.getHitbox().getY() - getHeight());
            break;
        case UP:
            setX(actor.getCenterPointX() - getWidth() / 2);
            setY(actor.getHitbox().getY() + actor.getHitbox().getHeight());
            break;
        case RIGHT:
            setX(actor.getCenterPointX() + actor.getHitbox().getWidth() / 2);
            setY(actor.getCenterPointY() - getHeight() / 2);
            break;
        default:
            throw new IllegalStateException(getClass().getSimpleName() + ": Unhandled direction: " + actor.getDirection());
        }
    }

    @Override
    public void logic() {
        super.logic();

        if (!isActive()) return;

        if (stateTime >= duration) {
            setState(State.DEAD);
        }

        stateTime += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void draw(SpriteBatch batch) {
    }

    @Override
    public Rectangle getHitbox() {
        hitbox.setX(getX());
        hitbox.setY(getY());

        return hitbox;
    }

    @Override
    public Rectangle getCollisionBox() {
        return this;
    }

    @Override
    public void onHit() {
    }
}
