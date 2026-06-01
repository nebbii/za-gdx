package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.PickupFirestorm;
import com.nebbii.zagdx.World;

public class PickupFirestormAnimation extends GameAnimation {
    private final PickupFirestorm pickup;

    private Animation<TextureRegion> idle;

    private int[] idleOffsetX;
    private int[] idleOffsetY;

    public PickupFirestormAnimation(PickupFirestorm pickup) {
        super("idle");
        this.pickup = pickup;

        baseOffsetX = 0;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;

        idle = initIdle();
        animation = idle;
    }

    @Override
    public TextureRegion playCurrentAnimation() {
        animation = idle;
        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);
        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        offsetX = idleOffsetX[frameIndex];
        offsetY = idleOffsetY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initIdle() {
        Texture[] textures = World.images.getFirestormAnimation();

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        idleOffsetX = new int[frameData.length];
        idleOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            idleOffsetX[i] = frameData[i][1];
            idleOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.120f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public float getX() {
        return pickup.getX() + baseOffsetX + offsetX;
    }

    public float getY() {
        return pickup.getY() + baseOffsetY + offsetY;
    }
}
