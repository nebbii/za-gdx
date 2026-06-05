package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.SpriteLlortGateTopAnimation;

public class SpriteLlortGateTop extends Sprite {
    public SpriteLlortGateTopAnimation animation;

    public SpriteLlortGateTop() {
        super(ActorType.FRIENDLY, false);
        setWidth(83);
        setHeight(32);

        this.animation = new SpriteLlortGateTopAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();

        if (map.getSaveManager().hasLocationEntry("s121_0")) {
            setState(State.DEAD);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
