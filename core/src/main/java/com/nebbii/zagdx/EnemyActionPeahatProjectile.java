package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.animation.EnemyPeahatProjectileAnimation;

public class EnemyActionPeahatProjectile extends EnemyActionProjectile {
    public EnemyPeahatProjectileAnimation animation;

    private static final float PROJECTILE_SIZE = 20f;
    private static final float HITBOX_SIZE = 20f;

    public EnemyActionPeahatProjectile(Actor actor, float x, float y) {
        this(actor, x, y, 0f, 0f);
    }

    public EnemyActionPeahatProjectile(Actor actor, float x, float y, float offsetX, float offsetY) {
        super(actor, x, y, 150f, 1.2f);
        this.animation = new EnemyPeahatProjectileAnimation();
        this.animation.setProjectileOffset(offsetX, offsetY);

        setWidth(PROJECTILE_SIZE);
        setHeight(PROJECTILE_SIZE);
        alignToSourceActor(actor);

        setDamage(46);
        hitbox.setWidth(HITBOX_SIZE);
        hitbox.setHeight(HITBOX_SIZE);
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) return;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), this.getX() + animation.getX(), this.getY() + animation.getY());
    }

    public void setProjectileOffset(float offsetX, float offsetY) {
        animation.setProjectileOffset(offsetX, offsetY);
    }

    @Override
    public Rectangle getHitbox() {
        hitbox.setX(this.x + getCollisionBox().getWidth() / 2 - hitbox.getWidth() / 2);
        hitbox.setY(this.y + getCollisionBox().getHeight() / 2 - hitbox.getHeight() / 2);

        return hitbox;
    }

    @Override
    public Rectangle getCollisionBox() {
        return this;
    }

    private void alignToSourceActor(Actor actor) {
        switch(actor.getDirection()) {
        case LEFT:
            setX(actor.getCenterPointX() - actor.getHitbox().getWidth() / 2 - getWidth());
            setY(actor.getCenterPointY() - getHeight() / 2);
            break;
        case DOWN:
            setX(actor.getCenterPointX() - getWidth() / 2);
            setY(actor.getHitbox().getY() - getHeight() / 2);
            break;
        case UP:
            setX(actor.getCenterPointX() - getWidth() / 2);
            setY(actor.getCenterPointY() + actor.getHitbox().getHeight());
            break;
        case RIGHT:
            setX(actor.getCenterPointX() + actor.getHitbox().getWidth());
            setY(actor.getCenterPointY() - getHeight() / 2);
            break;
        default:
            break;
        }
    }
}
