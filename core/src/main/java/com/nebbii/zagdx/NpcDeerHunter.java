package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcDeerHunterAnimation;

/*
npc.whiteSteedLodge.unknownActor122 (renamed NpcDeerHunter, low confidence; one of three White
    Steed Lodge characters at overworld:n11a alongside the existing NpcWaiter,
    NpcDebblinOfDurod and NpcLodgeOwner):
    scripts.py (overworld:n11a:0) has no onLoad dialogue; onLoad only spawns the actor.
    onTouchOrPushBlockStoppedMoving plays soundId=1 once, gated on a local save flag. Npc has no
    touch/interact-triggered dialogue hook, so line1 is loaded and exposed via getLine1() for
    future wiring, following the NpcTownMerchant convention. Implemented as a basic idle actor
    otherwise.
*/
public class NpcDeerHunter extends Npc {
    public NpcDeerHunterAnimation animation;

    private Sound line1;

    public NpcDeerHunter() {
        super(ActorType.FRIENDLY, true);
        setWidth(56);
        setHeight(55);

        this.animation = new NpcDeerHunterAnimation(this);
        this.line1 = World.sounds.getNpcDeerHunterLine1();
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }

    public Sound getLine1() {
        return line1;
    }
}
