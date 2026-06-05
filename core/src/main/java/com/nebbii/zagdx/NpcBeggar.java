package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcBeggarAnimation;

public class NpcBeggar extends Npc {
    public NpcBeggarAnimation animation;

    private Sound line0;
    private Sound line2;
    private float timer;

    public NpcBeggar() {
        super(ActorType.FRIENDLY, false);
        setWidth(20);
        setHeight(32);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcBeggarAnimation(this);
        this.line0 = World.sounds.getNpcBeggarLine0();
        this.line2 = World.sounds.getNpcBeggarLine2();
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
                line0.play();
                setNpcState(NpcState.TALKING);
                break;
            case TALKING:
                /*
                timer += Gdx.graphics.getDeltaTime();
                if (timer > 6f) {
                    for (Spawner spawner : map.findActiveActorsByType(Spawner.class)) {
                        spawner.activate();
                    }

                    setNpcState(NpcState.DONE);
                }
                */
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
