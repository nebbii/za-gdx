package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcPickpocketAnimation;

// TODO: scripts.py (overworld:l27a:1) has more complex behavior not implemented here: on first
// touch, it deducts 25 rupees directly from the player's purse (gated by a local save flag so it
// only happens once). There is no voice line for this actor at all. Implemented as a basic idle
// stub rather than wiring up the rupee-stealing mechanic, since there's no equivalent player
// currency hook on Npc yet.
public class NpcPickpocket extends Npc {
    public NpcPickpocketAnimation animation;

    public NpcPickpocket() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(55);

        this.animation = new NpcPickpocketAnimation(this);
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
