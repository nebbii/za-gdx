package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcZeldaSlidingAnimation;

/*
npc.zeldaSliding (underworld:s202:0):
    Despite the name, this is not a normal background NPC. scripts.py's only handler is
    onDeathOrRaftRideFinished, which re-spawns the regular cell cast (cast[1..3]) and restores
    player visibility/input once a slide sequence completes; Cell.onEntry spawns this actor in
    place of the player (hiding/disabling the player) while the slide plays out. It is a
    special-case animation standing in for the player character during a scripted slide, not an
    interactable NPC. Kept intentionally simple as a plain idle stub rather than over-engineering
    a cutscene/player-substitution system; solid=false since it never blocks movement in its own
    right.
*/
public class NpcZeldaSliding extends Npc {
    public NpcZeldaSlidingAnimation animation;

    public NpcZeldaSliding() {
        super(ActorType.FRIENDLY, false);
        setWidth(80);
        setHeight(58);

        this.animation = new NpcZeldaSlidingAnimation(this);
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
