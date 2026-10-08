package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcQuarryMinerAnimation;

// TODO: scripts.py (overworld:aa15:0) has more complex behavior not implemented here: on load,
// while save[Diamond] == 0 and save[Coal] == 0, it plays soundId=0; on further interaction (again
// gated on those flags) it plays soundId=3 and calls an unmapped opcode (op12_0x2cd0) on a
// companion coal item actor (cast[1]), which likely spawns/reveals it. There are also no voice
// files under this cell's export folder, so the lines can't be wired even loosely. Implemented as
// a basic idle stub rather than reverse-engineering that treasure-gated chain.
public class NpcQuarryMiner extends Npc {
    public NpcQuarryMinerAnimation animation;

    public NpcQuarryMiner() {
        super(ActorType.FRIENDLY, true);
        setWidth(56);
        setHeight(46);

        this.animation = new NpcQuarryMinerAnimation(this);
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
