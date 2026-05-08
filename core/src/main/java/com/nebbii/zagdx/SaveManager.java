package com.nebbii.zagdx;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class SaveManager {
    private static final String SAVE_FOLDER = "saves/";

    private final FileHandle saveFolder;
    private final Json json;

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

        SaveData saveData = new SaveData();
        saveData.name = fileName;

        file.writeString(json.prettyPrint(saveData), false);
    }

    public static class SaveData {
        public String name;
    }
}
