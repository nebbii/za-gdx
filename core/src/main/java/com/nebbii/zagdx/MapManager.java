package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

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

        if (zelda.getSpawnX() == -1 && zelda.getSpawnY() == -1) {
            updateSpawnLocation("overworld_pedestal");
        }
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

    public Actor findActorByType(Class<? extends Actor> type) {
        for (Actor actor : actors) {
            if (type.isInstance(actor)) {
                return actor;
            }
        }

        return null;
    }

    public void freezeAllActors() {
        Gdx.app.log("MapManager", "Freezing actors");
        for (Actor actor : actors) {
            if (actor.getState() == State.IDLE) continue;
            Gdx.app.log("MapManager", "Actor: " + actor.getClass() + " set to idle");
            actor.setState(State.IDLE);
        }
    }

    public void freezeVisibleActors() {
        for (Actor actor : actors) {
            if (!isActorVisible(actor)) continue;

            actor.setState(State.IDLE);
        }
    }

    public void unfreezeVisibleActors() {
        Gdx.app.log("MapManager", "Unfreezing actors");

        for (Actor actor : actors) {
            if (!isActorVisible(actor)) continue;

            Gdx.app.log("MapManager", "Actor: " + actor.getClass() + " set to active");
            actor.setState(State.ACTIVE);
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
        Gdx.app.log("MapManager", "Handle dead actor: " + actor.getClass());

        switch (actor.getClass().getSimpleName()) {
        case "EnemyGoriya":
        case "EnemyTumblebot":
            dropRandomPickup(actor.getCenterPointX(), actor.getCenterPointY());
            iterator.remove();
            break;
        default:
            iterator.remove();
            break;
        }
    }

    public void dropRandomPickup(float x, float y) {
        int roll = MathUtils.random(0, 3);

        Actor pickup;

        Gdx.app.log("MapManager", "dropRandomPickup: rolled " + roll);
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
        Gdx.app.log("MapManager", "loading map: " + mapName);

        overlay.loadObjectsFromLayer(loader, mapName, "Overlay");
        collision.loadObjectsFromLayer(loader, mapName, "Collision");
        special.loadObjectsFromLayer(loader, mapName, "Special");
        renderer.loadTiledRenderer(loader, mapName);

        MapData data = JsonLoader.load("gamedata/" + mapName + ".json", MapData.class);

        actors.clear();

        zelda.setPosition(zelda.getSpawnX(), zelda.getSpawnY());
        addActor(zelda);

        for (ActorJsonEntry entry : data.actors) {
            if (entry.type == null) continue;
            addActor(createActorFromJsonEntry(entry));
        }

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
    public Actor createActorFromJsonEntry(ActorJsonEntry entry) {
        try {
            switch (entry.type) {
            case "PickupRuby":
                RubyType rubyType = RubyType.valueOf(entry.rubyType);
                PickupRuby ruby = new PickupRuby(rubyType);
                ruby.setPosition(entry.x, entry.y);
                return ruby;
            default:
                Class<?> newClass = Class.forName("com.nebbii.zagdx." + entry.type);
                Object object = newClass.getDeclaredConstructor().newInstance();

                if (!(object instanceof Actor)) {
                    throw new IllegalArgumentException(entry.type + " is not a known actor");
                }

                Actor actor = (Actor) object;
                actor.getCollisionBox().setPosition(entry.x, entry.y);
                return actor;
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to load actor: " + entry.type, e);
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
}
