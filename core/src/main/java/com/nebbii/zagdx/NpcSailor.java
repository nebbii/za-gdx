package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcSailorAnimation;

/*
npc.sailor:
    scripts.py (overworld:l27:0) plays soundId=0 from the cell's onEntry handler (not the
    actor's own onLoad), alongside the cell's teleport-trigger setup. line0 is wired into the
    standard TALKY/TALKING/DONE flow here, firing automatically rather than only on cell entry.
*/
public class NpcSailor extends Npc {
    public NpcSailorAnimation animation;

    private float timer;
    private Sound line0;

    public NpcSailor() {
        super(ActorType.FRIENDLY, true);
        setWidth(56);
        setHeight(48);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcSailorAnimation(this);
        this.line0 = World.sounds.getNpcSailorLine0();
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
