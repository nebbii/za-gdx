package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcDebblinOfDurodAnimation;

/*
npc.whiteSteedLodge.unknownActor124 (renamed NpcDebblinOfDurod, low confidence; one of three
    White Steed Lodge characters at overworld:n11a alongside the existing NpcWaiter,
    NpcDeerHunter and NpcLodgeOwner):
    scripts.py (overworld:n11a:2) only has an onLoad handler that spawns the actor; there are no
    playVoiceLine calls anywhere for this actor. Implemented as a basic idle actor with no
    dialogue.
*/
public class NpcDebblinOfDurod extends Npc {
    public NpcDebblinOfDurodAnimation animation;

    public NpcDebblinOfDurod() {
        super(ActorType.FRIENDLY, true);
        setWidth(40);
        setHeight(39);

        this.animation = new NpcDebblinOfDurodAnimation(this);
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
