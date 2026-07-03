package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.Direction;
import com.nebbii.zagdx.World;

public class FriendlyCalmAnimation extends GameAnimation {
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

    public FriendlyCalmAnimation() {
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
        play();

        Animation<TextureRegion> animation;
        int[] offsetsX;
        int[] offsetsY;

        switch(direction) {
        case UP:
            animation = up;
            offsetsX = upOffsetX;
            offsetsY = upOffsetY;
            break;
        case RIGHT:
            animation = right;
            offsetsX = rightOffsetX;
            offsetsY = rightOffsetY;
            break;
        case DOWN:
            animation = down;
            offsetsX = downOffsetX;
            offsetsY = downOffsetY;
            break;
        case LEFT:
            animation = left;
            offsetsX = leftOffsetX;
            offsetsY = leftOffsetY;
            break;
        default:
            throw new IllegalStateException(getClass().getSimpleName() + ": Unhandled direction: " + direction);
        }

        int frameIndex = animation.getKeyFrameIndex(stateTime);
        offsetX = offsetsX[frameIndex];
        offsetY = offsetsY[frameIndex];

        return animation.getKeyFrame(stateTime, true);
    }

    private Animation<TextureRegion> initUp() {
        int[][] frameData = {
            {0, -20, -8},
            {1, -20, -8},
            {2, -20, -8}
        };

        upOffsetX = new int[frameData.length];
        upOffsetY = new int[frameData.length];
        return initAnimation(0, frameData, upOffsetX, upOffsetY);
    }

    private Animation<TextureRegion> initRight() {
        int[][] frameData = {
            {0, -26, -2},
            {1, -26, -2},
            {2, -26, -2}
        };

        rightOffsetX = new int[frameData.length];
        rightOffsetY = new int[frameData.length];
        return initAnimation(1, frameData, rightOffsetX, rightOffsetY);
    }

    private Animation<TextureRegion> initDown() {
        int[][] frameData = {
            {0, -20, -4},
            {1, -20, -4},
            {2, -20, -4}
        };

        downOffsetX = new int[frameData.length];
        downOffsetY = new int[frameData.length];
        return initAnimation(2, frameData, downOffsetX, downOffsetY);
    }

    private Animation<TextureRegion> initLeft() {
        int[][] frameData = {
            {0, -10, -2},
            {1, -10, -2},
            {2, -10, -2}
        };

        leftOffsetX = new int[frameData.length];
        leftOffsetY = new int[frameData.length];
        return initAnimation(3, frameData, leftOffsetX, leftOffsetY);
    }

    private Animation<TextureRegion> initAnimation(int group, int[][] frameData, int[] offsetsX, int[] offsetsY) {
        Texture[] textures = World.images.getFriendlyCalm()[group];
        TextureRegion[] frames = new TextureRegion[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            offsetsX[i] = frameData[i][1];
            offsetsY[i] = frameData[i][2];
        }

        Animation<TextureRegion> animation = new Animation<>(0.2f, frames);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        return animation;
    }

    public float getX() {
        return offsetX + baseOffsetX;
    }

    public float getY() {
        return offsetY + baseOffsetY;
    }
}
