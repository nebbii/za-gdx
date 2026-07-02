package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.LocationInfoEvent;
import io.github.archipelagomw.events.ReceiveItemEvent;
import io.github.archipelagomw.events.DeathLinkEvent;
import io.github.archipelagomw.parts.NetworkItem;

public class ArchipelagoManager {
    private ArchipelagoClient archipelagoClient;
    private GameManager gameManager;
    private SaveManager saveManager;

    private Map<Long, Long> scoutedLocations;

    public ArchipelagoManager(ArchipelagoClient client, GameManager gameManager, SaveManager saveManager) {
        this.archipelagoClient = client;
        this.gameManager = gameManager;
        this.saveManager = saveManager;

        this.archipelagoClient.getEventManager().registerListener(this);

        ArrayList<Long> locationIDs = new ArrayList<>(ArchipelagoItemMap.ID_TO_ITEM.keySet());

        if (this.archipelagoClient.isConnected()) {
            scoutedLocations = new HashMap<Long, Long>();
            archipelagoClient.scoutLocations(locationIDs);
        }
    }

    public void logic() {
        if (saveManager.canSyncAP()) {
            Gdx.app.log(this.getClass().getSimpleName(), "Running sync");

            // send locations
            for (SavedLocationEntry locationEntry : saveManager.getLocations()) {
                long locationId = ArchipelagoLocationMap.getId(locationEntry.id);
                archipelagoClient.checkLocation(locationId);
                if (!locationEntry.action.equals("dead")) {
                    saveManager.addArchipelagoCheck(locationId);
                }
            }

            // retrieve locations
            for (NetworkItem item : archipelagoClient.getItemManager().getReceivedItems()) {
                handleReceivedItem(item);
            }

            saveManager.setSyncAP(false);
        }

        if (saveManager.canDeathOutAP()) {
            archipelagoClient.sendDeathlink(archipelagoClient.getAlias(), archipelagoClient.getAlias() + " ran out of hearts!");

            saveManager.setDeathOutAP(false);
        }
    }

    private void handleReceivedItem(NetworkItem item) {
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
            /*
            Gdx.app.log(
                this.getClass().getSimpleName(),
                "Existing AP item"
                    + ", item=" + item.itemID
                    + ", location=" + item.locationID
                    + ", itemName=" + receivedItem.toString()
            );
            */
        }
    }

    public ActorJsonEntry overrideJsonEntry(ActorJsonEntry entry, String locationString) {
        Long archipelagoId = ArchipelagoLocationMap.getId(locationString);
        Long itemId = scoutedLocations.get(archipelagoId);

        if (itemId == null) { // not in list of replaceable locations
            return entry;
        }

        String scoutedItem = ArchipelagoItemMap.getPickup(Math.toIntExact(itemId));

        if (entry.type.equals("SpawnerPickup")) {
            Gdx.app.log(this.getClass().getSimpleName(), "Replaced " + entry.pickupItem + " with " + scoutedItem);
            entry.pickupItem = scoutedItem;
        }
        else if (entry.type.startsWith("Pickup")) {
            Gdx.app.log(this.getClass().getSimpleName(), "Replaced " + entry.type + " with " + scoutedItem);
            entry.type = scoutedItem;
        }

        if (itemId == 1L) {
            entry.rubyType = "BLUE";
        }

        if (itemId == 15L) {
            entry.rubyType = "YELLOW";
        }

        return entry;
    }

    @ArchipelagoEventListener
    public void onReceiveItem(ReceiveItemEvent event) {
        Gdx.app.log(this.getClass().getSimpleName(), "Received an item!");

        saveManager.setSyncAP(true);
    }

    @ArchipelagoEventListener
    public void onLocationInfo(LocationInfoEvent event) {
        for (NetworkItem item : event.locations) {
            Gdx.app.log(
                this.getClass().getSimpleName(),
                "Scouted location"
                    + ", location=" + item.locationID
                    + ", item=" + item.itemID
                //+ ", itemName=" + ArchipelagoItemMap.getItem((int) item.itemID).toString()
            );

            scoutedLocations.put(item.locationID, item.itemID);
        }
    }

    @ArchipelagoEventListener
    public void onDeathLink(DeathLinkEvent event) {
        saveManager.setDeathInAP(true);
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
