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
            sound.pause();
            return;
        }

        switch(npcState) {
            case TALKY:
                sound.play();
                setNpcState(NpcState.TALKING);
                break;
            case TALKING:
                sound.resume();
                timer += Gdx.graphics.getDeltaTime();
                if (timer > 6f) {
                    for (Spawner spawner : map.findActiveActorsByType(Spawner.class)) {
                        spawner.activate();
                    }

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
