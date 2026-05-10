package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

// TODO: Eventually swap out the dummy assets with real ones once the exporter supports it
public class MainMenuScreen extends MenuScreen {
    MenuButtonPlay menuButtonPlay;
    MenuButtonExit menuButtonExit;
    MenuButtonCreateSave menuButtonCreateSave;
    MenuButtonDeleteSave menuButtonDeleteSave;
    MenuButtonHowToPlay menuButtonHowToPlay;

    private SaveManager saveManager;
    private String selectedFilename;

	public MainMenuScreen(Game core) {
        super(core);
    }

    public void show() {
        super.show();

        menuButtonPlay = new MenuButtonPlay(core, this, 46, 71, 92, 29);
        menuButtons.add(menuButtonPlay);
        menuButtonCreateSave = new MenuButtonCreateSave(core, 46, 37, 92, 29);
        menuButtons.add(menuButtonCreateSave);
        menuButtonDeleteSave = new MenuButtonDeleteSave(core, 147, 37, 92, 29);
        menuButtons.add(menuButtonDeleteSave);
        menuButtonExit = new MenuButtonExit(core, 290, 71, 55, 39);
        menuButtons.add(menuButtonExit);
        menuButtonHowToPlay = new MenuButtonHowToPlay(core, 251, 37, 92, 29);
        menuButtons.add(menuButtonHowToPlay);

        saveManager = new SaveManager();

        loadSaveBox();

        background = new Texture(Gdx.files.internal("dummy-main-menu.png"));
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
    }

    public void logic() {
        super.logic();
    }

    public void draw(){
        super.draw();

    }

    public void loadSaveBox() {
        ArrayList<SaveData> saves = saveManager.getSaves();

        int i = 0;
        for (SaveData save : saves) {
            menuButtons.add(new MenuButtonSaveFile(this, save, 90, 180 - (20 * i), 200, 20));
            i++;
        }
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

    public String getSelectedFilename() {
        return selectedFilename;
    }

    public void setSelectedFilename(String selectedFilename) {
        this.selectedFilename = selectedFilename;
    }
}
