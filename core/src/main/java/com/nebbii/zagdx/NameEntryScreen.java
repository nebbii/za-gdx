package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class NameEntryScreen extends MenuScreen {
    private static final float LETTER_GRID_X = 64f;
    private static final float LETTER_GRID_Y = 34f;
    private static final float LETTER_GRID_WIDTH = 255f;
    private static final float LETTER_GRID_HEIGHT = 81f;

    private static final int LETTER_GRID_COLUMNS = 9;
    private static final int LETTER_GRID_ROWS = 3;

    private static final float LETTER_CELL_WIDTH = LETTER_GRID_WIDTH / LETTER_GRID_COLUMNS;
    private static final float LETTER_CELL_HEIGHT = LETTER_GRID_HEIGHT / LETTER_GRID_ROWS;

    private MenuButtonEnterName menuButtonEnterName;

    public NameEntryScreen(Game core) {
        super(core);
    }

    public void show() {
        super.show();

        setupLetterButtons();

        menuButtonEnterName = new MenuButtonEnterName(core, 291, 37, 26, 24);
        menuButtons.add(menuButtonEnterName);

        background = new Texture(Gdx.files.internal("dummy-entry-menu.png"));
    }

    private void setupLetterButtons() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);

            int visualRow = i / LETTER_GRID_COLUMNS;
            int column = i % LETTER_GRID_COLUMNS;

            float x = LETTER_GRID_X + column * LETTER_CELL_WIDTH;
            float y = LETTER_GRID_Y + (LETTER_GRID_ROWS - 1 - visualRow) * LETTER_CELL_HEIGHT;

            menuButtons.add(new MenuButtonLetter(letter, x, y, LETTER_CELL_WIDTH, LETTER_CELL_HEIGHT));
        }
    }
}
