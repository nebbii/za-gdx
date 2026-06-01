package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.MenuScreen.FadeToggle;

public class MenuButtonEnter implements MenuButton {
    private final NameEntryScreen nameEntry;
    private final Rectangle collisionBox;

    public MenuButtonEnter(NameEntryScreen nameEntry, float x, float y, float width, float height) {
        this.nameEntry = nameEntry;
        this.collisionBox = new Rectangle(x, y, width, height);
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void onTouch() {
        nameEntry.createNewSave();

        Core core = nameEntry.core;
        MenuScreen currentMenuScreen = (MenuScreen) core.getScreen();
        currentMenuScreen.setFadeToggle(FadeToggle.OUT);
        core.setNextScreen(new MainMenuScreen(nameEntry.core));
    }
}
