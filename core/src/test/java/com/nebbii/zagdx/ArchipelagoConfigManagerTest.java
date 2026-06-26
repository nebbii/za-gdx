package com.nebbii.zagdx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class ArchipelagoConfigManagerTest {
    @TempDir
    Path tempDir;

    private FileHandle configFile;
    private FileHandle defaultConfigFile;
    private ArchipelagoConfigManager manager;

    @BeforeEach
    public void setUp() {
        configFile = new FileHandle(tempDir.resolve("archipelago/config.json").toFile());
        defaultConfigFile = new FileHandle(tempDir.resolve("config.example.json").toFile());
        defaultConfigFile.writeString(
            "{\n" +
            "  \"server\": \"archipelago.gg:12345\",\n" +
            "  \"slotName\": \"playername\",\n" +
            "  \"tags\": [\"DeathLink\"]\n" +
            "}",
            false
        );
        manager = new ArchipelagoConfigManager(configFile, defaultConfigFile);
    }

    @Test
    public void missingConfigReportsDisabled() {
        assertFalse(manager.isEnabled());
    }

    @Test
    public void enablingCreatesLocalConfigFromExampleDefaults() {
        ArchipelagoConfig config = manager.enable();

        assertTrue(manager.isEnabled());
        assertTrue(configFile.exists());
        assertEquals("archipelago.gg:12345", config.server);
        assertEquals("playername", config.slotName);
        assertTrue(manager.hasTag(config, "DeathLink"));

        JsonValue root = parseConfig();
        assertEquals("archipelago.gg:12345", root.getString("server"));
        assertEquals("playername", root.getString("slotName"));
        assertTrue(root.get("tags").isArray());
        assertTrue(hasTag(root, "DeathLink"));
    }

    @Test
    public void savingWritesServerSlotNameAndTags() {
        ArchipelagoConfig config = manager.enable();
        config.server = "localhost:38281";
        config.slotName = "Zelda";
        config.tags = new ArrayList<>();
        config.tags.add("DeathLink");

        manager.save(config);

        JsonValue root = parseConfig();
        assertEquals("localhost:38281", root.getString("server"));
        assertEquals("Zelda", root.getString("slotName"));
        assertTrue(root.get("tags").isArray());
        assertTrue(hasTag(root, "DeathLink"));
    }

    @Test
    public void togglingDeathLinkAddsAndRemovesOnlyDeathLink() {
        writeExistingConfig(
            "{\n" +
            "  \"server\": \"localhost:38281\",\n" +
            "  \"slotName\": \"Zelda\",\n" +
            "  \"tags\": [\"DeathLink\", \"OtherTag\"]\n" +
            "}"
        );

        ArchipelagoConfig config = manager.load();
        manager.setTagEnabled(config, "DeathLink", false);
        manager.save(config);

        JsonValue disabledRoot = parseConfig();
        assertFalse(hasTag(disabledRoot, "DeathLink"));
        assertTrue(hasTag(disabledRoot, "OtherTag"));

        config = manager.load();
        manager.setTagEnabled(config, "DeathLink", true);
        manager.save(config);

        JsonValue enabledRoot = parseConfig();
        assertTrue(hasTag(enabledRoot, "DeathLink"));
        assertTrue(hasTag(enabledRoot, "OtherTag"));
        assertEquals(1, tagCount(enabledRoot, "DeathLink"));
    }

    @Test
    public void savingPreservesExistingPassword() {
        writeExistingConfig(
            "{\n" +
            "  \"server\": \"old.server:12345\",\n" +
            "  \"slotName\": \"OldSlot\",\n" +
            "  \"password\": \"secret\",\n" +
            "  \"tags\": []\n" +
            "}"
        );

        ArchipelagoConfig config = manager.load();
        config.server = "new.server:12345";
        config.slotName = "NewSlot";
        manager.setTagEnabled(config, "DeathLink", true);
        manager.save(config);

        JsonValue root = parseConfig();
        assertEquals("new.server:12345", root.getString("server"));
        assertEquals("NewSlot", root.getString("slotName"));
        assertEquals("secret", root.getString("password"));
        assertTrue(hasTag(root, "DeathLink"));
    }

    @Test
    public void disablingDeletesLocalConfig() {
        manager.enable();

        assertTrue(configFile.exists());

        manager.disable();

        assertFalse(configFile.exists());
        assertFalse(manager.isEnabled());
    }

    private void writeExistingConfig(String json) {
        FileHandle parent = configFile.parent();

        if (!parent.exists()) {
            parent.mkdirs();
        }

        configFile.writeString(json, false);
    }

    private JsonValue parseConfig() {
        return new JsonReader().parse(configFile);
    }

    private boolean hasTag(JsonValue root, String tag) {
        return tagCount(root, tag) > 0;
    }

    private int tagCount(JsonValue root, String tag) {
        JsonValue tags = root.get("tags");
        int count = 0;

        assertNotNull(tags);

        for (JsonValue tagJson = tags.child; tagJson != null; tagJson = tagJson.next) {
            if (tag.equals(tagJson.asString())) {
                count++;
            }
        }

        return count;
    }
}
