package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcBeggarAnimation;

public class NpcBeggar extends Npc {
    public NpcBeggarAnimation animation;

    public NpcBeggar() {
        super(ActorType.FRIENDLY, false);
        setWidth(32);
        setHeight(32);
        setNpcState(NpcState.TALKY);

        this.animation = new NpcBeggarAnimation(this);
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
