package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class SaveManager {
    private static final String SAVE_FOLDER = "saves/";

    private final FileHandle saveFolder;
    private final Json json;
    private final Map<String, MapData> mapDataCache = new HashMap<>();

    private SaveData currentSave;
    private FileHandle currentSaveFile;

    private boolean syncAP = false;
    private boolean deathInAP = false;
    private boolean deathOutAP = false;

    public SaveManager() {
        saveFolder = Gdx.files.local(SAVE_FOLDER);

        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }

        json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
    }

    public void createSave(String fileName) {
        FileHandle file = saveFolder.child(fileName + ".json");

        int i = 0;
        while (file.exists()) {
            i++;
            file = saveFolder.child(fileName + "_" + i + ".json");
        }

        currentSave = new SaveData();
        currentSave.name = fileName;

        currentSaveFile = file;

        writeCurrentSave();
    }

    public ArrayList<SaveData> getSaves() {
        FileHandle[] files = saveFolder.list();

        ArrayList<SaveData> saves = new ArrayList<>();

        for(FileHandle file : files) {
            SaveData save = json.fromJson(SaveData.class, file);

            save.filename = file.name();
            saves.add(save);
        }

        return saves;
    }

    public void loadSave(String fileName) {
        FileHandle file = saveFolder.child(fileName);

        if (!file.exists()) {
            throw new RuntimeException("Requested save doesn't exist: " + file.name());
        }

        currentSave = json.fromJson(SaveData.class, file);
        currentSaveFile = file;
    }

    // TODO: this needs a confirm prompt lmao
    public boolean deleteSave(String fileName) {
        FileHandle file = saveFolder.child(fileName);

        return file.delete();
    }

    // update save file with the current info
    public void writeCurrentSave() {
        if (currentSave == null || currentSaveFile == null) {
            throw new RuntimeException("No save is currently loaded");
        }

        setSyncAP(true);

        currentSaveFile.writeString(json.prettyPrint(currentSave), false);
    }

    // write the current game state to the save
    public void writeCurrentSave(GameManager gameManager) {
        if (currentSave == null || currentSaveFile == null) {
            throw new RuntimeException("No save is currently loaded");
        }

        syncInventoryFromGameManager(gameManager);

        setSyncAP(true);

        currentSaveFile.writeString(json.prettyPrint(currentSave), false);
    }

    private void syncInventoryFromGameManager(GameManager gameManager) {
        if (gameManager == null) {
            return;
        }

        currentSave.rubies = gameManager.getRubies();
        currentSave.equippedItem = gameManager.getZelda().getCurrentItem();

        if (gameManager.getTreasures() != null) {
            currentSave.treasures = new ArrayList<>(gameManager.getTreasures());
            currentSave.treasures.removeIf(treasure -> treasure == Treasure.RUBIES);
        }

        if (gameManager.getWeapons() != null) {
            currentSave.weapons = new ArrayList<>(gameManager.getWeapons());
        }
    }

    public Item getEquippedItem() {
        return currentSave.equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.currentSave.equippedItem = equippedItem;
    }

    public int getRubies() {
        return currentSave.rubies;
    }

    public void setRubies(int rubies) {
        currentSave.rubies = rubies;
    }

    public void increaseRubies(int count) {
        setRubies(getRubies() + count);
    }

    public void decreaseRubies(int count) {
        setRubies(getRubies() - count);
    }

    public void addTreasure(Treasure treasure) {
        currentSave.treasures.add(treasure);
    }

    public void addWeapon(Weapon weapon) {
        currentSave.weapons.add(weapon);
    }

    public boolean removeTreasure(Treasure treasure) {
        return currentSave.treasures.remove(treasure);
    }

    public boolean removeWeapon(Weapon weapon) {
        return currentSave.weapons.remove(weapon);
    }

    public ArrayList<Treasure> getTreasures() {
        return currentSave.treasures;
    }

    public ArrayList<Weapon> getWeapons() {
        return currentSave.weapons;
    }

    public ArrayList<SavedLocationEntry> getLocations() {
        return currentSave.locations;
    }

    public boolean hasArchipelagoCheck(long id) {
        ensureArchipelagoChecks();

        return currentSave.archipelagoChecks.contains(id);
    }

    public void addArchipelagoCheck(long id) {
        ensureArchipelagoChecks();

        if (hasArchipelagoCheck(id)) {
            return;
        }

        currentSave.archipelagoChecks.add(id);
        writeCurrentSave();
    }

    public SavedLocationEntry getLocationEntryById(String id) {
        for (SavedLocationEntry entry : currentSave.locations) {
            if (entry.id.equals(id)) {
                return entry;
            }
        }

        return null;
    }

    public boolean hasLocationEntry(String id) {
        for (SavedLocationEntry entry : currentSave.locations) {
            if (entry.id.equals(id)) {
                return true;
            }
        }

        return false;
    }

    public void addLocationEntry(String location, String action) {
        if (hasLocationEntry(location)) {
            return;
        }

        SavedLocationEntry entry = new SavedLocationEntry();
        entry.id = location;
        entry.action = action;

        currentSave.locations.add(entry);
        writeCurrentSave();
    }

    public boolean hasLocationForClass(String mapName, String className, String action) {
        if (currentSave == null || currentSave.locations == null) {
            return false;
        }

        for (SavedLocationEntry savedEntry : currentSave.locations) {
            if (action != null && !action.equals(savedEntry.action)) {
                continue;
            }

            String savedClassName = getClassNameByLocationEntry(mapName, savedEntry.id);

            if (className.equals(savedClassName)) {
                return true;
            }
        }

        return false;
    }

    private MapData getCachedMapData(String mapName) {
        MapData data = mapDataCache.get(mapName);

        if (data == null) {
            data = JsonLoader.load("gamedata/" + mapName + ".json", MapData.class);
            mapDataCache.put(mapName, data);
        }

        return data;
    }

    public void clearMapDataCache() {
        mapDataCache.clear();
    }

    private void ensureArchipelagoChecks() {
        if (currentSave.archipelagoChecks == null) {
            currentSave.archipelagoChecks = new ArrayList<>();
        }
    }

    public String getClassNameByLocationEntry(String mapName, String locationEntryId) {
        if (mapName == null || locationEntryId == null) {
            throw new IllegalArgumentException("Blank argument(s): " + mapName + ", " + locationEntryId);
        }

        int underscoreIndex = locationEntryId.lastIndexOf("_");

        if (underscoreIndex == -1 || underscoreIndex == locationEntryId.length() - 1) {
            return null;
        }

        String locationId = locationEntryId.substring(0, underscoreIndex);
        String actorIndexText = locationEntryId.substring(underscoreIndex + 1);

        int actorIndex;

        try {
            actorIndex = Integer.parseInt(actorIndexText);
        }
        catch (NumberFormatException e) {
            return null;
        }

        MapData data = getCachedMapData(mapName);

        if (data == null || data.locations == null) {
            throw new IllegalStateException(
                "Map to save discrepancy error: Couldn't find map and/or locations"
            );
        }

        for (LocationJsonEntry location : data.locations) {
            if (!locationId.equals(location.location)) {
                continue;
            }

            if (location.actors == null) {
                throw new IllegalStateException(
                    "Map to save discrepancy error: Save entry points to location with no actors (mapName=" + mapName + ", locationEntryId=" + locationEntryId + ")"
                );
            }

            int currentIndex = 0;

            for (ActorJsonEntry actorEntry : location.actors) {
                if (actorEntry.type == null) {
                    continue; // _comment entry
                }

                if (currentIndex == actorIndex) {
                    return actorEntry.type;
                }

                currentIndex++;
            }
            return null;
        }

        return null;
    }

    public SaveData getCurrentSave() {
        return currentSave;
    }

    public void setCurrentSave(SaveData currentSave) {
        this.currentSave = currentSave;
    }

    public boolean canDeathInAP() {
        return deathInAP;
    }

    public void setDeathInAP(boolean deathInAP) {
        this.deathInAP = deathInAP;
    }

    public boolean canDeathOutAP() {
        return deathOutAP;
    }

    public void setDeathOutAP(boolean deathOutAP) {
        this.deathOutAP = deathOutAP;
    }

    public boolean canSyncAP() {
        return syncAP;
    }

    public void setSyncAP(boolean syncAP) {
        this.syncAP = syncAP;
    }
}
