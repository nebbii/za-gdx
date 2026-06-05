package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.ImageLoader.NpcBeggarAnimationGroup;
import com.nebbii.zagdx.NpcBeggar;
import com.nebbii.zagdx.World;

public class NpcBeggarAnimation extends GameAnimation {
    private final NpcBeggar npc;

    private Animation<TextureRegion> idle0;
    private Animation<TextureRegion> idle1;

    private int[] idle0OffsetX;
    private int[] idle0OffsetY;
    private int[] idle1OffsetX;
    private int[] idle1OffsetY;

    public NpcBeggarAnimation(NpcBeggar npc) {
        super("idle0");
        this.npc = npc;

        baseOffsetX = 0;
        baseOffsetY = 15;
        offsetX = 0;
        offsetY = 0;

        idle0 = initIdle0();
        idle1 = initIdle1();
    }

    @Override
    public TextureRegion playCurrentAnimation() {
        int[] currentOffsetX;
        int[] currentOffsetY;

        switch(currentAnimation) {
            case "idle1":
                animation = idle1;
                currentOffsetX = idle1OffsetX;
                currentOffsetY = idle1OffsetY;
                break;
            case "idle0":
            default:
                animation = idle0;
                currentOffsetX = idle0OffsetX;
                currentOffsetY = idle0OffsetY;
                break;
        }

        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);
        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        offsetX = currentOffsetX[frameIndex];
        offsetY = currentOffsetY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initIdle0() {
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

        idle0OffsetX = new int[frameData.length];
        idle0OffsetY = new int[frameData.length];

        return initIdle(NpcBeggarAnimationGroup.IDLE0, frameData, idle0OffsetX, idle0OffsetY);
    }

    private Animation<TextureRegion> initIdle1() {
        int[][] frameData = {
            {0, -3, 0},
            {1, -3, 0},
            {2, -3, 0},
            {3, -3, 0},
            {4, -3, 0},
            {3, -3, 0},
            {2, -3, 0},
            {1, -3, 0}
        };

        idle1OffsetX = new int[frameData.length];
        idle1OffsetY = new int[frameData.length];

        return initIdle(NpcBeggarAnimationGroup.IDLE1, frameData, idle1OffsetX, idle1OffsetY);
    }

    private Animation<TextureRegion> initIdle(
        NpcBeggarAnimationGroup group,
        int[][] frameData,
        int[] animationOffsetX,
        int[] animationOffsetY
    ) {
        Texture[] textures = World.images.getNpcBeggarAnimation(group);

        TextureRegion[] frames = new TextureRegion[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            animationOffsetX[i] = frameData[i][1];
            animationOffsetY[i] = frameData[i][2];
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
