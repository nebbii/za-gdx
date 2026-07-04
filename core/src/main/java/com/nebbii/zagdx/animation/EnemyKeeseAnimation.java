package com.nebbii.zagdx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyKeese;
import com.nebbii.zagdx.ImageLoader.EnemyKeeseAnimationGroup;
import com.nebbii.zagdx.World;
import com.nebbii.zagdx.Enemy.EnemyState;

public class EnemyKeeseAnimation extends GameAnimation {
    private EnemyKeese enemy;
    private Animation<TextureRegion> fly;
    private int currentFrameIndex;
    private float stateTime;

    private final float[] xOffsets = {-15f, -15f, -16f, -15f, -16f, -15f, -16f, -15f};
    private final float[] yOffsets = {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};

    public EnemyKeeseAnimation(EnemyKeese enemy) {
        super("fly");
        this.enemy = enemy;

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 1},
            {2, 0, 2},
            {3, 0, 3},
            {4, 0, 4},
            {3, 0, 5},
            {2, 0, 6},
            {1, 0, 7}
        };

        this.fly = createAnimation(
            World.images.getEnemyKeeseAnimation(EnemyKeeseAnimationGroup.FLY),
            frameData
        );
    }

    private Animation<TextureRegion> createAnimation(Texture[] textures, int[][] frameData) {
        TextureRegion[] frames = new TextureRegion[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
        }

        return new Animation<>(getAnimationSpeed(), frames);
    }

    public TextureRegion playCurrentAnimation() {
        if (!enemy.isHoldingGoal()) {
            stateTime = Gdx.graphics.getDeltaTime();
        }

        fly.setFrameDuration(getAnimationSpeed());
        currentFrameIndex = fly.getKeyFrameIndex(stateTime);

        return fly.getKeyFrame(stateTime, true);
    }

    public TextureRegion playFirstFrame() {
        currentFrameIndex = fly.getKeyFrameIndex(0f);
        return fly.getKeyFrame(0f, true);
    }

    public float getX() {
        return enemy.getX() + xOffsets[currentFrameIndex];
    }

    public float getY() {
        return enemy.getY() + yOffsets[currentFrameIndex];
    }

    private float getAnimationSpeed() {
        return enemy.getAnimationSpeed();
    }
}
