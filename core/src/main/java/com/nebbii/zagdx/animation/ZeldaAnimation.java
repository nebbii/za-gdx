package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.ImageLoader.ZeldaAnimationGroup;
import com.nebbii.zagdx.World;
import com.nebbii.zagdx.Zelda;

public class ZeldaAnimation extends GameAnimation {
    private Zelda zelda;

    private Animation<TextureRegion> walkLeft;
    private Animation<TextureRegion> walkDown;
    private Animation<TextureRegion> walkUp;
    private Animation<TextureRegion> walkRight;

    private Animation<TextureRegion> attackLeft;
    private Animation<TextureRegion> attackDown;
    private Animation<TextureRegion> attackUp;
    private Animation<TextureRegion> attackRight;

    private int[] walkLeftOffsetX;
    private int[] walkLeftOffsetY;
    private int[] walkDownOffsetX;
    private int[] walkDownOffsetY;
    private int[] walkUpOffsetX;
    private int[] walkUpOffsetY;
    private int[] walkRightOffsetX;
    private int[] walkRightOffsetY;

    private int[] attackLeftOffsetX;
    private int[] attackLeftOffsetY;
    private int[] attackDownOffsetX;
    private int[] attackDownOffsetY;
    private int[] attackUpOffsetX;
    private int[] attackUpOffsetY;
    private int[] attackRightOffsetX;
    private int[] attackRightOffsetY;

    public ZeldaAnimation(Zelda zelda) {
        super("walkDown");
        this.zelda = zelda;

        baseOffsetX = -35;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;

        attackLeft = initAttackLeft();
        attackDown = initAttackDown();
        attackUp = initAttackUp();
        attackRight = initAttackRight();

        walkLeft = initWalkLeft();
        walkDown = initWalkDown();
        walkUp = initWalkUp();
        walkRight = initWalkRight();
    }

    public TextureRegion playCurrentAnimation() {
        int[] offsetsX;
        int[] offsetsY;

        switch (zelda.getAnimState()) {
            case ATTACKLEFT:
                play();
                animation = attackLeft;
                offsetsX = attackLeftOffsetX;
                offsetsY = attackLeftOffsetY;
                break;
            case ATTACKDOWN:
                play();
                animation = attackDown;
                offsetsX = attackDownOffsetX;
                offsetsY = attackDownOffsetY;
                break;
            case ATTACKUP:
                play();
                animation = attackUp;
                offsetsX = attackUpOffsetX;
                offsetsY = attackUpOffsetY;
                break;
            case ATTACKRIGHT:
                play();
                animation = attackRight;
                offsetsX = attackRightOffsetX;
                offsetsY = attackRightOffsetY;
                break;

            case MOVELEFT:
                play();
                animation = walkLeft;
                offsetsX = walkLeftOffsetX;
                offsetsY = walkLeftOffsetY;
                break;
            case MOVEDOWN:
                play();
                animation = walkDown;
                offsetsX = walkDownOffsetX;
                offsetsY = walkDownOffsetY;
                break;
            case MOVEUP:
                play();
                animation = walkUp;
                offsetsX = walkUpOffsetX;
                offsetsY = walkUpOffsetY;
                break;
            case MOVERIGHT:
                play();
                animation = walkRight;
                offsetsX = walkRightOffsetX;
                offsetsY = walkRightOffsetY;
                break;

            case STOPLEFT:
                stateTime = 0;
                animation = walkLeft;
                offsetsX = walkLeftOffsetX;
                offsetsY = walkLeftOffsetY;
                break;
            case STOPDOWN:
                stateTime = 0;
                animation = walkDown;
                offsetsX = walkDownOffsetX;
                offsetsY = walkDownOffsetY;
                break;
            case STOPUP:
                stateTime = 0;
                animation = walkUp;
                offsetsX = walkUpOffsetX;
                offsetsY = walkUpOffsetY;
                break;
            case STOPRIGHT:
                stateTime = 0;
                animation = walkRight;
                offsetsX = walkRightOffsetX;
                offsetsY = walkRightOffsetY;
                break;

            default:
                throw new IllegalStateException(
                    "ZeldaAnimation: Unhandled animation state: " + zelda.getAnimState()
                );
        }

        TextureRegion frame = animation.getKeyFrame(stateTime, true);

        float wrappedTime = stateTime % animation.getAnimationDuration();
        int frameIndex = animation.getKeyFrameIndex(wrappedTime);

        offsetX = offsetsX[frameIndex];
        offsetY = offsetsY[frameIndex];

        return frame;
    }

