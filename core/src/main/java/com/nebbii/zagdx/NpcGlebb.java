package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.animation.NpcGlebbAnimation;

public class NpcGlebb extends Npc {
    private Texture spritesheet;
    public NpcGlebbAnimation animation;

    public NpcGlebb() {
        super(ActorType.FRIENDLY, false);
        this.animation = new NpcGlebbAnimation(this);

        this.npcState = NpcState.TALKY;
        setX(3716f);
        setY(2050f);

        // her bottle spawns at x 3672 y 2055
    }

    @Override
    public void logic() {
        super.logic();

        switch(npcState) {
        case TALKY:
            break;
        case TALKING:
            break;
        case TALKED:
            break;
        case DONE:
            break;
        default:
            break;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
