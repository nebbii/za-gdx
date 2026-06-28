package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

public class ArchipelagoManager {
    private ArchipelagoClient archipelagoClient;

    public ArchipelagoManager(ArchipelagoClient client) {
        archipelagoClient = client;
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
