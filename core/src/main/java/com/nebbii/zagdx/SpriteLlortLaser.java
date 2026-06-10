package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.SpriteLlortLaserAnimation;

public class SpriteLlortLaser extends Sprite {
    public SpriteLlortLaserAnimation animation;

    public SpriteLlortLaser() {
        super(ActorType.FRIENDLY, false);
        setDamage(50);
        setWidth(103);
        setHeight(17);

        this.animation = new SpriteLlortLaserAnimation(this);

    }

    @Override
    public void logic() {
        super.logic();
        SaveManager saveManager = map.getSaveManager();

        // extra check in case the laser is on screen with the defeated sardak
        if (saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakBlue", "permadead")
            && saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakRed", "permadead")
            && saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakYellow", "permadead"))
        {
            setState(State.DEAD);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }

    public Direction getDirection() {
        return Direction.DOWN;
    }
}
