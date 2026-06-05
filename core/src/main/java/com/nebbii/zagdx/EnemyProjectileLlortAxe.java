package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.animation.EnemyLlortAxeAnimation;

public class EnemyProjectileLlortAxe extends EnemyAction {
    float speed;
    public EnemyLlortAxeAnimation animation;

    private Rectangle hitbox;

    public EnemyProjectileLlortAxe(Actor actor, Direction direction) {
        super(actor, actor.getCenterPointX(), actor.getCenterPointY());
        this.animation = new EnemyLlortAxeAnimation();
        this.speed = 200f;
        this.duration = 2.0f;

        solid = true;
        setWidth(10);
        setHeight(10);

        hitbox = new Rectangle();

        hitbox.setWidth(32);
        hitbox.setHeight(32);

        setX(actor.getCenterPointX() - getWidth() / 2f);
        setY(actor.getCenterPointY() - getHeight() / 2f);
        setDamage(40);
        setDirection(direction);
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) return;

        float deltaTime = Gdx.graphics.getDeltaTime();
        float distance = speed * deltaTime;

        setX(getX() + (getDirection() == Direction.LEFT ? -0.5f : 0.5f) * distance);
        setY(getY() - distance);

        if (stateTime >= duration) setState(State.DEAD);
        stateTime += deltaTime;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), getX() + animation.getX(), getY() + animation.getY());
    }

    @Override
    public Rectangle getHitbox() {
        Rectangle collision = getCollisionBox();
        hitbox.setX(collision.x + collision.width / 2f - hitbox.width / 2f);
        hitbox.setY(collision.y + collision.height / 2f - hitbox.height / 2f);

        return hitbox;
    }

    @Override
    public Rectangle getCollisionBox() {
        return this;
    }
}
