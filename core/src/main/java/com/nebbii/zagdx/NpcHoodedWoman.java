package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcHoodedWomanAnimation;

/*
npc.hoodedWoman:
    scripts.py (overworld:v16:0) plays soundId=0 on load (while save[GoldenBoots] == 0), then
    soundId=1 and soundId=2 in sequence on further interaction. line0 is wired into the standard
    TALKY/TALKING/DONE flow here; line1/line2 are loaded and exposed via getLine1()/getLine2() for
    future interaction-triggered playback, following the NpcBlacksmith getLine1() convention.
*/
public class NpcHoodedWoman extends Npc {
    public NpcHoodedWomanAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;
    private Sound line2;

    public NpcHoodedWoman() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(58);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcHoodedWomanAnimation(this);
        this.line0 = World.sounds.getNpcHoodedWomanLine0();
        this.line1 = World.sounds.getNpcHoodedWomanLine1();
        this.line2 = World.sounds.getNpcHoodedWomanLine2();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line0.pause();
            line1.stop();
            line2.stop();
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

    public Sound getLine1() {
        return line1;
    }

    public Sound getLine2() {
        return line2;
    }
}
