package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyMoblin;
import com.nebbii.zagdx.ImageLoader.EnemyMoblinAnimationGroup;
import com.nebbii.zagdx.World;

// TODO: align animation frames
public class EnemyMoblinAnimation extends GameAnimation {
    private final EnemyMoblin enemy;

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

    public EnemyMoblinAnimation(EnemyMoblin enemy) {
        super("walkDown");
        this.enemy = enemy;

        baseOffsetX = -10;
        baseOffsetY = 15;
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
                throw new IllegalStateException("Unhandled moblin direction");
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
        Texture[] textures = World.images.getEnemyMoblinAnimation(EnemyMoblinAnimationGroup.WALKUP);

        int[][] frameData = {
            {0, -1, 3-5},
            {1, 0, 2+5},
            {2, 0, 3+5},
            {3, 0, 2+5},
            {4, 0, 0+5},
            {3, 0, 2+5},
            {2, 0, 3+5},
            {1, 0, 2+5}
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
        Texture[] textures = World.images.getEnemyMoblinAnimation(EnemyMoblinAnimationGroup.WALKRIGHT);

        int[][] frameData = {
            {0, -1, 0-12}, // 67 65
            {1, 0, 5-12}, // 75 60
            {2, 0, 7-12}, // 79 58
            {3, 0, 13-12}, // 91 51
            {4, 0, 14-12}, // 95 48
            {3, 0, 13-12}, // 91 51
            {2, 0, 7-12}, // 79 58
            {1, 0, 5-12}, // 75 60
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
        Texture[] textures = World.images.getEnemyMoblinAnimation(EnemyMoblinAnimationGroup.WALKDOWN);

        int[][] frameData = {
            {0, 1, 0-6},
            {1, 1, -2-6},
            {2, 1, 1-6},
            {3, 1, 11-6},
            {4, 2, 9-6},
            {3, 1, 11-6},
            {2, 1, 1-6},
            {1, 1, -2-6},
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
        Texture[] textures = World.images.getEnemyMoblinAnimation(EnemyMoblinAnimationGroup.WALKLEFT);

        int[][] frameData = {
            {0, 0, 3},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, -2},
            {3, 0, 0},
            {2, 0, 0},
            {1, 0, 0},
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
                return 0.08f;
            case SEARCH:
            default:
                return 0.20f;
        }
    }

    public float getX() {
        return enemy.getX() - enemy.getWidth() / 2f + offsetX + baseOffsetX;
    }

    public float getY() {
        return enemy.getY() - enemy.getHeight() / 2f + offsetY + baseOffsetY;
    }
}
