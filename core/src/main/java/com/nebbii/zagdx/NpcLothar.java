package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcLotharAnimation;

public class NpcLothar extends Npc {
    public NpcLotharAnimation animation;

    public NpcLothar() {
        super(ActorType.FRIENDLY, false);
        setWidth(32);
        setHeight(32);
        setNpcState(NpcState.TALKY);

        this.animation = new NpcLotharAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();

        switch(npcState) {
            case TALKY:
                break;
            case TALKING:
                break;
            case DONE:
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
