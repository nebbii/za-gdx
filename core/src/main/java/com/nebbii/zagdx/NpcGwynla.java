package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcGwynlaAnimation;

// TODO: scripts.py (overworld:r9:0) has more complex behavior not implemented here: this is one
// half of a two-actor OpalAmulet quest chain shared with NpcLonlyn (also in this cell) and a
// hidden item_weapon_noise actor. On touch it plays a single voice line and sets a couple of save
// flags, then onItemInteractOrSoundFileDone spawns the amulet item via cast[2] and advances the
// shared OpalAmulet save state that Lonlyn's own dialogue later depends on. Implemented as a basic
// idle stub rather than reverse-engineering that whole chain.
public class NpcGwynla extends Npc {
    public NpcGwynlaAnimation animation;

    public NpcGwynla() {
        super(ActorType.FRIENDLY, true);
        setWidth(56);
        setHeight(53);

        this.animation = new NpcGwynlaAnimation(this);
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
