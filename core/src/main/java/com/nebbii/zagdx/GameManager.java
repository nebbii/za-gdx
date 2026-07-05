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
        FADE_WARP,
        AP_SYNC
    }

    public enum FadeToggle {
        IN,
        OUT
    }

    private GameState gameState;
    private GameState priorGameState;
    private float apSyncTimer = 0;

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

            if (getSaveManager().canDeathInAP()) {
                world.getMapManager().getZelda().setHealth(-1);
            }

            if (world.getMapManager().getZelda().getHealth() <= 0) {
                Gdx.app.log(getClass().getSimpleName(), "Zelda has died! Initiate game over sequence");

                if (world.getArchipelagoManager().isConnected()) {
                    Gdx.app.log(getClass().getSimpleName(), "Setting death link switch to on");
                    if (getSaveManager().canDeathInAP()) {
                        getSaveManager().setDeathInAP(false);
                    }
                    else {
                        getSaveManager().setDeathOutAP(true);
                    }
                }

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
        case AP_SYNC:
            apSyncTimer += Gdx.graphics.getDeltaTime();

            if (apSyncTimer > 30) {
                world.getArchipelagoManager().getArchipelagoClient().disconnect();
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
        MapManager map = world.getMapManager();
        WorldCamera worldCamera = world.getWorldCamera();

        if (hasDied) {
            zelda.revive();

            String lastCell = world.rowAndColumnToRealCell(worldCamera.getTargetCellColumn(), worldCamera.getTargetCellRow());

            switch(currentMap) {
            case "shrine_of_earth":
                if (lastCell.equals("g2")) {
                    map.updateSpawnLocation("shrine_of_earth_llort_gate_entrance");
                }
                else {
                    map.updateSpawnLocation("overworld_entrance_earth");
                }
                break;
            default:
                map.updateSpawnLocation("overworld_pedestal");
            }

            reloadSave();
        }

        map.loadMapByName(currentMap);
        reloadLocations();

        zelda.setPosition(zelda.getSpawnX(), zelda.getSpawnY());
        worldCamera.resetPosition();
    }

    public void reloadSave() {
        reloadRubies();
        reloadEquippedItem();
        reloadTreasures();
        reloadWeapons();
        //reloadLocations();
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

        if (this.rubies > 0) {
            addTreasure(Treasure.RUBIES, false);
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
        MapManager mapManager = world.getMapManager();

        for(SavedLocationEntry locationEntry : saveManager.getLocations()) {
            switch(locationEntry.action) {
            case "interacted":
                Npc npc = (Npc) mapManager.findActorByLocationEntry(locationEntry.id);
                if (npc != null && npc instanceof Npc) {
                    npc.setInteracted(true);
                }
                break;
            case "picked_up":
            case "permadead":
            case "spawned":
                Actor actor = mapManager.findActorByLocationEntry(locationEntry.id);
                if (actor != null) {
                    actor.setState(State.DEAD);
                }
                break;
            }
        }

        // exceptions (for now)
        if (saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakBlue", "permadead")
            && saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakRed", "permadead")
            && saveManager.hasLocationForClass("shrine_of_earth", "EnemySardakYellow", "permadead"))
        {
            for (SpriteLlortLaser laser : mapManager.findAllActorsByType(SpriteLlortLaser.class)) {
                laser.setState(State.DEAD);
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

    // Basing this on damage for now as the actual formula is not fully figured out
    /* Phlosion: "Knockback happens in 5-pixel chunks applied once every three frames. The number of knockback chunks depends on the weapon stats, which are embedded in a bespoke weapon scripting language that I don't think I managed to automate decoding." */
    public float calculateZeldaKnockback(Actor attacker, Actor defender) {
        float pixelAmount = calculateDamage(attacker, defender) / 10f;
        return pixelAmount * 5f;
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
        reloadTreasures();
    }

    public void decreaseRubies(int count, boolean save) {
        setRubies(getRubies() - count, save);
        reloadTreasures();
    }

    public void addTreasure(Treasure treasure, boolean save) {
        treasures.add(treasure);

        if (save) {
            world.getSaveManager().addTreasure(treasure);
            world.getSaveManager().writeCurrentSave(this);
        }

        // simple for now until we have more goals!
        if (world.getArchipelagoManager().isConnected() && hasItem(Treasure.CELESTIAL_SIGN_1)) {
            world.getArchipelagoManager().setHasGoaled(true);
        }
    }

    public void removeTreasure(Treasure treasure, boolean save) {
        treasures.remove(treasure);

        if (save) {
            world.getSaveManager().removeTreasure(treasure);
            world.getSaveManager().writeCurrentSave(this);
        }
    }

    public void addWeapon(Weapon weapon, boolean save) {
        weapons.add(weapon);

        if (save) {
            world.getSaveManager().addWeapon(weapon);
            world.getSaveManager().writeCurrentSave(this);
        }
    }

    public void removeWeapon(Weapon weapon, boolean save) {
        weapons.remove(weapon);

        if (save) {
            world.getSaveManager().removeWeapon(weapon);
            world.getSaveManager().writeCurrentSave(this);
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

    public GameState getPriorGameState() {
        return priorGameState;
    }

    public void setPriorGameState(GameState priorGameState) {
        this.priorGameState = priorGameState;
    }

    public void storeCurrentGameState() {
        this.priorGameState = this.gameState;
    }

    public void restorePriorGameState() {
        this.gameState = this.priorGameState;
        this.priorGameState = null;
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
