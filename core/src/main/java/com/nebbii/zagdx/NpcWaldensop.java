package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcWaldensopAnimation;

/*
npc.unknownActor118 (renamed NpcWaldensop):
    scripts.py (overworld:n11:0) plays soundId=0 automatically on load when
    save[HAS_ENTERED_WHITE_STEED_LODGE] == 0 and save[RingsOfFire] == 0, or soundId=2 when the
    lodge has been entered and save[RingsOfFire] == 0. line0 is wired into the standard
    TALKY/TALKING/DONE flow here (the pre-lodge line), firing automatically rather than only
    when the save flags match, following the NpcOldSailor precedent. line2 (the post-lodge
    alternate) and line1 (an item-interact-triggered follow-up, requiring a touch/interact hook
    Npc does not have) are loaded and exposed via getLine1()/getLine2() for future flag-driven
    wiring.
*/
public class NpcWaldensop extends Npc {
    public NpcWaldensopAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;
    private Sound line2;

    public NpcWaldensop() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(48);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcWaldensopAnimation(this);
        this.line0 = World.sounds.getNpcWaldensopLine0();
        this.line1 = World.sounds.getNpcWaldensopLine1();
        this.line2 = World.sounds.getNpcWaldensopLine2();
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

    public Sound getLine2() {
        return line2;
    }
}
