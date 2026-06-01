package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.animation.EnemySpearAnimation;

public class EnemyActionSpear extends EnemyActionProjectile {
    public EnemySpearAnimation animation;

    private static final float SPEAR_LENGTH = 51f;
    private static final float SPEAR_THICKNESS = 8f;
    private static final float TIP_BOX_SIZE = 10f;
    private static final float CENTER_TO_TIP = 21f;

    private final Direction direction;
    private final Rectangle collisionBox;

    public EnemyActionSpear(Actor actor, float x, float y) {
        super(actor, x, y, 100f, 1.2f);
        this.animation = new EnemySpearAnimation();
        this.direction = actor.getDirection();
        this.collisionBox = new Rectangle();

        setWidth(TIP_BOX_SIZE);
        setHeight(TIP_BOX_SIZE);
        setDamage(40);
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) return;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(direction), this.getX() + animation.getX(), this.getY() + animation.getY());
    }

    @Override
    public Rectangle getHitbox() {
        Rectangle collision = getCollisionBox();

        switch(direction) {
        case UP:
            hitbox.setWidth(SPEAR_THICKNESS);
            hitbox.setHeight(SPEAR_LENGTH);
            hitbox.setX(collision.x + collision.width / 2f - hitbox.width / 2f);
            hitbox.setY(collision.y + collision.height - hitbox.height);
            break;
        case RIGHT:
            hitbox.setWidth(SPEAR_LENGTH);
            hitbox.setHeight(SPEAR_THICKNESS);
            hitbox.setX(collision.x + collision.width - hitbox.width);
            hitbox.setY(collision.y + collision.height / 2f - hitbox.height / 2f);
            break;
        case DOWN:
            hitbox.setWidth(SPEAR_THICKNESS);
            hitbox.setHeight(SPEAR_LENGTH);
            hitbox.setX(collision.x + collision.width / 2f - hitbox.width / 2f);
            hitbox.setY(collision.y);
            break;
        case LEFT:
            hitbox.setWidth(SPEAR_LENGTH);
            hitbox.setHeight(SPEAR_THICKNESS);
            hitbox.setX(collision.x);
            hitbox.setY(collision.y + collision.height / 2f - hitbox.height / 2f);
            break;
        default:
            hitbox.setWidth(TIP_BOX_SIZE);
            hitbox.setHeight(TIP_BOX_SIZE);
            hitbox.setX(collision.x);
            hitbox.setY(collision.y);
            break;
        }

        return hitbox;
    }

    @Override
    public Rectangle getCollisionBox() {
        collisionBox.setWidth(TIP_BOX_SIZE);
        collisionBox.setHeight(TIP_BOX_SIZE);

        switch(direction) {
        case UP:
            collisionBox.setX(this.x);
            collisionBox.setY(this.y + CENTER_TO_TIP);
            break;
        case RIGHT:
            collisionBox.setX(this.x + CENTER_TO_TIP);
            collisionBox.setY(this.y);
            break;
        case DOWN:
            collisionBox.setX(this.x);
            collisionBox.setY(this.y - CENTER_TO_TIP);
            break;
        case LEFT:
            collisionBox.setX(this.x - CENTER_TO_TIP);
            collisionBox.setY(this.y);
            break;
        default:
            collisionBox.setX(this.x);
            collisionBox.setY(this.y);
            break;
        }

        return collisionBox;
    }
}
