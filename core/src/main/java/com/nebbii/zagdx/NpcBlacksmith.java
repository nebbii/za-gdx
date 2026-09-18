package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcBlacksmithAnimation;

/*
npc.blacksmith:
    scripts.py (overworld:l13a:0) plays soundId=0 on load and soundId=1 the first time the
    player interacts afterward; the k14:3 cell variant of this same actor has no voice lines
    at all. line0 is wired into the standard TALKY/TALKING/DONE flow here; line1 is loaded and
    exposed via getLine1() for future interaction-triggered playback, following the
    NpcGlebb/NpcBeggar getLine2() convention.
*/
public class NpcBlacksmith extends Npc {
    public NpcBlacksmithAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;

    public NpcBlacksmith() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(51);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcBlacksmithAnimation(this);
        this.line0 = World.sounds.getNpcBlacksmithLine0();
        this.line1 = World.sounds.getNpcBlacksmithLine1();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line0.pause();
            line1.stop();
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
}
