package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcGhostFarmerAnimation;

// TODO: scripts.py (overworld:j17) has more complex behavior not implemented here: this cell
// contains a 3-stage chain of npc_ghostFarmer actors (descriptions 0, 1, 2), each playing its own
// voice line on first touch and spawning the next ghost in the chain via cast[] once the item is
// interacted with. Implemented as a basic idle stub (non-solid, it's a ghost) rather than
// reverse-engineering that whole chain.
public class NpcGhostFarmer extends Npc {
    public NpcGhostFarmerAnimation animation;

    public NpcGhostFarmer() {
        super(ActorType.FRIENDLY, false);
        setWidth(48);
        setHeight(48);

        this.animation = new NpcGhostFarmerAnimation(this);
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
