package com.nebbii.zagdx;

import java.util.ArrayList;

import com.nebbii.zagdx.MenuPause.MenuState;

public class GameManager {
    private World world;
    private int rubies;

    private ArrayList<Treasure> treasures;
    private ArrayList<Weapon> weapons;

    public enum GameState {
        PLAY,
        PAUSE_ITEMS,
        PAUSE_MAP,
        MOVE,
        FADE,
    }

    private GameState gameState;

    public GameManager(World world) {
        this.world = world;
        this.treasures = new ArrayList<Treasure>();
        this.weapons = new ArrayList<Weapon>();

        gameState = GameState.PLAY;

        rubies = 0;
    }

    public void logic() {
        switch(gameState) {
        case PLAY:
            if (world.getWorldCamera().isTransitioning()) {
                world.getMapManager().freezeAllActors();
                setGameState(GameState.MOVE);
            }
            break;
        case PAUSE_ITEMS:
        case PAUSE_MAP:
            break;
        case FADE:
            break;
        case MOVE:
            if (!world.getWorldCamera().isTransitioning()) {
                unpauseGame();
            }
            break;
        default:
            break;
        }
    }

    public void unpauseGame() {
        setGameState(GameState.PLAY);
        world.getMapManager().unfreezeVisibleActors();
    }

    public World getWorld() {
        return world;
    }

    public int getRubies() {
        return rubies;
    }

    public void setRubies(int rubies) {
        this.rubies = rubies;
    }

    public void increaseRubies(int count) {
        setRubies(getRubies() + count);
    }

    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }

    public void removeTreasure(Treasure treasure) {
        treasures.remove(treasure);
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void removeWeapon(Weapon weapon) {
        weapons.remove(weapon);
    }

    public Zelda getZelda() {
        return world.getMapManager().getZelda();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void togglePause() {
        if (world.getMenuPause().getMenuState() == MenuState.INACTIVE) {
            world.getMapManager().freezeAllActors();
            world.getMenuPause().setMenuState(MenuState.FADING_IN);
        }
        else if (world.getMenuPause().getMenuState() == MenuState.ACTIVE) {
            world.getMenuPause().setMenuState(MenuState.FADING_OUT);
        }
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }
}
