package com.nebbii.zagdx;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import io.github.archipelagomw.Client;

public class ArchipelagoClient extends Client {
    private Json json;

    private ArchipelagoConfig config;

    public ArchipelagoClient() {
        tryToConnect();
    }

    public void tryToConnect() {
        FileHandle configFile = Gdx.files.local(ArchipelagoConfigManager.CONFIG_PATH);

        if (configFile.exists()) {
            json = new Json();
            config = json.fromJson(ArchipelagoConfig.class, configFile);

            try {
                setGame("Zelda's Adventure GDX");
                setName(config.slotName);
                setPassword(config.password);
                setTags(new HashSet<>(config.tags == null ? new ArrayList<String>() : config.tags));
                setItemsHandlingFlags(7);

                connect(config.server);
            }
            catch (URISyntaxException ex) {
                throw new RuntimeException("Failed to connect to Archipelago server", ex);
            }
        }
        else {
            if (isConnected()) {
                disconnect();
            }
        }
    }

    @Override
    public void onError(Exception ex) {
        Gdx.app.log(this.getClass().getSimpleName(), "Archipelago client error: " + ex);
        throw new RuntimeException("Archipelago client error", ex);
    }

    @Override
    public void onClose(String reason, int attemptingReconnect) {
        Gdx.app.log(this.getClass().getSimpleName(), "Archipelago connection closed: " + reason);
    }
}
