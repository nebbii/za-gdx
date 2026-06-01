package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.SpriteSparkleAnimation;

public class SpriteSparkle extends Sprite {
    public SpriteSparkleAnimation animation;

    public SpriteSparkle(float x, float y) {
        super(ActorType.FRIENDLY, false);
        setWidth(31);
        setHeight(32);
        setX(x - this.width / 2);
        setY(y - this.height / 2);

        this.animation = new SpriteSparkleAnimation(this);
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
