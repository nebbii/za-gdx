package com.nebbii.zagdx.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.Direction;
import com.nebbii.zagdx.Enemy.EnemyState;
import com.nebbii.zagdx.EnemyUrsore;
import com.nebbii.zagdx.ImageLoader.EnemyUrsoreAnimationGroup;
import com.nebbii.zagdx.World;

/*
enemy.ursore animation groups:
    extractor reported 8/2/4/2/8 frames for group0..group4, but only 5/2/3/2/5 sprite
    files exist on disk; using the real counts.

    Group semantics are inferred from cast.json's per-group animationFrameOrder shapes
    (group0/group4 are full walk cycles, group1/group3 are short 2-frame idle/roar
    loops, group2 is a 3-frame attack swing) since this boss only has left/right facing
    sprites. Verify naming/behavior against the original game.
*/
// TODO: Set actual original game accurate values
public class EnemyUrsoreAnimation extends GameAnimation {
    private final EnemyUrsore enemy;

    private Animation<TextureRegion> walkRight;
    private Animation<TextureRegion> idleRight;
    private Animation<TextureRegion> attack;
    private Animation<TextureRegion> idleLeft;
    private Animation<TextureRegion> walkLeft;

    private int[] walkRightOffsetX;
    private int[] walkRightOffsetY;
    private int[] idleRightOffsetX;
    private int[] idleRightOffsetY;
    private int[] attackOffsetX;
    private int[] attackOffsetY;
    private int[] idleLeftOffsetX;
    private int[] idleLeftOffsetY;
    private int[] walkLeftOffsetX;
    private int[] walkLeftOffsetY;

    public EnemyUrsoreAnimation(EnemyUrsore enemy) {
        super("walkRight");
        this.enemy = enemy;

        baseOffsetX = 0;
        baseOffsetY = 0;
        offsetX = 0;
        offsetY = 0;

        walkRight = initWalkRight();
        idleRight = initIdleRight();
        attack = initAttack();
        idleLeft = initIdleLeft();
        walkLeft = initWalkLeft();
    }

    public TextureRegion playCurrentAnimation() {
        int[] offsetsX;
        int[] offsetsY;

        if (enemy.getEnemyState() == EnemyState.FIGHT) {
            animation = attack;
            offsetsX = attackOffsetX;
            offsetsY = attackOffsetY;
        }
        else if (enemy.getDirection() == Direction.LEFT) {
            animation = walkLeft;
            offsetsX = walkLeftOffsetX;
            offsetsY = walkLeftOffsetY;
        }
        else if (enemy.getDirection() == Direction.RIGHT) {
            animation = walkRight;
            offsetsX = walkRightOffsetX;
            offsetsY = walkRightOffsetY;
        }
        else {
            // UP/DOWN fall back to the closest facing since this boss only has left/right sprites
            animation = walkRight;
            offsetsX = walkRightOffsetX;
            offsetsY = walkRightOffsetY;
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

    private Animation<TextureRegion> initWalkRight() {
        Texture[] textures = World.images.getEnemyUrsoreAnimation(EnemyUrsoreAnimationGroup.WALKRIGHT);

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
        walkRightOffsetX = new int[frameData.length];
        walkRightOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkRightOffsetX[i] = frameData[i][1];
            walkRightOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.12f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initIdleRight() {
        Texture[] textures = World.images.getEnemyUrsoreAnimation(EnemyUrsoreAnimationGroup.IDLERIGHT);

        int[][] frameData = {
            {0, 0, 0},
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

        Animation<TextureRegion> anim = new Animation<>(0.16f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initAttack() {
        Texture[] textures = World.images.getEnemyUrsoreAnimation(EnemyUrsoreAnimationGroup.ATTACK);

        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
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

        Animation<TextureRegion> anim = new Animation<>(0.10f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initIdleLeft() {
        Texture[] textures = World.images.getEnemyUrsoreAnimation(EnemyUrsoreAnimationGroup.IDLELEFT);

        int[][] frameData = {
            {0, 0, 0},
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

        Animation<TextureRegion> anim = new Animation<>(0.16f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private Animation<TextureRegion> initWalkLeft() {
        Texture[] textures = World.images.getEnemyUrsoreAnimation(EnemyUrsoreAnimationGroup.WALKLEFT);

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
        walkLeftOffsetX = new int[frameData.length];
        walkLeftOffsetY = new int[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            frames[i] = new TextureRegion(textures[frameData[i][0]]);
            walkLeftOffsetX[i] = frameData[i][1];
            walkLeftOffsetY[i] = frameData[i][2];
        }

        Animation<TextureRegion> anim = new Animation<>(0.12f, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }

    private float getAnimationSpeed() {
        switch(enemy.getEnemyState()) {
            case FIGHT:
                return 0.10f;
            case SEARCH:
            default:
                return 0.12f;
        }
    }

    public float getX() {
        return enemy.getX() - enemy.getWidth() / 2f + offsetX + baseOffsetX;
    }

    public float getY() {
        return enemy.getY() - enemy.getHeight() / 2f + offsetY + baseOffsetY;
    }
}
