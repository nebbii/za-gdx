package com.nebbii.zagdx;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerMapping;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class ControlBindingScreen extends MenuScreen {
    private static final float ACTION_LABEL_COLUMN = 24f;
    private static final float KEYBOARD_COLUMN = 126f;
    private static final float GAMEPAD_COLUMN = 248f;
    private static final float ROW_START_COLUMN = 164f;
    private static final float ROW_HEIGHT = 23f;
    private static final float CELL_WIDTH = 108f;
    private static final float CELL_HEIGHT = 18f;
    private static final float STICK_BIND_THRESHOLD = 0.4f;

    private enum CaptureMode {
        NONE,
        KEYBOARD,
        GAMEPAD
    }

    private BitmapFont font;
    private GlyphLayout layout;

    private CaptureMode captureMode = CaptureMode.NONE;
    private ControlAction captureAction;

    private Controller capturedController;
    private int previousButtonMin;
    private boolean[] previousButtons = new boolean[0];
    private float[] previousAxes = new float[0];

    public ControlBindingScreen(Core core) {
        super(core);
    }

    @Override
    public void show() {
        super.show();

        font = new BitmapFont();
        layout = new GlyphLayout();
        background = new Texture(Gdx.files.internal("blank-menu-screen-2.png"));

        setupBindingButtons();

        menuButtons.add(new MenuTextButton("Back", font, 18, 12, 70, 20, () -> {
            setFadeToggle(FadeToggle.OUT);
            core.setNextScreen(new SettingsMenuScreen(core));
        }));
    }

    @Override
    public void logic() {
        if (captureMode != CaptureMode.NONE) {
            handleCapture();
            return;
        }

        super.logic();
    }

    @Override
    public void draw() {
        super.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Controls", 157, 222);
        font.draw(batch, "Action", ACTION_LABEL_COLUMN, 199);
        font.draw(batch, "Keyboard", KEYBOARD_COLUMN + 18, 199);
        font.draw(batch, "Controller", GAMEPAD_COLUMN + 22, 199);

        for (ControlAction action : ControlAction.values()) {
            float y = rowY(action);
            font.draw(batch, actionLabel(action), ACTION_LABEL_COLUMN, y + 14);
        }

        drawStatus();
        batch.end();

        drawFade();
    }

    private void setupBindingButtons() {
        for (ControlAction action : ControlAction.values()) {
            float y = rowY(action);

            menuButtons.add(new MenuTextButton(
                () -> keyboardLabel(action),
                font,
                KEYBOARD_COLUMN,
                y,
                CELL_WIDTH,
                CELL_HEIGHT,
                () -> startKeyboardCapture(action)
            ));

            menuButtons.add(new MenuTextButton(
                () -> gamepadLabel(action),
                font,
                GAMEPAD_COLUMN,
                y,
                CELL_WIDTH,
                CELL_HEIGHT,
                () -> startGamepadCapture(action)
            ));
        }
    }

    private float rowY(ControlAction action) {
        return ROW_START_COLUMN - action.ordinal() * ROW_HEIGHT;
    }

    private void startKeyboardCapture(ControlAction action) {
        captureAction = action;
        captureMode = CaptureMode.KEYBOARD;
    }

    private void startGamepadCapture(ControlAction action) {
        captureAction = action;
        captureMode = CaptureMode.GAMEPAD;
        capturedController = core.getControlInput().getActiveController();
        snapshotGamepadState();
    }

    private void handleCapture() {
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            finishCapture();
            return;
        }

        if (captureMode == CaptureMode.KEYBOARD) {
            handleKeyboardCapture();
        }
        else if (captureMode == CaptureMode.GAMEPAD) {
            handleGamepadCapture();
        }
    }

    private void handleKeyboardCapture() {
        for (int keyCode = 1; keyCode <= Keys.MAX_KEYCODE; keyCode++) {
            if (keyCode == Keys.ESCAPE) {
                continue;
            }

            if (Gdx.input.isKeyJustPressed(keyCode)) {
                core.getSettingsManager().setKeyboardBind(captureAction, keyCode);
                core.getSettingsManager().saveSettings();
                finishCapture();
                return;
            }
        }
    }

    private void handleGamepadCapture() {
        if (capturedController == null || !capturedController.isConnected()) {
            capturedController = core.getControlInput().getActiveController();
            snapshotGamepadState();
            return;
        }

        for (int button = capturedController.getMinButtonIndex(); button <= capturedController.getMaxButtonIndex(); button++) {
            boolean down = safeGetButton(button);
            boolean wasDown = wasButtonDown(button);

            if (down && !wasDown) {
                bindGamepadButton(button);
                return;
            }

            setWasButtonDown(button, down);
        }

        for (int axis = 0; axis < capturedController.getAxisCount(); axis++) {
            float value = safeGetAxis(axis);
            float previousValue = previousAxisValue(axis);

            if (Math.abs(value) >= STICK_BIND_THRESHOLD && Math.abs(previousValue) < STICK_BIND_THRESHOLD) {
                bindGamepadAxis(axis, value < 0f ? -1 : 1);
                return;
            }

            setPreviousAxisValue(axis, value);
        }
    }

    private void bindGamepadButton(int buttonCode) {
        GamepadControl standardControl = findStandardGamepadControl(buttonCode, true);

        if (standardControl != null) {
            core.getSettingsManager().setGamepadButtonBind(captureAction, standardControl);
        }
        else {
            core.getSettingsManager().setGamepadButtonBind(captureAction, buttonCode);
        }

        core.getSettingsManager().saveSettings();
        finishCapture();
    }

    private void bindGamepadAxis(int axisCode, int direction) {
        GamepadControl standardControl = findStandardGamepadControl(axisCode, false);

        if (standardControl != null) {
            core.getSettingsManager().setGamepadAxisBind(captureAction, standardControl, direction);
        }
        else {
            core.getSettingsManager().setGamepadAxisBind(captureAction, axisCode, direction);
        }

        core.getSettingsManager().saveSettings();
        finishCapture();
    }

    private GamepadControl findStandardGamepadControl(int code, boolean button) {
        if (capturedController == null) {
            return null;
        }

        ControllerMapping mapping = capturedController.getMapping();

        for (GamepadControl control : GamepadControl.values()) {
            if (button != control.isButton()) {
                continue;
            }

            int resolvedCode = control.resolve(mapping);

            if (resolvedCode >= 0 && resolvedCode == code) {
                return control;
            }
        }

        return null;
    }

    private void snapshotGamepadState() {
        previousButtons = new boolean[0];
        previousAxes = new float[0];

        if (capturedController == null || !capturedController.isConnected()) {
            return;
        }

        previousButtonMin = capturedController.getMinButtonIndex();
        int buttonMax = capturedController.getMaxButtonIndex();

        if (buttonMax >= previousButtonMin) {
            previousButtons = new boolean[buttonMax - previousButtonMin + 1];

            for (int button = previousButtonMin; button <= buttonMax; button++) {
                setWasButtonDown(button, safeGetButton(button));
            }
        }

        previousAxes = new float[capturedController.getAxisCount()];

        for (int axis = 0; axis < previousAxes.length; axis++) {
            previousAxes[axis] = safeGetAxis(axis);
        }
    }

    private boolean safeGetButton(int buttonCode) {
        try {
            return capturedController.getButton(buttonCode);
        }
        catch (RuntimeException e) {
            return false;
        }
    }

    private float safeGetAxis(int axisCode) {
        try {
            return capturedController.getAxis(axisCode);
        }
        catch (RuntimeException e) {
            return 0f;
        }
    }

    private boolean wasButtonDown(int buttonCode) {
        int index = buttonCode - previousButtonMin;

        return index >= 0 && index < previousButtons.length && previousButtons[index];
    }

    private void setWasButtonDown(int buttonCode, boolean down) {
        int index = buttonCode - previousButtonMin;

        if (index >= 0 && index < previousButtons.length) {
            previousButtons[index] = down;
        }
    }

    private float previousAxisValue(int axisCode) {
        if (axisCode < 0 || axisCode >= previousAxes.length) {
            return 0f;
        }

        return previousAxes[axisCode];
    }

    private void setPreviousAxisValue(int axisCode, float value) {
        if (axisCode >= 0 && axisCode < previousAxes.length) {
            previousAxes[axisCode] = value;
        }
    }

    private void finishCapture() {
        captureMode = CaptureMode.NONE;
        captureAction = null;
        capturedController = null;
        previousButtons = new boolean[0];
        previousAxes = new float[0];
    }

    private String keyboardLabel(ControlAction action) {
        ControlBind bind = findBind(action, ControlBindType.KEYBOARD);

        if (bind == null) {
            return "Unbound";
        }

        String keyName = Keys.toString(bind.code);

        return keyName == null ? "Key " + bind.code : keyName.toUpperCase();
    }

    private String gamepadLabel(ControlAction action) {
        ControlBind bind = findGamepadBind(action);

        if (bind == null) {
            return "UNBOUND";
        }

        if (bind.gamepadControl != null) {
            if (bind.type == ControlBindType.GAMEPAD_AXIS) {
                return (bind.gamepadControl.getLabel() + axisSuffix(bind.getDirection())).toUpperCase();
            }

            return bind.gamepadControl.getLabel().toUpperCase();
        }

        if (bind.type == ControlBindType.GAMEPAD_AXIS) {
            return ("Axis " + bind.code + axisSuffix(bind.getDirection())).toUpperCase();
        }

        return ("Button " + bind.code).toUpperCase();
    }

    private ControlBind findBind(ControlAction action, ControlBindType type) {
        List<ControlBind> binds = core.getSettingsManager().getBinds(action);

        for (ControlBind bind : binds) {
            if (bind.type == type) {
                return bind;
            }
        }

        return null;
    }

    private ControlBind findGamepadBind(ControlAction action) {
        List<ControlBind> binds = core.getSettingsManager().getBinds(action);

        for (ControlBind bind : binds) {
            if (bind.type == ControlBindType.GAMEPAD_BUTTON || bind.type == ControlBindType.GAMEPAD_AXIS) {
                return bind;
            }
        }

        return null;
    }

    private String actionLabel(ControlAction action) {
        return action.name().replace('_', ' ');
    }

    private String axisSuffix(int direction) {
        return direction < 0 ? "-" : "+";
    }

    private void drawStatus() {
        String status = "";

        if (captureMode == CaptureMode.KEYBOARD) {
            status = actionLabel(captureAction) + ": Press key";
        }
        else if (captureMode == CaptureMode.GAMEPAD) {
            status = capturedController == null ? "Connect Gamepad" : actionLabel(captureAction) + ": Press Gamepad";
        }

        if (status.length() == 0) {
            return;
        }

        float originalScaleX = font.getData().scaleX;
        float originalScaleY = font.getData().scaleY;

        layout.setText(font, status);

        if (layout.width > 220f && layout.width > 0f) {
            float scale = 220f / layout.width;
            font.getData().setScale(originalScaleX * scale, originalScaleY * scale);
            layout.setText(font, status);
        }

        font.draw(batch, status, 99 + (220f - layout.width) / 2f, 34);
        font.getData().setScale(originalScaleX, originalScaleY);
    }
}
