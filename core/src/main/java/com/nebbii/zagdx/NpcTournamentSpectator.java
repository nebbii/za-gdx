package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcTournamentSpectatorAnimation;

/*
npc.unknownActor336 (renamed NpcTournamentSpectator):
    scripts.py (underworld:s611:0) plays soundId=0 on load, gated on RED_KNIGHT_DEFEATED == 0.
    line0 is wired into the standard TALKY/TALKING/DONE flow here, firing automatically rather
    than only when the save flag is unset, following the NpcOldSailor precedent.
    onItemInteractOrSoundFileDone plays soundId=1 once, gated on a local flag and save[RedBow].
    Npc has no touch/interact-triggered dialogue hook, so line1 is loaded and exposed via
    getLine1() for future wiring, following the NpcTownMerchant convention.
*/
public class NpcTournamentSpectator extends Npc {
    public NpcTournamentSpectatorAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;

    public NpcTournamentSpectator() {
        super(ActorType.FRIENDLY, true);
        setWidth(68);
        setHeight(54);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcTournamentSpectatorAnimation(this);
        this.line0 = World.sounds.getNpcTournamentSpectatorLine0();
        this.line1 = World.sounds.getNpcTournamentSpectatorLine1();
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
