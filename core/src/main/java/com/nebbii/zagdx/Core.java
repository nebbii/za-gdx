package com.nebbii.zagdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game {
    private ArchipelagoClient archipelagoClient;
    private SettingsManager settingsManager;

    private Screen nextScreen;

    @Override
    public void create() {
        settingsManager = new SettingsManager();
        archipelagoClient = new ArchipelagoClient();

        setScreen(new MainMenuScreen(this));
    }

    public ArchipelagoClient getArchipelagoClient() {
		return archipelagoClient;
	}

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public Screen getNextScreen() {
        return nextScreen;
    }

    public void setNextScreen(Screen nextScreen) {
        this.nextScreen = nextScreen;
    }
}
