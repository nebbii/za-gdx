package com.nebbii.zagdx;

import java.net.URISyntaxException;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import io.github.archipelagomw.Client;

public class ArchipelagoClient extends Client {
    private static final String CONFIG = "archipelago/config.json";
    private Json json;

    private ArchipelagoConfig config;

    public ArchipelagoClient() {
        FileHandle configFile = Gdx.files.local(CONFIG);

        if (configFile.exists()) {
            json = new Json();
            config = json.fromJson(ArchipelagoConfig.class, configFile);

            try {
                setGame("Donkey Kong 64");
                setName(config.slotName);
                setPassword(config.password);
                setTags(new HashSet<>(config.tags));

                connect(config.server);
            }
            catch (URISyntaxException ex) {
                throw new RuntimeException("Failed to connect to Archipelago server", ex);
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
