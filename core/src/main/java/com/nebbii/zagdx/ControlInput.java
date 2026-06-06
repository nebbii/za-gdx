package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Array;

public class ControlInput {
    private final SettingsManager settingsManager;

    public ControlInput(SettingsManager settingsManager) {
        this.settingsManager = settingsManager;
    }

    public void initialize() {
        getActiveController();
    }

    public boolean isActionPressed(ControlAction action) {
        Controller controller = getActiveController();

        for (ControlBind bind : settingsManager.getControlBinds()) {
            if (bind.action != action) {
                continue;
            }

            if (isBindPressed(bind, controller)) {
                return true;
            }
        }

        return false;
    }

    private boolean isBindPressed(ControlBind bind, Controller controller) {
        switch (bind.type) {
        case KEYBOARD:
            return Gdx.input.isKeyPressed(bind.code);
        case MOUSE:
            return Gdx.input.isButtonPressed(bind.code);
        case GAMEPAD_BUTTON:
            return isGamepadButtonPressed(bind, controller);
        case GAMEPAD_AXIS:
            return isGamepadAxisPressed(bind, controller);
        default:
            return false;
        }
    }

    private boolean isGamepadButtonPressed(ControlBind bind, Controller controller) {
        if (controller == null) {
            return false;
        }

        int code = resolveGamepadCode(bind, controller);

        return code >= 0 && controller.getButton(code);
    }

    private boolean isGamepadAxisPressed(ControlBind bind, Controller controller) {
        if (controller == null) {
            return false;
        }

        int code = resolveGamepadCode(bind, controller);

        if (code < 0) {
            return false;
        }

        float value = controller.getAxis(code);

        if (bind.getDirection() < 0) {
            return value <= -bind.getThreshold();
        }

        return value >= bind.getThreshold();
    }

    private int resolveGamepadCode(ControlBind bind, Controller controller) {
        if (bind.gamepadControl == null) {
            return bind.code;
        }

        return bind.gamepadControl.resolve(controller.getMapping());
    }

    public Controller getActiveController() {
        try {
            Controller current = Controllers.getCurrent();

            if (current != null && current.isConnected()) {
                return current;
            }

            Array<Controller> controllers = Controllers.getControllers();

            if (controllers.size > 0) {
                return controllers.first();
            }
        }
        catch (RuntimeException e) {
            return null;
        }

        return null;
    }
}
