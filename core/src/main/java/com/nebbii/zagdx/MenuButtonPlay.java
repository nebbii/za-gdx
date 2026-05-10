package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonPlay extends Rectangle implements MenuButton {
    final Game core;
    private MainMenuScreen mainMenuScreen;

    public MenuButtonPlay(Game core, MainMenuScreen mainMenuScreen, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.core = core;
        this.mainMenuScreen = mainMenuScreen;
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public void onTouch() {
        core.setScreen(new GameScreen(core, mainMenuScreen.getSelectedFilename()));
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
