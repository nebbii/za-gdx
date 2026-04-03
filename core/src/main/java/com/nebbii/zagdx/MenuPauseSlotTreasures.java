package com.nebbii.zagdx;

import com.badlogic.gdx.math.Rectangle;

public class MenuPauseSlotTreasures extends Rectangle implements MenuPauseButton {
    private GameManager gameManager;
    private Treasure heldItem;

    public MenuPauseSlotTreasures(float x, float y, int width, int height, GameManager gameManager) {
        super(x, y, width, height);
        this.gameManager = gameManager;

        heldItem = Treasure.NONE;
    }

    @Override
    public void onTouch() {
        gameManager.getZelda().setCurrentItem(getHeldItem());
    }

    public Rectangle getHitbox() {
        return this;
    }

    public Rectangle getCollisionBox() {
        return this;
    }

    public Treasure getHeldItem() {
        return heldItem;
    }

    public void setHeldItem(Treasure heldItem) {
        this.heldItem = heldItem;
    }
}
