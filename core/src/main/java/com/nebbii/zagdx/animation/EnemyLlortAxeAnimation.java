package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.World;

public class EnemyLlortAxeAnimation extends GameAnimation {
    private static final float COLLISION_BOX_SIZE = 10f;

    private Animation<TextureRegion> animation;

    private float[] defaultOffsetsX;
    private float[] defaultOffsetsY;

    public EnemyLlortAxeAnimation() {
        super("default");

        animation = initSpin();
    }

    @Override
    public TextureRegion playCurrentAnimation() {
        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);
        int frameIndex = animation.getKeyFrameIndex(stateTime % animation.getAnimationDuration());

        offsetX = defaultOffsetsX[frameIndex];
        offsetY = defaultOffsetsY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initSpin() {
        Texture[] textures = World.images.getEnemyLlortAxe();

        int[] frameData = {0, 1, 2, 3};

        TextureRegion[] frames = new TextureRegion[frameData.length];
        defaultOffsetsX = new float[frameData.length];
        defaultOffsetsY = new float[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            int textureIndex = frameData[i];
            Texture texture = textures[textureIndex];

            frames[i] = new TextureRegion(texture);
            defaultOffsetsX[i] = COLLISION_BOX_SIZE / 2f - texture.getWidth() / 2f;
            defaultOffsetsY[i] = COLLISION_BOX_SIZE / 2f - texture.getHeight() / 2f;
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
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
