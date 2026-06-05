package com.nebbii.zagdx;

import com.badlogic.gdx.controllers.ControllerMapping;

public enum GamepadControl {
    BUTTON_A(0),
    BUTTON_START(6),
    BUTTON_DPAD_UP(11),
    BUTTON_DPAD_DOWN(12),
    BUTTON_DPAD_LEFT(13),
    BUTTON_DPAD_RIGHT(14),
    AXIS_LEFT_X(0),
    AXIS_LEFT_Y(1);

    private final int fallbackCode;

    GamepadControl(int fallbackCode) {
        this.fallbackCode = fallbackCode;
    }

    public int getFallbackCode() {
        return fallbackCode;
    }

    public int resolve(ControllerMapping mapping) {
        if (mapping == null) {
            return fallbackCode;
        }

        switch (this) {
        case BUTTON_A:
            return mapping.buttonA;
        case BUTTON_START:
            return mapping.buttonStart;
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
        default:
            return fallbackCode;
        }
    }
}
