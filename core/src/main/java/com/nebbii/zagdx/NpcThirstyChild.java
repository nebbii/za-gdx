package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcThirstyChildAnimation;

/*
npc.thirstyChild:
    scripts.py (underworld:s605:0) plays soundId=0 automatically on load, gated on
    save[Hammer] == 0. line0 is wired into the standard TALKY/TALKING/DONE flow here, firing
    automatically rather than only when the save flag is unset, following the NpcOldSailor
    precedent. Cell.onTouchTrigger separately plays soundId=1 through cast[0] (this actor) when
    the player steps on a trigger tile with a specific local-flag combination set; that
    cell-triggered chain is not modeled since it depends on Cell-level trigger geometry. line1 is
    loaded and exposed via getLine1() for future wiring.
*/
public class NpcThirstyChild extends Npc {
    public NpcThirstyChildAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;

    public NpcThirstyChild() {
        super(ActorType.FRIENDLY, true);
        setWidth(52);
        setHeight(44);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcThirstyChildAnimation(this);
        this.line0 = World.sounds.getNpcThirstyChildLine0();
        this.line1 = World.sounds.getNpcThirstyChildLine1();
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
