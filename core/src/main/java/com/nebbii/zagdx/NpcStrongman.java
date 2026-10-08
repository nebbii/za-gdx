package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcStrongmanAnimation;

/*
npc.unknownActor323 (renamed NpcStrongman, low confidence; carnival/market-themed NPC in the
    Shrine of Power dungeon hub cast alongside NpcFortuneTeller):
    scripts.py (underworld:s603:0) has no playVoiceLine calls at all. onTouchOrPushBlockStopped
    Moving deducts 25 rupees once (a carnival "strongman game" payment), which is not modeled.
    Implemented as a basic idle actor with a TODO for the rupee-payment interaction.
*/
// TODO: wire the 25-rupee payment interaction from scripts.py once Npc gains a
// touch/interact-triggered hook.
public class NpcStrongman extends Npc {
    public NpcStrongmanAnimation animation;

    public NpcStrongman() {
        super(ActorType.FRIENDLY, true);
        setWidth(64);
        setHeight(55);

        this.animation = new NpcStrongmanAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
