package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.SpriteExplosionAnimation;

public class SpriteExplosion extends Sprite {
    public SpriteExplosionAnimation animation;

    public SpriteExplosion(float x, float y) {
        super(ActorType.FRIENDLY, false);
        setWidth(35);
        setHeight(36);
        setX(x - this.width / 2);
        setY(y - this.height / 2);

        this.animation = new SpriteExplosionAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();

        if (animation.isAnimationFinished()) setState(State.DEAD);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
