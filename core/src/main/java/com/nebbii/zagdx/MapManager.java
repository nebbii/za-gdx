package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.SpawnerPickup.Trigger;

public class MapManager {
    private World world;
    public MapLoader loader;
    public MapLayerObjects overlay;
    public MapLayerObjects collision;
    public MapLayerObjects special;
    public MapRenderer renderer;
    public MapRendererMask mask;

    public ArrayList<Actor> actors;
    public ArrayList<Actor> newActors;

    private int mapWidth = 0;
    private int mapHeight = 0;

    private Zelda zelda;

    private LayerToggle currentLayerToggle;

    public MapManager(World world, SpriteBatch batch, OrthographicCamera camera) {
        currentLayerToggle = LayerToggle.MAIN;

        this.world = world;

        renderer = new MapRenderer(this, batch, camera);
        loader = new MapLoader();
        overlay = new MapLayerObjects();
        collision = new MapLayerObjects();
        special = new MapLayerObjects();
        mask = new MapRendererMask();

        actors = new ArrayList<>();
        newActors = new ArrayList<>();

        zelda = new Zelda(world, this);
    }

    public void logic() {
        Iterator<Actor> iterator = actors.iterator();

        while (iterator.hasNext()) {
            Actor actor = iterator.next();
            actor.logic();

            if (actor.getState() == State.DEAD) {
                handleDeadActor(actor, iterator);
            }
        }

        if (!newActors.isEmpty()) {
            actors.addAll(newActors);
            newActors.clear();
        }
    }

    public void drawActors(SpriteBatch batch) {
        actors.sort(Comparator.comparingInt(Actor::getDrawOrder));

        for (Actor actor : actors) {
            actor.draw(batch);
        }
    }

    public void drawActorsWithMask(SpriteBatch batch, OrthographicCamera camera) {
        actors.sort(Comparator.comparingInt(Actor::getDrawOrder));

        for (Actor actor : actors) {
            Rectangle actorBox = actor.getCollisionBox();
            float actorLowestY = actorBox.y;

            mask.beginMask(camera);

            // draw the actor above or below the mask depending on the bottom position
            for (PolygonMapObject object : overlay.getPolygonObjects()) {
                Polygon polygon = object.getPolygon();

                // cheap bounds check
                if (!polygon.getBoundingRectangle().contains(actorBox.x, actorBox.y)) continue;

                float maskLowestY = mask.getLowestY(polygon);

                if (actorLowestY > maskLowestY) {
                    mask.maskPolygon(polygon);
                }
            }

            mask.endMask();

            batch.begin();
            actor.draw(batch);
            batch.end();

            mask.disable();
        }
    }

    public void drawBoundingBoxes(ShapeRenderer shapes, OrthographicCamera camera) {
        switch(getCurrentLayerToggle()) {
        case MAIN:
        case PAINT:
            break;
        case COLLISION:
            collision.drawBoundingBoxes(shapes, camera, Color.RED);
            this.drawActorBoundingBoxes(shapes, camera, Color.WHITE);
            world.drawWorldBorders(Color.BLACK);
            break;
        case OVERLAP:
            overlay.drawBoundingBoxes(shapes, camera, Color.GREEN);
            break;
        case COLLISIONOVERLAP:
            overlay.drawBoundingBoxes(shapes, camera, Color.GREEN);
            collision.drawBoundingBoxes(shapes, camera, Color.RED);
            this.drawActorBoundingBoxes(shapes, camera, Color.WHITE);
            world.drawWorldBorders(Color.BLACK);
        case OVERLAPPAINT:
            overlay.drawBoundingBoxes(shapes, camera, Color.GREEN);
            break;
        case ALL:
            overlay.drawBoundingBoxes(shapes, camera, Color.GREEN);
            collision.drawBoundingBoxes(shapes, camera, Color.RED);
            world.drawWorldBorders(Color.BLACK);
            this.drawActorBoundingBoxes(shapes, camera, Color.WHITE);
            break;
        default:
            break;
        }
    }

    public void drawActorBoundingBoxes(ShapeRenderer shapes, OrthographicCamera camera, Color color) {
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Line);

