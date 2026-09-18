package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcPurpleStallOwnerAnimation;

// TODO: scripts.py (underworld:s609:0) has more complex behavior not implemented here: on load,
// while save[Ticket_RedKnight] == 0, it plays soundId=0 and spawns two companion actors
// (cast[2]/cast[3], a knight and an admission ticket item) which despawn once the ticket is
// purchased; the cell also gates the player's spawn side/position on a respawn-cell flag.
// Implemented as a basic idle stub rather than reverse-engineering that spawn/purchase chain.
public class NpcPurpleStallOwner extends Npc {
    public NpcPurpleStallOwnerAnimation animation;

    public NpcPurpleStallOwner() {
        super(ActorType.FRIENDLY, true);
        setWidth(52);
        setHeight(42);

        this.animation = new NpcPurpleStallOwnerAnimation(this);
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
