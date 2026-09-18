package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcSquireGripAnimation;

/*
npc.unknownActor149 (renamed NpcSquireGrip):
    scripts.py (overworld:r10:1) has no onLoad dialogue; it plays soundId=3 on touch and
    soundId=1 on item interact (the latter also flips local flags shared with the neighboring
    npc.unknownActor148/NpcMerribal actor in this cell, presumably advancing a merchant/squire
    exchange). Npc has no touch/interact-triggered dialogue hook (see Npc.onOverlap()), so no
    NpcState/TALKY flow is wired here, and the cross-actor flag chain with NpcMerribal is not
    modeled. line1/line3 are loaded and exposed via getLine1()/getLine3() for future wiring.
    Implemented as a basic idle actor otherwise.
*/
public class NpcSquireGrip extends Npc {
    public NpcSquireGripAnimation animation;

    private Sound line1;
    private Sound line3;

    public NpcSquireGrip() {
        super(ActorType.FRIENDLY, true);
        setWidth(52);
        setHeight(50);

        this.animation = new NpcSquireGripAnimation(this);
        this.line1 = World.sounds.getNpcSquireGripLine1();
        this.line3 = World.sounds.getNpcSquireGripLine3();
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

    public Sound getLine3() {
        return line3;
    }
}
