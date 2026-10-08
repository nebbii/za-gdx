package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcForeignWomanAnimation;

/*
npc.unknownActor142 (renamed NpcForeignWoman):
    scripts.py (overworld:p20a:1) has no onLoad dialogue; it only plays soundId=5 once on touch.
    Npc has no touch/interact-triggered dialogue hook (see Npc.onOverlap()), so no NpcState/TALKY
    flow is wired here. line5 is loaded and exposed via getLine5() for future touch-triggered
    playback, following the NpcFatShopkeeper convention. Implemented as a basic idle actor
    otherwise.
*/
public class NpcForeignWoman extends Npc {
    public NpcForeignWomanAnimation animation;

    private Sound line5;

    public NpcForeignWoman() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(61);

        this.animation = new NpcForeignWomanAnimation(this);
        this.line5 = World.sounds.getNpcForeignWomanLine5();
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }

    public Sound getLine5() {
        return line5;
    }
}
