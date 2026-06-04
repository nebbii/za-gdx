package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

public class SettingsManager {
    private static final String SETTINGS_FILE = "settings.json";
    private static final int SETTINGS_VERSION = 1;

    private final FileHandle settingsFile;
    private final JsonReader jsonReader = new JsonReader();
    private final ArrayList<ControlBind> controls = new ArrayList<>();

    private JsonValue settingsRoot;

    public SettingsManager() {
        this(Gdx.files.local(SETTINGS_FILE));
    }

    public SettingsManager(FileHandle settingsFile) {
        this.settingsFile = settingsFile;
        loadSettings();
    }

    public List<ControlBind> getBinds(ControlAction action) {
        ArrayList<ControlBind> binds = new ArrayList<>();

        for (ControlBind bind : controls) {
            if (bind.action == action) {
                binds.add(bind.copy());
            }
        }

        return binds;
    }

    List<ControlBind> getControlBinds() {
        return controls;
    }

    public void setKeyboardBind(ControlAction action, int keyCode) {
        replaceBinds(action, ControlBindType.KEYBOARD, ControlBind.keyboard(action, keyCode));
    }

    public void setMouseBind(ControlAction action, int buttonCode) {
        replaceBinds(action, ControlBindType.MOUSE, ControlBind.mouse(action, buttonCode));
    }

    public void setGamepadButtonBind(ControlAction action, int buttonCode) {
        replaceBinds(action, ControlBindType.GAMEPAD_BUTTON, ControlBind.gamepadButton(action, buttonCode));
    }

    public void setGamepadAxisBind(ControlAction action, int axisCode, int direction) {
        replaceBinds(action, ControlBindType.GAMEPAD_AXIS, ControlBind.gamepadAxis(action, axisCode, direction));
    }

    public void resetDefaultBinds() {
        controls.clear();
        controls.addAll(createDefaultBinds());
    }

    public void saveSettings() {
        ensureRoot();

        if (!settingsRoot.has("version")) {
            settingsRoot.addChild("version", new JsonValue((long) SETTINGS_VERSION));
        }

        settingsRoot.setChild("controls", controlsToJson());
        settingsFile.writeString(settingsRoot.prettyPrint(JsonWriter.OutputType.json, 0), false);
    }

    private void loadSettings() {
        settingsRoot = readSettingsRoot();
        loadControls();

        if (controls.isEmpty()) {
            resetDefaultBinds();
        }

        ensureAllActionsBound();
        saveSettings();
    }

    private JsonValue readSettingsRoot() {
        if (!settingsFile.exists() || settingsFile.length() == 0) {
            return new JsonValue(JsonValue.ValueType.object);
        }

        try {
            JsonValue root = jsonReader.parse(settingsFile);

            if (root == null || !root.isObject()) {
                return new JsonValue(JsonValue.ValueType.object);
            }

            return root;
        }
        catch (GdxRuntimeException e) {
            return new JsonValue(JsonValue.ValueType.object);
        }
    }

    private void loadControls() {
        controls.clear();

        JsonValue controlsJson = settingsRoot.get("controls");

        if (controlsJson == null || !controlsJson.isArray()) {
            return;
        }

        for (JsonValue bindJson = controlsJson.child; bindJson != null; bindJson = bindJson.next) {
            ControlBind bind = bindFromJson(bindJson);

            if (bind != null) {
                controls.add(bind);
            }
        }
    }

    private ControlBind bindFromJson(JsonValue bindJson) {
        if (bindJson == null || !bindJson.isObject()) {
            return null;
        }

        ControlAction action = parseEnum(ControlAction.class, bindJson.getString("action", null));
        ControlBindType type = parseEnum(ControlBindType.class, bindJson.getString("type", null));

        if (action == null || type == null) {
            return null;
        }

        ControlBind bind = new ControlBind();
        bind.action = action;
        bind.type = type;
        bind.code = bindJson.getInt("code", 0);
        bind.direction = bindJson.getInt("direction", 1);
        bind.threshold = bindJson.getFloat("threshold", 0.4f);
        bind.gamepadControl = parseEnum(GamepadControl.class, bindJson.getString("gamepadControl", null));

        return bind;
    }

