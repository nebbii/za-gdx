package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonLetter implements MenuButton {
    private final char letter;
    private final Rectangle collisionBox;

    public MenuButtonLetter(char letter, float x, float y, float width, float height) {
        this.letter = letter;
        this.collisionBox = new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void onTouch() {
        Gdx.app.log(getClass().getSimpleName(), "current letter is: " + letter);
    }
}
