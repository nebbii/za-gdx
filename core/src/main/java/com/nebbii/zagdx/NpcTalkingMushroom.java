package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcTalkingMushroomAnimation;

public class NpcTalkingMushroom extends Npc {
    public NpcTalkingMushroomAnimation animation;

    private float timer;
    private Sound line0;

    public NpcTalkingMushroom() {
        super(ActorType.FRIENDLY, false);
        setWidth(60);
        setHeight(54);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcTalkingMushroomAnimation(this);
        this.line0 = World.sounds.getNpcTalkingMushroomLine0();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line0.stop();
            return;
        }

        switch(npcState) {
            case TALKY:
                break;
            case TALKING:
                timer += Gdx.graphics.getDeltaTime();
                if (timer > 10.5f) {
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
            line0.play();
            setNpcState(NpcState.TALKING);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
