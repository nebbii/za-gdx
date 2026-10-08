package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcLodgeOwnerAnimation;

/*
npc.whiteSteedLodge.unknownActor125 (renamed NpcLodgeOwner, low confidence; one of three White
    Steed Lodge characters at overworld:n11a alongside the existing NpcWaiter, NpcDeerHunter and
    NpcDebblinOfDurod):
    scripts.py (overworld:n11a:3) only has an onLoad handler that spawns the actor; there are no
    playVoiceLine calls anywhere for this actor. Implemented as a basic idle actor with no
    dialogue.
*/
public class NpcLodgeOwner extends Npc {
    public NpcLodgeOwnerAnimation animation;

    public NpcLodgeOwner() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(55);

        this.animation = new NpcLodgeOwnerAnimation(this);
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
