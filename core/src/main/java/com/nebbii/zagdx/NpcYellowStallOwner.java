package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcYellowStallOwnerAnimation;

/*
npc.yellowStallOwner:
    scripts.py (underworld:s618:0) plays soundId=0 on load, gated on
    save[Ticket_BlueGreenKnights] == 0 (which also gates whether the actor spawns at all and
    whether it spawns the accompanying ticket-stall cast). line0 is wired into the standard
    TALKY/TALKING/DONE flow here, firing automatically rather than only when the ticket hasn't
    been bought yet, following the NpcOldSailor precedent; the ticket-purchase gating itself is
    not modeled.
*/
public class NpcYellowStallOwner extends Npc {
    public NpcYellowStallOwnerAnimation animation;

    private float timer;
    private Sound line0;

    public NpcYellowStallOwner() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(45);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcYellowStallOwnerAnimation(this);
        this.line0 = World.sounds.getNpcYellowStallOwnerLine0();
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