    private <T extends Enum<T>> T parseEnum(Class<T> enumType, String name) {
        if (name == null) {
            return null;
        }

        try {
            return Enum.valueOf(enumType, name);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    private void ensureAllActionsBound() {
        for (ControlAction action : ControlAction.values()) {
            if (!hasBindForAction(action)) {
                addDefaultBindsForAction(action);
            }
        }
    }

    private boolean hasBindForAction(ControlAction action) {
        for (ControlBind bind : controls) {
            if (bind.action == action) {
                return true;
            }
        }

        return false;
    }

    private void addDefaultBindsForAction(ControlAction action) {
        for (ControlBind bind : createDefaultBinds()) {
            if (bind.action == action) {
                controls.add(bind);
            }
        }
    }

    private void replaceBinds(ControlAction action, ControlBindType type, ControlBind newBind) {
        for (int i = controls.size() - 1; i >= 0; i--) {
            ControlBind bind = controls.get(i);

            if (bind.action == action && bind.type == type) {
                controls.remove(i);
            }
        }

        controls.add(newBind);
    }

    private ArrayList<ControlBind> createDefaultBinds() {
        ArrayList<ControlBind> defaults = new ArrayList<>();

        defaults.add(ControlBind.keyboard(ControlAction.MOVE_UP, Keys.UP));
        defaults.add(ControlBind.keyboard(ControlAction.MOVE_DOWN, Keys.DOWN));
        defaults.add(ControlBind.keyboard(ControlAction.MOVE_LEFT, Keys.LEFT));
        defaults.add(ControlBind.keyboard(ControlAction.MOVE_RIGHT, Keys.RIGHT));
        defaults.add(ControlBind.keyboard(ControlAction.ACTION, Keys.SHIFT_LEFT));
        defaults.add(ControlBind.keyboard(ControlAction.PAUSE, Keys.P));

        defaults.add(ControlBind.standardGamepadAxis(ControlAction.MOVE_UP, GamepadControl.AXIS_LEFT_Y, -1));
        defaults.add(ControlBind.standardGamepadAxis(ControlAction.MOVE_DOWN, GamepadControl.AXIS_LEFT_Y, 1));
        defaults.add(ControlBind.standardGamepadAxis(ControlAction.MOVE_LEFT, GamepadControl.AXIS_LEFT_X, -1));
        defaults.add(ControlBind.standardGamepadAxis(ControlAction.MOVE_RIGHT, GamepadControl.AXIS_LEFT_X, 1));

        defaults.add(ControlBind.standardGamepadButton(ControlAction.MOVE_UP, GamepadControl.BUTTON_DPAD_UP));
        defaults.add(ControlBind.standardGamepadButton(ControlAction.MOVE_DOWN, GamepadControl.BUTTON_DPAD_DOWN));
        defaults.add(ControlBind.standardGamepadButton(ControlAction.MOVE_LEFT, GamepadControl.BUTTON_DPAD_LEFT));
        defaults.add(ControlBind.standardGamepadButton(ControlAction.MOVE_RIGHT, GamepadControl.BUTTON_DPAD_RIGHT));
        defaults.add(ControlBind.standardGamepadButton(ControlAction.ACTION, GamepadControl.BUTTON_A));
        defaults.add(ControlBind.standardGamepadButton(ControlAction.PAUSE, GamepadControl.BUTTON_START));

        return defaults;
    }

    private JsonValue controlsToJson() {
        JsonValue controlsJson = new JsonValue(JsonValue.ValueType.array);

        for (ControlBind bind : controls) {
            JsonValue bindJson = new JsonValue(JsonValue.ValueType.object);
            bindJson.addChild("action", new JsonValue(bind.action.name()));
            bindJson.addChild("type", new JsonValue(bind.type.name()));
            bindJson.addChild("code", new JsonValue((long) bind.code));

            if (bind.type == ControlBindType.GAMEPAD_AXIS) {
                bindJson.addChild("direction", new JsonValue((long) bind.getDirection()));
                bindJson.addChild("threshold", new JsonValue((double) bind.getThreshold()));
            }

            if (bind.gamepadControl != null) {
                bindJson.addChild("gamepadControl", new JsonValue(bind.gamepadControl.name()));
            }

            controlsJson.addChild(bindJson);
        }

        return controlsJson;
    }

    private void ensureRoot() {
        if (settingsRoot == null || !settingsRoot.isObject()) {
            settingsRoot = new JsonValue(JsonValue.ValueType.object);
        }
    }
}
