package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuPauseExitButton extends Rectangle implements MenuButton {
    public MenuPauseExitButton(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public void onTouch() {
        Gdx.app.exit();
    }

    public Rectangle getHitbox() {
        return this;
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
