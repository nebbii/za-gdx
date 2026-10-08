package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcPurpleRavenAnimation;

/*
npc.unknownActor166 (renamed NpcPurpleRaven):
    scripts.py (overworld:s301:0, overworld:z5:0 and underworld equivalents) has no
    playVoiceLine calls at all. Its onTouchOrPushBlockStoppedMoving teleports the player to
    another cell (a "BIRD" travel-trigger marker per extraData), and onLoad conditionally spawns
    one of two casts depending on save state. This is a map teleport trigger, not a talking NPC;
    prior visual inspection also identified the sprite as a bird rather than a humanoid.
    Implemented as a simple non-solid ambient idle actor with no dialogue.
*/
public class NpcPurpleRaven extends Npc {
    public NpcPurpleRavenAnimation animation;

    public NpcPurpleRaven() {
        super(ActorType.FRIENDLY, false);
        setWidth(180);
        setHeight(130);

        this.animation = new NpcPurpleRavenAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
