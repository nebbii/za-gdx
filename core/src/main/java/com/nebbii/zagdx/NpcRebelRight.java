package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcRebelRightAnimation;

/*
npc.rebel.right:
    scripts.py (overworld:z13:0) plays soundId=0 once on load, alongside its paired
    npc.rebel.left actor in the same cell (which has no dialogue of its own). line0 is wired
    into the standard TALKY/TALKING/DONE flow here.
*/
public class NpcRebelRight extends Npc {
    public NpcRebelRightAnimation animation;

    private float timer;
    private Sound line0;

    public NpcRebelRight() {
        super(ActorType.FRIENDLY, true);
        setWidth(64);
        setHeight(43);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcRebelRightAnimation(this);
        this.line0 = World.sounds.getNpcRebelRightLine0();
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
