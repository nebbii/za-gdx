package com.nebbii.zagdx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameAnimation {
    protected String currentAnimation;
    protected Animation<TextureRegion> animation;
    protected float stateTime;
    protected float baseOffsetX; // actor wide
    protected float baseOffsetY;
    protected float offsetX; // animation specific
    protected float offsetY;


    public GameAnimation(String currentAnimation) {
        this.currentAnimation = currentAnimation;
        stateTime = 0f;
        baseOffsetX = 0;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;
    }

    /*
     * Override this if you need more than one animation
     */
    public TextureRegion playCurrentAnimation() {
        play();
        return animation.getKeyFrame(stateTime, true);
    }

    public String getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(String animationString) {
        this.currentAnimation = animationString;
    }

    public void play() {
        stateTime += Gdx.graphics.getDeltaTime();
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(stateTime);
    }

    public float getStateTime() {
        return stateTime;
    }
}
