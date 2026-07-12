package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcFarmerAnimation;

public class NpcFarmer extends Npc {
    public NpcFarmerAnimation animation;

    private float timer;
    private Sound line0;

    public NpcFarmer() {
        super(ActorType.FRIENDLY, false);
        setWidth(32);
        setHeight(32);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcFarmerAnimation(this);
        this.line0 = World.sounds.getNpcFarmerLine0();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            line0.stop();
            return;
        }

        switch(npcState) {
            case TALKY:
                timer = 0;
                line0.play();
                setNpcState(NpcState.TALKING);
                break;
            case TALKING:
                timer += Gdx.graphics.getDeltaTime();
                if (timer > 7f) {
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
