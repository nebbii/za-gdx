package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemySardakYellow;
import com.nebbii.zagdx.ImageLoader.EnemySardakYellowAnimationGroup;
import com.nebbii.zagdx.World;

public class EnemySardakYellowAnimation extends GameAnimation {
    private final EnemySardakYellow enemy;

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

    public EnemySardakYellowAnimation(EnemySardakYellow enemy) {
        super("walkDown");
        this.enemy = enemy;

        baseOffsetX = 16;
        baseOffsetY = 17;
        offsetX = 0;
        offsetY = 0;

        walkUp = initWalkUp();
        walkRight = initWalkRight();
        walkDown = initWalkDown();
        walkLeft = initWalkLeft();
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
                throw new IllegalStateException("Unhandled sardak yellow direction");
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

    private Animation<TextureRegion> initWalkUp() {
        Texture[] textures = World.images.getEnemySardakYellowAnimation(EnemySardakYellowAnimationGroup.WALKUP);

        int[][] frameData = {
            {0, 0, 0},
            {1, -1, 1},
            {2, 0, 2},
            {3, 1, 1},
            {4, 0, 0},
            {3, 1, 1},
            {2, 0, 2},
            {1, -1, 1}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkUpOffsetX = new int[frameData.length];
        walkUpOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkUpOffsetX[i] = frameData[i][1];
            walkUpOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initWalkRight() {
        Texture[] textures = World.images.getEnemySardakYellowAnimation(EnemySardakYellowAnimationGroup.WALKRIGHT);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 1},
            {2, -1, 2},
            {3, -1, 1},
            {4, -2, 0},
            {3, -1, 1},
            {2, -1, 2},
            {1, 0, 1}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkRightOffsetX = new int[frameData.length];
        walkRightOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkRightOffsetX[i] = frameData[i][1];
            walkRightOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initWalkDown() {
        Texture[] textures = World.images.getEnemySardakYellowAnimation(EnemySardakYellowAnimationGroup.WALKDOWN);

        int[][] frameData = {
            {0, 0, 0},
            {1, -1, 1},
            {2, 0, 2},
            {3, 1, 1},
            {4, 0, 0},
            {3, 1, 1},
            {2, 0, 2},
            {1, -1, 1}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkDownOffsetX = new int[frameData.length];
        walkDownOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkDownOffsetX[i] = frameData[i][1];
            walkDownOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initWalkLeft() {
        Texture[] textures = World.images.getEnemySardakYellowAnimation(EnemySardakYellowAnimationGroup.WALKLEFT);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 1},
            {2, 1, 2},
            {3, 1, 1},
            {4, 2, 0},
            {3, 1, 1},
            {2, 1, 2},
            {1, 0, 1}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkLeftOffsetX = new int[frameData.length];
        walkLeftOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkLeftOffsetX[i] = frameData[i][1];
            walkLeftOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private float getAnimationSpeed() {
        switch(enemy.getEnemyState()) {
            case FIGHT:
                return 0.15f;
            case SEARCH:
            default:
                return 0.25f;
        }
    }

    public float getX() {
        return enemy.getX() - enemy.getWidth() / 2f + offsetX + baseOffsetX;
    }

    public float getY() {
        return enemy.getY() - enemy.getHeight() / 2f + offsetY + baseOffsetY;
    }
}
