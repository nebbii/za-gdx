package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonExit extends Rectangle implements MenuButton {
    final Game core;

    public MenuButtonExit(Game core, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.core = core;
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public void onTouch() {
        Gdx.app.exit();
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
