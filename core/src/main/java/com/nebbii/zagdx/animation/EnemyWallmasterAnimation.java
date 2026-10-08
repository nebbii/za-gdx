package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.EnemyWallmaster;
import com.nebbii.zagdx.ImageLoader.EnemyWallmasterAnimationGroup;
import com.nebbii.zagdx.World;

// TODO: extractor reported 3/1/1 frames for group0/1/2, but 3/3/2 sprite files exist on disk;
// using the real counts. The 3 groups look like an appear/grab/retreat sequence (per cast.json
// animation commands) rather than 4-way walking; only group0 (IDLE) is wired up for now since
// the Enemy base class has no grab/retreat state machine yet. ATTACK/RETREAT textures are still
// loaded in ImageLoader for future use.
public class EnemyWallmasterAnimation extends GameAnimation {
    private final EnemyWallmaster enemy;

    private Animation<TextureRegion> idle;

    private int[] idleOffsetX;
    private int[] idleOffsetY;

    public EnemyWallmasterAnimation(EnemyWallmaster enemy) {
        super("idle");
        this.enemy = enemy;

        baseOffsetX = 0;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;

        idle = initIdle();
    }

    public TextureRegion playCurrentAnimation() {
        animation = idle;

        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        offsetX = idleOffsetX[frameIndex];
        offsetY = idleOffsetY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initIdle() {
        Texture[] textures = World.images.getEnemyWallmasterAnimation(EnemyWallmasterAnimationGroup.IDLE);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {1, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        idleOffsetX = new int[frameData.length];
        idleOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            idleOffsetX[i] = frameData[i][1];
            idleOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.15f, frames);
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
