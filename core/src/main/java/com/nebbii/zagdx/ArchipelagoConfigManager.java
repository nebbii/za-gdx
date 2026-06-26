package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

public class ArchipelagoConfigManager {
    public static final String CONFIG_PATH = "archipelago/config.json";
    public static final String DEFAULT_CONFIG_PATH = "archipelago/config.example.json";

    private static final String DEFAULT_SERVER = "archipelago.gg:12345";
    private static final String DEFAULT_SLOT_NAME = "playername";

    private final FileHandle configFile;
    private final FileHandle defaultConfigFile;
    private final JsonReader jsonReader = new JsonReader();

    public ArchipelagoConfigManager() {
        this(Gdx.files.local(CONFIG_PATH), Gdx.files.internal(DEFAULT_CONFIG_PATH));
    }

    ArchipelagoConfigManager(FileHandle configFile, FileHandle defaultConfigFile) {
        this.configFile = configFile;
        this.defaultConfigFile = defaultConfigFile;
    }

    public boolean isEnabled() {
        return configFile.exists();
    }

    public ArchipelagoConfig enable() {
        if (!configFile.exists()) {
            writeRoot(defaultRoot());
        }

        return load();
    }

    public void disable() {
        if (configFile.exists()) {
            configFile.delete();
        }
    }

    public ArchipelagoConfig load() {
        JsonValue root = readRoot(configFile);

        if (root == null) {
            root = defaultRoot();
        }

        return configFromRoot(root);
    }

    public void save(ArchipelagoConfig config) {
        JsonValue root = readRoot(configFile);

        if (root == null) {
            root = new JsonValue(JsonValue.ValueType.object);
        }

        root.setChild("server", new JsonValue(nullToEmpty(config.server)));
        root.setChild("slotName", new JsonValue(nullToEmpty(config.slotName)));
        root.setChild("tags", tagsToJson(config.tags));

        writeRoot(root);
    }

    public boolean hasTag(ArchipelagoConfig config, String tag) {
        if (config.tags == null) {
            return false;
        }

        for (String existingTag : config.tags) {
            if (tag.equals(existingTag)) {
                return true;
            }
        }

        return false;
    }

    public void setTagEnabled(ArchipelagoConfig config, String tag, boolean enabled) {
        if (config.tags == null) {
            config.tags = new ArrayList<>();
        }

        for (int i = config.tags.size() - 1; i >= 0; i--) {
            if (tag.equals(config.tags.get(i))) {
                config.tags.remove(i);
            }
        }

        if (enabled) {
            config.tags.add(tag);
        }
    }

    private void writeRoot(JsonValue root) {
        FileHandle parent = configFile.parent();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        configFile.writeString(root.prettyPrint(JsonWriter.OutputType.json, 0), false);
    }

    private JsonValue readRoot(FileHandle file) {
        if (file == null || !file.exists() || file.length() == 0) {
            return null;
        }

        try {
            JsonValue root = jsonReader.parse(file);

            if (root == null || !root.isObject()) {
                return null;
            }

            return root;
        }
        catch (GdxRuntimeException e) {
            return null;
        }
    }

    private JsonValue defaultRoot() {
        JsonValue root = readRoot(defaultConfigFile);

        if (root != null) {
            return root;
        }

        root = new JsonValue(JsonValue.ValueType.object);
        root.addChild("server", new JsonValue(DEFAULT_SERVER));
        root.addChild("slotName", new JsonValue(DEFAULT_SLOT_NAME));
        root.addChild("tags", new JsonValue(JsonValue.ValueType.array));

        return root;
    }

    private ArchipelagoConfig configFromRoot(JsonValue root) {
        ArchipelagoConfig config = new ArchipelagoConfig();
        config.server = root.getString("server", "");
        config.slotName = root.getString("slotName", "");
        config.password = root.getString("password", null);
        config.tags = tagsFromJson(root.get("tags"));

        return config;
    }

    private ArrayList<String> tagsFromJson(JsonValue tagsJson) {
        ArrayList<String> tags = new ArrayList<>();

        if (tagsJson == null || !tagsJson.isArray()) {
            return tags;
        }

        for (JsonValue tagJson = tagsJson.child; tagJson != null; tagJson = tagJson.next) {
            if (tagJson.isString()) {
                tags.add(tagJson.asString());
            }
        }

        return tags;
    }

    private JsonValue tagsToJson(ArrayList<String> tags) {
        JsonValue tagsJson = new JsonValue(JsonValue.ValueType.array);

        for (String tag : normalizedTags(tags)) {
            tagsJson.addChild(new JsonValue(tag));
        }

        return tagsJson;
    }

    private ArrayList<String> normalizedTags(ArrayList<String> tags) {
        LinkedHashSet<String> uniqueTags = new LinkedHashSet<>();

        if (tags != null) {
            for (String tag : tags) {
                if (tag != null && tag.length() > 0) {
                    uniqueTags.add(tag);
                }
            }
        }

        return new ArrayList<>(uniqueTags);
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}
