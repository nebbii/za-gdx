package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcSailor2Animation;

// TODO: scripts.py (overworld:l28:1) only spawns and animates npc.sailor2 when save[SIGN_S5] ==
// 1, and it has no dialogue of its own. The same cell hosts a raft-journey sequence
// (env_raft actors, a rupee-gated purchase via npc.sailor's onItemInteractOrSoundFileDone, and a
// multi-stage LOCALS state machine) that isn't modeled. Implemented as a basic idle stub rather
// than reverse-engineering the raft/purchase flow.
public class NpcSailor2 extends Npc {
    public NpcSailor2Animation animation;

    public NpcSailor2() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(44);

        this.animation = new NpcSailor2Animation(this);
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
