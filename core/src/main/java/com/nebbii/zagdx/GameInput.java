package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.nebbii.zagdx.GameManager.GameState;
import com.nebbii.zagdx.MenuPause.MenuState;

public class GameInput {
    private World world;
    private ControlInput controlInput;
    private boolean pauseWasPressed;

    public GameInput(World world, ControlInput controlInput) {
        this.world = world;
        this.controlInput = controlInput;
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
        float inputX = 0f;
        float inputY = 0f;

        if (controlInput.isActionPressed(ControlAction.MOVE_UP)) {
            inputY += zelda.getSpeed();
        }

        if (controlInput.isActionPressed(ControlAction.MOVE_DOWN)) {
            inputY -= zelda.getSpeed();
        }

        if (controlInput.isActionPressed(ControlAction.MOVE_LEFT)) {
            inputX -= zelda.getSpeed();
        }

        if (controlInput.isActionPressed(ControlAction.MOVE_RIGHT)) {
            inputX += zelda.getSpeed();
        }

        zelda.move(inputX, inputY);
    }

    public void handleAction(Zelda zelda) {
        if (controlInput.isActionPressed(ControlAction.ACTION)) {
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
        boolean pausePressed = controlInput.isActionPressed(ControlAction.PAUSE);

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
}
