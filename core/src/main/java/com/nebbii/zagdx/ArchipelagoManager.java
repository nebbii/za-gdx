package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class ArchipelagoManager {
    private ArchipelagoClient archipelagoClient;
    private SaveManager saveManager;

    public ArchipelagoManager(ArchipelagoClient client, SaveManager saveManager) {
        this.archipelagoClient = client;
        this.saveManager = saveManager;
    }

    public void logic() {
        if (!saveManager.canSyncAP()) return;

        Gdx.app.log(this.getClass().getSimpleName(), "Running sync");

        saveManager.setSyncAP(false);
    }

    public boolean isConnected() {
        return archipelagoClient.isConnected();
    }

    public ArchipelagoClient getArchipelagoClient() {
        return archipelagoClient;
    }

    public void setArchipelagoClient(ArchipelagoClient archipelagoClient) {
        this.archipelagoClient = archipelagoClient;
    }
}
