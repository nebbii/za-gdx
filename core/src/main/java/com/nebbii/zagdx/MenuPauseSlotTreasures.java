package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuPauseSlotTreasures extends Rectangle implements MenuButton {
    private GameManager gameManager;
    private Treasure heldItem;

    private static final Treasure[] NOTEQUIPPABLE = {
        Treasure.RED_BOOTS,
        Treasure.CELESTIAL_SIGN_1,
        Treasure.CELESTIAL_SIGN_2,
        Treasure.CELESTIAL_SIGN_3,
        Treasure.CELESTIAL_SIGN_4,
        Treasure.CELESTIAL_SIGN_5,
        Treasure.CELESTIAL_SIGN_6,
        Treasure.CELESTIAL_SIGN_7,
        Treasure.UNDERWORLD_MAP_1,
        Treasure.UNDERWORLD_MAP_2,
        Treasure.UNDERWORLD_MAP_3,
        Treasure.UNDERWORLD_MAP_4,
        Treasure.UNDERWORLD_MAP_5,
        Treasure.UNDERWORLD_MAP_6,
        Treasure.UNDERWORLD_MAP_7
    };

    public MenuPauseSlotTreasures(float x, float y, int width, int height, GameManager gameManager) {
        super(x, y, width, height);
        this.gameManager = gameManager;

        heldItem = Treasure.NONE;
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public void onTouch() {
        if (!isEquippable()) return;

        gameManager.getZelda().setCurrentItem(getHeldItem());
        gameManager.getSaveManager().setEquippedItem(getHeldItem());
        gameManager.getSaveManager().writeCurrentSave();
    }

    private boolean isEquippable() {
        for (Treasure treasure : NOTEQUIPPABLE) {
            if (treasure == getHeldItem()) {
                return false;
            }
        }
        return true;
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
