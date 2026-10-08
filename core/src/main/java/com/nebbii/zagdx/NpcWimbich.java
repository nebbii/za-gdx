package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcWimbichAnimation;

/*
npc.wimbich.unknownActor89 (renamed NpcWimbich; "Wimbich" appears to be the ROM's own internal
    label, so this name carries higher confidence than the other manually-assigned names in this
    batch):
    scripts.py (overworld:l13:1, alongside the existing NpcRandomCapeGuy at desc0 in the same
    cell) only has an onLoad handler that spawns the actor; there are no playVoiceLine calls
    anywhere for this actor. Implemented as a basic idle actor with no dialogue.
*/
public class NpcWimbich extends Npc {
    public NpcWimbichAnimation animation;

    public NpcWimbich() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(52);

        this.animation = new NpcWimbichAnimation(this);
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
