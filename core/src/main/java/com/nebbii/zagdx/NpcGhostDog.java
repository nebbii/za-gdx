package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcGhostDogAnimation;

// TODO: scripts.py (overworld:s6:1) plays its voice line from Cell.onEntry rather than from this
// actor's own onLoad/onTouch hooks, so it reads as ambient cell narration rather than an
// interaction the NPC itself drives. Implemented as a basic idle stub (non-solid, it's a ghost)
// rather than wiring up cell-level dialogue that this codebase has no equivalent hook for yet.
public class NpcGhostDog extends Npc {
    public NpcGhostDogAnimation animation;

    public NpcGhostDog() {
        super(ActorType.FRIENDLY, false);
        setWidth(64);
        setHeight(50);

        this.animation = new NpcGhostDogAnimation(this);
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
