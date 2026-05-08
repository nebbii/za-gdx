package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonEnter implements MenuButton {
    private final Game core;
    private final Rectangle collisionBox;

    public MenuButtonEnter(Game core, float x, float y, float width, float height) {
        this.core = core;
        this.collisionBox = new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void onTouch() {
        Gdx.app.log(getClass().getSimpleName(), "enter button touched");
    }
}
