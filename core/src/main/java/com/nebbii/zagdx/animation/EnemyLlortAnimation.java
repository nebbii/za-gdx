package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyLlort;
import com.nebbii.zagdx.ImageLoader.EnemyLlortAnimationGroup;
import com.nebbii.zagdx.World;

public class EnemyLlortAnimation extends GameAnimation {
    private final EnemyLlort enemy;

    private Animation<TextureRegion> walk;
    private Animation<TextureRegion> attack;

    private int[] walkOffsetX;
    private int[] walkOffsetY;
    private int[] attackOffsetX;
    private int[] attackOffsetY;

    public EnemyLlortAnimation(EnemyLlort enemy) {
        super("walk");
        this.enemy = enemy;

        baseOffsetX = -46;
        baseOffsetY = -5;
        offsetX = 0;
        offsetY = 0;

        walk = initWalk();
        attack = initAttack();
    }

    public TextureRegion playCurrentAnimation() {
        int[] offsetsX;
        int[] offsetsY;

        switch (enemy.getEnemyState()) {
            case FIGHT:
                animation = attack;
                offsetsX = attackOffsetX;
                offsetsY = attackOffsetY;
                break;
            case SEARCH:
            case STOP:
                animation = walk;
                offsetsX = walkOffsetX;
                offsetsY = walkOffsetY;
                break;
            default:
                throw new IllegalStateException("Unhandled Llort enemy state");
        }

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        animation.setFrameDuration(getAnimationSpeed());

        offsetX = offsetsX[frameIndex];
        offsetY = offsetsY[frameIndex];

        return frame;
    }

    public float getX() {
        return enemy.getX() + baseOffsetX + offsetX;
    }

    public float getY() {
        return enemy.getY() + baseOffsetY + offsetY;
    }

    private Animation<TextureRegion> initWalk() {
        Texture[] textures = World.images.getEnemyLlortAnimation(EnemyLlortAnimationGroup.WALK);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 1},
            {2, 0, 2},
            {3, 0, 1},
            {4, 0, 0},
            {3, 0, 1},
            {2, 0, 2},
            {1, 0, 1}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkOffsetX = new int[frameData.length];
        walkOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkOffsetX[i] = frameData[i][1];
            walkOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> result = new Animation<>(0.14f, frames);
        result.setPlayMode(Animation.PlayMode.LOOP);
        return result;
    }

    private Animation<TextureRegion> initAttack() {
        Texture[] textures = World.images.getEnemyLlortAnimation(EnemyLlortAnimationGroup.ATTACK);

        int[][] frameData = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {4, 0, 0},
            {4, 0, 0},
            {5, 0, 0},
            {6, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        attackOffsetX = new int[frameData.length];
        attackOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            attackOffsetX[i] = frameData[i][1];
            attackOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> result = new Animation<>(0.14f, frames);
        result.setPlayMode(Animation.PlayMode.NORMAL);
        return result;
    }

    private float getAnimationSpeed() {
        switch(enemy.getEnemyState()) {
            case FIGHT:
                return 0.10f;
            case SEARCH:
            case STOP:
            default:
                return 0.10f;
        }
    }
}
