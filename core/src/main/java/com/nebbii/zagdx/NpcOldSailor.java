package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcOldSailorAnimation;

/*
npc.oldSailor:
    scripts.py (overworld:p20a:0) plays soundId=0 once on touch, gated by local save flags that
    also coordinate with a second actor in the same cell (npc.unknownActor142, not imported here)
    so only one of them can be "in conversation" at a time. line0 is wired into the standard
    TALKY/TALKING/DONE flow here, firing automatically rather than only on touch; the flag-based
    exclusivity with the other actor is not modeled.
*/
public class NpcOldSailor extends Npc {
    public NpcOldSailorAnimation animation;

    private float timer;
    private Sound line0;

    public NpcOldSailor() {
        super(ActorType.FRIENDLY, true);
        setWidth(56);
        setHeight(46);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcOldSailorAnimation(this);
        this.line0 = World.sounds.getNpcOldSailorLine0();
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
