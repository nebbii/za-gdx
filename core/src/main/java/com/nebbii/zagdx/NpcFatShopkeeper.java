package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcFatShopkeeperAnimation;

/*
npc.shopkeeperAndKnave.unknownActor73 (renamed NpcFatShopkeeper):
    scripts.py (overworld:k13a:1) has no onLoad dialogue; it only plays soundId=5 on touch and
    soundId=6 on item interact. Npc currently has no touch/interact-triggered dialogue hook (see
    Npc.onOverlap()), so no NpcState/TALKY flow is wired here. line5/line6 are loaded and exposed
    via getLine5()/getLine6() for future touch/interact-triggered playback, following the
    NpcBlacksmith getLine1() convention. Implemented as a basic idle actor otherwise.
*/
public class NpcFatShopkeeper extends Npc {
    public NpcFatShopkeeperAnimation animation;

    private Sound line5;
    private Sound line6;

    public NpcFatShopkeeper() {
        super(ActorType.FRIENDLY, true);
        setWidth(60);
        setHeight(60);

        this.animation = new NpcFatShopkeeperAnimation(this);
        this.line5 = World.sounds.getNpcFatShopkeeperLine5();
        this.line6 = World.sounds.getNpcFatShopkeeperLine6();
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }

    public Sound getLine5() {
        return line5;
    }

    public Sound getLine6() {
        return line6;
    }
}
