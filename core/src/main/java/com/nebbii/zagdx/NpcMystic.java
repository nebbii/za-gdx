package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcMysticAnimation;

/*
npc.mystic:
    scripts.py (overworld:v11:0) plays soundId=0 once on touch, gated by a local save flag so it
    only fires the first time. line0 is wired into the standard TALKY/TALKING/DONE flow here,
    firing automatically rather than only on touch; the touch-gating itself is not modeled.
*/
public class NpcMystic extends Npc {
    public NpcMysticAnimation animation;

    private float timer;
    private Sound line0;

    public NpcMystic() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(50);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcMysticAnimation(this);
        this.line0 = World.sounds.getNpcMysticLine0();
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
