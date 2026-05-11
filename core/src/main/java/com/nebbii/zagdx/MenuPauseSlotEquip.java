package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuPauseSlotEquip extends Rectangle implements MenuButton {
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

    public void draw(SpriteBatch batch) {}

	@Override
    public void onTouch() {
        switch(slotType) {
        case MAINHAND:
        default:
            gameManager.getZelda().setCurrentItem(Treasure.NONE);
            gameManager.getSaveManager().setEquippedItem(Treasure.NONE);
            gameManager.getSaveManager().writeCurrentSave();
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
