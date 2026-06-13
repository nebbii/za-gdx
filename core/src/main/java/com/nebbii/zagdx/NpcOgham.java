package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcOghamAnimation;

public class NpcOgham extends Npc {
    public NpcOghamAnimation animation;

    private Sound line0;
    private Sound line2;

    private float timer;

    public NpcOgham() {
        super(ActorType.FRIENDLY, false);
        setWidth(32);
        setHeight(32);
        setNpcState(NpcState.TALKY);

        this.line0 = World.sounds.getNpcOghamLine0();
        this.line2 = World.sounds.getNpcOghamLine2();
        this.timer = 0;
        this.animation = new NpcOghamAnimation(this);
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
                if (timer > 8) {
                    setNpcState(NpcState.DONE);
                }
                break;
            case DONE:
                break;
            default:
                break;
        }
        timer += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (timer > 8) {
            animation.setCurrentAnimation("idle1");
            Gdx.app.log(getClass().getSimpleName(), "setting animation idle1");
            if (timer > 16) timer = 0;
        }
        else {
            animation.setCurrentAnimation("idle0");
            Gdx.app.log(getClass().getSimpleName(), "setting animation idle0");
        }
    }
}
