package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyGoriya;
import com.nebbii.zagdx.ImageLoader.EnemyGoriyaAnimationGroup;
import com.nebbii.zagdx.World;

public class EnemyGoriyaAnimation extends GameAnimation {
    private final EnemyGoriya enemy;

    private Animation<TextureRegion> walkUp;
    private Animation<TextureRegion> walkRight;
    private Animation<TextureRegion> walkDown;
    private Animation<TextureRegion> walkLeft;

    private int[] walkUpOffsetX;
    private int[] walkUpOffsetY;
    private int[] walkRightOffsetX;
    private int[] walkRightOffsetY;
    private int[] walkDownOffsetX;
    private int[] walkDownOffsetY;
    private int[] walkLeftOffsetX;
    private int[] walkLeftOffsetY;

    public EnemyGoriyaAnimation(EnemyGoriya enemy) {
        super("walkDown");
        this.enemy = enemy;

        baseOffsetX = 20;
        baseOffsetY = 20;
        offsetX = 0;
        offsetY = 0;

        walkUp = initwalkUp();
        walkRight = initwalkRight();
        walkDown = initwalkDown();
        walkLeft = initwalkLeft();
    }

    public TextureRegion playCurrentAnimation() {
        int[] offsetsX;
        int[] offsetsY;

        switch (enemy.getDirection()) {
            case UP:
                animation = walkUp;
                offsetsX = walkUpOffsetX;
                offsetsY = walkUpOffsetY;
                break;
            case RIGHT:
                animation = walkRight;
                offsetsX = walkRightOffsetX;
                offsetsY = walkRightOffsetY;
                break;
            case DOWN:
                animation = walkDown;
                offsetsX = walkDownOffsetX;
                offsetsY = walkDownOffsetY;
                break;
            case LEFT:
                animation = walkLeft;
                offsetsX = walkLeftOffsetX;
                offsetsY = walkLeftOffsetY;
                break;
            default:
                throw new IllegalStateException("Unhandled Goriya direction");
        }

        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        offsetX = offsetsX[frameIndex];
        offsetY = offsetsY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initwalkUp() {
        Texture[] textures = World.images.getEnemyGoriyaAnimation(EnemyGoriyaAnimationGroup.WALKUP);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkUpOffsetX = new int[frameData.length];
        walkUpOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkUpOffsetX[i] = frameData[i][1];
            walkUpOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.25f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initwalkRight() {
        Texture[] textures = World.images.getEnemyGoriyaAnimation(EnemyGoriyaAnimationGroup.WALKRIGHT);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkRightOffsetX = new int[frameData.length];
        walkRightOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkRightOffsetX[i] = frameData[i][1];
            walkRightOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.25f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initwalkDown() {
        Texture[] textures = World.images.getEnemyGoriyaAnimation(EnemyGoriyaAnimationGroup.WALKDOWN);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkDownOffsetX = new int[frameData.length];
        walkDownOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkDownOffsetX[i] = frameData[i][1];
            walkDownOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.25f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initwalkLeft() {
        Texture[] textures = World.images.getEnemyGoriyaAnimation(EnemyGoriyaAnimationGroup.WALKLEFT);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkLeftOffsetX = new int[frameData.length];
        walkLeftOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkLeftOffsetX[i] = frameData[i][1];
            walkLeftOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.25f, frames);
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
