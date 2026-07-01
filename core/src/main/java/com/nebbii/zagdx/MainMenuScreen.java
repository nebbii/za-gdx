package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

// TODO: Eventually swap out the dummy assets with real ones once the exporter supports it
public class MainMenuScreen extends MenuScreen {
    MenuButtonPlay menuButtonPlay;
    MenuButtonExit menuButtonExit;
    MenuButtonCreateSave menuButtonCreateSave;
    MenuButtonDeleteSave menuButtonDeleteSave;
    MenuButtonHowToPlay menuButtonHowToPlay;
    MenuButtonSettings menuButtonSettings;

    protected ArrayList<MenuButtonSaveFile> menuButtonSaves;

    private SaveManager saveManager;
    private SaveData selectedFile;

    private ArchipelagoClient archipelagoClient;

    private BitmapFont font;

    public MainMenuScreen(Core core) {
        super(core);
        this.archipelagoClient = core.getArchipelagoClient();
    }

    public void show() {
        super.show();

        core.getControlInput().initialize();
        this.font = new BitmapFont();

        menuButtonPlay = new MenuButtonPlay(core, this, 46, 71, 92, 29);
        menuButtons.add(menuButtonPlay);
        menuButtonCreateSave = new MenuButtonCreateSave(core, 46, 37, 92, 29);
        menuButtons.add(menuButtonCreateSave);
        menuButtonDeleteSave = new MenuButtonDeleteSave(this, 147, 37, 92, 29);
        menuButtons.add(menuButtonDeleteSave);
        menuButtonSettings = new MenuButtonSettings(core, 147, 71, 92, 29);
        menuButtons.add(menuButtonSettings);
        menuButtonExit = new MenuButtonExit(core, 290, 71, 55, 39);
        menuButtons.add(menuButtonExit);
        menuButtonHowToPlay = new MenuButtonHowToPlay(core, 251, 37, 92, 29);
        menuButtons.add(menuButtonHowToPlay);

        saveManager = new SaveManager();

        reloadSaves();
        background = new Texture(Gdx.files.internal("dummy-main-menu.png"));
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
    }

    public void draw() {
        super.draw();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (MenuButtonSaveFile button : menuButtonSaves) {
            button.draw(batch);
        }

        if (archipelagoClient.isConnected()) {
            font.draw(batch, "AP connected", 10, 15);
        }

        batch.end();

        drawFade();
    }

    public void reloadSaves() {
        menuButtonSaves = new ArrayList<MenuButtonSaveFile>();

        ArrayList<SaveData> saves = saveManager.getSaves();

        int i = 0;
        for (SaveData save : saves) {
            boolean selected = false;

            if (getSelectedFile() != null && save.filename.equals(getSelectedFile().filename)) {
                selected = true;
            }

            menuButtonSaves.add(new MenuButtonSaveFile(this, save, selected, 90, 165 - (20 * i), 200, 20));
            i++;
        }
    }

    @Override
    protected ArrayList<MenuButton> getSelectableMenuButtons() {
        int saveCount = menuButtonSaves == null ? 0 : menuButtonSaves.size();
        ArrayList<MenuButton> buttons = new ArrayList<MenuButton>(menuButtons.size() + saveCount);
        buttons.addAll(menuButtons);

        if (menuButtonSaves != null) {
            buttons.addAll(menuButtonSaves);
        }

        return buttons;
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

    public SaveData getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(SaveData selectedFile) {
        this.selectedFile = selectedFile;
    }

    public SaveManager getSaveManager() {
        return this.saveManager;
    }
}
