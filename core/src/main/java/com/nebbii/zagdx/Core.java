package com.nebbii.zagdx;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game {
    private ArchipelagoClient archipelagoClient;

    @Override
    public void create() {
        archipelagoClient = new ArchipelagoClient();

        setScreen(new MainMenuScreen(this));
    }

    public ArchipelagoClient getArchipelagoClient() {
		return archipelagoClient;
	}
}
