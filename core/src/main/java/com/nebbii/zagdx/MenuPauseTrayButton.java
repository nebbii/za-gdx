package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuPauseTrayButton extends Rectangle implements MenuButton {
    public enum TrayType {
        TREASURE,
        WEAPON
    }

    public enum TrayDirection {
        LEFT,
        RIGHT
    }

    private final TrayType trayType;
    private final TrayDirection direction;
    private final MenuPause menuPause;

    public MenuPauseTrayButton(float x, float y, int width, int height, TrayType trayType, TrayDirection direction, MenuPause menuPause) {
        super(x, y, width, height);
        this.trayType = trayType;
        this.direction = direction;
        this.menuPause = menuPause;
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public void onTouch() {
        if (trayType == TrayType.TREASURE) {
            if (direction == TrayDirection.LEFT) {
                menuPause.decreaseTreasureTrayIndex();
            }
            else {
                menuPause.increaseTreasureTrayIndex();
            }
        }

        if (trayType == TrayType.WEAPON) {
            if (direction == TrayDirection.LEFT) {
                menuPause.decreaseWeaponTrayIndex();
            }
            else {
                menuPause.increaseWeaponTrayIndex();
            }
        }
    }

    public Rectangle getHitbox() {
        return this;
    }

    public Rectangle getCollisionBox() {
        return this;
    }

    public TrayType getTrayType() {
        return trayType;
    }

    public TrayDirection getDirection() {
        return direction;
    }
}
