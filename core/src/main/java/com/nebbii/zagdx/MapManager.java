package com.nebbii.zagdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MapManager {
    private World world;
    public MapLoader loader;
    public MapLayerObjects overlay;
    public MapLayerObjects collision;
    public MapRenderer renderer;
    public MapRendererMask mask;

    public ArrayList<Actor> actors;

    private Zelda zelda;

    private LayerToggle currentLayerToggle;

    public MapManager(World world, SpriteBatch batch, OrthographicCamera camera) {
        currentLayerToggle = LayerToggle.MAIN;

        this.world = world;

        loader = new MapLoader();
        overlay = new MapLayerObjects();
        collision = new MapLayerObjects();
        renderer = new MapRenderer(this, batch, camera);
        mask = new MapRendererMask();

        actors = new ArrayList<>();
        zelda = new Zelda(world, this);
    }

    public void logic() {
        for (Actor actor : actors) {
            actor.logic();
        }

        removeDeadActors();
    }

    public void drawActors(SpriteBatch batch) {
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
            break;
        case OVERLAP:
            overlay.drawBoundingBoxes(shapes, camera, Color.GREEN);
            break;
        case COLLISIONOVERLAP:
            overlay.drawBoundingBoxes(shapes, camera, Color.GREEN);
            collision.drawBoundingBoxes(shapes, camera, Color.RED);
            this.drawActorBoundingBoxes(shapes, camera, Color.WHITE);
        case OVERLAPPAINT:
            overlay.drawBoundingBoxes(shapes, camera, Color.GREEN);
            break;
        case ALL:
            overlay.drawBoundingBoxes(shapes, camera, Color.GREEN);
            collision.drawBoundingBoxes(shapes, camera, Color.RED);
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
        }

        shapes.end();
    }

    public void addActor(Actor actor) {
        actors.add(actor);

        if (actor instanceof Zelda) {
            setZelda((Zelda) actor);
        }
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
        for (Actor actor : actors) {
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
        for (Actor actor : actors) {
            if (!isActorVisible(actor)) continue;

            actor.setState(State.ACTIVE);
        }
    }

    public boolean isActorVisible(Actor actor) {
        boolean currentColumn =
            World.convertWorldXToCellColumn(actor.getCenterPointX()) == World.convertWorldXToCellColumn(world.getWorldCamera().getTargetX());
        boolean currentRow =
            World.convertWorldYToCellRow(actor.getCenterPointY()) == World.convertWorldYToCellRow(world.getWorldCamera().getTargetY());

        return currentColumn && currentRow;

    }

    public void removeDeadActors() {
        actors.removeIf(actor -> actor.getState() == State.DEAD);
    }

    public void loadOverworld() {
        overlay.loadOverworld(loader, "Overlay");
        collision.loadOverworld(loader, "Collision");
        renderer.loadOverworld(loader);

        actors = new ArrayList<>();

        zelda.setPosition(2465.32f, 2249.46f);

        addActor(zelda);

        addActor(new NpcGlebb());

        PickupPitcherEmpty pitcherEmpty = new PickupPitcherEmpty();
        pitcherEmpty.setPosition(3672, 2055);
        addActor(pitcherEmpty);

        SpawnerPitcherFull spawnerPitcherFull = new SpawnerPitcherFull();
        spawnerPitcherFull.setPosition(1627, 2938);
        addActor(spawnerPitcherFull);

        SpawnerVialOfWind spawnerVialOfWind = new SpawnerVialOfWind();
        spawnerVialOfWind.setPosition(3672, 2075);
        addActor(spawnerVialOfWind);

        PickupRuby testRuby = new PickupRuby(PickupRuby.RubyType.BLUE);
        testRuby.setX(zelda.x + 200);
        testRuby.setY(zelda.y);
        addActor(testRuby);

        PickupHeart testHeart = new PickupHeart();
        testHeart.setX(zelda.x - 200);
        testHeart.setY(zelda.y);
        addActor(testHeart);

        PickupWand testWand = new PickupWand();
        testWand.setX(2755f);
        testWand.setY(2280f);
        addActor(testWand);
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