        for (Actor actor : actors) {
            shapes.setColor(Color.RED);
            Rectangle collision = actor.getCollisionBox();
            shapes.rect(collision.x, collision.y, collision.width, collision.height);

            shapes.setColor(Color.BLUE);
            Rectangle hitbox = actor.getHitbox();
            shapes.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

            if (actor instanceof Enemy) {
                shapes.setColor(Color.PURPLE);
                Rectangle alertBox = ((Enemy) actor).getAlertBox();
                shapes.rect(alertBox.x, alertBox.y, alertBox.width, alertBox.height);
            }
        }

        shapes.end();
    }

    public void addActor(Actor actor) {
        actor.setMap(this);
        actors.add(actor);

        if (actor instanceof Zelda) {
            setZelda((Zelda) actor);
        }
    }

    public void addNewActor(Actor actor) {
        actor.setMap(this);
        newActors.add(actor);
    }

    // for applying checks when a spawned item gets picked up
    public void addNewPickupWithParent(Pickup pickup, Spawner spawner) {
        pickup.setMap(this);
        pickup.setSpawnerParent(spawner);
        newActors.add(pickup);
    }

    public <T extends Actor> T findFirstActorByType(Class<T> type) {
        for (Actor actor : actors) {
            if (type.isInstance(actor)) {
                return type.cast(actor);
            }
        }

        return null;
    }

    public <T extends Actor> Array<T> findAllActorsByType(Class<T> type) {
        Array<T> results = new Array<>();

        for (Actor actor : actors) {
            if (type.isInstance(actor)) {
                results.add(type.cast(actor));
            }
        }

        return results;
    }

    public Actor findActorByLocationEntry(String locationEntry) {
        if (locationEntry == null) {
            return null;
        }

        for (Actor actor : actors) {
            if (locationEntry.equals(actor.getLocationEntry())) {
                return actor;
            }
        }

        return null;
    }

    public <T extends Actor> Array<T> findActiveActorsByType(Class<T> type) {
        Array<T> results = new Array<>();

        for (Actor actor : actors) {
            if (type.isInstance(actor) && actor.isActive()) {
                results.add(type.cast(actor));
            }
        }

        return results;
    }

    public Pickup findOverlappingPurchasablePickup(Actor overlapper) {
        for (Actor actor : actors) {
            if (!(actor instanceof Pickup) || !actor.isActive()) continue;

            Pickup pickup = (Pickup) actor;

            if (pickup.isPurchasable() && pickup.getHitbox().overlaps(overlapper.getHitbox())) {
                return pickup;
            }
        }

        return null;
    }

    public SpawnerPickup findActivePickupSpawner(String pickupType) {
        for (SpawnerPickup spawner : findActiveActorsByType(SpawnerPickup.class)) {
            if (pickupType.equals(spawner.getPickupType())) {
                return spawner;
            }
        }

        return null;
    }

    public void freezeAllActors() {
        Gdx.app.log(getClass().getSimpleName(), "Freezing actors");
        for (Actor actor : actors) {
            if (actor.getState() != State.ACTIVE) continue;
            Gdx.app.log(getClass().getSimpleName(), "Actor: " + actor.getClass() + " set to idle");
            actor.setState(State.IDLE);
        }
    }



    public void freezeVisibleActors() {
        for (Actor actor : actors) {
            if (!isActorVisible(actor)) continue;
            if (actor.getState() != State.ACTIVE) continue;

            actor.setState(State.IDLE);
        }
    }

    public void unfreezeVisibleActors() {
        Gdx.app.log(getClass().getSimpleName(), "Unfreezing actors");

        for (Actor actor : actors) {
            if (!isActorVisible(actor)) continue;
            if (actor.getState() != State.IDLE) continue;

            Gdx.app.log(getClass().getSimpleName(), "Actor: " + actor.getClass() + " set to active");
            actor.setState(State.ACTIVE);
        }
    }

    public void deleteAllProjectiles() {
        for (Actor actor : actors) {
            if (!(actor instanceof Projectile)) continue;

            actor.setState(State.DEAD);
        }
    }

    public ArrayList<Actor> getActiveActors() {
        ArrayList<Actor> activeActors = new ArrayList<>();

        for (Actor actor : actors) {
            if (actor.getState() == State.ACTIVE) {
                activeActors.add(actor);
            }
        }

        return activeActors;
    }

    public boolean activeActorsContain(Class<? extends Actor> type) {
        for (Actor actor : getActiveActors()) {
            if (type.isInstance(actor)) {
                return true;
            }
        }
        return false;
    }

    public boolean isActorVisible(Actor actor) {
        boolean currentColumn =
            World.convertWorldXToCellColumn(actor.getCenterPointX()) == World.convertWorldXToCellColumn(world.getWorldCamera().getTargetX());
        boolean currentRow =
            World.convertWorldYToCellRow(actor.getCenterPointY()) == World.convertWorldYToCellRow(world.getWorldCamera().getTargetY());

        return currentColumn && currentRow;

    }

    public void handleDeadActor(Actor actor, Iterator<Actor> iterator) {
        Gdx.app.log(getClass().getSimpleName(), "Handle dead actor: " + actor.getClass());

        switch (actor.getClass().getSimpleName()) {
        case "EnemyLlort":
        case "EnemySardakRed":
        case "EnemySardakYellow":
        case "EnemySardakBlue":
            break;
        case "EnemyDragonfly":
            // TODO: drop a guaranteed heart in the gauntlet
            dropRandomPickup(actor.getCenterPointX(), actor.getCenterPointY());
            break;
        default:
            if (actor instanceof Enemy) {
                dropRandomPickup(actor.getCenterPointX(), actor.getCenterPointY());
            }
            break;
        }

        iterator.remove();
    }

    public void dropSpecificPickup(String pickupName, float x, float y) {
        Actor pickup;

        switch (pickupName) {
        case "PickupHeart":
            pickup = new PickupHeart();
            break;
        case "PickupRubyBlue":
            pickup = new PickupRuby(RubyType.BLUE);
            break;
        case "PickupRubyYellow":
            pickup = new PickupRuby(RubyType.YELLOW);
            break;
        default:
            throw new RuntimeException("Requested specific pickup does not exist (" + pickupName + ")");
        }

        pickup.setMap(this);
        pickup.getCollisionBox().setPosition(x, y);
        newActors.add(pickup);
    }

    public void dropRandomPickup(float x, float y) {
        int roll = MathUtils.random(0, 3);

        Actor pickup;

        Gdx.app.log(getClass().getSimpleName(), "dropRandomPickup: rolled " + roll);
        switch (roll) {
        case 0:
            pickup = new PickupHeart();
            break;
        case 1:
            pickup = new PickupRuby(RubyType.BLUE);
            break;
        case 2:
            pickup = new PickupRuby(RubyType.YELLOW);
            break;
        case 3:
            return;
        default:
            throw new RuntimeException("Failed to choose random pickup actor (" + roll + ")");
        }

        pickup.setMap(this);
        pickup.getCollisionBox().setPosition(x, y);
        newActors.add(pickup);
    }

    public void loadMapByName(String mapName) {
        Gdx.app.log(getClass().getSimpleName(), "loadMapByName(" + mapName + ")");

        overlay.loadObjectsFromLayer(loader, mapName, "Overlay");
        collision.loadObjectsFromLayer(loader, mapName, "Collision");
        special.loadObjectsFromLayer(loader, mapName, "Special");
        renderer.loadTiledRenderer(loader, mapName);

        MapData data = JsonLoader.load("gamedata/" + mapName + ".json", MapData.class);

        actors.clear();

        zelda.setPosition(zelda.getSpawnX(), zelda.getSpawnY());
        addActor(zelda);

        for (LocationJsonEntry location : data.locations) {
            if (location.location == null) continue;

            int i = 0;
            for (ActorJsonEntry entry : location.actors) {
                if (entry.type == null) continue;

                addActor(createActorFromJsonEntry(entry, location.location + "_" + i));
                i++;
            }
        }

        setMapWidth(data.width);
        setMapHeight(data.height);

        world.getWorldCamera().resetPosition();
    }

    public void updateSpawnLocation(String spawnLocation) {
        SpawnData data = JsonLoader.load("gamedata/spawn_locations.json", SpawnData.class);

        SpawnJsonEntry spawn = data.get(spawnLocation);

        if (spawn == null) {
            throw new RuntimeException("Requested spawn doesn't exist in the JSON: " + spawnLocation);
        }

        world.getGameManager().setCurrentMap(spawn.targetMap);
        zelda.setSpawnX(spawn.x);
        zelda.setSpawnY(spawn.y);
    }

    // initialize actors based on class names from json files, unique parameters through switch
    public Actor createActorFromJsonEntry(ActorJsonEntry entry, String locationEntry) {
        try {
            Actor actor;

            switch (entry.type) {
            case "PickupRuby":
                RubyType rubyType = RubyType.valueOf(entry.rubyType);
                actor = new PickupRuby(rubyType);
                break;
            default:
                Class<?> newClass = Class.forName("com.nebbii.zagdx." + entry.type);
                Object object = newClass.getDeclaredConstructor().newInstance();

                if (!(object instanceof Actor)) {
                    throw new IllegalArgumentException(entry.type + " is not a known actor");
                }

                actor = (Actor) object;
                break;
            }

            // Check for NPCs that only vanish after a map reload
            if (shouldActorBeDead(actor)) {
                actor.setState(State.DEAD);
                return actor;
            }

            actor.getCollisionBox().setPosition(entry.x, entry.y);
            actor.setLocationEntry(locationEntry);

            if (actor instanceof EnemyKeese) {
                configureKeesePath((EnemyKeese) actor, entry);
            }

            if (actor instanceof SpawnerPickup) {
                SpawnerPickup spawner = (SpawnerPickup) actor;
                spawner.setTrigger(entry.trigger);
                spawner.setPickupType(entry.pickupType);

                if (spawner.getTrigger() == Trigger.MANUAL_NPC) {
                    spawner.setState(State.PENDING);
                }
            }

            if (actor instanceof Pickup) {
                Pickup pickup = (Pickup) actor;
                pickup.setPurchasable(entry.purchasable);
                pickup.setPrice(entry.price);

                if (entry.purchasable) {
                    pickup.setState(State.ACTIVE);
                }
            }

            return actor;
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to load actor: " + entry.type, e);
        }
    }

    private boolean shouldActorBeDead(Actor actor) {
        if (actor instanceof NpcBeggar && getSaveManager().hasLocationEntry("j22_1")) {
            return true;
        }

        if (actor instanceof NpcTalkingChest && getSaveManager().hasLocationEntry("s108_1")) {
            return true;
        }

        if (actor instanceof NpcGlebb && getSaveManager().hasLocationEntry("j24_2")) {
            return true;
        }

        return false;
    }

    private void configureKeesePath(EnemyKeese keese, ActorJsonEntry entry) {
        boolean hasSetPath = entry.path != null && !entry.path.isEmpty();

        if (entry.pathMode == null || entry.pathMode.trim().isEmpty()) {
            if (hasSetPath) {
                keese.setRelativePath(entry.path);
            }
            return;
        }

        switch (entry.pathMode.trim().toUpperCase()) {
        case "SET":
            if (!hasSetPath) {
                throw new IllegalArgumentException("EnemyKeese pathMode SET requires at least one path point");
            }
            keese.setRelativePath(entry.path);
            break;
        case "RANDOM":
            keese.enableRandomPathMode();
            break;
        default:
            throw new IllegalArgumentException("Unknown EnemyKeese pathMode: " + entry.pathMode);
        }
    }

    public Zelda getZelda() {
        return zelda;
    }

    public void setZelda(Zelda zelda) {
        this.zelda = zelda;
    }

    public void dispose() {
        loader.dispose();
    }

    public LayerToggle getCurrentLayerToggle() {
        return currentLayerToggle;
    }

    public void setCurrentLayerToggle(LayerToggle currentLayerToggle) {
        this.currentLayerToggle = currentLayerToggle;
    }

    public SaveManager getSaveManager() {
        return world.getSaveManager();
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }
}
