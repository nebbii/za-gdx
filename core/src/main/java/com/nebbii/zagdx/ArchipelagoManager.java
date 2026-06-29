package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ReceiveItemEvent;
import io.github.archipelagomw.parts.NetworkItem;

public class ArchipelagoManager {
    private ArchipelagoClient archipelagoClient;
    private GameManager gameManager;
    private SaveManager saveManager;

    public ArchipelagoManager(ArchipelagoClient client, GameManager gameManager, SaveManager saveManager) {
        this.archipelagoClient = client;
        this.gameManager = gameManager;
        this.saveManager = saveManager;

        this.archipelagoClient.getEventManager().registerListener(this);
    }

    public void logic() {
        if (saveManager.canSyncAP()) {
            Gdx.app.log(this.getClass().getSimpleName(), "Running sync");

            // send locations
            for (SavedLocationEntry locationEntry : saveManager.getLocations()) {
                long locationId = ArchipelagoLocationMap.getId(locationEntry.id);
                archipelagoClient.checkLocation(locationId);
                saveManager.addArchipelagoCheck(locationId);
            }

            // retrieve locations
            for (NetworkItem item : archipelagoClient.getItemManager().getReceivedItems()) {
                int itemId = (int) item.itemID;
                Item receivedItem = ArchipelagoItemMap.getItem(itemId);

                if (!saveManager.hasArchipelagoCheck(item.locationID)) {
                    Gdx.app.log(
                        this.getClass().getSimpleName(),
                        "New AP item"
                            + ", item=" + item.itemID
                            + ", location=" + item.locationID
                            + ", itemName=" + receivedItem.toString()
                    );

                    if (receivedItem instanceof Treasure) {
                        if (itemId == 1) {
                            gameManager.increaseRubies(5, true);
                        }
                        else if (itemId == 15) { // 15 = yellow rubies
                            gameManager.increaseRubies(10, true);
                        }
                        else {
                            gameManager.addTreasure((Treasure) receivedItem, true);
                        }
                    }
                    else if (receivedItem instanceof Weapon) {
                        gameManager.addWeapon((Weapon) receivedItem, true);
                    }

                    saveManager.addArchipelagoCheck(item.locationID);
                }
                else {
                    Gdx.app.log(
                        this.getClass().getSimpleName(),
                        "Existing AP item"
                            + ", item=" + item.itemID
                            + ", location=" + item.locationID
                            + ", itemName=" + receivedItem.toString()
                    );
                }
            }

            saveManager.setSyncAP(false);
        }
    }

    @ArchipelagoEventListener
    public void onReceiveItem(ReceiveItemEvent event) {
        Gdx.app.log(this.getClass().getSimpleName(), "Received an item!");

        saveManager.setSyncAP(true);
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
