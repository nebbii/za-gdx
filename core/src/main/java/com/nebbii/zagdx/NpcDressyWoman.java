package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcDressyWomanAnimation;

/*
npc.dressyWoman:
    scripts.py (overworld:l15:0) plays soundId=1 (no soundId=0 line) the first time the
    player touches her; wired here into the standard TALKY/TALKING/DONE flow like the other
    single-line NPCs.
*/
public class NpcDressyWoman extends Npc {
    public NpcDressyWomanAnimation animation;

    private float timer;
    private Sound line1;

    public NpcDressyWoman() {
        super(ActorType.FRIENDLY, true);
        setWidth(52);
        setHeight(54);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcDressyWomanAnimation(this);
        this.line1 = World.sounds.getNpcDressyWomanLine1();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line1.pause();
            return;
        }

        switch(npcState) {
            case TALKY:
                line1.play();
                setNpcState(NpcState.TALKING);
                break;
            case TALKING:
                line1.resume();
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
