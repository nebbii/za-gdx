package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcHorseAnimation;

public class NpcHorse extends Npc {
    public NpcHorseAnimation animation;

    public NpcHorse() {
        super(ActorType.FRIENDLY, true);
        setWidth(104);
        setHeight(91);

        this.animation = new NpcHorseAnimation(this);
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
