package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonSaveFile extends Rectangle implements MenuButton {
    private final MainMenuScreen mainMenuScreen;

    private String filename;
    private BitmapFont font;

    public MenuButtonSaveFile(MainMenuScreen mainMenuScreen, String filename, float x, float y, int width, int height) {
        super(x, y, width, height);

        this.mainMenuScreen = mainMenuScreen;
        this.filename = filename;

        this.font = new BitmapFont();
    }

    public void draw(SpriteBatch batch) {
        font.draw(batch, filename, x, y);
    }

    @Override
    public void onTouch() {
        Gdx.app.log(this.getClass().getSimpleName(), "file: " + filename);
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
