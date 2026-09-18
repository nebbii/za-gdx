package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcMerribalAnimation;

/*
npc.unknownActor148 (renamed NpcMerribal):
    scripts.py (overworld:r10:0) has no onLoad dialogue; it only plays soundId=0 once on touch.
    Npc has no touch/interact-triggered dialogue hook (see Npc.onOverlap()), so no NpcState/TALKY
    flow is wired here. line0 is loaded and exposed via getLine0() for future touch-triggered
    playback, following the NpcFatShopkeeper convention. This actor shares its cell with
    npc.unknownActor149 (NpcSquireGrip); see NpcSquireGrip for that actor's script. Implemented
    as a basic idle actor otherwise.
*/
public class NpcMerribal extends Npc {
    public NpcMerribalAnimation animation;

    private Sound line0;

    public NpcMerribal() {
        super(ActorType.FRIENDLY, true);
        setWidth(68);
        setHeight(59);

        this.animation = new NpcMerribalAnimation(this);
        this.line0 = World.sounds.getNpcMerribalLine0();
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
