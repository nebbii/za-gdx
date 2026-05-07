package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
public class MainMenuScreen implements Screen {
    static final int WORLD_WIDTH = 384;
    static final int WORLD_HEIGHT = 240;

    private SpriteBatch batch;
    private ShapeRenderer shapes;

    private OrthographicCamera camera;
    private Viewport viewport;
    private final Vector2 touchPos = new Vector2();

    private Texture background;
    public float fadeOpacity;

    final Game core;

    ArrayList<MenuButton> menuButtons;

    MenuButtonPlay menuButtonPlay;
    MenuButtonExit menuButtonExit;
    MenuButtonCreateSave menuButtonCreateSave;
    MenuButtonDeleteSave menuButtonDeleteSave;
    MenuButtonHowToPlay menuButtonHowToPlay;

    public MainMenuScreen(Game core) {
        this.core = core;
    }

    public void show() {
        batch = new SpriteBatch();
        shapes = new ShapeRenderer();

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply(true);

        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0f);
        camera.update();

        fadeOpacity = 0f;

        menuButtons = new ArrayList<>();

        menuButtonPlay = new MenuButtonPlay(core, 46, 71, 92, 29);
        menuButtons.add(menuButtonPlay);
        menuButtonCreateSave = new MenuButtonCreateSave(core, 46, 37, 92, 29);
        menuButtons.add(menuButtonCreateSave);
        menuButtonDeleteSave = new MenuButtonDeleteSave(core, 147, 37, 92, 29);
        menuButtons.add(menuButtonDeleteSave);
        menuButtonExit = new MenuButtonExit(core, 290, 71, 55, 39);
        menuButtons.add(menuButtonExit);
        menuButtonHowToPlay = new MenuButtonHowToPlay(core, 251, 37, 92, 29);
        menuButtons.add(menuButtonHowToPlay);

        // menuSaveBox = new MenuSaveBox(core, 76, 115, 234, 80)

        background = new Texture(Gdx.files.internal("dummy-main-menu.png"));
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
    }

    public void logic() {
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            for (MenuButton button : menuButtons) {
                if (button.contains(touchPos.x, touchPos.y)) {
                    button.onTouch();
                }
            }
        }
    }

    public void draw(){
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.end();

        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Line);

        // TODO: replace with button images once the export has it
        for (MenuButton button : menuButtons) {
            Rectangle rectangle = button.getCollisionBox();
            shapes.setColor(Color.RED);

            shapes.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        shapes.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(0f, 0f, 0f, fadeOpacity);
        shapes.rect(0f, 0f, WORLD_WIDTH, WORLD_HEIGHT);
        shapes.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
    }
}
