package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.nebbii.zagdx.MenuPause.MenuState;

public class GameManager {
    private World world;
    private int rubies;

    private ArrayList<Treasure> treasures;
    private ArrayList<Weapon> weapons;

    private float fade;
    private float fadeCap;

    public enum GameState {
        PLAY,
        PAUSE_ITEMS,
        PAUSE_MAP,
        MOVE,
        FADE_IN,
        FADE_GAMEOVER,
        FADE_WARP
    }

    public enum FadeToggle {
        IN,
        OUT
    }

    private GameState gameState;
    private FadeToggle fadeToggle;

    public GameManager(World world) {
        this.world = world;
        this.treasures = new ArrayList<Treasure>();
        this.weapons = new ArrayList<Weapon>();

        gameState = GameState.PLAY;
        fadeToggle = FadeToggle.OUT;

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
                setFadeToggle(FadeToggle.OUT);
                initializeFadeGameover();
            }
            break;
        case PAUSE_ITEMS:
        case PAUSE_MAP:
            break;
        case FADE_IN:
            if (handleFade()) {
                unpauseGame();
            }
            break;
        case FADE_GAMEOVER:
            if (handleFade()) {
                respawn();
                initializeFadeIn();
                setFadeToggle(FadeToggle.IN);
                setGameState(GameState.FADE_IN);
            }
            break;
        case FADE_WARP:
            if (handleFade()) {
                respawn();
                initializeFadeIn();
                setFadeToggle(FadeToggle.IN);
                setGameState(GameState.FADE_IN);
            }
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
        setFade(0f);
        setFadeCap(5f);
        setGameState(GameState.FADE_GAMEOVER);
        world.getMapManager().getZelda().onDeath();
    }

    public void initializeFadeIn() {
        setFade(0f);
        setFadeCap(0.5f);
    }

    public boolean handleFade() {
        if (fade >= fadeCap) {
            return true;
        }

        fade += Gdx.graphics.getDeltaTime();

        return false;
    }

    public void respawn() {
        Zelda zelda = world.getMapManager().getZelda();

        world.getMapManager().loadOverworld();

        zelda.setHealth(zelda.getMaxHealth());
        zelda.setAnimState(AnimState.STOPDOWN);
        zelda.setPosition(zelda.getSpawnX(), zelda.getSpawnY());
        world.getWorldCamera().resetPosition();
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

    public FadeToggle getFadeToggle() {
        return fadeToggle;
    }

    public void setFadeToggle(FadeToggle fadeToggle) {
        this.fadeToggle = fadeToggle;
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

    public float getFade() {
        return fade;
    }

    public void setFade(float fade) {
        this.fade = fade;
    }

    public float getFadeCap() {
        return fadeCap;
    }

    public void setFadeCap(float fadeCap) {
        this.fadeCap = fadeCap;
    }
}
