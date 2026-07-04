package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcExhaustedTravelerAnimation;

public class NpcExhaustedTraveler extends Npc {

    public NpcExhaustedTravelerAnimation animation;
    private float timer;
    private Sound line5;

    public NpcExhaustedTraveler() {
        super(ActorType.FRIENDLY, false);
        setWidth(32);
        setHeight(32);
        setNpcState(NpcState.TALKY);

        this.animation = new NpcExhaustedTravelerAnimation(this);
        this.timer = 0;
        this.line5 = World.sounds.getNpcExhaustedTravelerLine5();
    }

    @Override
    public void logic() {
        super.logic();

        if (!isActive()) {
            line5.stop();
            return;
        }

        switch(npcState) {
            case TALKY:
                break;
            case TALKING:
                timer += Gdx.graphics.getDeltaTime();
                if (timer > 11) {
                    setNpcState(NpcState.DONE);
                }
                break;
            case DONE:
                break;
            default:
                break;
        }
    }

    @Override
    public void onOverlap() {
        if (map.areAnyNpcsTalking()) {
            return;
        }

        if (getNpcState() == NpcState.TALKY) {
            timer = 0;
            line5.play();
            setNpcState(NpcState.TALKING);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
