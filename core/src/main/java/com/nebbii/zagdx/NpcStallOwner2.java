package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcStallOwner2Animation;

/*
npc.stallOwner2:
    scripts.py (underworld:s603:1) has no onLoad dialogue; it only plays soundId=0 on touch,
    gated on a pair of local flags shared with the neighboring stall actors in this cell.
    Npc has no touch/interact-triggered dialogue hook (see Npc.onOverlap()), so no NpcState/TALKY
    flow is wired here. line0 is loaded and exposed via getLine0() for future touch-triggered
    playback, following the NpcFatShopkeeper convention. Implemented as a basic idle actor
    otherwise.
*/
public class NpcStallOwner2 extends Npc {
    public NpcStallOwner2Animation animation;

    private Sound line0;

    public NpcStallOwner2() {
        super(ActorType.FRIENDLY, true);
        setWidth(64);
        setHeight(53);

        this.animation = new NpcStallOwner2Animation(this);
        this.line0 = World.sounds.getNpcStallOwner2Line0();
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
