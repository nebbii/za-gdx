package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcRandomCapeGuyAnimation;

/*
npc.randomCapeGuy:
    scripts.py (overworld:l13:0) only spawns and animates the actor on load; there is no
    dialogue. Implemented as a plain idle actor.
*/
public class NpcRandomCapeGuy extends Npc {
    public NpcRandomCapeGuyAnimation animation;

    public NpcRandomCapeGuy() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(51);

        this.animation = new NpcRandomCapeGuyAnimation(this);
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
