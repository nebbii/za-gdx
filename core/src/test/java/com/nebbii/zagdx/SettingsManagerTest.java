package com.nebbii.zagdx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class SettingsManagerTest {
    @TempDir
    Path tempDir;

    @Test
    public void missingSettingsFileCreatesDefaults() {
        FileHandle file = settingsFile();

        SettingsManager settings = new SettingsManager(file);

        assertTrue(file.exists());
        assertTrue(hasBind(settings.getBinds(ControlAction.MOVE_UP), ControlBindType.KEYBOARD, Keys.UP));
        assertTrue(hasStandardBind(settings.getBinds(ControlAction.MOVE_UP), ControlBindType.GAMEPAD_AXIS, GamepadControl.AXIS_LEFT_Y, -1));
        assertTrue(hasStandardBind(settings.getBinds(ControlAction.ACTION), ControlBindType.GAMEPAD_BUTTON, GamepadControl.BUTTON_A, 1));

        JsonValue root = parse(file);
        assertEquals(1, root.getInt("version"));
        assertNotNull(root.get("controls"));
        assertTrue(root.get("controls").isArray());
    }

    @Test
    public void loadsExistingControlsFromSettingsFile() {
        FileHandle file = settingsFile();
        file.writeString(
            "{\n" +
            "  \"version\": 1,\n" +
            "  \"futureSetting\": \"kept\",\n" +
            "  \"controls\": [\n" +
            "    { \"action\": \"ACTION\", \"type\": \"KEYBOARD\", \"code\": " + Keys.SPACE + " }\n" +
            "  ]\n" +
            "}",
            false
        );

        SettingsManager settings = new SettingsManager(file);
        List<ControlBind> actionBinds = settings.getBinds(ControlAction.ACTION);

        assertTrue(hasBind(actionBinds, ControlBindType.KEYBOARD, Keys.SPACE));
        assertFalse(hasBind(actionBinds, ControlBindType.KEYBOARD, Keys.SHIFT_LEFT));

        JsonValue root = parse(file);
        assertEquals("kept", root.getString("futureSetting"));
    }

    @Test
    public void savingChangedKeyboardBindWritesControlsArray() {
        FileHandle file = settingsFile();
        SettingsManager settings = new SettingsManager(file);

        settings.setKeyboardBind(ControlAction.MOVE_UP, Keys.W);
        settings.saveSettings();

        JsonValue moveUpKeyboard = findControl(parse(file), ControlAction.MOVE_UP, ControlBindType.KEYBOARD);

        assertNotNull(moveUpKeyboard);
        assertEquals(Keys.W, moveUpKeyboard.getInt("code"));
        assertFalse(hasControlCode(parse(file), ControlAction.MOVE_UP, ControlBindType.KEYBOARD, Keys.UP));
    }

    private FileHandle settingsFile() {
        return new FileHandle(tempDir.resolve("settings.json").toFile());
    }

    private JsonValue parse(FileHandle file) {
        return new JsonReader().parse(file);
    }

    private boolean hasBind(List<ControlBind> binds, ControlBindType type, int code) {
        for (ControlBind bind : binds) {
            if (bind.type == type && bind.code == code) {
                return true;
            }
        }

        return false;
    }

    private boolean hasStandardBind(List<ControlBind> binds, ControlBindType type, GamepadControl gamepadControl, int direction) {
        for (ControlBind bind : binds) {
            if (bind.type == type && bind.gamepadControl == gamepadControl && bind.getDirection() == direction) {
                return true;
            }
        }

        return false;
    }

    private JsonValue findControl(JsonValue root, ControlAction action, ControlBindType type) {
        JsonValue controls = root.get("controls");

        for (JsonValue bind = controls.child; bind != null; bind = bind.next) {
            if (action.name().equals(bind.getString("action")) && type.name().equals(bind.getString("type"))) {
                return bind;
            }
        }

        return null;
    }

    private boolean hasControlCode(JsonValue root, ControlAction action, ControlBindType type, int code) {
        JsonValue controls = root.get("controls");

        for (JsonValue bind = controls.child; bind != null; bind = bind.next) {
            if (action.name().equals(bind.getString("action")) &&
                type.name().equals(bind.getString("type")) &&
                bind.getInt("code") == code)
            {
                return true;
            }
        }

        return false;
    }
}
