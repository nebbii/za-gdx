package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuButtonDeleteSave extends Rectangle implements MenuButton {

    private MainMenuScreen menuScreen;
    private boolean beingDeleted = false;

    public MenuButtonDeleteSave(MainMenuScreen menuScreen, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.menuScreen = menuScreen;
    }

    public void draw(SpriteBatch batch) {

    }

    @Override
    public void onTouch() {
        if (menuScreen.getSelectedFile() == null) return;

        if (!beingDeleted) {
            beingDeleted = true;
        }
        else {
            menuScreen.getSaveManager().deleteSave(menuScreen.getSelectedFile().filename);
            menuScreen.setSelectedFile(null);
            menuScreen.reloadSaves();

            beingDeleted = false;
        }
   }

    public boolean isBeingDeleted() {
        return beingDeleted;
    }

    public void cancelDeletion() {
        beingDeleted = false;
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
