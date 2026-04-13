package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcGlebbAnimation;

public class NpcGlebb extends Npc {
    public NpcGlebbAnimation animation;

    public NpcGlebb() {
        super(ActorType.FRIENDLY, false);
        this.animation = new NpcGlebbAnimation(this);

        this.npcState = NpcState.TALKY;
    }

    @Override
    public void logic() {
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
        if (getState() != State.ACTIVE) return;

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
