package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuButtonLetter implements MenuButton {
    private final NameEntryScreen nameEntry;
    private final char letter;
    private final Rectangle collisionBox;

    public MenuButtonLetter(NameEntryScreen nameEntry, char letter, float x, float y, float width, float height) {
        this.letter = letter;
        this.nameEntry = nameEntry;
        this.collisionBox = new Rectangle(x, y, width, height);
    }

    public void draw(SpriteBatch batch) {}

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void onTouch() {
        Gdx.app.log(getClass().getSimpleName(), "current letter is: " + letter);
        nameEntry.addLetterToName(letter);
    }
}
