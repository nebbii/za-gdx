package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ReceiveItemEvent;
import io.github.archipelagomw.parts.NetworkItem;

public class ArchipelagoManager {
    private ArchipelagoClient archipelagoClient;
    private SaveManager saveManager;

    public ArchipelagoManager(ArchipelagoClient client, SaveManager saveManager) {
        this.archipelagoClient = client;
        this.saveManager = saveManager;

        this.archipelagoClient.getEventManager().registerListener(this);
    }

    public void logic() {

        if (saveManager.canSyncAP()) {
            Gdx.app.log(this.getClass().getSimpleName(), "Running sync");

            // send locations
            for (SavedLocationEntry locationEntry : saveManager.getLocations()) {
                int locationId = ArchipelagoLocationMap.getId(locationEntry.id);
                archipelagoClient.checkLocation(locationId);
            }

            // retrieve locations
            for (NetworkItem item : archipelagoClient.getItemManager().getReceivedItems()) {
                Gdx.app.log(
                    this.getClass().getSimpleName(),
                    "Existing AP item"
                        + ", item=" + item.itemID
                        + ", location=" + item.locationID
                        + ", player=" + item.playerID
                );
                // if AP item
            }

            saveManager.setSyncAP(false);
        }
    }

    @ArchipelagoEventListener
    public void onReceiveItem(ReceiveItemEvent event) {
        Item item = ArchipelagoItemMap.getItem(event.getItemName());

        /*
        if (item instanceof Treasure) {
            saveManager.addTreasure((Treasure) item);
        }
        else if (item instanceof Weapon) {
            saveManager.addWeapon((Weapon) item);
        }
        else {
            switch(event.getItemName()) {
            case "Blue Ruby":
                saveManager.increaseRubies(5);
                break;
            case "Yellow Ruby":
                saveManager.increaseRubies(10);
                break;
            }
        }
        */

        Gdx.app.log(this.getClass().getSimpleName(), "Received an item!"
                     + "Item: " + event.getItemName()
                     + "Location: " + event.getLocationName()
                     + "ID: " + event.getItemID()
                    );
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
