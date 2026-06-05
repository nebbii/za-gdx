package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.MenuScreen.FadeToggle;

public class MenuButtonCreateSave extends Rectangle implements MenuButton {
    final Core core;

    public MenuButtonCreateSave(Core core, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.core = core;
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public void onTouch() {
        MenuScreen currentMenuScreen = (MenuScreen) core.getScreen();
        currentMenuScreen.setFadeToggle(FadeToggle.OUT);
        core.setNextScreen(new NameEntryScreen(core));
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
