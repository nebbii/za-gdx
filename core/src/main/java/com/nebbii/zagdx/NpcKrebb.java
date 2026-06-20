package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcKrebbAnimation;

public class NpcKrebb extends Npc {
    public NpcKrebbAnimation animation;

    private float timer;
    private int currentLine;

    private Sound line0;
    private Sound line1;

    public NpcKrebb() {
        super(ActorType.FRIENDLY, false);
        setWidth(32);
        setHeight(32);
        setNpcState(NpcState.TALKY);

        this.animation = new NpcKrebbAnimation(this);
        this.timer = 0;
        this.currentLine = 0;
        this.line0 = World.sounds.getNpcKrebbLine0();
        this.line1 = World.sounds.getNpcKrebbLine1();
    }

    @Override
    public void logic() {
        super.logic();

        if (!isActive()) {
            line0.stop();
            line1.stop();
            return;
        }

        if (getNpcState() == NpcState.TALKING) {
            timer += Gdx.graphics.getDeltaTime();
            switch(currentLine) {
                case 0:
                    if (timer < 8) return;

                    currentLine = 1;
                    setInteracted(false);
                    setNpcState(NpcState.TALKY);
                    Gdx.app.log(this.getClass().getSimpleName(), "Switching to line 1");
                    break;
                case 1:
                default:
                    if (timer < 9) return;

                    for (Spawner spawner : map.findAllActorsByType(Spawner.class)) {
                        spawner.setState(State.ACTIVE);
                    }

                    Gdx.app.log(this.getClass().getSimpleName(), "Done with line 1!");
                    setNpcState(NpcState.DONE);
                break;
            }
        }
    }

    @Override
    public void onOverlap() {
        if (map.areAnyNpcsTalking()) {
            return;
        }

        if (getNpcState() == NpcState.TALKY) {
            timer = 0;
            switch(currentLine) {
                case 0:
                    line0.play();
                    break;
                case 1:
                default:
                    line1.play();
            }

            setNpcState(NpcState.TALKING);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
