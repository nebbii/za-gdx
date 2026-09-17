package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcGreatFairyAnimation;

// TODO: scripts.py (overworld:ac18:0) has more complex behavior not implemented here: onLoad only
// spawns this fairy while save[OpalAmulet] tracking isn't set (no dialogue of its own), and the
// cell's onTouchTrigger spawns six other cast actors and fully heals the player once. Implemented
// as a basic idle stub (non-solid, it floats) rather than wiring up that cell-level trigger.
public class NpcGreatFairy extends Npc {
    public NpcGreatFairyAnimation animation;

    public NpcGreatFairy() {
        super(ActorType.FRIENDLY, false);
        setWidth(52);
        setHeight(51);

        this.animation = new NpcGreatFairyAnimation(this);
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
