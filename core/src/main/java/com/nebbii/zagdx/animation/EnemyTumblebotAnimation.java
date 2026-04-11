package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyTumblebot;
import com.nebbii.zagdx.ImageLoader.EnemyTumblebotAnimationGroup;
import com.nebbii.zagdx.World;

public class EnemyTumblebotAnimation extends GameAnimation {
    private final EnemyTumblebot enemy;

    private Animation<TextureRegion> rollUp;
    private Animation<TextureRegion> rollRight;
    private Animation<TextureRegion> rollDown;
    private Animation<TextureRegion> rollLeft;

    private int[] rollUpOffsetX;
    private int[] rollUpOffsetY;
    private int[] rollRightOffsetX;
    private int[] rollRightOffsetY;
    private int[] rollDownOffsetX;
    private int[] rollDownOffsetY;
    private int[] rollLeftOffsetX;
    private int[] rollLeftOffsetY;

    public EnemyTumblebotAnimation(EnemyTumblebot enemy) {
        super("rollDown");
        this.enemy = enemy;

        baseOffsetX = 0;
        baseOffsetY = 13;
        offsetX = 0;
        offsetY = 0;

        rollUp = initRollUp();
        rollRight = initRollRight();
        rollDown = initRollDown();
        rollLeft = initRollLeft();
    }

    public TextureRegion playCurrentAnimation() {
        int[] offsetsX;
        int[] offsetsY;

        switch (enemy.getDirection()) {
            case UP:
                animation = rollUp;
                offsetsX = rollUpOffsetX;
                offsetsY = rollUpOffsetY;
                break;
            case RIGHT:
                animation = rollRight;
                offsetsX = rollRightOffsetX;
                offsetsY = rollRightOffsetY;
                break;
            case DOWN:
                animation = rollDown;
                offsetsX = rollDownOffsetX;
                offsetsY = rollDownOffsetY;
                break;
            case LEFT:
                animation = rollLeft;
                offsetsX = rollLeftOffsetX;
                offsetsY = rollLeftOffsetY;
                break;
            default:
                throw new IllegalStateException("Unhandled tumblebot direction");
        }

        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        offsetX = offsetsX[frameIndex];
        offsetY = offsetsY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initRollUp() {
        Texture[] textures = World.images.getEnemyTumblebotAnimation(EnemyTumblebotAnimationGroup.ROLLUP);

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
        rollUpOffsetX = new int[frameData.length];
        rollUpOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            rollUpOffsetX[i] = frameData[i][1];
            rollUpOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initRollRight() {
        Texture[] textures = World.images.getEnemyTumblebotAnimation(EnemyTumblebotAnimationGroup.ROLLRIGHT);

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
        rollRightOffsetX = new int[frameData.length];
        rollRightOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            rollRightOffsetX[i] = frameData[i][1];
            rollRightOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initRollDown() {
        Texture[] textures = World.images.getEnemyTumblebotAnimation(EnemyTumblebotAnimationGroup.ROLLDOWN);

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
        rollDownOffsetX = new int[frameData.length];
        rollDownOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            rollDownOffsetX[i] = frameData[i][1];
            rollDownOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initRollLeft() {
        Texture[] textures = World.images.getEnemyTumblebotAnimation(EnemyTumblebotAnimationGroup.ROLLLEFT);

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
        rollLeftOffsetX = new int[frameData.length];
        rollLeftOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            rollLeftOffsetX[i] = frameData[i][1];
            rollLeftOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public float getX() {
        return enemy.getX() - enemy.getWidth() / 2f + offsetX + baseOffsetX;
    }

    public float getY() {
        return enemy.getY() - enemy.getHeight() / 2f + offsetY + baseOffsetY;
    }
}
