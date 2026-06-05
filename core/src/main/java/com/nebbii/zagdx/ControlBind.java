package com.nebbii.zagdx;

public class ControlBind {
    public ControlAction action;
    public ControlBindType type;
    public int code;
    public int direction;
    public float threshold;
    public GamepadControl gamepadControl;

    public ControlBind() {
    }

    private ControlBind(ControlAction action, ControlBindType type, int code, int direction, float threshold, GamepadControl gamepadControl) {
        this.action = action;
        this.type = type;
        this.code = code;
        this.direction = direction;
        this.threshold = threshold;
        this.gamepadControl = gamepadControl;
    }

    public static ControlBind keyboard(ControlAction action, int keyCode) {
        return new ControlBind(action, ControlBindType.KEYBOARD, keyCode, 0, 0f, null);
    }

    public static ControlBind mouse(ControlAction action, int buttonCode) {
        return new ControlBind(action, ControlBindType.MOUSE, buttonCode, 0, 0f, null);
    }

    public static ControlBind gamepadButton(ControlAction action, int buttonCode) {
        return new ControlBind(action, ControlBindType.GAMEPAD_BUTTON, buttonCode, 0, 0f, null);
    }

    public static ControlBind standardGamepadButton(ControlAction action, GamepadControl gamepadControl) {
        return new ControlBind(action, ControlBindType.GAMEPAD_BUTTON, gamepadControl.getFallbackCode(), 0, 0f, gamepadControl);
    }

    public static ControlBind gamepadAxis(ControlAction action, int axisCode, int direction) {
        return new ControlBind(action, ControlBindType.GAMEPAD_AXIS, axisCode, normalizeDirection(direction), 0.4f, null);
    }

    public static ControlBind standardGamepadAxis(ControlAction action, GamepadControl gamepadControl, int direction) {
        return new ControlBind(action, ControlBindType.GAMEPAD_AXIS, gamepadControl.getFallbackCode(), normalizeDirection(direction), 0.4f, gamepadControl);
    }

    public ControlBind copy() {
        return new ControlBind(action, type, code, direction, threshold, gamepadControl);
    }

    public int getDirection() {
        return normalizeDirection(direction);
    }

    public float getThreshold() {
        return threshold > 0f ? threshold : 0.4f;
    }

    private static int normalizeDirection(int direction) {
        return direction < 0 ? -1 : 1;
    }
}
