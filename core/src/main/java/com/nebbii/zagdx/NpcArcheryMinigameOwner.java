package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcArcheryMinigameOwnerAnimation;

public class NpcArcheryMinigameOwner extends Npc {
    public NpcArcheryMinigameOwnerAnimation animation;

    private float timer;
    private Sound line0;

    public NpcArcheryMinigameOwner() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(47);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcArcheryMinigameOwnerAnimation(this);
        this.line0 = World.sounds.getNpcArcheryMinigameOwnerLine0();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line0.pause();
            return;
        }

        switch(npcState) {
            case TALKY:
                line0.play();
                setNpcState(NpcState.TALKING);
                break;
            case TALKING:
                line0.resume();
                timer += Gdx.graphics.getDeltaTime();
                if (timer > 6f) {
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
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
