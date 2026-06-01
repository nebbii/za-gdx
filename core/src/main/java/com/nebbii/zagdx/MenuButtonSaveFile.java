package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonSaveFile extends Rectangle implements MenuButton {
    private final MainMenuScreen mainMenuScreen;

    private SaveData saveFile;
    private BitmapFont font;
    private boolean selected;

    public MenuButtonSaveFile(MainMenuScreen mainMenuScreen, SaveData saveFile, boolean selected, float x, float y, int width, int height) {
        super(x, y, width, height);

        this.mainMenuScreen = mainMenuScreen;
        this.saveFile = saveFile;
        this.selected = selected;

        this.font = new BitmapFont();
    }

    public void draw(SpriteBatch batch) {
        String name;

        if (selected) {
            name = "> " + saveFile.name;
        }
        else {
            name = saveFile.name;
        }

        font.draw(batch, name, x, y + 15); // temporary (hopefully) until it's rendered
    }

    @Override
    public void onTouch() {
        mainMenuScreen.setSelectedFile(saveFile);
        mainMenuScreen.reloadSaves();
        Gdx.app.log(this.getClass().getSimpleName(), "saveFile: " + saveFile.filename);
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
