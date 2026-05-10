package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonEnter implements MenuButton {
    private final NameEntryScreen nameEntry;
    private final Rectangle collisionBox;

    public MenuButtonEnter(NameEntryScreen nameEntry, float x, float y, float width, float height) {
        this.nameEntry = nameEntry;
        this.collisionBox = new Rectangle(x, y, width, height);
    }

    public void draw() {}

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void onTouch() {
        nameEntry.createNewSave();
        nameEntry.core.setScreen(new MainMenuScreen(nameEntry.core));
    }
}
