package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcWaiterAnimation;

/*
npc.waiter (overworld:n11a:1, White Steed Lodge):
    scripts.py has no onLoad dialogue; onLoad only spawns the actor. onTouchOrPushBlockStopped
    Moving plays soundId=1 once, gated on a local save flag. Npc has no touch/interact-triggered
    dialogue hook (see Npc.onOverlap()), so line1 is loaded and exposed via getLine1() for future
    wiring, following the NpcTownMerchant convention. Implemented as a basic idle actor otherwise.
*/
public class NpcWaiter extends Npc {
    public NpcWaiterAnimation animation;

    private Sound line1;

    public NpcWaiter() {
        super(ActorType.FRIENDLY, true);
        setWidth(52);
        setHeight(59);

        this.animation = new NpcWaiterAnimation(this);
        this.line1 = World.sounds.getNpcWaiterLine1();
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
