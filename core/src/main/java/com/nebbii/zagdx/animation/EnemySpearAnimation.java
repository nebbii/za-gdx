package com.nebbii.zagdx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.Direction;
import com.nebbii.zagdx.World;

public class EnemySpearAnimation extends GameAnimation {
    private Animation<TextureRegion> up;
    private Animation<TextureRegion> right;
    private Animation<TextureRegion> down;
    private Animation<TextureRegion> left;

    private int[] upOffsetX;
    private int[] upOffsetY;
    private int[] rightOffsetX;
    private int[] rightOffsetY;
    private int[] downOffsetX;
    private int[] downOffsetY;
    private int[] leftOffsetX;
    private int[] leftOffsetY;

    private float stateTime;

    public EnemySpearAnimation() {
        super("up");

        baseOffsetX = 0;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;

        up = initUp();
        right = initRight();
        down = initDown();
        left = initLeft();
    }

    public TextureRegion playCurrentAnimation(Direction direction) {
        stateTime += Gdx.graphics.getDeltaTime();

        Animation<TextureRegion> currentAnimation;
        int[] offsetsX;
        int[] offsetsY;

        switch(direction) {
        case UP:
            currentAnimation = up;
            offsetsX = upOffsetX;
            offsetsY = upOffsetY;
            break;
        case RIGHT:
            currentAnimation = right;
            offsetsX = rightOffsetX;
            offsetsY = rightOffsetY;
            break;
        case DOWN:
            currentAnimation = down;
            offsetsX = downOffsetX;
            offsetsY = downOffsetY;
            break;
        case LEFT:
            currentAnimation = left;
            offsetsX = leftOffsetX;
            offsetsY = leftOffsetY;
            break;
        default:
            currentAnimation = down;
            offsetsX = downOffsetX;
            offsetsY = downOffsetY;
            break;
        }

        int frameIndex = currentAnimation.getKeyFrameIndex(stateTime);
        offsetX = offsetsX[frameIndex];
        offsetY = offsetsY[frameIndex];

        return currentAnimation.getKeyFrame(stateTime, true);
    }

    private Animation<TextureRegion> initUp() {
        Texture[] textures = World.images.getEnemySpear();
        int[][] frameData = {
            {0, -5, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        upOffsetX = new int[frameData.length];
        upOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            upOffsetX[i] = frameData[i][1];
            upOffsetY[i] = frameData[i][2];
        }

        return new Animation<>(0.2f, frames);
    }

    private Animation<TextureRegion> initRight() {
        Texture[] textures = World.images.getEnemySpear();
        int[][] frameData = {
            {1, 0, -5}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        rightOffsetX = new int[frameData.length];
        rightOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            rightOffsetX[i] = frameData[i][1];
            rightOffsetY[i] = frameData[i][2];
        }

        return new Animation<>(0.2f, frames);
    }

    private Animation<TextureRegion> initDown() {
        Texture[] textures = World.images.getEnemySpear();
        int[][] frameData = {
            {2, -5, -42}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        downOffsetX = new int[frameData.length];
        downOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            downOffsetX[i] = frameData[i][1];
            downOffsetY[i] = frameData[i][2];
        }

        return new Animation<>(0.2f, frames);
    }

    private Animation<TextureRegion> initLeft() {
        Texture[] textures = World.images.getEnemySpear();
        int[][] frameData = {
            {3, -42, -5}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        leftOffsetX = new int[frameData.length];
        leftOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            leftOffsetX[i] = frameData[i][1];
            leftOffsetY[i] = frameData[i][2];
        }

        return new Animation<>(0.2f, frames);
    }

    public float getX() {
        return offsetX + baseOffsetX;
    }

    public float getY() {
        return offsetY + baseOffsetY;
    }
}
