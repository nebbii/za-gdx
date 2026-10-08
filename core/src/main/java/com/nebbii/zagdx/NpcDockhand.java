package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcDockhandAnimation;

/*
npc.unknownActor102 (renamed NpcDockhand):
    scripts.py (overworld:l27:1) only spawns and animates on load; it has no dialogue of its
    own. The lone voice line in this cell (soundId=0, played automatically from Cell.onEntry)
    belongs to the neighboring npc_sailor actor (desc0), already covered by NpcSailor's
    getNpcSailorLine0(). Implemented as a plain idle actor.
*/
public class NpcDockhand extends Npc {
    public NpcDockhandAnimation animation;

    public NpcDockhand() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(51);

        this.animation = new NpcDockhandAnimation(this);
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
