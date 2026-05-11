package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuPauseSlotWeapons extends Rectangle implements MenuButton {
    private GameManager gameManager;
    private Weapon heldItem;

    public MenuPauseSlotWeapons(float x, float y, int width, int height, GameManager gameManager) {
        super(x, y, width, height);

        this.gameManager = gameManager;
        heldItem = Weapon.NONE;
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public void onTouch() {
        gameManager.getZelda().setCurrentItem(getHeldItem());
        gameManager.getSaveManager().setEquippedItem(getHeldItem());
        gameManager.getSaveManager().writeCurrentSave();
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
