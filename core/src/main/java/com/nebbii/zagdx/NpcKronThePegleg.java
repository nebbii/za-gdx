package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcKronThePeglegAnimation;

/*
npc.kronThePegleg:
    scripts.py (overworld:p12:0) has no greeting line of its own on load; its only voice line
    (soundId=1) plays once via onItemInteractOrSoundFileDone the first time the player interacts
    with him. line1 is wired into the standard TALKY/TALKING/DONE flow here (renamed from the
    NpcGlebb-style "line0" convention since soundId 1 is the only line this actor ever plays).
*/
public class NpcKronThePegleg extends Npc {
    public NpcKronThePeglegAnimation animation;

    private float timer;
    private Sound line1;

    public NpcKronThePegleg() {
        super(ActorType.FRIENDLY, true);
        setWidth(64);
        setHeight(55);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcKronThePeglegAnimation(this);
        this.line1 = World.sounds.getNpcKronThePeglegLine1();
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
