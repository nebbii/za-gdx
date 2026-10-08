package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcNimoneeAnimation;

/*
npc.unknownActor210 (renamed NpcNimonee):
    scripts.py (overworld:z1:0) has no onLoad dialogue of its own; onLoad only spawns the actor.
    onTouchOrPushBlockStoppedMoving plays soundId=1 once, gated on a local save flag. Npc has no
    touch/interact-triggered dialogue hook (see Npc.onOverlap()), so line1 is loaded and exposed
    via getLine1() for future wiring, following the NpcTownMerchant convention. Implemented as a
    basic idle actor otherwise.
*/
public class NpcNimonee extends Npc {
    public NpcNimoneeAnimation animation;

    private Sound line1;

    public NpcNimonee() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(54);

        this.animation = new NpcNimoneeAnimation(this);
        this.line1 = World.sounds.getNpcNimoneeLine1();
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
