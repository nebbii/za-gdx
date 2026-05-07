package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonDeleteSave extends Rectangle implements MenuButton {

    final Game core;

    public MenuButtonDeleteSave(Game core, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.core = core;
    }

    @Override
    public void onTouch() {
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
