package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcFaustAnimation;

// TODO: scripts.py (overworld:u18:0) has more complex behavior not implemented here: onLoad
// branches on save[RESPAWN_CELL_ID_maybe] and the Feather save flag to decide which voice line
// to play and which of two env_redBootsEffect actors to spawn (a red-boots river-crossing gate),
// plus a rupee-cost gate in onItemInteractOrSoundFileDone. Implemented as a basic idle stub for
// now rather than reverse-engineering that whole chain.
public class NpcFaust extends Npc {
    public NpcFaustAnimation animation;

    public NpcFaust() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(53);

        this.animation = new NpcFaustAnimation(this);
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
