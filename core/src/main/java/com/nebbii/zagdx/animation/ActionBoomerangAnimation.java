package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.World;

public class ActionBoomerangAnimation extends GameAnimation {
    private Animation<TextureRegion> animation;

    private int[] defaultOffsetsX;
    private int[] defaultOffsetsY;

    public ActionBoomerangAnimation() {
        super("default");

        baseOffsetX = -10;
        baseOffsetY = -5;
        offsetX = 0;
        offsetY = 0;

        animation = initSpin();
    }

    public TextureRegion playCurrentAnimation() {
        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        offsetX = defaultOffsetsX[frameIndex];
        offsetY = defaultOffsetsY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initSpin() {
        Texture[] textures = World.images.getProjectileBoomerang();

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        defaultOffsetsX = new int[frameData.length];
        defaultOffsetsY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            defaultOffsetsX[i] = frameData[i][1];
            defaultOffsetsY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.25f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public float getX() {
        return offsetX + baseOffsetX;
    }

    public float getY() {
        return offsetY + baseOffsetY;
    }
}
