package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.GameManager.GameState;
import com.nebbii.zagdx.MenuPause.MenuState;

public class GameInput {
    private World world;
    private SettingsManager settingsManager;
    private boolean pauseWasPressed;

    public GameInput(World world, SettingsManager settingsManager) {
        this.world = world;
        this.settingsManager = settingsManager;
    }

    public void logic() {
        Zelda zelda = world.getMapManager().getZelda();

        handlePause();

        handleAction(zelda);
        handleMovement(zelda);
        handleDebug(zelda);
        handleTouchMenuPause(zelda);
    }

    public void handleMovement(Zelda zelda) {
        if (isActionPressed(ControlAction.MOVE_UP)) {
            zelda.move(0, zelda.getSpeed());
        }
        if (isActionPressed(ControlAction.MOVE_DOWN)) {
            zelda.move(0, -zelda.getSpeed());
        }
        if (isActionPressed(ControlAction.MOVE_LEFT)) {
            zelda.move(-zelda.getSpeed(), 0);
        }
        if (isActionPressed(ControlAction.MOVE_RIGHT)) {
            zelda.move(zelda.getSpeed(), 0);
        }
    }

    public void handleAction(Zelda zelda) {
        if (isActionPressed(ControlAction.ACTION)) {
            zelda.action();
        }
    }

    public void handleDebug(Zelda zelda) {
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            int next = (world.getMapManager().getCurrentLayerToggle().ordinal() + 1) % LayerToggle.values().length;
            world.getMapManager().setCurrentLayerToggle(LayerToggle.values()[next]);
        }
    }

    public void handlePause() {
        boolean pausePressed = isActionPressed(ControlAction.PAUSE);

        if (pausePressed && !pauseWasPressed) {
            GameManager game = world.getGameManager();

            if (game.getGameState() == GameState.PLAY ||
                game.getGameState() == GameState.PAUSE_ITEMS ||
                game.getGameState() == GameState.PAUSE_MAP)
            {
                world.getGameManager().togglePause();
            }
        }

        pauseWasPressed = pausePressed;
    }

    public void handleTouchMenuPause(Zelda zelda) {
        if (Gdx.input.justTouched() && world.getMenuPause().getMenuState() == MenuState.ACTIVE) {
            Vector3 lastTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            world.getInterfaceViewport().unproject(lastTouch);

            ArrayList<MenuButton> allSlots = new ArrayList<>();
            allSlots.addAll(world.getMenuPause().getTreasureTray());
            allSlots.addAll(world.getMenuPause().getWeaponTray());
            allSlots.addAll(world.getMenuPause().getEquipTray());

            for (MenuButton slot : allSlots) {
                if (slot.contains(lastTouch.x, lastTouch.y)) {
                    slot.onTouch();
                }
            }
        }
    }

    private boolean isActionPressed(ControlAction action) {
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

    private Controller getActiveController() {
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
