package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcBrideAnimation;

/*
npc.unknownActor170 (renamed NpcBride, low confidence; cell has wedding-themed treasures
    bouquet/redBow/saltcellar nearby):
    scripts.py (overworld:s601:0 and underworld:s601:0) plays soundId=0 unconditionally on load
    unless all three treasures have already been bought. line0 is wired into the standard
    TALKY/TALKING/DONE flow here, firing automatically rather than only when the save-gate is
    unset, following the NpcRyco/NpcOldSailor precedent. line1 (touch-triggered, once) is loaded
    and exposed via getLine1() for future wiring, since Npc has no touch/interact-triggered
    dialogue hook (see Npc.onOverlap()).
*/
public class NpcBride extends Npc {
    public NpcBrideAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;

    public NpcBride() {
        super(ActorType.FRIENDLY, true);
        setWidth(56);
        setHeight(50);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcBrideAnimation(this);
        this.line0 = World.sounds.getNpcBrideLine0();
        this.line1 = World.sounds.getNpcBrideLine1();
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

    public Sound getLine1() {
        return line1;
    }
}
