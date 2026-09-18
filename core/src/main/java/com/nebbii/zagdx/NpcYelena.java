package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcYelenaAnimation;

/*
npc.yelena (ROM-labeled npc.merchant.unknownActor181):
    scripts.py (overworld:u16:0) plays soundId=0 on load (while save[Map4] == 0), then soundId=1,
    soundId=2, soundId=3 and soundId=4 in sequence on further interaction; the soundId=3 step also
    spawns a treasure map item via op12_0x2cd0(actor=cast[1]). line0 is wired into the standard
    TALKY/TALKING/DONE flow here; line1/line2/line3/line4 are loaded and exposed via
    getLine1()/getLine2()/getLine3()/getLine4() for future interaction-triggered playback,
    following the NpcBlacksmith getLine1() convention. The op12_0x2cd0 map-item spawn is not
    implemented.
*/
public class NpcYelena extends Npc {
    public NpcYelenaAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;
    private Sound line2;
    private Sound line3;
    private Sound line4;

    public NpcYelena() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(59);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcYelenaAnimation(this);
        this.line0 = World.sounds.getNpcYelenaLine0();
        this.line1 = World.sounds.getNpcYelenaLine1();
        this.line2 = World.sounds.getNpcYelenaLine2();
        this.line3 = World.sounds.getNpcYelenaLine3();
        this.line4 = World.sounds.getNpcYelenaLine4();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line0.pause();
            line1.stop();
            line2.stop();
            line3.stop();
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

    public Sound getLine2() {
        return line2;
    }

    public Sound getLine3() {
        return line3;
    }

    public Sound getLine4() {
        return line4;
    }
}
