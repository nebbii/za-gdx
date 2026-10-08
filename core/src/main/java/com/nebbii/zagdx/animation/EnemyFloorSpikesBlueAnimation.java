package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyFloorSpikesBlue;
import com.nebbii.zagdx.ImageLoader.EnemyFloorSpikesBlueAnimationGroup;
import com.nebbii.zagdx.World;

// TODO: extractor reported 14 frames, but only 8 sprite files exist on disk; using the 8 real frames
public class EnemyFloorSpikesBlueAnimation extends GameAnimation {
    private final EnemyFloorSpikesBlue enemy;

    private Animation<TextureRegion> idle;

    private int[] idleOffsetX;
    private int[] idleOffsetY;

    public EnemyFloorSpikesBlueAnimation(EnemyFloorSpikesBlue enemy) {
        super("idle");
        this.enemy = enemy;

        baseOffsetX = 0;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;

        idle = initIdle();
    }

    public TextureRegion playCurrentAnimation() {
        animation = idle;

        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        animation.setFrameDuration(getAnimationSpeed());

        offsetX = idleOffsetX[frameIndex];
        offsetY = idleOffsetY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initIdle() {
        Texture[] textures = World.images.getEnemyFloorSpikesBlueAnimation(EnemyFloorSpikesBlueAnimationGroup.IDLE);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {5, 0, 0},
            {6, 0, 0},
            {7, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        idleOffsetX = new int[frameData.length];
        idleOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            idleOffsetX[i] = frameData[i][1];
            idleOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private float getAnimationSpeed() {
        switch(enemy.getEnemyState()) {
            case FIGHT:
                return 0.06f;
            case SEARCH:
            default:
                return 0.10f;
        }
    }

    public float getX() {
        return enemy.getX() - enemy.getWidth() / 2f + offsetX + baseOffsetX;
    }

    public float getY() {
        return enemy.getY() - enemy.getHeight() / 2f + offsetY + baseOffsetY;
    }
}
