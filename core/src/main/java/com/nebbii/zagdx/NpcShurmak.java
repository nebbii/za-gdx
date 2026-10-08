package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcShurmakAnimation;

/*
npc.shurmak:
    scripts.py (overworld:p15:0) only spawns and plays soundId=8 on load while save[SIGN_S2] ==
    1. line8 is wired into the standard TALKY/TALKING/DONE flow here, firing automatically
    rather than only when the save flag is set, following the NpcOldSailor precedent.
*/
public class NpcShurmak extends Npc {
    public NpcShurmakAnimation animation;

    private float timer;
    private Sound line8;

    public NpcShurmak() {
        super(ActorType.FRIENDLY, true);
        setWidth(84);
        setHeight(63);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcShurmakAnimation(this);
        this.line8 = World.sounds.getNpcShurmakLine8();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line8.pause();
            return;
        }

        switch(npcState) {
            case TALKY:
                line8.play();
                setNpcState(NpcState.TALKING);
                break;
            case TALKING:
                line8.resume();
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
