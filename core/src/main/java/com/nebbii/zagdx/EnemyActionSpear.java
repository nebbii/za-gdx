package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.animation.EnemySpearAnimation;

public class EnemyActionSpear extends EnemyActionProjectile {
    public EnemySpearAnimation animation;

    private final Direction direction;

    public EnemyActionSpear(Actor actor, float x, float y) {
        super(actor, x, y, 150f, 1.2f);
        this.animation = new EnemySpearAnimation();
        this.direction = actor.getDirection();

        setWidth(10);
        setHeight(10);
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

    public Rectangle getHitbox() {
        switch(direction) {
        case UP:
        case DOWN:
            hitbox.setWidth(8);
            hitbox.setHeight(44);
            break;
        case RIGHT:
        case LEFT:
            hitbox.setWidth(44);
            hitbox.setHeight(8);
            break;
        default:
            hitbox.setWidth(10);
            hitbox.setHeight(10);
            break;
        }

        hitbox.setX(this.x + getCollisionBox().getWidth() / 2 - hitbox.getWidth() / 2);
        hitbox.setY(this.y + getCollisionBox().getHeight() / 2 - hitbox.getHeight() / 2);

        return hitbox;
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
