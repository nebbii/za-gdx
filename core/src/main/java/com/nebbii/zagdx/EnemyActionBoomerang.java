package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.animation.EnemyBoomerangAnimation;

public class EnemyActionBoomerang extends EnemyActionProjectile {
    public EnemyBoomerangAnimation animation;

    int[] defaultOffset;

    public EnemyActionBoomerang(Actor actor, float x, float y) {
        super(actor, x, y, 150f, 1.2f);
        this.animation = new EnemyBoomerangAnimation();

        setWidth(10);
        setHeight(10);
        setDamage(40);
        hitbox.setWidth(28);
        hitbox.setHeight(31);
    }

    public void logic() {
        super.logic();
        if (!isActive()) return;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), this.getX() + animation.getX(), this.getY() + animation.getY());
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
