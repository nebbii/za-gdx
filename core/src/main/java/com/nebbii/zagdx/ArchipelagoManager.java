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
            }

            saveManager.setSyncAP(false);
        }
    }

    @ArchipelagoEventListener
    public void onReceiveItem(ReceiveItemEvent event) {
        switch(event.getItemName()) {
        case "Boomerang":
            saveManager.addWeapon(Weapon.BOOMERANG);
            break;
        case "Celestial Sign 1":
            saveManager.addTreasure(Treasure.CELESTIAL_SIGN_1);
            break;
        case "Compass 1":
            saveManager.addTreasure(Treasure.COMPASS_1);
            break;
        case "Dagger":
            saveManager.addWeapon(Weapon.DAGGER);
            break;
        case "Empty Pitcher":
            saveManager.addTreasure(Treasure.PITCHER_EMPTY);
            break;
        case "Firestorm":
            saveManager.addWeapon(Weapon.FIRESTORM);
            break;
        case "Full Pitcher":
            saveManager.addTreasure(Treasure.PITCHER_FULL);
            break;
        case "Jade Ring":
            saveManager.addWeapon(Weapon.JADE_RING);
            break;
        case "Ladder":
            saveManager.addTreasure(Treasure.LADDER);
            break;
        case "Red Boots":
            saveManager.addTreasure(Treasure.RED_BOOTS);
            break;
        case "Underworld Map 1":
            saveManager.addTreasure(Treasure.UNDERWORLD_MAP_1);
            break;
        case "Vial of Wind":
            saveManager.addTreasure(Treasure.VIAL_OF_WIND);
            break;
        case "Wand":
            saveManager.addWeapon(Weapon.WAND);
            break;
        case "Blue Ruby":
            saveManager.increaseRubies(5);
            break;
        case "Yellow Ruby":
            saveManager.increaseRubies(10);
            break;
        }

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
