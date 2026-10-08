package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcYvonneAnimation;

/*
npc.yvonne (overworld:l14:1, alongside npc.ericAndIan at desc0 in the same cell):
    scripts.py has no onLoad dialogue; onLoad only conditionally spawns the actor while
    save[Flute] == 0. onTouchOrPushBlockStoppedMoving plays soundId=3 once, gated by local save
    flags. onItemInteractOrSoundFileDone then conditionally plays soundId=4 and hands over the
    flute treasure (item_treasure_flute, cast[3]) the first time, which is not modeled. Npc has
    no touch/interact-triggered dialogue hook, so line3/line4 are loaded and exposed via
    getLine3()/getLine4() for future wiring, following the NpcTownMerchant convention.
    Implemented as a basic idle actor otherwise.
*/
public class NpcYvonne extends Npc {
    public NpcYvonneAnimation animation;

    private Sound line3;
    private Sound line4;

    public NpcYvonne() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(53);

        this.animation = new NpcYvonneAnimation(this);
        this.line3 = World.sounds.getNpcYvonneLine3();
        this.line4 = World.sounds.getNpcYvonneLine4();
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }

    public Sound getLine3() {
        return line3;
    }

    public Sound getLine4() {
        return line4;
    }
}
