package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcRebelLeftAnimation;

/*
npc.rebel.left:
    scripts.py (overworld:z13:1) only spawns and animates the actor on load; the paired
    npc.rebel.right actor in the same cell (descIndex 0) carries the dialogue for this pair.
    Implemented as a plain idle actor.
*/
public class NpcRebelLeft extends Npc {
    public NpcRebelLeftAnimation animation;

    public NpcRebelLeft() {
        super(ActorType.FRIENDLY, true);
        setWidth(64);
        setHeight(45);

        this.animation = new NpcRebelLeftAnimation(this);
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
