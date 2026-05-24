package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcTalkingChestAnimation;

public class NpcTalkingChest extends Npc {
    public NpcTalkingChestAnimation animation;

    private Sound sound;
    private float timer;

    public NpcTalkingChest() {
        super(ActorType.FRIENDLY, false);
        setWidth(43);
        setHeight(40);
        setNpcState(NpcState.TALKY);
        this.timer = 0;

        this.animation = new NpcTalkingChestAnimation(this);
        this.sound = World.sounds.getNpcTalkingChestLine0();
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) {
            sound.stop();
            return;
        }

        switch(npcState) {
            case TALKY:
                sound.play();
                setNpcState(NpcState.TALKING);
                break;
            case TALKING:
                timer += Gdx.graphics.getDeltaTime();
                if (timer > 6) {
                    Gdx.app.log(this.getClass().getSimpleName(), "spawn the ring!");
                    setNpcState(NpcState.DONE);
                }
                break;
            case DONE:
                sound.stop();
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
