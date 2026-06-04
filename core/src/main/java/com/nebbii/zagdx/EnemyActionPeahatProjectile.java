package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.animation.EnemyPeahatProjectileAnimation;

public class EnemyActionPeahatProjectile extends EnemyActionProjectile {
    public EnemyPeahatProjectileAnimation animation;

    private static final float PROJECTILE_SIZE = 20f;
    private static final float HITBOX_SIZE = 20f;

    private float velocityX;
    private float velocityY;

    public EnemyActionPeahatProjectile(Actor actor, float x, float y) {
        super(actor, x, y, 150f, 1.2f);

        this.animation = new EnemyPeahatProjectileAnimation();
        this.animation.setProjectileOffset(0, 0);

        setWidth(PROJECTILE_SIZE);
        setHeight(PROJECTILE_SIZE);

        aimAtZelda(actor);

        setDamage(46);

        hitbox.setWidth(HITBOX_SIZE);
        hitbox.setHeight(HITBOX_SIZE);
    }

    @Override
    public void logic() {
        if (!isActive()) return;

        float deltaTime = Gdx.graphics.getDeltaTime();

        setX(getX() + velocityX * deltaTime);
        setY(getY() + velocityY * deltaTime);

        stateTime += deltaTime;

        if (stateTime >= duration) {
            setState(State.DEAD);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
            animation.playCurrentAnimation(),
            getX() + animation.getX(),
            getY() + animation.getY()
        );
    }

    public void setProjectileOffset(float offsetX, float offsetY) {
        animation.setProjectileOffset(offsetX, offsetY);
    }

    @Override
    public Rectangle getHitbox() {
        hitbox.setX(getX() + getWidth() / 2f - hitbox.getWidth() / 2f);
        hitbox.setY(getY() + getHeight() / 2f - hitbox.getHeight() / 2f);

        return hitbox;
    }

    @Override
    public Rectangle getCollisionBox() {
        return this;
    }

    private void aimAtZelda(Actor actor) {
        Zelda zelda = actor.getMap().getZelda();

        if (zelda == null) {
            return;
        }

        float deltaX = zelda.getCenterPointX() - getCenterPointX();
        float deltaY = zelda.getCenterPointY() - getCenterPointY();

        float length = (float)Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        velocityX = deltaX / length * speed;
        velocityY = deltaY / length * speed;

        setDirectionFromVelocity();
    }

    private void setDirectionFromVelocity() {
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            setDirection(velocityX < 0f ? Direction.LEFT : Direction.RIGHT);
        }
        else {
            setDirection(velocityY < 0f ? Direction.DOWN : Direction.UP);
        }
    }
}
