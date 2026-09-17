package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcLonlynAnimation;

// TODO: scripts.py (overworld:r9:1) has more complex behavior not implemented here: this is the
// other half of the two-actor OpalAmulet quest chain shared with NpcGwynla (also in this cell).
// It only spawns while save[OpalAmulet] == 1 and save[LONLYN_GAVE_RUPEES] == 0, plays a single
// voice line on touch, then grants rupees and sets LONLYN_GAVE_RUPEES via
// onItemInteractOrSoundFileDone. Implemented as a basic idle stub rather than reverse-engineering
// that shared save-flag chain.
public class NpcLonlyn extends Npc {
    public NpcLonlynAnimation animation;

    public NpcLonlyn() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(55);

        this.animation = new NpcLonlynAnimation(this);
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
