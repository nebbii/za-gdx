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
public class MenuScreen implements Screen {
    static final int WORLD_WIDTH = 384;
    static final int WORLD_HEIGHT = 240;

    protected SpriteBatch batch;
    protected ShapeRenderer shapes;

    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected final Vector2 touchPos = new Vector2();

    protected Texture background;
    public float fadeOpacity;

    protected final Game core;

    protected ArrayList<MenuButton> menuButtons;

    public MenuScreen(Game core) {
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

            for (MenuButton button : menuButtons) {
                if (button.contains(touchPos.x, touchPos.y)) {
                    button.onTouch();
                }
            }
        }
    }

    public void draw(){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        ScreenUtils.clear(0f, 0f, 0f, 1f);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        for (MenuButton button : menuButtons) {
            button.draw();
        }
        batch.end();

        /*
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Line);

        // TODO: replace with button images once the export has it
        for (MenuButton button : menuButtons) {
            Rectangle rectangle = button.getCollisionBox();
            shapes.setColor(Color.RED);

            shapes.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        shapes.end();
        */

        /*
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(0f, 0f, 0f, fadeOpacity);
        shapes.rect(0f, 0f, WORLD_WIDTH, WORLD_HEIGHT);
        shapes.end();
        */
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
