package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ZeldaActionJadeRing extends ZeldaAction {
    private Rectangle hitbox;
    private float offsetX; // animation specific
    private float offsetY;
    private float speed;

    public ZeldaActionJadeRing(Actor actor, float x, float y) {
        super(actor, x, y);

        duration = 1.2f;
        speed = 150f;
        solid = true;

        setWidth(10);
        setHeight(10);
        setDamage(actor.getDamage());
        setDirection(actor.getDirection());

        hitbox = new Rectangle();
        hitbox.setWidth(39);
        hitbox.setHeight(39);

        switch(getDirection()) {
        case LEFT:
            offsetX = -15;
            offsetY = -15;
            setX(actor.getCenterPointX() - actor.getHitbox().getWidth() / 2 - this.getWidth());
            setY(actor.getCenterPointY() + actor.getHitbox().getHeight() / 3);
            break;
        case DOWN:
            offsetX = -15;
            offsetY = -10;
            setX(actor.getCenterPointX() - this.getWidth() / 2);
            setY(actor.getHitbox().getY() - this.getHeight() / 2);
            break;
        case UP:
            offsetX = -15;
            offsetY = 0;
            setX(actor.getCenterPointX() - this.getWidth() / 2);
            setY(actor.getCenterPointY() + actor.getHitbox().getHeight());
            break;
        case RIGHT:
            offsetX = -15;
            offsetY = -15;
            setX(actor.getCenterPointX() + actor.getHitbox().getWidth());
            setY(actor.getCenterPointY() + actor.getHitbox().getHeight() / 3);
            break;
        default:
            throw new IllegalStateException(getClass().getSimpleName() + ": Unhandled direction: " + actor.getDirection());
        }
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) return;

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
            throw new IllegalStateException(getClass().getSimpleName() + "Unhandled direction");
        }

        if (stateTime >= duration) setState(State.DEAD);
        stateTime += deltaTime;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (getImage() == null) {
            switch(getDirection()) {
            case LEFT:
                setImage(World.images.getFriendlyJadeRing()[3]);
                break;
            case DOWN:
                setImage(World.images.getFriendlyJadeRing()[2]);
                break;
            case UP:
                setImage(World.images.getFriendlyJadeRing()[0]);
                break;
            case RIGHT:
                setImage(World.images.getFriendlyJadeRing()[1]);
                break;
            default:
                throw new IllegalStateException(getClass().getSimpleName() + ": Unhandled direction: " + getDirection());
            }
        }
        batch.draw(getImage(), getX() + offsetX, getY() + offsetY);
    }

    @Override
    public Rectangle getHitbox() {
        hitbox.setX(this.x + getCollisionBox().getWidth() / 2 - hitbox.getWidth() / 2 );
        hitbox.setY(this.y + getCollisionBox().getHeight() / 2 - hitbox.getHeight() / 2);

        return hitbox;
    }

    @Override
    public Rectangle getCollisionBox() {
        return this;
    }
}
