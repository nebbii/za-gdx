package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.nebbii.zagdx.GameManager.GameState;

import io.github.archipelagomw.APResult;
import io.github.archipelagomw.ClientStatus;
import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.LocationInfoEvent;
import io.github.archipelagomw.events.ReceiveItemEvent;
import io.github.archipelagomw.events.DeathLinkEvent;
import io.github.archipelagomw.parts.NetworkItem;

public class ArchipelagoManager {
    private ArchipelagoClient archipelagoClient;
    private GameManager gameManager;
    private SaveManager saveManager;

    private Map<Long, NetworkItem> scoutedLocations;

    private boolean hasGoaled;

    public ArchipelagoManager(ArchipelagoClient client, GameManager gameManager, SaveManager saveManager) {
        this.archipelagoClient = client;
        this.gameManager = gameManager;
        this.saveManager = saveManager;

        this.archipelagoClient.getEventManager().registerListener(this);

        ArrayList<Long> locationIDs = new ArrayList<>(ArchipelagoItemMap.ID_TO_ITEM.keySet());

        if (this.archipelagoClient.isConnected()) {
            scoutedLocations = new HashMap<Long, NetworkItem>();
            Gdx.app.log(this.getClass().getSimpleName(), "Start location scout");

            archipelagoClient.scoutLocations(locationIDs);

            gameManager.storeCurrentGameState();
            gameManager.setGameState(GameState.AP_SYNC);
        }
    }

    public void logic() {
        if (saveManager.canSyncAP()) {
            Gdx.app.log(this.getClass().getSimpleName(), "Running sync");

            // send locations
            for (SavedLocationEntry locationEntry : saveManager.getLocations()) {
                long locationId = ArchipelagoLocationMap.getId(locationEntry.id);
                archipelagoClient.checkLocation(locationId);
                if (!locationEntry.action.endsWith("dead")) {
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

        if (hasGoaled) {
            APResult<Void> result = archipelagoClient.setGameState(ClientStatus.CLIENT_GOAL);

            if (result.getCode() == APResult.ResultCode.SUCCESS) {
                setHasGoaled(false);
            }
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
        else if (item.locationID == -1) {
            Gdx.app.log(
                this.getClass().getSimpleName(),
                "Location-less item"
                    + ", item=" + item.itemID
                    + ", location=" + item.locationID
                    + ", itemName=" + receivedItem.toString()
            );
            if (receivedItem instanceof Treasure) {
                if (!gameManager.hasItem(receivedItem)) {
                    gameManager.addTreasure((Treasure) receivedItem, true);
                }
            }
            else if (receivedItem instanceof Weapon) {
                if (!gameManager.hasItem(receivedItem)) {
                    gameManager.addWeapon((Weapon) receivedItem, true);
                }
            }
        }
    }

    public ActorJsonEntry overrideJsonEntry(ActorJsonEntry entry, String locationString) {
        Long archipelagoId = ArchipelagoLocationMap.getId(locationString);
        NetworkItem item = scoutedLocations.get(archipelagoId);
        String scoutedItem = "";
        if (item == null) { // Item not found in AP
            Gdx.app.log(
                this.getClass().getSimpleName(),
                "Item not found in AP"
                    + ", location=" + archipelagoId
            );
            return entry;
        }
        else if (item.playerID != archipelagoClient.getSlot()) { // external item
            Gdx.app.log(
                this.getClass().getSimpleName(),
                "Item from a different world!"
                    + ", location=" + archipelagoId
                    + ", player=" + item.playerID
            );
            scoutedItem = "PickupArchipelago";
        }
        else {
            Gdx.app.log(
                this.getClass().getSimpleName(),
                "Item for us!"
                    + ", location=" + archipelagoId
                    + ", itemid=" + item.itemID
                    + ", item=" + item.itemName
            );
            scoutedItem = ArchipelagoItemMap.getPickup(Math.toIntExact(item.itemID));

            // TODO: just make these separate items lol
            if (item.itemID == 1L) {
                entry.rubyType = "BLUE";
            }

            if (item.itemID == 15L) {
                entry.rubyType = "YELLOW";
            }
        }

        if (entry.type.equals("SpawnerPickup")) {
            entry.pickupItem = scoutedItem;
        }
        else if (entry.type.startsWith("Pickup")) {
            entry.type = scoutedItem;
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
            /*
            Gdx.app.log(
                this.getClass().getSimpleName(),
                "Scouted location"
                    + ", location=" + item.locationID
                    + ", item=" + item.itemID
            );
            */

            scoutedLocations.put(item.locationID, item);
        }

        gameManager.restorePriorGameState();
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

    public boolean isHasGoaled() {
        return hasGoaled;
    }

    public void setHasGoaled(boolean hasGoaled) {
        this.hasGoaled = hasGoaled;
    }
}
