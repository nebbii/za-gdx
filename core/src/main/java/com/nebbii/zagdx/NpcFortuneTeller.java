package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcFortuneTellerAnimation;

/*
npc.unknownActor325 (renamed NpcFortuneTeller, low confidence; carnival/market-themed NPC in the
    Shrine of Power dungeon hub cast alongside NpcStrongman):
    scripts.py (underworld:s603:2) has no onLoad dialogue; onTouchOrPushBlockStoppedMoving plays
    soundId=3 once, gated by local save flags. onItemInteractOrSoundFileDone then chains an
    unmodeled compass-reward hand-off (item_compass_s6, cast[3]) the first time the sixth compass
    hasn't already been collected. Npc has no touch/interact-triggered dialogue hook, so line3 is
    loaded and exposed via getLine3() for future wiring, following the NpcTownMerchant convention.
    Implemented as a basic idle actor otherwise; the compass-reward chain is not modeled.
*/
public class NpcFortuneTeller extends Npc {
    public NpcFortuneTellerAnimation animation;

    private Sound line3;

    public NpcFortuneTeller() {
        super(ActorType.FRIENDLY, true);
        setWidth(34);
        setHeight(26);

        this.animation = new NpcFortuneTellerAnimation(this);
        this.line3 = World.sounds.getNpcFortuneTellerLine3();
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }

    public Sound getLine3() {
        return line3;
    }
}
