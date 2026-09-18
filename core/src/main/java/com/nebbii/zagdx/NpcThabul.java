package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcThabulAnimation;

/*
npc.thabul:
    scripts.py (overworld:v5:0) has no onLoad dialogue; it plays soundId=0 on touch, then
    advances to soundId=1 on the next item interact. Npc has no touch/interact-triggered
    dialogue hook (see Npc.onOverlap()), so no NpcState/TALKY flow is wired here. line0/line1 are
    loaded and exposed via getLine0()/getLine1() for future touch/interact-triggered playback,
    following the NpcFatShopkeeper convention. Note: the batch data lists nonzero
    maxHealth/damage for this actor (leftover ROM combat fields), but Npc has no health/damage
    support, so they are not used here. Implemented as a basic idle actor otherwise.
*/
public class NpcThabul extends Npc {
    public NpcThabulAnimation animation;

    private Sound line0;
    private Sound line1;

    public NpcThabul() {
        super(ActorType.FRIENDLY, true);
        setWidth(64);
        setHeight(60);

        this.animation = new NpcThabulAnimation(this);
        this.line0 = World.sounds.getNpcThabulLine0();
        this.line1 = World.sounds.getNpcThabulLine1();
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
}
