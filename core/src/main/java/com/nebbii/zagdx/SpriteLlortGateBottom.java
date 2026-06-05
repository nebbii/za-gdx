package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.SpriteLlortGateBottomAnimation;

public class SpriteLlortGateBottom extends Sprite {
    public SpriteLlortGateBottomAnimation animation;

    public SpriteLlortGateBottom() {
        super(ActorType.FRIENDLY, false);
        setWidth(87);
        setHeight(23);

        this.animation = new SpriteLlortGateBottomAnimation(this);
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
