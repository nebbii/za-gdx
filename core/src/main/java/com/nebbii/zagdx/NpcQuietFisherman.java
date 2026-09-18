package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcQuietFishermanAnimation;

/*
npc.unknownActor116 (renamed NpcQuietFisherman):
    scripts.py (overworld:m27:0) only spawns and animates on load; it has no dialogue of its
    own and no touch/interact hooks at all. Implemented as a plain idle actor.
*/
public class NpcQuietFisherman extends Npc {
    public NpcQuietFishermanAnimation animation;

    public NpcQuietFisherman() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(51);

        this.animation = new NpcQuietFishermanAnimation(this);
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
