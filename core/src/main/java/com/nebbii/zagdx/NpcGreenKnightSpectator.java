package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcGreenKnightSpectatorAnimation;

// TODO: scripts.py (underworld:s622:0) has more complex behavior not implemented here: onLoad only
// spawns this spectator while the red/green/blue knight defeat save flags are not all set, and the
// cell's onTouchTrigger gates a return value on that same combined flag. Implemented as a basic
// idle stub rather than reverse-engineering the save-flag gating.
public class NpcGreenKnightSpectator extends Npc {
    public NpcGreenKnightSpectatorAnimation animation;

    public NpcGreenKnightSpectator() {
        super(ActorType.FRIENDLY, true);
        setWidth(40);
        setHeight(44);

        this.animation = new NpcGreenKnightSpectatorAnimation(this);
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
