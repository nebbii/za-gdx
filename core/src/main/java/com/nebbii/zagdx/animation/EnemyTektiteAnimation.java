package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyTektite;
import com.nebbii.zagdx.ImageLoader.EnemyTektiteAnimationGroup;
import com.nebbii.zagdx.World;

public class EnemyTektiteAnimation extends GameAnimation {
    private final EnemyTektite enemy;

    private Animation<TextureRegion> walk;

    private int[] walkOffsetX;
    private int[] walkOffsetY;

    public EnemyTektiteAnimation(EnemyTektite enemy) {
        super("walk");
        this.enemy = enemy;

        baseOffsetX = 10;
        baseOffsetY = 11;
        offsetX = 0;
        offsetY = 0;

        walk = initWalk();
    }

    public TextureRegion playCurrentAnimation() {
        animation = walk;

        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        animation.setFrameDuration(getAnimationSpeed());

        offsetX = walkOffsetX[frameIndex];
        offsetY = walkOffsetY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initWalk() {
        Texture[] textures = World.images.getEnemyTektiteAnimation(EnemyTektiteAnimationGroup.WALK);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 2},
            {2, 0, 4},
            {3, 0, 2},
            {4, 2, -1},
            {3, 0, 2},
            {2, 0, 4},
            {1, 0, 2}
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

    private float getAnimationSpeed() {
        switch(enemy.getEnemyState()) {
            case FIGHT:
                return 0.20f;
            case SEARCH:
            default:
                return 0.30f;
        }
    }

    public float getX() {
        return enemy.getX() - enemy.getWidth() / 2f + offsetX + baseOffsetX;
    }

    public float getY() {
        return enemy.getY() - enemy.getHeight() / 2f + offsetY + baseOffsetY;
    }
}
