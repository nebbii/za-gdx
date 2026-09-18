package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcSkaterDudeAnimation;

/*
npc.skaterDude:
    scripts.py (overworld:g11:0) only reacts to onItemInteractOrSoundFileDone: it counts
    interactions in save[LOCALS + 0] until it reaches 100, then plays soundId=0 once and spawns
    cast[0] (a swept egg-count/skate-by side effect per the EGGCOUNT/SKATEBY extraData labels).
    Npc has no touch/interact-triggered hook (see Npc.onOverlap()), so this counter/spawn chain
    is not modeled. line0 is loaded and exposed via getLine0() for future wiring. Implemented as
    a basic idle actor otherwise.
*/
public class NpcSkaterDude extends Npc {
    public NpcSkaterDudeAnimation animation;

    private Sound line0;

    public NpcSkaterDude() {
        super(ActorType.FRIENDLY, true);
        setWidth(88);
        setHeight(75);

        this.animation = new NpcSkaterDudeAnimation(this);
        this.line0 = World.sounds.getNpcSkaterDudeLine0();
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }

    public Sound getLine0() {
        return line0;
    }
}
