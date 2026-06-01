package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.ImageLoader.NpcEnidAnimationGroup;
import com.nebbii.zagdx.NpcEnid;
import com.nebbii.zagdx.World;

public class NpcEnidAnimation extends GameAnimation {
    private final NpcEnid npc;

    private Animation<TextureRegion> idle;

    private int[] idleOffsetX;
    private int[] idleOffsetY;

    public NpcEnidAnimation(NpcEnid npc) {
        super("idle");
        this.npc = npc;

        baseOffsetX = 0;
        baseOffsetY = 15;
        offsetX = 0;
        offsetY = 0;

        idle = initIdle();
    }

    @Override
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
        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {1, 0, 0}
        };

        Texture[] textures = World.images.getNpcEnidAnimation(NpcEnidAnimationGroup.IDLE);

        TextureRegion[] frames = new TextureRegion[frameData.length];
        idleOffsetX = new int[frameData.length];
        idleOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            idleOffsetX[i] = frameData[i][1];
            idleOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.400f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public float getX() {
        return npc.getX() - npc.getWidth() / 2f + offsetX + baseOffsetX;
    }

    public float getY() {
        return npc.getY() - npc.getHeight() / 2f + offsetY + baseOffsetY;
    }
}
