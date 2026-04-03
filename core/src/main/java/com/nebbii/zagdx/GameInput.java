package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.nebbii.zagdx.MenuPause.MenuState;

public class GameInput {
    private World world;
    private Vector3 lastTouch;

    public GameInput(World world) {
        this.world = world;
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
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            zelda.move(0, zelda.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            zelda.move(0, -zelda.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            zelda.move(-zelda.getSpeed(), 0);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            zelda.move(zelda.getSpeed(), 0);
        }
    }

    public void handleAction(Zelda zelda) {
        if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
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
        if (Gdx.input.isKeyJustPressed(Keys.P)) {
            world.getGameManager().togglePause();
        }
    }

    public void handleTouchMenuPause(Zelda zelda) {
        if (Gdx.input.justTouched() && world.getMenuPause().getMenuState() == MenuState.ACTIVE) {
            Vector3 lastTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            world.getInterfaceViewport().unproject(lastTouch);

            ArrayList<MenuPauseButton> allSlots = new ArrayList<>();
            allSlots.addAll(world.getMenuPause().getTreasureTray());
            allSlots.addAll(world.getMenuPause().getWeaponTray());
            allSlots.addAll(world.getMenuPause().getEquipTray());

            for (MenuPauseButton slot : allSlots) {
                if (slot.contains(lastTouch.x, lastTouch.y)) {
                    slot.onTouch();
                }
            }
        }
    }
}
