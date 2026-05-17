package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonPlay extends Rectangle implements MenuButton {
    final Core core;
    private MainMenuScreen mainMenuScreen;

    public MenuButtonPlay(Core core, MainMenuScreen mainMenuScreen, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.core = core;
        this.mainMenuScreen = mainMenuScreen;
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public void onTouch() {
        if (mainMenuScreen.getSelectedFile() != null) {
            core.setScreen(new GameScreen(core, mainMenuScreen.getSelectedFile()));
        }
        else {
            Gdx.app.log(getClass().getSimpleName(), "No file selected");
        }
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
