package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonDeleteSave extends Rectangle implements MenuButton {

    private MainMenuScreen menuScreen;

    public MenuButtonDeleteSave(MainMenuScreen menuScreen, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.menuScreen = menuScreen;
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public void onTouch() {
        if (menuScreen.getSelectedFile() == null) return;

        menuScreen.getSaveManager().deleteSave(menuScreen.getSelectedFile().filename);
        menuScreen.setSelectedFile(null);

        menuScreen.reloadSaves();
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
