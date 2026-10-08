package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcKnaveAnimation;

/*
npc.shopkeeperAndKnave.unknownActor72 (renamed NpcKnave):
    scripts.py (overworld:k13a:0) plays soundId=0 on load, then advances a local state machine
    through soundId=4 and soundId=1 on further interaction. line0 is wired into the standard
    TALKY/TALKING/DONE flow here; line1/line4 are loaded and exposed via getLine1()/getLine4()
    for future interaction-triggered playback, following the NpcBlacksmith getLine1() convention.
*/
public class NpcKnave extends Npc {
    public NpcKnaveAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;
    private Sound line4;

    public NpcKnave() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(49);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcKnaveAnimation(this);
        this.line0 = World.sounds.getNpcKnaveLine0();
        this.line1 = World.sounds.getNpcKnaveLine1();
        this.line4 = World.sounds.getNpcKnaveLine4();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line0.pause();
            line1.stop();
            line4.stop();
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

    public Sound getLine4() {
        return line4;
    }
}
