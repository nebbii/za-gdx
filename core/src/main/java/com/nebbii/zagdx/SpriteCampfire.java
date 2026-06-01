package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.SpriteCampfireAnimation;

public class SpriteCampfire extends Sprite {
    public SpriteCampfireAnimation animation;

    public SpriteCampfire() {
        super(ActorType.FRIENDLY, false);
        setWidth(19);
        setHeight(18);

        this.animation = new SpriteCampfireAnimation(this);
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
