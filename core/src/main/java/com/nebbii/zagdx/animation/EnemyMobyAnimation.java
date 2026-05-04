package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyMoby;
import com.nebbii.zagdx.ImageLoader.EnemyMobyAnimationGroup;
import com.nebbii.zagdx.World;

// TODO: align animation frames
public class EnemyMobyAnimation extends GameAnimation {
    private final EnemyMoby enemy;

    private Animation<TextureRegion> flyUp;
    private Animation<TextureRegion> flyRight;
    private Animation<TextureRegion> flyDown;
    private Animation<TextureRegion> flyLeft;

    private int[] flyUpOffsetX;
    private int[] flyUpOffsetY;
    private int[] flyRightOffsetX;
    private int[] flyRightOffsetY;
    private int[] flyDownOffsetX;
    private int[] flyDownOffsetY;
    private int[] flyLeftOffsetX;
    private int[] flyLeftOffsetY;

    public EnemyMobyAnimation(EnemyMoby enemy) {
        super("flyDown");
        this.enemy = enemy;

        baseOffsetX = 0;
        baseOffsetY = 13;
        offsetX = 0;
        offsetY = 0;

        flyUp = initFlyUp();
        flyRight = initFlyRight();
        flyDown = initFlyDown();
        flyLeft = initFlyLeft();
    }

    public TextureRegion playCurrentAnimation() {
        int[] offsetsX;
        int[] offsetsY;

        switch (enemy.getDirection()) {
            case UP:
                animation = flyUp;
                offsetsX = flyUpOffsetX;
                offsetsY = flyUpOffsetY;
                break;
            case RIGHT:
                animation = flyRight;
                offsetsX = flyRightOffsetX;
                offsetsY = flyRightOffsetY;
                break;
            case DOWN:
                animation = flyDown;
                offsetsX = flyDownOffsetX;
                offsetsY = flyDownOffsetY;
                break;
            case LEFT:
                animation = flyLeft;
                offsetsX = flyLeftOffsetX;
                offsetsY = flyLeftOffsetY;
                break;
            default:
                throw new IllegalStateException("Unhandled moby direction");
        }

        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        animation.setFrameDuration(getAnimationSpeed());

        offsetX = offsetsX[frameIndex];
        offsetY = offsetsY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initFlyUp() {
        Texture[] textures = World.images.getEnemyMobyAnimation(EnemyMobyAnimationGroup.FLYUP);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        flyUpOffsetX = new int[frameData.length];
        flyUpOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            flyUpOffsetX[i] = frameData[i][1];
            flyUpOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initFlyRight() {
        Texture[] textures = World.images.getEnemyMobyAnimation(EnemyMobyAnimationGroup.FLYRIGHT);

        int[][] frameData = {
            {0, 0, 5},
            {1, 0, 5},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        flyRightOffsetX = new int[frameData.length];
        flyRightOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            flyRightOffsetX[i] = frameData[i][1];
            flyRightOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initFlyDown() {
        Texture[] textures = World.images.getEnemyMobyAnimation(EnemyMobyAnimationGroup.FLYDOWN);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        flyDownOffsetX = new int[frameData.length];
        flyDownOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            flyDownOffsetX[i] = frameData[i][1];
            flyDownOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initFlyLeft() {
        Texture[] textures = World.images.getEnemyMobyAnimation(EnemyMobyAnimationGroup.FLYLEFT);

        int[][] frameData = {
            {0, 0, 5},
            {1, 0, 5},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        flyLeftOffsetX = new int[frameData.length];
        flyLeftOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            flyLeftOffsetX[i] = frameData[i][1];
            flyLeftOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private float getAnimationSpeed() {
        switch(enemy.getEnemyState()) {
            case FIGHT:
                return 0.05f;
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
