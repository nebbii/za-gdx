package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcRycoAnimation;

/*
npc.unknown20 (renamed NpcRyco):
    scripts.py (overworld:e7:0) plays soundId=0 automatically on load, gated on
    save[LifePotion] == 0. line0 is wired into the standard TALKY/TALKING/DONE flow here, firing
    automatically rather than only when the save flag is unset, following the NpcOldSailor
    precedent.
*/
public class NpcRyco extends Npc {
    public NpcRycoAnimation animation;

    private float timer;
    private Sound line0;

    public NpcRyco() {
        super(ActorType.FRIENDLY, true);
        setWidth(64);
        setHeight(56);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcRycoAnimation(this);
        this.line0 = World.sounds.getNpcRycoLine0();
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
