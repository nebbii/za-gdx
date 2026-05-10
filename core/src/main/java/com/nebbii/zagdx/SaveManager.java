package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class SaveManager {
    private static final String SAVE_FOLDER = "saves/";

    private World world;

    private final FileHandle saveFolder;
    private final Json json;

    private SaveData currentSave;
    private FileHandle currentSaveFile;

    public SaveManager() {
        saveFolder = Gdx.files.local(SAVE_FOLDER);

        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }

        json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
    }

    public SaveManager(World world) {
        saveFolder = Gdx.files.local(SAVE_FOLDER);

        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }

        json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);

        this.world = world;
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

    public ArrayList<String> listSaves() {
        FileHandle[] files = saveFolder.list();

        ArrayList<String> fileNames = new ArrayList<>();

        for(FileHandle file : files) {
            SaveData save = json.fromJson(SaveData.class, file);
            if (save.name != null) {
                fileNames.add(save.name);
            }
        }

        return fileNames;
    }

    public void loadSave(String fileName) {
        FileHandle file = saveFolder.child(fileName + ".json");

        if (!file.exists()) {
            throw new RuntimeException("Requested save doesn't exist: " + fileName);
        }

        currentSave = json.fromJson(SaveData.class, file);
        currentSaveFile = file;
    }

    public void writeCurrentSave() {
        if (currentSave == null || currentSaveFile == null) {
            throw new RuntimeException("No save is currently loaded");
        }

        currentSaveFile.writeString(json.prettyPrint(currentSave), false);
    }

    public void addTreasure(String treasure) {
        currentSave.treasures.add(treasure);
    }

    public void addWeapon(String weapon) {
        currentSave.weapons.add(weapon);
    }

    public boolean removeTreasure(String treasure) {
        return currentSave.treasures.remove(treasure);
    }

    public boolean removeWeapon(String weapon) {
        return currentSave.weapons.remove(weapon);
    }

    public ArrayList<String> getTreasures() {
        return currentSave.treasures;
    }

    public ArrayList<String> getWeapons() {
        return currentSave.weapons;
    }

    public ArrayList<SavedLocationEntry> getLocations() {
        return currentSave.locations;
    }

    public boolean hasLocationEntry(String location) {
        for (SavedLocationEntry entry : currentSave.locations) {
            if (entry.id.equals(location)) {
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
}
