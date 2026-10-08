package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcTwinsFatherAnimation;

/*
npc.unknownActor96 (renamed NpcTwinsFather):
    scripts.py (overworld:l14a:0) plays soundId=0 unconditionally on load. line0 is wired into
    the standard TALKY/TALKING/DONE flow here. onItemInteractOrSoundFileDone then chains
    soundId=1 then soundId=2 as the player interacts further, which is not modeled since Npc has
    no touch/interact-triggered dialogue hook; line1/line2 are loaded and exposed via getLine1()/
    getLine2() for future wiring, following the NpcTownMerchant convention.
*/
public class NpcTwinsFather extends Npc {
    public NpcTwinsFatherAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;
    private Sound line2;

    public NpcTwinsFather() {
        super(ActorType.FRIENDLY, true);
        setWidth(48);
        setHeight(44);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcTwinsFatherAnimation(this);
        this.line0 = World.sounds.getNpcTwinsFatherLine0();
        this.line1 = World.sounds.getNpcTwinsFatherLine1();
        this.line2 = World.sounds.getNpcTwinsFatherLine2();
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
