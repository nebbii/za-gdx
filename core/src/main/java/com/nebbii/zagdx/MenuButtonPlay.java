package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonPlay extends Rectangle implements MenuButton {
    final Game core;

    public MenuButtonPlay(Game core, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.core = core;
    }

    public void draw() {}

    @Override
    public void onTouch() {
        core.setScreen(new GameScreen(core));
    }

    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    public Rectangle getCollisionBox() {
        return this;
    }
}
