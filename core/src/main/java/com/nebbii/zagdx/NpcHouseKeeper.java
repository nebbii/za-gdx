package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcHouseKeeperAnimation;

/*
npc.houseKeeper:
    scripts.py (overworld:w10:0) plays soundId=0 or soundId=1 on touch depending on a save flag
    (save[113], set once by this cell's onLeave) and resets after each interaction so the greeting
    can repeat. line0 is wired into the standard TALKY/TALKING/DONE flow here; line1 is loaded and
    exposed via getLine1() for future branch-triggered playback, following the NpcBlacksmith
    getLine1() convention. The save[113] branch itself is not modeled: this always plays line0.
*/
public class NpcHouseKeeper extends Npc {
    public NpcHouseKeeperAnimation animation;

    private float timer;
    private Sound line0;
    private Sound line1;

    public NpcHouseKeeper() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(59);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcHouseKeeperAnimation(this);
        this.line0 = World.sounds.getNpcHouseKeeperLine0();
        this.line1 = World.sounds.getNpcHouseKeeperLine1();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line0.pause();
            line1.stop();
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
