package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.nebbii.zagdx.MenuPause.MenuState;

public class GameManager {
    private World world;
    private int rubies;

    private ArrayList<Treasure> treasures;
    private ArrayList<Weapon> weapons;

    private float fade;
    private float fadeCap;

    private String currentMap;

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

        currentMap = "overworld";

        gameState = GameState.FADE_GAMEOVER;
        fadeToggle = FadeToggle.IN;

        rubies = 0;
    }

    public void logic() {
        switch(gameState) {
        case PLAY:
            if (world.getWorldCamera().isTransitioning()) {
                world.getMapManager().freezeAllActors();
                world.getMapManager().deleteAllProjectiles();
                setGameState(GameState.MOVE);
            }

            if (world.getMapManager().getZelda().getHealth() <= 0) {
                Gdx.app.log(getClass().getSimpleName(), "Zelda has died! Initiate game over sequence");
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
                respawnZelda(true);
                initializeFadeIn();
                setFadeToggle(FadeToggle.IN);
                setGameState(GameState.FADE_IN);
            }
            break;
        case FADE_WARP:
            if (handleFade()) {
                respawnZelda(false);
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
        setFadeToggle(FadeToggle.OUT);
        setFade(0f);
        setFadeCap(5f);
        setGameState(GameState.FADE_GAMEOVER);
        world.getMapManager().getZelda().onDeath();
    }

    public void initializeFadeWarp() {
        world.getMapManager().freezeAllActors();
        setFadeToggle(FadeToggle.OUT);
        setFade(0f);
        setFadeCap(1f);
        setGameState(GameState.FADE_WARP);
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

    public void respawnZelda(boolean hasDied) {
        Gdx.app.log(getClass().getSimpleName(), "Respawning zelda...");

        Zelda zelda = world.getMapManager().getZelda();

        if (hasDied) {
            zelda.revive();

            switch(currentMap) {
            case "shrine_of_earth":
                world.getMapManager().updateSpawnLocation("exit_earth");
                break;
            default:
                world.getMapManager().updateSpawnLocation("exit_earth");
            }

            reloadSave();
        }
        else {
            reloadLocations();
        }

        world.getMapManager().loadMapByName(currentMap);

        zelda.setPosition(zelda.getSpawnX(), zelda.getSpawnY());
        world.getWorldCamera().resetPosition();
    }

    public void reloadSave() {
        reloadRubies();
        reloadEquippedItem();
        reloadTreasures();
        reloadWeapons();
        reloadLocations();
    }

    public void reloadRubies() {
        SaveManager saveManager = world.getSaveManager();
        setRubies(saveManager.getRubies(), false);
    }

    public void reloadEquippedItem() {
        SaveManager saveManager = world.getSaveManager();
        if (saveManager.getEquippedItem() != null) {
            getZelda().setCurrentItem(saveManager.getEquippedItem());
        }
    }

    public void reloadTreasures() {
        SaveManager saveManager = world.getSaveManager();

        this.treasures = new ArrayList<Treasure>();

        for(Treasure treasure : saveManager.getTreasures()) {
            addTreasure(treasure, false);
        }
    }

    public void reloadWeapons() {
        SaveManager saveManager = world.getSaveManager();

        this.weapons = new ArrayList<Weapon>();

        for(Weapon weapon : saveManager.getWeapons()) {
            addWeapon(weapon, false);
        }
    }

    public void reloadLocations() {
        SaveManager saveManager = world.getSaveManager();

        for(SavedLocationEntry locationEntry : saveManager.getLocations()) {
            switch(locationEntry.action) {
            case "picked_up":
            case "permadead":
            case "spawned":
                Actor actor = world.getMapManager().findActorByLocationEntry(locationEntry.id);
                if (actor != null) {
                    actor.setState(State.DEAD);
                }
                break;
            }
        }
    }


    public int calculateProjectileDamage(Projectile projectile) {
        return 20;
    }

    public int calculateDamage(Actor attacker, Actor defender) {
        // grab the damage value from the weapon
        int damage = attacker.getDamage();

        // subtract defense capped on 0
        damage = Math.max(0, damage - defender.getDefense());

        String attackerType = attacker.getClass().getSimpleName();

        if (defender instanceof Enemy) {
            Enemy enemy = (Enemy) defender;

            if (enemy.getWeaknesses().size > 0) {
                enemy.setHurtWeakness(false);
            }

            // on weakness, add bonus damage
            for (String weakness : enemy.getWeaknesses()) {
                if (weakness.equals(attackerType)) {
                    damage += enemy.getBonusDamage();
                    enemy.setHurtWeakness(true);
                    break;
                }
            }
        }
        // return damage amount
        return damage;
    }

    public float calculateZeldaKnockback() {
        return 0.2f;
    }

    public World getWorld() {
        return world;
    }

    public int getRubies() {
        return rubies;
    }

    public void setRubies(int rubies, boolean save) {
        this.rubies = rubies;

        if (save) {
            world.getSaveManager().setRubies(rubies);
            world.getSaveManager().writeCurrentSave();
        }
    }

    public void increaseRubies(int count, boolean save) {
        setRubies(getRubies() + count, save);
    }

    public void decreaseRubies(int count, boolean save) {
        setRubies(getRubies() - count, save);
    }

    public void addTreasure(Treasure treasure, boolean save) {
        treasures.add(treasure);

        if (save) {
            world.getSaveManager().addTreasure(treasure);
            world.getSaveManager().writeCurrentSave();
        }
    }

    public void removeTreasure(Treasure treasure, boolean save) {
        treasures.remove(treasure);

        if (save) {
            world.getSaveManager().removeTreasure(treasure);
            world.getSaveManager().writeCurrentSave();
        }
    }

    public void addWeapon(Weapon weapon, boolean save) {
        weapons.add(weapon);

        if (save) {
            world.getSaveManager().addWeapon(weapon);
            world.getSaveManager().writeCurrentSave();
        }
    }

    public void removeWeapon(Weapon weapon, boolean save) {
        weapons.remove(weapon);

        if (save) {
            world.getSaveManager().removeWeapon(weapon);
            world.getSaveManager().writeCurrentSave();
        }
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

    public boolean hasItem(Item item) {
        return weapons.contains(item) || treasures.contains(item);
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

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public SaveManager getSaveManager() {
        return this.world.getSaveManager();
    }
}
