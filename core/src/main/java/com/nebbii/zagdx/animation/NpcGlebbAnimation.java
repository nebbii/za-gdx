package com.nebbii.zagdx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nebbii.zagdx.NpcGlebb;
import com.nebbii.zagdx.World;

public class NpcGlebbAnimation extends GameAnimation {
    private Texture[] textures;
    private NpcGlebb npc;

    private int[] idleOffsetX;
    private int[] idleOffsetY;

    public NpcGlebbAnimation(NpcGlebb npc) {
        super("idle");
        textures = World.images.getNpcGlebb();
        this.npc = npc;

        animation = initIdleAnim();
    }

    public Animation<TextureRegion> initIdleAnim() {
        int[][] frameData = {
            {0, 0, 0},
            {1, 0, 0},
            {2, 0, 0},
            {3, 0, 0},
            {4, 0, 0},
            {3, 0, 0},
            {3, 0, 0},
            {3, 0, 0},
            {3, 0, 0},
            {3, 0, 0}
        };

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
        return npc.getX()-npc.getWidth()/2+offsetX+baseOffsetX;
    }

    public float getY() {
        return npc.getY()-npc.getHeight()/2+offsetY+baseOffsetY;
    }

}
