package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcLoungerAnimation;

// TODO: scripts.py (overworld:k13:0) has more complex behavior not implemented here: on load it
// picks between two different greeting lines depending on the persistent LOUNGER_LINE_K13 save
// flag (first visit vs. repeat visits), then plays further lines through
// onItemInteractOrSoundFileDone gated by a second local flag; the same cell also hosts a
// shopkeeperDog and two teleport-pixel actors. Implemented as a basic idle stub rather than
// reverse-engineering that branching, repeat-visit dialogue.
public class NpcLounger extends Npc {
    public NpcLoungerAnimation animation;

    public NpcLounger() {
        super(ActorType.FRIENDLY, true);
        setWidth(52);
        setHeight(54);

        this.animation = new NpcLoungerAnimation(this);
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
