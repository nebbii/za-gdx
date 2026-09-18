package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcTownMerchantAnimation;

/*
npc.townMerchant:
    scripts.py (overworld:k14:0) has no onLoad dialogue of its own; it plays soundId=6 on touch,
    then chains soundId=7 then soundId=8 on further item interacts. onLoad only conditionally
    despawns once both the candle and life potion sold from this cell have been purchased. Npc
    has no touch/interact-triggered dialogue hook (see Npc.onOverlap()), and the candle/life
    potion purchase flow (item_treasure_candle, item_treasure_lifePotion in the same cell) is not
    modeled. line6/line7/line8 are loaded and exposed via getLine6()/getLine7()/getLine8() for
    future wiring, following the NpcFatShopkeeper convention. Implemented as a basic idle actor
    otherwise.
*/
public class NpcTownMerchant extends Npc {
    public NpcTownMerchantAnimation animation;

    private Sound line6;
    private Sound line7;
    private Sound line8;

    public NpcTownMerchant() {
        super(ActorType.FRIENDLY, true);
        setWidth(68);
        setHeight(58);

        this.animation = new NpcTownMerchantAnimation(this);
        this.line6 = World.sounds.getNpcTownMerchantLine6();
        this.line7 = World.sounds.getNpcTownMerchantLine7();
        this.line8 = World.sounds.getNpcTownMerchantLine8();
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }

    public Sound getLine6() {
        return line6;
    }

    public Sound getLine7() {
        return line7;
    }

    public Sound getLine8() {
        return line8;
    }
}
