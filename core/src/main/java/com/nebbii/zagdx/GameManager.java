package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.nebbii.zagdx.MenuPause.MenuState;

public class GameManager {
    private World world;
    private int rubies;

    private ArrayList<Treasure> treasures;
    private ArrayList<Weapon> weapons;

    private float gameoverFade;
    private float gameoverFadeCap;

    public enum GameState {
        PLAY,
        PAUSE_ITEMS,
        PAUSE_MAP,
        MOVE,
        FADE_IN,
        FADE_GAMEOVER,
        FADE_WARP
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

            if (world.getMapManager().getZelda().getHealth() <= 0) {
                initializeFadeGameover();
            }
            break;
        case PAUSE_ITEMS:
        case PAUSE_MAP:
            break;
        case FADE_GAMEOVER:
            if (handleFadeGameover()) {
                setGameState(GameState.PLAY);
            }
            break;
        case FADE_WARP:
            world.getMapManager().freezeAllActors();
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

    public void initializeFadeGameover() {
        world.getMapManager().freezeAllActors();
        setGameoverFade(0);
        setGameoverFadeCap(5);
        setGameState(GameState.FADE_GAMEOVER);
        world.getMapManager().getZelda().onDeath();
    }

    public boolean handleFadeGameover() {
        if (gameoverFade >= gameoverFadeCap) {
            return true;
        }

        gameoverFade += Gdx.graphics.getDeltaTime();

        return false;
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


    public float getGameoverFade() {
        return gameoverFade;
    }

    public void setGameoverFade(float gameoverFade) {
        this.gameoverFade = gameoverFade;
    }

    public float getGameoverFadeCap() {
        return gameoverFadeCap;
    }

    public void setGameoverFadeCap(float gameoverFadeCap) {
        this.gameoverFadeCap = gameoverFadeCap;
    }
}
