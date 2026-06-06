package com.nebbii.zagdx;

import com.badlogic.gdx.controllers.ControllerMapping;

public enum GamepadControl {
    BUTTON_A(0, ControlBindType.GAMEPAD_BUTTON, "A"),
    BUTTON_B(1, ControlBindType.GAMEPAD_BUTTON, "B"),
    BUTTON_X(2, ControlBindType.GAMEPAD_BUTTON, "X"),
    BUTTON_Y(3, ControlBindType.GAMEPAD_BUTTON, "Y"),
    BUTTON_BACK(4, ControlBindType.GAMEPAD_BUTTON, "Back"),
    BUTTON_START(6, ControlBindType.GAMEPAD_BUTTON, "Start"),
    BUTTON_L1(9, ControlBindType.GAMEPAD_BUTTON, "L1"),
    BUTTON_R1(10, ControlBindType.GAMEPAD_BUTTON, "R1"),
    BUTTON_LEFT_STICK(7, ControlBindType.GAMEPAD_BUTTON, "L Stick"),
    BUTTON_RIGHT_STICK(8, ControlBindType.GAMEPAD_BUTTON, "R Stick"),
    BUTTON_DPAD_UP(11, ControlBindType.GAMEPAD_BUTTON, "Dpad Up"),
    BUTTON_DPAD_DOWN(12, ControlBindType.GAMEPAD_BUTTON, "Dpad Down"),
    BUTTON_DPAD_LEFT(13, ControlBindType.GAMEPAD_BUTTON, "Dpad Left"),
    BUTTON_DPAD_RIGHT(14, ControlBindType.GAMEPAD_BUTTON, "Dpad Right"),
    AXIS_LEFT_X(0, ControlBindType.GAMEPAD_AXIS, "Left X"),
    AXIS_LEFT_Y(1, ControlBindType.GAMEPAD_AXIS, "Left Y"),
    AXIS_RIGHT_X(2, ControlBindType.GAMEPAD_AXIS, "Right X"),
    AXIS_RIGHT_Y(3, ControlBindType.GAMEPAD_AXIS, "Right Y");

    private final int fallbackCode;
    private final ControlBindType bindType;
    private final String label;

    GamepadControl(int fallbackCode, ControlBindType bindType, String label) {
        this.fallbackCode = fallbackCode;
        this.bindType = bindType;
        this.label = label;
    }

    public int getFallbackCode() {
        return fallbackCode;
    }

    public boolean isButton() {
        return bindType == ControlBindType.GAMEPAD_BUTTON;
    }

    public boolean isAxis() {
        return bindType == ControlBindType.GAMEPAD_AXIS;
    }

    public String getLabel() {
        return label;
    }

    public int resolve(ControllerMapping mapping) {
        if (mapping == null) {
            return fallbackCode;
        }

        switch (this) {
        case BUTTON_A:
            return mapping.buttonA;
        case BUTTON_B:
            return mapping.buttonB;
        case BUTTON_X:
            return mapping.buttonX;
        case BUTTON_Y:
            return mapping.buttonY;
        case BUTTON_BACK:
            return mapping.buttonBack;
        case BUTTON_START:
            return mapping.buttonStart;
        case BUTTON_L1:
            return mapping.buttonL1;
        case BUTTON_R1:
            return mapping.buttonR1;
        case BUTTON_LEFT_STICK:
            return mapping.buttonLeftStick;
        case BUTTON_RIGHT_STICK:
            return mapping.buttonRightStick;
        case BUTTON_DPAD_UP:
            return mapping.buttonDpadUp;
        case BUTTON_DPAD_DOWN:
            return mapping.buttonDpadDown;
        case BUTTON_DPAD_LEFT:
            return mapping.buttonDpadLeft;
        case BUTTON_DPAD_RIGHT:
            return mapping.buttonDpadRight;
        case AXIS_LEFT_X:
            return mapping.axisLeftX;
        case AXIS_LEFT_Y:
            return mapping.axisLeftY;
        case AXIS_RIGHT_X:
            return mapping.axisRightX;
        case AXIS_RIGHT_Y:
            return mapping.axisRightY;
        default:
            return fallbackCode;
        }
    }
}
