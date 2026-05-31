package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.ImageLoader.NpcOghamAnimationGroup;
import com.nebbii.zagdx.NpcOgham;
import com.nebbii.zagdx.World;

public class NpcOghamAnimation extends GameAnimation {
    private static final int[][] IDLE_FRAME_DATA = {
        {0, 0, 0},
        {1, 0, 0},
        {2, 0, 0}
    };

    private final NpcOgham npc;

    private Animation<TextureRegion> idle0;
    private Animation<TextureRegion> idle1;
    private Animation<TextureRegion> idle2;

    private int[] idle0OffsetX;
    private int[] idle0OffsetY;
    private int[] idle1OffsetX;
    private int[] idle1OffsetY;
    private int[] idle2OffsetX;
    private int[] idle2OffsetY;

    public NpcOghamAnimation(NpcOgham npc) {
        super("idle0");
        this.npc = npc;

        baseOffsetX = 0;
        baseOffsetY = 15;
        offsetX = 0;
        offsetY = 0;

        idle0OffsetX = new int[IDLE_FRAME_DATA.length];
        idle0OffsetY = new int[IDLE_FRAME_DATA.length];
        idle1OffsetX = new int[IDLE_FRAME_DATA.length];
        idle1OffsetY = new int[IDLE_FRAME_DATA.length];
        idle2OffsetX = new int[IDLE_FRAME_DATA.length];
        idle2OffsetY = new int[IDLE_FRAME_DATA.length];

        idle0 = initIdle(NpcOghamAnimationGroup.IDLE0, idle0OffsetX, idle0OffsetY);
        idle1 = initIdle(NpcOghamAnimationGroup.IDLE1, idle1OffsetX, idle1OffsetY);
        idle2 = initIdle(NpcOghamAnimationGroup.IDLE2, idle2OffsetX, idle2OffsetY);
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
            case "idle2":
                animation = idle2;
                currentOffsetX = idle2OffsetX;
                currentOffsetY = idle2OffsetY;
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

    private Animation<TextureRegion> initIdle(
        NpcOghamAnimationGroup group,
        int[] animationOffsetX,
        int[] animationOffsetY
    ) {
        Texture[] textures = World.images.getNpcOghamAnimation(group);

        TextureRegion[] frames = new TextureRegion[IDLE_FRAME_DATA.length];

        for (int i = 0; i < IDLE_FRAME_DATA.length; i++) {
            frames[i] = new TextureRegion(textures[IDLE_FRAME_DATA[i][0]]);
            animationOffsetX[i] = IDLE_FRAME_DATA[i][1];
            animationOffsetY[i] = IDLE_FRAME_DATA[i][2];
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
