package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyAviana;
import com.nebbii.zagdx.ImageLoader.EnemyAvianaAnimationGroup;
import com.nebbii.zagdx.World;

// TODO: extractor reported 8 frames per group, but only 5 sprite files exist on disk; using the 5 real frames.
// Two groups (no direction data) are assumed to represent a search/fight pair, similar to EnemyLlort's walk/attack split.
public class EnemyAvianaAnimation extends GameAnimation {
    private final EnemyAviana enemy;

    private Animation<TextureRegion> walk;
    private Animation<TextureRegion> attack;

    private int[] walkOffsetX;
    private int[] walkOffsetY;
    private int[] attackOffsetX;
    private int[] attackOffsetY;

    public EnemyAvianaAnimation(EnemyAviana enemy) {
        super("walk");
        this.enemy = enemy;

        baseOffsetX = 0;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;

        walk = initWalk();
        attack = initAttack();
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
            case SEARCH:
            case STOP:
            default:
                animation = walk;
                offsetsX = walkOffsetX;
                offsetsY = walkOffsetY;
                break;
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

    private Animation<TextureRegion> initWalk() {
        Texture[] textures = World.images.getEnemyAvianaAnimation(EnemyAvianaAnimationGroup.WALK);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {3, 0, 0},
            {2, 0, 0},
            {1, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkOffsetX = new int[frameData.length];
        walkOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkOffsetX[i] = frameData[i][1];
            walkOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initAttack() {
        Texture[] textures = World.images.getEnemyAvianaAnimation(EnemyAvianaAnimationGroup.ATTACK);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {3, 0, 0},
            {2, 0, 0},
            {1, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        attackOffsetX = new int[frameData.length];
        attackOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            attackOffsetX[i] = frameData[i][1];
            attackOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.06f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private float getAnimationSpeed() {
        switch(enemy.getEnemyState()) {
            case FIGHT:
                return 0.06f;
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
