package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
    private ArrayList<MenuPauseTrayButton> trayButtons;

    private int treasureTrayIndex;
    private int weaponTrayIndex;

    private MenuState menuState;
    private float opacity;
    private int selectedSlotIndex;

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
        this(gameManager, World.images.getItemScreen());
    }

    MenuPause(GameManager gameManager, Texture pauseTexture) {
        this.gameManager = gameManager;
        this.pauseTexture = pauseTexture;
        this.opacity = 0f;
        this.menuState = MenuState.INACTIVE;
        this.selectedSlotIndex = 0;

        treasureTray = new ArrayList<MenuPauseSlotTreasures>();
        weaponTray = new ArrayList<MenuPauseSlotWeapons>();
        equipTray = new ArrayList<MenuPauseSlotEquip>();
        trayButtons = new ArrayList<MenuPauseTrayButton>();

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

        trayButtons.add(new MenuPauseTrayButton(
            treasureTrayX - treasureSlotWidth,
            treasureTrayY,
            treasureSlotWidth,
            treasureTrayHeight,
            MenuPauseTrayButton.TrayType.TREASURE,
            MenuPauseTrayButton.TrayDirection.LEFT,
            this
        ));
        trayButtons.add(new MenuPauseTrayButton(
            treasureTrayX + treasureSlotWidth * treasureTray.size(),
            treasureTrayY,
            treasureSlotWidth,
            treasureTrayHeight,
            MenuPauseTrayButton.TrayType.TREASURE,
            MenuPauseTrayButton.TrayDirection.RIGHT,
            this
        ));
        trayButtons.add(new MenuPauseTrayButton(
            weaponTrayX - weaponSlotWidth,
            weaponTrayY,
            weaponSlotWidth,
            weaponTrayHeight,
            MenuPauseTrayButton.TrayType.WEAPON,
            MenuPauseTrayButton.TrayDirection.LEFT,
            this
        ));
        trayButtons.add(new MenuPauseTrayButton(
            weaponTrayX + weaponSlotWidth * weaponTray.size(),
            weaponTrayY,
            weaponSlotWidth,
            weaponTrayHeight,
            MenuPauseTrayButton.TrayType.WEAPON,
            MenuPauseTrayButton.TrayDirection.RIGHT,
            this
        ));

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

        if (getMenuState() == MenuState.ACTIVE) {
            drawCursor(batch);
        }

        batch.setColor(1f, 1f, 1f, 1f);
    }

    private void drawCursor(SpriteBatch batch) {
        MenuButton selectedSlot = getSelectedSlot();

        if (selectedSlot == null) {
            return;
        }

        Rectangle currentBox = selectedSlot.getCollisionBox();

        batch.draw(
            World.images.getGlow(),
            currentBox.x,
            currentBox.y,
            currentBox.width,
            currentBox.height
        );
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
        opacity += 1.3f * Gdx.graphics.getDeltaTime();
    }

    public void decreaseOpacity() {
        opacity -= 1.3f * Gdx.graphics.getDeltaTime();
    }

    public boolean handleMenuInput(ControlAction action) {
        switch(action) {
        case MOVE_UP:
        case MOVE_DOWN:
        case MOVE_LEFT:
        case MOVE_RIGHT:
            return moveSelection(action);
        case ACTION:
            return activateSelectedSlot();
        default:
            return false;
        }
    }

    public boolean moveSelection(ControlAction action) {
        MenuButton currentSlot = getSelectedSlot();

        if (currentSlot == null) {
            selectedSlotIndex = 0;
            return false;
        }

        ArrayList<MenuButton> slots = getSelectableSlots();
        Rectangle currentBounds = currentSlot.getCollisionBox();
        float currentCenterX = centerX(currentBounds);
        float currentCenterY = centerY(currentBounds);
        int bestIndex = -1;
        float bestDistance = Float.MAX_VALUE;

        for (int i = 0; i < slots.size(); i++) {
            if (i == selectedSlotIndex) {
                continue;
            }

            Rectangle candidateBounds = slots.get(i).getCollisionBox();
            float dx = centerX(candidateBounds) - currentCenterX;
            float dy = centerY(candidateBounds) - currentCenterY;

            if (!isInDirection(action, dx, dy)) {
                continue;
            }

            float distance = dx * dx + dy * dy;

            if (distance < bestDistance) {
                bestDistance = distance;
                bestIndex = i;
            }
        }

        if (bestIndex < 0) {
            return false;
        }

        selectedSlotIndex = bestIndex;
        return true;
    }

    public boolean activateSelectedSlot() {
        MenuButton selectedSlot = getSelectedSlot();

        if (selectedSlot == null) {
            return false;
        }

        selectedSlot.onTouch();
        return true;
    }

    public void selectSlot(MenuButton slot) {
        ArrayList<MenuButton> slots = getSelectableSlots();

        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i) == slot) {
                selectedSlotIndex = i;
                return;
            }
        }
    }

    private boolean isInDirection(ControlAction action, float dx, float dy) {
        switch(action) {
        case MOVE_UP:
            return dy > 0f;
        case MOVE_DOWN:
            return dy < 0f;
        case MOVE_LEFT:
            return dx < 0f;
        case MOVE_RIGHT:
            return dx > 0f;
        default:
            return false;
        }
    }

    private float centerX(Rectangle bounds) {
        return bounds.x + bounds.width / 2f;
    }

    private float centerY(Rectangle bounds) {
        return bounds.y + bounds.height / 2f;
    }

    private ArrayList<MenuButton> getSelectableSlots() {
        ArrayList<MenuButton> slots = new ArrayList<MenuButton>(
            6 + 6 + 1 + 4
        );

        slots.addAll(treasureTray);
        slots.addAll(weaponTray);
        slots.addAll(equipTray);
        slots.addAll(trayButtons);

        return slots;
    }

    public MenuButton getSelectedSlot() {
        ArrayList<MenuButton> slots = getSelectableSlots();

        if (slots.isEmpty()) {
            return null;
        }

        if (selectedSlotIndex < 0 || selectedSlotIndex >= slots.size()) {
            selectedSlotIndex = 0;
        }

        return slots.get(selectedSlotIndex);
    }

    public int getSelectedSlotIndex() {
        return selectedSlotIndex;
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

    public ArrayList<MenuPauseTrayButton> getTrayButtons() {
        return trayButtons;
    }

    public int getTreasureTrayIndex() {
        return treasureTrayIndex;
    }

    public void setTreasureTrayIndex(int treasureTrayIndex) {
        this.treasureTrayIndex = treasureTrayIndex;
    }

    public void increaseTreasureTrayIndex() {
        if (treasureTrayIndex < 50) {
            treasureTrayIndex++;
        }
    }

    public void decreaseTreasureTrayIndex() {
        if (treasureTrayIndex > 0) {
            treasureTrayIndex--;
        }
    }

    public int getWeaponTrayIndex() {
        return weaponTrayIndex;
    }

    public void setWeaponTrayIndex(int weaponTrayIndex) {
        this.weaponTrayIndex = weaponTrayIndex;
    }

    public void increaseWeaponTrayIndex() {
        if (weaponTrayIndex < 50) {
            weaponTrayIndex++;
        }
    }

    public void decreaseWeaponTrayIndex() {
        if (weaponTrayIndex > 0) {
            weaponTrayIndex--;
        }
    }
}
