package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// TODO: Set actual original game accurate values
public class SpriteLadder extends Sprite {
    public SpriteLadder() {
        super(ActorType.FRIENDLY, true);
        setImage(World.images.getSpriteLadder());
        setWidth(123);
        setHeight(23);
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (getMap().getSaveManager().hasLocationEntry("s1-2_0")) {
            batch.draw(getImage(), getX(), getY(), getWidth(), getHeight());
        }
    }
}
