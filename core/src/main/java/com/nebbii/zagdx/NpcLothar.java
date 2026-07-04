package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcLotharAnimation;

public class NpcLothar extends Npc {
    public NpcLotharAnimation animation;

    private Sound line8;
    private Sound line9;
    private float timer;
    private int currentLine;

    public NpcLothar() {
        super(ActorType.FRIENDLY, false);
        setWidth(32);
        setHeight(32);
        setNpcState(NpcState.TALKY);
        this.timer = 0;
        this.currentLine = 0;

        this.line8 = World.sounds.getNpcLotharLine8();
        this.line9 = World.sounds.getNpcLotharLine9();
        this.animation = new NpcLotharAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();

        if (!isActive()) {
            line8.stop();
            line9.stop();
            return;
        }

        switch(npcState) {
            case TALKY:
                if (currentLine == 0) {
                    line8.play();
                }
                else if (currentLine == 1) {
                    line9.play();
                }

                setNpcState(NpcState.TALKING);
                break;
            case TALKING:
                timer += Gdx.graphics.getDeltaTime();
                if (currentLine == 0 && timer > 10f) {
                    currentLine = 1;
                    setNpcState(NpcState.TALKY);
                }

                if (timer > 14f) {
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
