package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcSleepingManAnimation;

/*
npc.sleepingMan:
    scripts.py (overworld:n17:0) has no dialogue of its own (onLoad only spawns/animates), but
    Cell.onEntry unconditionally plays soundId=0 when the cell is entered. line0 is wired into
    the standard TALKY/TALKING/DONE flow here, firing automatically on load, following the
    NpcShurmak/NpcOldSailor precedent.
*/
public class NpcSleepingMan extends Npc {
    public NpcSleepingManAnimation animation;

    private float timer;
    private Sound line0;

    public NpcSleepingMan() {
        super(ActorType.FRIENDLY, true);
        setWidth(68);
        setHeight(58);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcSleepingManAnimation(this);
        this.line0 = World.sounds.getNpcSleepingManLine0();
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
