package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcGlebbAnimation;

public class NpcGlebb extends Npc {
    public NpcGlebbAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line2;

    public NpcGlebb() {
        super(ActorType.FRIENDLY, false);
        setWidth(32);
        setHeight(32);
        setNpcState(NpcState.TALKY);
        this.animation = new NpcGlebbAnimation(this);
        this.timer = 0;
        this.line0 = World.sounds.getNpcGlebbLine0();
        this.line2 = World.sounds.getNpcGlebbLine2();
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
                timer += Gdx.graphics.getDeltaTime();
                if (timer > 10) {
                    for (Spawner spawner : map.findAllActorsByType(Spawner.class)) {
                        spawner.setState(State.ACTIVE);
                    }

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

    public Sound getLine2() {
        return line2;
    }
}
