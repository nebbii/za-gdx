package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class EnemyActionProjectile extends EnemyAction {
    float speed;

    int[] defaultOffset;

    protected Rectangle hitbox;

    public EnemyActionProjectile(Actor actor, float x, float y, float speed, float duration) {
        super(actor, x, y);
        this.duration = duration;
        this.speed = speed;

        solid = true;

        setWidth(10);
        setHeight(10);
        setDamage(10);
        hitbox = new Rectangle();
        hitbox.setWidth(16);
        hitbox.setHeight(16);

        switch(actor.getDirection()) {
        case LEFT:
            setX(actor.getCenterPointX() - actor.getHitbox().getWidth() / 2 - this.getWidth());
            setY(actor.getCenterPointY());
            break;
        case DOWN:
            setX(actor.getCenterPointX() - this.getWidth() / 2);
            setY(actor.getHitbox().getY() - this.getHeight() / 2);
            break;
        case UP:
            setX(actor.getCenterPointX() - this.getWidth() / 2);
            setY(actor.getCenterPointY() + actor.getHitbox().getHeight());
            break;
        case RIGHT:
            setX(actor.getCenterPointX() + actor.getHitbox().getWidth());
            setY(actor.getCenterPointY());
            break;
        default:
            break;
        }

        setDirection(enemy.getDirection());
    }

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
                throw new IllegalStateException(getClass().getSimpleName() + "Unhandled direction somehow?!");
        }

        if (stateTime >= duration) setState(State.DEAD);
        stateTime += deltaTime;
    }

    @Override
    public void draw(SpriteBatch batch) {
        //batch.draw(animation.playCurrentAnimation(), this.getX() + animation.getX(), this.getY() + animation.getY());
    }

    public Rectangle getHitbox() {
        hitbox.setX(this.x + getCollisionBox().getWidth() / 2 - hitbox.getWidth() / 2 );
        hitbox.setY(this.y + getCollisionBox().getHeight() / 2 - hitbox.getHeight() / 2);

        return hitbox;
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
