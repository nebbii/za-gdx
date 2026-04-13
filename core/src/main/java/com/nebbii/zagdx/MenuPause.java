package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nebbii.zagdx.GameManager.GameState;

public class MenuPause {
    public enum MenuState {
        ACTIVE,
        FADING_IN,
        FADING_OUT,
        INACTIVE
    }

    private final GameManager gameManager;
    private final Texture pauseTexture;

    private ArrayList<MenuPauseSlotTreasures> treasureTray;
    private ArrayList<MenuPauseSlotWeapons> weaponTray;
    private ArrayList<MenuPauseSlotEquip> equipTray;

    private int treasureTrayIndex;
    private int weaponTrayIndex;

    private MenuState menuState;
    private float opacity;

    final int pauseMenuWidth = 346;
    final int pauseMenuHeight = 210;

    final int treasureTrayX = 20 + (World.WORLD_WIDTH - pauseMenuWidth);
    final int treasureTrayY = 60 + (World.WORLD_HEIGHT - pauseMenuHeight);
    final int treasureTrayWidth = 224;
    final int treasureTrayHeight = 30;
    final int treasureSlotWidth = treasureTrayWidth / 6;

    final int weaponTrayX = 20 + (World.WORLD_WIDTH - pauseMenuWidth);
    final int weaponTrayY = (World.WORLD_HEIGHT - pauseMenuHeight);
    final int weaponTrayWidth = 224;
    final int weaponTrayHeight = 30;
    final int weaponSlotWidth = weaponTrayWidth / 6;

    // currently just one slot
    final int equipTrayX = (World.WORLD_WIDTH - pauseMenuWidth) + pauseMenuWidth - 68;
    final int equipTrayY = (World.WORLD_HEIGHT - pauseMenuHeight) + 34;
    final int equipTrayWidth = 29;
    final int equipTrayHeight = 29;
    final int equipSlotWidth = weaponTrayWidth / 6;

    public MenuPause(GameManager gameManager) {
        this.gameManager = gameManager;
        this.pauseTexture = World.images.getItemScreen();
        this.opacity = 0f;
        this.menuState = MenuState.INACTIVE;

        treasureTray = new ArrayList<MenuPauseSlotTreasures>();
        weaponTray = new ArrayList<MenuPauseSlotWeapons>();
        equipTray = new ArrayList<MenuPauseSlotEquip>();

        treasureTrayIndex = 0;
        weaponTrayIndex = 0;

        for (int i = 0; i < 6; i++) {
            treasureTray.add(new MenuPauseSlotTreasures(
                treasureTrayX + i * treasureSlotWidth,
                treasureTrayY,
                treasureSlotWidth,
                treasureTrayHeight,
                gameManager
            ));
        }

        for (int i = 0; i < 6; i++) {
            weaponTray.add(new MenuPauseSlotWeapons(
                weaponTrayX + i * weaponSlotWidth,
                weaponTrayY,
                weaponSlotWidth,
                weaponTrayHeight,
                gameManager
            ));
        }

        // potential multiple slot functionality
        equipTray.add(new MenuPauseSlotEquip(
            equipTrayX,
            equipTrayY,
            equipSlotWidth,
            equipTrayHeight,
            gameManager,
            MenuPauseSlotEquip.EquipSlotType.MAINHAND
        ));
    }

    public void logic() {
        switch(getMenuState()) {
        case ACTIVE:
            updateTrays();
            break;
        case FADING_IN:
            updateTrays();
            increaseOpacity();
            gameManager.setGameState(GameState.PAUSE_ITEMS);

            if (opacity >= 1f) {
                opacity = 1f;
                setMenuState(MenuState.ACTIVE);
            }
            break;
        case FADING_OUT:
            decreaseOpacity();
            gameManager.setGameState(GameState.PAUSE_ITEMS);

            if (opacity <= 0f) {
                opacity = 0f;

                setMenuState(MenuState.INACTIVE);
                gameManager.getWorld().getMapManager().unfreezeVisibleActors();
                gameManager.unpauseGame();
            }
            break;
        case INACTIVE:
            if (gameManager.getGameState() == GameState.PAUSE_ITEMS) {
                setMenuState(MenuState.FADING_IN);
            }
            break;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.setColor(1f, 1f, 1f, opacity);

        batch.draw(
            pauseTexture,
            (World.WORLD_WIDTH - 346) / 2,
            (World.WORLD_HEIGHT - 210) / 2,
            346,
            210
        );

        for (MenuPauseSlotTreasures slot : treasureTray) {
            batch.draw(
                World.images.getImageByItem(slot.getHeldItem()),
                slot.x,
                slot.y,
                slot.width,
                slot.height
            );
        }

        for (MenuPauseSlotWeapons slot : weaponTray) {
            batch.draw(
                World.images.getImageByItem(slot.getHeldItem()),
                slot.x,
                slot.y,
                slot.width,
                slot.height
            );
        }

        for (MenuPauseSlotEquip slot : equipTray) {
            batch.draw(
                World.images.getImageByItem(slot.getHeldItem()),
                slot.x,
                slot.y,
                slot.width,
                slot.height
            );
        }

        batch.setColor(1f, 1f, 1f, 1f);
    }

    public void updateTrays() {
        ArrayList<Treasure> treasures = gameManager.getTreasures();

        int treasureIndex = 0;
        for (MenuPauseSlotTreasures slot : treasureTray) {
            if (treasureIndex + treasureTrayIndex < treasures.size()) {
                slot.setHeldItem(treasures.get(treasureIndex+treasureTrayIndex));
            } else {
                slot.setHeldItem(Treasure.NONE);
            }
            treasureIndex++;
        }

        ArrayList<Weapon> weapons = gameManager.getWeapons();

        int weaponIndex = 0;
        for (MenuPauseSlotWeapons slot : weaponTray) {
            if (weaponIndex + weaponTrayIndex < weapons.size()) {
                slot.setHeldItem(weapons.get(weaponIndex+weaponTrayIndex));
            }
            else {
                slot.setHeldItem(Weapon.NONE);
            }

            weaponIndex++;
        }
    }

    public void increaseOpacity() {
        opacity += 1f * Gdx.graphics.getDeltaTime();
    }

    public void decreaseOpacity() {
        opacity -= 1f * Gdx.graphics.getDeltaTime();
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = Math.max(0f, Math.min(1f, opacity));
    }

    public ArrayList<MenuPauseSlotTreasures> getTreasureTray() {
        return treasureTray;
    }

    public ArrayList<MenuPauseSlotWeapons> getWeaponTray() {
        return weaponTray;
    }

    public ArrayList<MenuPauseSlotEquip> getEquipTray() {
        return equipTray;
    }
}
