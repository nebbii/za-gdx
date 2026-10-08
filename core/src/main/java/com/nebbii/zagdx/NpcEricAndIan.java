package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcEricAndIanAnimation;

// TODO: scripts.py (overworld:l14:0) has more complex behavior not implemented here: this cell
// contains npc_ericAndIan, npc_yvonne, and a hidden item_treasure_flute, with branching dialogue
// across several save-flag locals (twins/Yvonne conversation stage, a shared "someone is talking"
// flag) and a flute item that gets spawned once Yvonne's dialogue chain completes. Implemented as
// a basic idle stub for now rather than reverse-engineering that whole chain.
public class NpcEricAndIan extends Npc {
    public NpcEricAndIanAnimation animation;

    public NpcEricAndIan() {
        super(ActorType.FRIENDLY, true);
        setWidth(72);
        setHeight(70);

        this.animation = new NpcEricAndIanAnimation(this);
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
