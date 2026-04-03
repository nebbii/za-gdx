package com.nebbii.zagdx;

import com.badlogic.gdx.math.Rectangle;

public class MenuPauseSlotWeapons extends Rectangle implements MenuPauseButton {
    private GameManager gameManager;
    private Weapon heldItem;

    public MenuPauseSlotWeapons(float x, float y, int width, int height, GameManager gameManager) {
        super(x, y, width, height);

        this.gameManager = gameManager;
        heldItem = Weapon.NONE;
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

    public Weapon getHeldItem() {
        return heldItem;
    }

    public void setHeldItem(Weapon heldItem) {
        this.heldItem = heldItem;
    }
}
