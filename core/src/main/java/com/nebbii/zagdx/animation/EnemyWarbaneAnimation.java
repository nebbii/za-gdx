package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyWarbane;
import com.nebbii.zagdx.ImageLoader.EnemyWarbaneAnimationGroup;
import com.nebbii.zagdx.World;

// TODO: extractor reported 8 frames, but only 5 sprite files exist on disk; using the real count
public class EnemyWarbaneAnimation extends GameAnimation {
    private final EnemyWarbane enemy;

    private Animation<TextureRegion> walk;

    private int[] walkOffsetX;
    private int[] walkOffsetY;

    public EnemyWarbaneAnimation(EnemyWarbane enemy) {
        super("walk");
        this.enemy = enemy;

        baseOffsetX = 0;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;

        walk = initWalk();
    }

    public TextureRegion playCurrentAnimation() {
        animation = walk;

        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        animation.setFrameDuration(getAnimationSpeed());

        offsetX = walkOffsetX[frameIndex];
        offsetY = walkOffsetY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initWalk() {
        Texture[] textures = World.images.getEnemyWarbaneAnimation(EnemyWarbaneAnimationGroup.WALK);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {3, 0, 0},
            {2, 0, 0},
            {1, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkOffsetX = new int[frameData.length];
        walkOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkOffsetX[i] = frameData[i][1];
            walkOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.12f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private float getAnimationSpeed() {
        switch(enemy.getEnemyState()) {
            case FIGHT:
                return 0.08f;
            case SEARCH:
            default:
                return 0.12f;
        }
    }

    public float getX() {
        return enemy.getX() - enemy.getWidth() / 2f + offsetX + baseOffsetX;
    }

    public float getY() {
        return enemy.getY() - enemy.getHeight() / 2f + offsetY + baseOffsetY;
    }
}
