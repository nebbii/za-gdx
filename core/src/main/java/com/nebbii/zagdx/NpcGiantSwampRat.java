package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcGiantSwampRatAnimation;

/*
npc.giantSwampRat (ROM-labeled npc.merchant.unknownActor189):
    scripts.py (overworld:v14:0) plays soundId=0 unconditionally on load, matching the
    NpcLivingFarmer/NpcBitterbeck single-greeting-line convention. Wired into the standard
    TALKY/TALKING/DONE flow here.
*/
public class NpcGiantSwampRat extends Npc {
    public NpcGiantSwampRatAnimation animation;

    private float timer;
    private Sound line0;

    public NpcGiantSwampRat() {
        super(ActorType.FRIENDLY, true);
        setWidth(56);
        setHeight(49);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcGiantSwampRatAnimation(this);
        this.line0 = World.sounds.getNpcGiantSwampRatLine0();
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
