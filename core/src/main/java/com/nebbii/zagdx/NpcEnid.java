package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcEnidAnimation;

public class NpcEnid extends Npc {
    public NpcEnidAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;

    public NpcEnid() {
        super(ActorType.FRIENDLY, false);
        setWidth(40);
        setHeight(32);
        setNpcState(NpcState.TALKY);

        this.timer = 0;
        this.line0 = World.sounds.getNpcEnidLine0();
        this.line1 = World.sounds.getNpcEnidLine1();
        this.animation = new NpcEnidAnimation(this);
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
                line0.play();
                setNpcState(NpcState.TALKING);
                break;
            case TALKING:
                timer += Gdx.graphics.getDeltaTime();
                if (timer > 5) {
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

        line1.play();
        map.getZelda().decreaseHealth(20);
        map.getWorld().getSaveManager().addLocationEntry("h29_0", "permadead");
        map.getWorld().getSaveManager().addLocationEntry("h29_1", "permadead");
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
