package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcEtheraAnimation;

// scripts.py (overworld:s6:0, ROM-labeled npc.merchant.unknownActor168) has no interaction logic
// at all beyond onLoad's spawnAndAnimate(actor=self); the cell's only voice line (soundId=0) is
// played by the neighboring npc_ghostDog actor from Cell.onEntry, not by this NPC. Implemented as
// a plain idle actor since there is nothing else in the script to wire up.
public class NpcEthera extends Npc {
    public NpcEtheraAnimation animation;

    public NpcEthera() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(60);

        this.animation = new NpcEtheraAnimation(this);
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
