package com.nebbii.zagdx;

import com.badlogic.gdx.math.Rectangle;

public class MenuPauseSlotEquip extends Rectangle implements MenuPauseButton {
    private GameManager gameManager;

    public enum EquipSlotType {
        MAINHAND
    }

    private EquipSlotType slotType;

    public MenuPauseSlotEquip(float x, float y, int width, int height, GameManager gameManager, EquipSlotType slotType) {
        super(x, y, width, height);

        this.slotType = slotType;
        this.gameManager = gameManager;
    }

	@Override
    public void onTouch() {
        switch(slotType) {
        case MAINHAND:
        default:
            gameManager.getZelda().setCurrentItem(Treasure.NONE);
        }
    }

    public Rectangle getHitbox() {
        return this;
    }

    public Rectangle getCollisionBox() {
        return this;
    }

    public Item getHeldItem() {
        switch(slotType) {
        case MAINHAND:
        default:
            return gameManager.getZelda().getCurrentItem();
        }
    }
}
