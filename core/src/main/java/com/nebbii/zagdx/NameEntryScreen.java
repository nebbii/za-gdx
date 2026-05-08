package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NameEntryScreen extends MenuScreen {
    private static final float LETTER_GRID_X = 64f;
    private static final float LETTER_GRID_Y = 34f;
    private static final float LETTER_GRID_WIDTH = 255f;
    private static final float LETTER_GRID_HEIGHT = 81f;

    private static final int LETTER_GRID_COLUMNS = 9;
    private static final int LETTER_GRID_ROWS = 3;

    private static final float LETTER_CELL_WIDTH = LETTER_GRID_WIDTH / LETTER_GRID_COLUMNS;
    private static final float LETTER_CELL_HEIGHT = LETTER_GRID_HEIGHT / LETTER_GRID_ROWS;

    private MenuButtonBackspace menuButtonBackspace;
    private MenuButtonEnter menuButtonEnter;

    private SpriteBatch batch;

    private BitmapFont font;
    private GlyphLayout drawLayout;
    public String nameString;
    private float nameWidth;

    private SaveManager saveManager;

    public NameEntryScreen(Game core) {
        super(core);

        saveManager = new SaveManager();

        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    public void show() {
        super.show();

        nameString = "";

        setupLetterButtons();

        menuButtonBackspace = new MenuButtonBackspace(this, 291, 37, 26, 24);
        menuButtons.add(menuButtonBackspace);

        // 290 160 51 31
        menuButtonEnter = new MenuButtonEnter(this, 290, 160, 51, 31);
        menuButtons.add(menuButtonEnter);

        background = new Texture(Gdx.files.internal("dummy-entry-menu.png"));
    }

    public void logic() {
        super.logic();

        if (drawLayout != null) {
            nameWidth = drawLayout.width;
        }
    }

    public void draw() {
        super.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawLayout = font.draw(batch, nameString, 90, 145f);
        batch.end();
    }

    public void addLetterToName(char letter) {
        if (nameWidth > 200) return; // cdi devs rule

        nameString = nameString+letter;
    }

    public void backspaceLastLetter() {
        if (nameString.length() <= 0) return;

        nameString = nameString.substring(0, nameString.length() - 1);
    }

    private void setupLetterButtons() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);

            int visualRow = i / LETTER_GRID_COLUMNS;
            int column = i % LETTER_GRID_COLUMNS;

            float x = LETTER_GRID_X + column * LETTER_CELL_WIDTH;
            float y = LETTER_GRID_Y + (LETTER_GRID_ROWS - 1 - visualRow) * LETTER_CELL_HEIGHT;

            menuButtons.add(new MenuButtonLetter(this, letter, x, y, LETTER_CELL_WIDTH, LETTER_CELL_HEIGHT));
        }
    }

    public void createNewSave() {
        saveManager.createSave(nameString);
    }
}
