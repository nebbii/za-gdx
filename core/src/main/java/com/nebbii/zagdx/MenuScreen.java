package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    protected Texture buttonTexture;
    public float fadeOpacity;

    protected final Core core;

    protected ArrayList<MenuButton> menuButtons;

    public enum FadeToggle {
        IN,
        OUT
    }

    private FadeToggle fadeToggle;

    public MenuScreen(Core core) {
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

        fadeToggle = FadeToggle.IN;
        fadeOpacity = 1f;

        buttonTexture = new Texture(Gdx.files.internal("menu-button.png"));
        menuButtons = new ArrayList<>();
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
    }

    public void logic() {
        if (isFading()) {
            return;
        }
        else if (fadeToggle == FadeToggle.OUT && core.getNextScreen() != null) {
            core.setScreen(core.getNextScreen());
        }

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
            button.draw(batch);
        }
        batch.end();
    }

    public void drawFade() {
        switch(fadeToggle) {
			case IN:
            if (fadeOpacity > 0) {
                fadeOpacity -= Gdx.graphics.getDeltaTime() * 2;
                if (fadeOpacity < 0) fadeOpacity = 0;
            }
				break;
			case OUT:
            if (fadeOpacity < 1) {
                fadeOpacity += Gdx.graphics.getDeltaTime() * 2;
                if (fadeOpacity > 1) fadeOpacity = 1;
            }
				break;
			default:
				break;
        }

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(0f, 0f, 0f, fadeOpacity);
        shapes.rect(0f, 0f, WORLD_WIDTH, WORLD_HEIGHT);
        shapes.end();
    }

    public boolean isFading() {
        return fadeOpacity > 0 && fadeOpacity < 1;
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
        buttonTexture.dispose();
    }

    public FadeToggle getFadeToggle() {
        return fadeToggle;
    }

    public void setFadeToggle(FadeToggle fadeToggle) {
        this.fadeToggle = fadeToggle;
    }

    public Texture getButtonTexture() {
        return buttonTexture;
    }
}
