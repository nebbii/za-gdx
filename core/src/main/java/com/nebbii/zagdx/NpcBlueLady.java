package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcBlueLadyAnimation;

public class NpcBlueLady extends Npc {
    public NpcBlueLadyAnimation animation;
    private float timer;
    private Sound line11;

    public NpcBlueLady() {
        super(ActorType.FRIENDLY, false);
        setWidth(32);
        setHeight(32);
        setNpcState(NpcState.TALKY);

        this.animation = new NpcBlueLadyAnimation(this);
        this.timer = 0;
        this.line11 = World.sounds.getNpcBlueLadyLine11();
    }

    @Override
    public void logic() {
        super.logic();

        if (!isActive()) {
            line11.stop();
            return;
        }

        switch(npcState) {
            case TALKY:
                break;
            case TALKING:
                timer += Gdx.graphics.getDeltaTime();
                if (timer > 21) {
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
            line11.play();
            setNpcState(NpcState.TALKING);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
