package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nebbii.zagdx.MenuScreen.FadeToggle;

public class MenuButtonSettings extends Rectangle implements MenuButton {
    final Core core;
    private MenuScreen currentMenuScreen;
    private BitmapFont font;

    public MenuButtonSettings(Core core, float x, float y, int width, int height) {
        super(x, y, width, height);
        currentMenuScreen = (MenuScreen) core.getScreen();
        this.core = core;

        this.font = new BitmapFont();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(currentMenuScreen.getButtonTexture(), x, y, width, height);
        font.draw(batch, "Settings", x + 18, y + 22);
    }

    @Override
    public void onTouch() {
        MenuScreen currentMenuScreen = (MenuScreen) core.getScreen();
        currentMenuScreen.setFadeToggle(FadeToggle.OUT);
        core.setNextScreen(new SettingsMenuScreen(core));
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
