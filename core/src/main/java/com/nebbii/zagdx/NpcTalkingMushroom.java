package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcTalkingMushroomAnimation;

/*
npc.talkingMushroom:
    scripts.py (overworld:k20:0) has no onLoad dialogue; it only plays soundId=0 once on touch.
    Npc has no touch/interact-triggered dialogue hook (see Npc.onOverlap()), so no NpcState/TALKY
    flow is wired here. line0 is loaded and exposed via getLine0() for future touch-triggered
    playback, following the NpcFatShopkeeper convention. Implemented as a basic idle actor
    otherwise.
*/
public class NpcTalkingMushroom extends Npc {
    public NpcTalkingMushroomAnimation animation;

    private Sound line0;

    public NpcTalkingMushroom() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(54);

        this.animation = new NpcTalkingMushroomAnimation(this);
        this.line0 = World.sounds.getNpcTalkingMushroomLine0();
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
