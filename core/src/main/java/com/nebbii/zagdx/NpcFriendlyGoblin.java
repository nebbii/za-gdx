package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcFriendlyGoblinAnimation;

public class NpcFriendlyGoblin extends Npc {
    public NpcFriendlyGoblinAnimation animation;

    private float timer;
    private Sound line0;

    public NpcFriendlyGoblin() {
        super(ActorType.FRIENDLY, true);
        setWidth(72);
        setHeight(58);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcFriendlyGoblinAnimation(this);
        this.line0 = World.sounds.getNpcFriendlyGoblinLine0();
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
