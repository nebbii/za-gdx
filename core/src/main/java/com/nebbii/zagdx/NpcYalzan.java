package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcYalzanAnimation;

/*
npc.yalzan:
    scripts.py (overworld:q15:0) plays soundId=0 unconditionally on load. line0 is wired into the
    standard TALKY/TALKING/DONE flow here. onItemInteractOrSoundFileDone handles an unmodeled
    10-rupee toll-payment interaction that despawns two floorSpikes actors once paid; there are
    no further playVoiceLine calls in that handler.
*/
public class NpcYalzan extends Npc {
    public NpcYalzanAnimation animation;

    private float timer;
    private Sound line0;

    public NpcYalzan() {
        super(ActorType.FRIENDLY, true);
        setWidth(64);
        setHeight(53);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcYalzanAnimation(this);
        this.line0 = World.sounds.getNpcYalzanLine0();
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
