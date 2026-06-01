package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.SpriteLlortLaserAnimation;

public class SpriteLlortLaser extends Sprite {
    public SpriteLlortLaserAnimation animation;

    public SpriteLlortLaser() {
        super(ActorType.FRIENDLY, false);
        setWidth(103);
        setHeight(17);

        this.animation = new SpriteLlortLaserAnimation(this);

    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