    public Animation<TextureRegion> initWalkLeft() {
        Texture[] textures = World.images.getZeldaAnimation(ZeldaAnimationGroup.WALKLEFT);

        // frame #, x offset, y offset
        int[][] frameData = {
            {0, 0, 4},
            {1, 0, 4},
            {2, 0, 4},
            {3, 0, 4},
            {4, 0, 4}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkLeftOffsetX = new int[frameData.length];
        walkLeftOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkLeftOffsetX[i] = frameData[i][1];
            walkLeftOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.100f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public Animation<TextureRegion> initWalkDown() {
        Texture[] textures = World.images.getZeldaAnimation(ZeldaAnimationGroup.WALKDOWN);

        // frame #, x offset, y offset
        int[][] frameData = {
            {0, 2, 0},
            {1, 2, 2},
            {2, 2, -2},
            {3, 2, 0},
            {4, 2, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkDownOffsetX = new int[frameData.length];
        walkDownOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkDownOffsetX[i] = frameData[i][1];
            walkDownOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.100f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public Animation<TextureRegion> initWalkUp() {
        Texture[] textures = World.images.getZeldaAnimation(ZeldaAnimationGroup.WALKUP);

        // frame #, x offset, y offset
        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 2},
            {2, 0, 4},
            {3, 0, 4},
            {4, 0, 3}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkUpOffsetX = new int[frameData.length];
        walkUpOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkUpOffsetX[i] = frameData[i][1];
            walkUpOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.100f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public Animation<TextureRegion> initWalkRight() {
        Texture[] textures = World.images.getZeldaAnimation(ZeldaAnimationGroup.WALKRIGHT);

        // frame #, x offset, y offset
        int[][] frameData = {
            {0, 0, 4},
            {1, 0, 4},
            {2, 0, 4},
            {3, 0, 4},
            {4, 0, 4}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        walkRightOffsetX = new int[frameData.length];
        walkRightOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkRightOffsetX[i] = frameData[i][1];
            walkRightOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.100f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public Animation<TextureRegion> initAttackLeft() {
        Texture[] textures = World.images.getZeldaAnimation(ZeldaAnimationGroup.ATTACKLEFT);

        // frame #, x offset, y offset
        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {1, 0, 0},
            {0, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        attackLeftOffsetX = new int[frameData.length];
        attackLeftOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            attackLeftOffsetX[i] = frameData[i][1];
            attackLeftOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.050f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public Animation<TextureRegion> initAttackDown() {
        Texture[] textures = World.images.getZeldaAnimation(ZeldaAnimationGroup.ATTACKDOWN);

        // frame #, x offset, y offset
        int[][] frameData = {
            {0, 0, 0},
            {1, 0, -7},
            {2, 0, -12}, // needs to be centered better
            {1, 0, -7},
            {0, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        attackDownOffsetX = new int[frameData.length];
        attackDownOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            attackDownOffsetX[i] = frameData[i][1];
            attackDownOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.050f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public Animation<TextureRegion> initAttackUp() {
        Texture[] textures = World.images.getZeldaAnimation(ZeldaAnimationGroup.ATTACKUP);

        // frame #, x offset, y offset
        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {1, 0, 0},
            {0, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        attackUpOffsetX = new int[frameData.length];
        attackUpOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            attackUpOffsetX[i] = frameData[i][1];
            attackUpOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.050f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public Animation<TextureRegion> initAttackRight() {
        Texture[] textures = World.images.getZeldaAnimation(ZeldaAnimationGroup.ATTACKRIGHT);

        // frame #, x offset, y offset
        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {1, 0, 0},
            {0, 0, 0}
        };

        TextureRegion[] frames = new TextureRegion[frameData.length];
        attackRightOffsetX = new int[frameData.length];
        attackRightOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            attackRightOffsetX[i] = frameData[i][1];
            attackRightOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.050f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    public float getX() {
        return zelda.getX() - zelda.getWidth() / 2 + offsetX + baseOffsetX;
    }

    public float getY() {
        return zelda.getY() - zelda.getHeight() / 2 + offsetY + baseOffsetY;
    }
}
