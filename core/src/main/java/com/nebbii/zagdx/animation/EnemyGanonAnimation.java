package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyGanon;
import com.nebbii.zagdx.ImageLoader.EnemyGanonAnimationGroup;
import com.nebbii.zagdx.World;

// TODO: extractor reported 8 frames for group0 (walk), but only 4 sprite files exist on disk; using the 4 real frames
public class EnemyGanonAnimation extends GameAnimation {
    private final EnemyGanon enemy;

    private Animation<TextureRegion> walk;
    private Animation<TextureRegion> attack;
    private Animation<TextureRegion> idle;

    private int[] walkOffsetX;
    private int[] walkOffsetY;
    private int[] attackOffsetX;
    private int[] attackOffsetY;
    private int[] idleOffsetX;
    private int[] idleOffsetY;

    public EnemyGanonAnimation(EnemyGanon enemy) {
        super("walk");
        this.enemy = enemy;

        baseOffsetX = 0;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;

        walk = initWalk();
        attack = initAttack();
        idle = initIdle();
        animation = walk;
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
            case STOP:
                animation = idle;
                offsetsX = idleOffsetX;
                offsetsY = idleOffsetY;
                break;
            case SEARCH:
            default:
                animation = walk;
                offsetsX = walkOffsetX;
                offsetsY = walkOffsetY;
                break;
        }

        animation.setFrameDuration(getAnimationSpeed());

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        offsetX = offsetsX[frameIndex];
        offsetY = offsetsY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initWalk() {
        Texture[] textures = World.images.getEnemyGanonAnimation(EnemyGanonAnimationGroup.WALK);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkOffsetX = new int[frameData.length];
        walkOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkOffsetX[i] = frameData[i][1];
            walkOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.14f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initAttack() {
        Texture[] textures = World.images.getEnemyGanonAnimation(EnemyGanonAnimationGroup.ATTACK);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        attackOffsetX = new int[frameData.length];
        attackOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            attackOffsetX[i] = frameData[i][1];
            attackOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.14f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initIdle() {
        Texture[] textures = World.images.getEnemyGanonAnimation(EnemyGanonAnimationGroup.IDLE);

        int[][] frameData = {
            {0, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        idleOffsetX = new int[frameData.length];
        idleOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            idleOffsetX[i] = frameData[i][1];
            idleOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.2f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private float getAnimationSpeed() {
        switch(enemy.getEnemyState()) {
            case FIGHT:
                return 0.10f;
            case STOP:
                return 0.2f;
            case SEARCH:
            default:
                return 0.14f;
        }
    }

    public float getX() {
        return enemy.getX() - enemy.getWidth() / 2f + offsetX + baseOffsetX;
    }

    public float getY() {
        return enemy.getY() - enemy.getHeight() / 2f + offsetY + baseOffsetY;
    }
}
