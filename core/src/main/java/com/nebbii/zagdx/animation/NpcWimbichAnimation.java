package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.ImageLoader.NpcWimbichAnimationGroup;
import com.nebbii.zagdx.NpcWimbich;
import com.nebbii.zagdx.World;

// TODO: extractor reported 8 frames per direction, but only 6 sprite files exist on disk per
// direction; using the real counts. Npc.getDirection() currently always returns DOWN (the base
// class does not track a real facing), so this always shows IDLEDOWN today.
public class NpcWimbichAnimation extends GameAnimation {
    private final NpcWimbich npc;

    private Animation<TextureRegion> idleUp;
    private Animation<TextureRegion> idleRight;
    private Animation<TextureRegion> idleDown;
    private Animation<TextureRegion> idleLeft;

    private int[] idleUpOffsetX;
    private int[] idleUpOffsetY;
    private int[] idleRightOffsetX;
    private int[] idleRightOffsetY;
    private int[] idleDownOffsetX;
    private int[] idleDownOffsetY;
    private int[] idleLeftOffsetX;
    private int[] idleLeftOffsetY;

    public NpcWimbichAnimation(NpcWimbich npc) {
        super("idleDown");
        this.npc = npc;

        baseOffsetX = 0;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;

        idleUp = initIdleUp();
        idleRight = initIdleRight();
        idleDown = initIdleDown();
        idleLeft = initIdleLeft();
    }

    public TextureRegion playCurrentAnimation() {
        int[] offsetsX;
        int[] offsetsY;

        switch (npc.getDirection()) {
            case UP:
                animation = idleUp;
                offsetsX = idleUpOffsetX;
                offsetsY = idleUpOffsetY;
                break;
            case RIGHT:
                animation = idleRight;
                offsetsX = idleRightOffsetX;
                offsetsY = idleRightOffsetY;
                break;
            case DOWN:
                animation = idleDown;
                offsetsX = idleDownOffsetX;
                offsetsY = idleDownOffsetY;
                break;
            case LEFT:
                animation = idleLeft;
                offsetsX = idleLeftOffsetX;
                offsetsY = idleLeftOffsetY;
                break;
            default:
                throw new IllegalStateException("Unhandled NpcWimbich direction");
        }

        play();

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        offsetX = offsetsX[frameIndex];
        offsetY = offsetsY[frameIndex];

        return frame;
    }

    private Animation<TextureRegion> initIdleUp() {
        Texture[] textures = World.images.getNpcWimbichAnimation(NpcWimbichAnimationGroup.IDLEUP);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {4, 0, 0},
            {3, 0, 0},
            {2, 0, 0},
            {1, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        idleUpOffsetX = new int[frameData.length];
        idleUpOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            idleUpOffsetX[i] = frameData[i][1];
            idleUpOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.15f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initIdleRight() {
        Texture[] textures = World.images.getNpcWimbichAnimation(NpcWimbichAnimationGroup.IDLERIGHT);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {4, 0, 0},
            {3, 0, 0},
            {2, 0, 0},
            {1, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        idleRightOffsetX = new int[frameData.length];
        idleRightOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            idleRightOffsetX[i] = frameData[i][1];
            idleRightOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.15f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initIdleDown() {
        Texture[] textures = World.images.getNpcWimbichAnimation(NpcWimbichAnimationGroup.IDLEDOWN);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {4, 0, 0},
            {3, 0, 0},
            {2, 0, 0},
            {1, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        idleDownOffsetX = new int[frameData.length];
        idleDownOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            idleDownOffsetX[i] = frameData[i][1];
            idleDownOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.15f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initIdleLeft() {
        Texture[] textures = World.images.getNpcWimbichAnimation(NpcWimbichAnimationGroup.IDLELEFT);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {4, 0, 0},
            {3, 0, 0},
            {2, 0, 0},
            {1, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        idleLeftOffsetX = new int[frameData.length];
        idleLeftOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            idleLeftOffsetX[i] = frameData[i][1];
            idleLeftOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.15f, frames);
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
