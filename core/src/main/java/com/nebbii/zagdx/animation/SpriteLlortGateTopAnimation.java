package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.ImageLoader.SpriteLlortGateTopAnimationGroup;
import com.nebbii.zagdx.SpriteLlortGateTop;
import com.nebbii.zagdx.World;

public class SpriteLlortGateTopAnimation extends GameAnimation {
    private final SpriteLlortGateTop sprite;

    private Animation<TextureRegion> idle;

    private int[] idleOffsetX;
    private int[] idleOffsetY;

    public SpriteLlortGateTopAnimation(SpriteLlortGateTop sprite) {
        super("idle");
        this.sprite = sprite;

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

        int frameIndex = animation.getKeyFrameIndex(stateTime);
        offsetX = idleOffsetX[frameIndex];
        offsetY = idleOffsetY[frameIndex];

        return animation.getKeyFrame(stateTime, true);
    }

    private Animation<TextureRegion> initIdle() {
        Texture[] textures = World.images.getSpriteLlortGateTopAnimation(SpriteLlortGateTopAnimationGroup.IDLE);

        int[][] frameData = {
            {0, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        idleOffsetX = new int[frameData.length];
        idleOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            int textureIndex = frameData[i][0];

            frames[i] = new TextureRegion(textures[textureIndex]);
            idleOffsetX[i] = frameData[i][1];
            idleOffsetY[i] = frameData[i][2];
        }

        return new Animation<TextureRegion>(0.12f, frames);
    }

    public float getX() {
        return sprite.getX() + baseOffsetX + offsetX;
    }

    public float getY() {
        return sprite.getY() + baseOffsetY + offsetY;
    }
}
