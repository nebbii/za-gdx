package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcToobarAnimation;

/*
npc.toobar:
    scripts.py (overworld:l27a:0) has no onLoad dialogue; it plays soundId=0 on touch, then
    advances through soundId=1 and soundId=2 on further item interacts. Npc has no
    touch/interact-triggered dialogue hook (see Npc.onOverlap()), so no NpcState/TALKY flow is
    wired here. line0/line1/line2 are loaded and exposed via getLine0()/getLine1()/getLine2() for
    future touch/interact-triggered playback, following the NpcFatShopkeeper convention.
    Implemented as a basic idle actor otherwise.
*/
public class NpcToobar extends Npc {
    public NpcToobarAnimation animation;

    private Sound line0;
    private Sound line1;
    private Sound line2;

    public NpcToobar() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(48);

        this.animation = new NpcToobarAnimation(this);
        this.line0 = World.sounds.getNpcToobarLine0();
        this.line1 = World.sounds.getNpcToobarLine1();
        this.line2 = World.sounds.getNpcToobarLine2();
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

    public Sound getLine1() {
        return line1;
    }

    public Sound getLine2() {
        return line2;
    }
}
