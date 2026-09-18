package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.NpcShopkeeperDogAnimation;

/*
npc.shopkeeperDog:
    scripts.py (overworld:k13:1) only spawns and animates the actor on load; there is no
    dialogue. It shares its cell with npc.lounger and two teleport-pixel actors. The dog sits
    near the shop entrance and is treated as solid so it physically blocks the path like the
    other standing NPCs in this cell.
*/
public class NpcShopkeeperDog extends Npc {
    public NpcShopkeeperDogAnimation animation;

    public NpcShopkeeperDog() {
        super(ActorType.FRIENDLY, true);
        setWidth(64);
        setHeight(50);

        this.animation = new NpcShopkeeperDogAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
