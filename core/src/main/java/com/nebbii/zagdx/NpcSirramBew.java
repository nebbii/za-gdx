package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcSirramBewAnimation;

/*
npc.sirramBew:
    scripts.py (overworld:v16a:0) plays soundId=0 unconditionally on load, then advances a local
    state machine through soundId=1, soundId=2, and soundId=3 in sequence on further interaction.
    line0 is wired into the standard TALKY/TALKING/DONE flow here; line1/line2/line3 are loaded
    and exposed via getLine1()/getLine2()/getLine3() for future interaction-triggered playback,
    following the NpcHoodedWoman convention. Note: the batch data lists nonzero maxHealth/damage
    for this actor (leftover ROM combat fields), but Npc has no health/damage support, so they
    are not used here.
*/
public class NpcSirramBew extends Npc {
    public NpcSirramBewAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;
    private Sound line2;
    private Sound line3;

    public NpcSirramBew() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(48);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcSirramBewAnimation(this);
        this.line0 = World.sounds.getNpcSirramBewLine0();
        this.line1 = World.sounds.getNpcSirramBewLine1();
        this.line2 = World.sounds.getNpcSirramBewLine2();
        this.line3 = World.sounds.getNpcSirramBewLine3();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line0.pause();
            line1.stop();
            line2.stop();
            line3.stop();
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
}
