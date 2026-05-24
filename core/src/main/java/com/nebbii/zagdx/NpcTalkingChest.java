package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcTalkingChestAnimation;

public class NpcTalkingChest extends Npc {
    public NpcTalkingChestAnimation animation;

    public NpcTalkingChest() {
        super(ActorType.FRIENDLY, false);
        setWidth(43);
        setHeight(40);

        this.animation = new NpcTalkingChestAnimation(this);

        this.npcState = NpcState.TALKY;
    }

    @Override
    public void logic() {
        switch(npcState) {
            case TALKY:
                break;
            case TALKING:
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
