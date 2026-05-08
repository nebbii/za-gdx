package com.nebbii.zagdx;

import java.awt.Menu;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

// TODO: Eventually swap out the dummy assets with real ones once the exporter supports it
public class NameEntryScreen extends MenuScreen {
    private ArrayList<LetterButton> letterButtons;

    private Rectangle enterButton;

    private static final float LETTER_GRID_X = 64f;
    private static final float LETTER_GRID_Y = 34f;
    private static final float LETTER_GRID_WIDTH = 255f;
    private static final float LETTER_GRID_HEIGHT = 81f;

    private static final int LETTER_GRID_COLUMNS = 9;
    private static final int LETTER_GRID_ROWS = 3;

    private static final float LETTER_CELL_WIDTH = LETTER_GRID_WIDTH / LETTER_GRID_COLUMNS;
    private static final float LETTER_CELL_HEIGHT = LETTER_GRID_HEIGHT / LETTER_GRID_ROWS;

    public NameEntryScreen(Game core) {
        super(core);
    }

    public void show() {
        batch = new SpriteBatch();
        shapes = new ShapeRenderer();

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply(true);

        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0f);
        camera.update();

        background = new Texture(Gdx.files.internal("dummy-entry-menu.png"));

        letterButtons = new ArrayList<>();

        setupLetterButtons();

        enterButton = new Rectangle(291f, 37f, 26f, 26f);
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
    }

    public void logic() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            for (LetterButton letterButton : letterButtons) {
                if (letterButton.contains(touchPos.x, touchPos.y)) {
                    onLetterTouched(letterButton.getLetter());
                    return;
                }
            }

            if (enterButton.contains(touchPos.x, touchPos.y)) {
                onEnterTouched();
            }
        }
    }

    public void draw() {
        ScreenUtils.clear(0f, 0f, 0f, 1f);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.end();

        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Line);

        shapes.setColor(Color.RED);
        for (LetterButton letterButton : letterButtons) {
            Rectangle rectangle = letterButton.getCollisionBox();
            shapes.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }

        shapes.setColor(Color.GREEN);
        shapes.rect(enterButton.x, enterButton.y, enterButton.width, enterButton.height);

        shapes.end();
    }

    private void onLetterTouched(char letter) {
        Gdx.app.log(getClass().getSimpleName(), "current letter is: " + letter);
    }

    private void onEnterTouched() {
        Gdx.app.log(getClass().getSimpleName(), "enter button touched");
    }

    private void setupLetterButtons() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);

            int visualRow = i / LETTER_GRID_COLUMNS;
            int column = i % LETTER_GRID_COLUMNS;

            float x = LETTER_GRID_X + column * LETTER_CELL_WIDTH;
            float y = LETTER_GRID_Y + (LETTER_GRID_ROWS - 1 - visualRow) * LETTER_CELL_HEIGHT;

            Rectangle rectangle = new Rectangle(x, y, LETTER_CELL_WIDTH, LETTER_CELL_HEIGHT);
            letterButtons.add(new LetterButton(letter, rectangle));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapes.dispose();
        background.dispose();
    }

    private static class LetterButton {
        private final char letter;
        private final Rectangle collisionBox;

        public LetterButton(char letter, Rectangle collisionBox) {
            this.letter = letter;
            this.collisionBox = collisionBox;
        }

        public boolean contains(float x, float y) {
            return collisionBox.contains(x, y);
        }

        public char getLetter() {
            return letter;
        }

        public Rectangle getCollisionBox() {
            return collisionBox;
        }
    }
}
