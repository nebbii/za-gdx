package com.nebbii.zagdx;

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

        switch(npcState) {
            case TALKY:
            case TALKING:
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
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
