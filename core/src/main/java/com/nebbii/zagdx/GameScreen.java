package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private World world;

    final Core core;
    SaveData selectedFile;

    public GameScreen(Core core, SaveData selectedFile) {
        this.core = core;
        this.selectedFile = selectedFile;
    }

    public void show() {
        batch = new SpriteBatch();

        world = new World(batch, selectedFile, core.getArchipelagoClient(), core.getSettingsManager(), core.getControlInput());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);

        // for transparency
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        world.logic();
        world.draw();
    }

    @Override
    public void resize(int width, int height) {
        world.resize(width, height);
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
        world.dispose();
    }
}
