package com.nebbii.zagdx;

import java.util.List;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class World {
    static final int WORLD_WIDTH = 384;
    static final int WORLD_HEIGHT = 240;
    static final public ImageLoader images = new ImageLoader();

    private final Viewport worldViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
    private WorldCamera worldCamera;
    private OrthographicCamera camera;

    private OrthographicCamera interfaceCamera;
    private Viewport interfaceViewport;

    private GameInput input;
    private GameManager gameManager;

    private SpriteBatch batch;

    private MapManager map;

    private ShapeRenderer shapes;

    private WorldCollision worldCollision;

    private MenuPause menuPause;

    private BitmapFont font;

    public World(SpriteBatch batch) {
        camera = (OrthographicCamera) worldViewport.getCamera();
        input = new GameInput(this);

        map = new MapManager(this, batch, camera);
        gameManager = new GameManager(this);

        map.loadOverworld();

        shapes = new ShapeRenderer();

        worldCamera = new WorldCamera(camera, map);
        worldCollision = new WorldCollision(map, gameManager);
        font = new BitmapFont();

        menuPause = new MenuPause(gameManager);

        interfaceCamera = new OrthographicCamera();
        interfaceViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, interfaceCamera);

        this.batch = batch;
    }

    public void logic() {
        switch(gameManager.getGameState()) {
        case PLAY:
        case MOVE:
        case FADE:
        case PAUSE_MAP:
        case PAUSE_ITEMS:
            input.logic();
            map.logic();
            gameManager.logic();
            worldCamera.logic();
            worldCollision.logic();
            menuPause.logic();
            break;
        default:
            throw new IllegalStateException("World->draw(): Unhandled game state: " + gameManager.getGameState());
        }
    }

    public void draw() {
        switch(gameManager.getGameState()) {
        case PLAY:
        case MOVE:
        case FADE:
        case PAUSE_MAP:
        case PAUSE_ITEMS:
            drawGame();
            drawItemScreen();
            drawHud(batch, font);
            drawDebugText(batch, font);
            break;
        default:
            throw new IllegalStateException("World->draw(): Unhandled game state: " + gameManager.getGameState());
        }
    }

    /**
     * @nebbii: The map is rendered with a mask marked by a polygon object
     * layer, this mask gets drawn over all the actors and then disabled for
     * the debug stuff
     */
    public void drawGame() {
        map.renderer.draw();
        map.mask.beginMask(camera);

        for (PolygonMapObject object : map.overlay.getPolygonObjects()) {
            map.mask.maskPolygon(object.getPolygon());
        }

        map.mask.endMask();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        map.drawActors(batch);
        batch.end();

        map.mask.disable();

        // debug
        map.drawBoundingBoxes(shapes, camera);
    }

    public void drawItemScreen() {
        interfaceViewport.apply();
        batch.setProjectionMatrix(interfaceCamera.combined);
        batch.begin();
        menuPause.draw(batch);
        batch.end();
    }


    public void resize(int width, int height) {
        if(width <= 0 || height <= 0) return;
        worldViewport.update(width, height, false);
        worldCamera.resetPosition();

        interfaceViewport.update(width, height, true);
    }

    // TODO: turn into cute hud sprites
    private void drawHud(SpriteBatch batch, BitmapFont font) {
        batch.setProjectionMatrix(interfaceCamera.combined);
        batch.begin();
        font.draw(batch,
                  "R: " + gameManager.getRubies(),
                  20f,
                  WORLD_HEIGHT - 20f);

        font.draw(batch,
                  "H: " + gameManager.getZelda().getHealth(),
                  WORLD_WIDTH - 60f,
                  WORLD_HEIGHT - 20f);
        batch.end();
    }

    // taken from my previous game
    private void drawDebugText(SpriteBatch batch, BitmapFont font) {
        List<String> debugLines = new ArrayList<>();

        debugLines.add("X: " + map.getZelda().getX());
        debugLines.add("Y: " + map.getZelda().getY());
        //debugLines.add("rX: " + getRelativePositionX(map.getZelda().getX()));
        //debugLines.add("rY: " + getRelativePositionY(map.getZelda().getY()));
        debugLines.add("cellColumn: " + worldCamera.getTargetCellColumn());
        debugLines.add("cellRow: " + worldCamera.getTargetCellRow());
        debugLines.add("Equip: " + map.getZelda().getCurrentItem().toString());
        //debugLines.add("State: " + map.getZelda().getState());
        //debugLines.add("GameState: " + gameManager.getGameState());

        batch.setProjectionMatrix(interfaceCamera.combined);
        batch.begin();
        for (int i = debugLines.size() - 1, line = 0; i >= 0; i--, line++) {
            font.draw(batch, debugLines.get(i), 20f, 20f + line * 20f);
        }
        batch.end();
    }

    public WorldCamera getWorldCamera() {
        return worldCamera;
    }

    public OrthographicCamera getInterfaceCamera() {
        return interfaceCamera;
    }

    public Viewport getInterfaceViewport() {
        return interfaceViewport;
    }

    public float getRelativePositionX(float x) {
        return x % WORLD_WIDTH;
    }

    public float getRelativePositionY(float y) {
        return y % WORLD_HEIGHT;
    }

    public MapManager getMapManager() {
        return map;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public MenuPause getMenuPause() {
        return menuPause;
    }

    public void setMenuPause(MenuPause menuPause) {
        this.menuPause = menuPause;
    }

    // get the cell X based on an X coordinate
    public static int convertWorldXToCellColumn(float x) {
        return MathUtils.floor(x / World.WORLD_WIDTH);
    }

    // get the cell Y based on a Y coordinate
    public static int convertWorldYToCellRow(float y) {
        return MathUtils.floor(y / World.WORLD_HEIGHT);
    }

    // get the center point X based on the cell number
    public static float getCenterPointOfCellColumn(int cellColumn) {
        return cellColumn * World.WORLD_WIDTH + World.WORLD_WIDTH / 2f;
    }

    // get the center point Y based on the cell number
    public static float getCenterPointOfCellRow(int cellRow) {
        return cellRow * World.WORLD_HEIGHT + World.WORLD_HEIGHT / 2f;
    }

    // get the center point of the cell that inhabits an arbitrary X point
    public static float getCenterOfCellByX(float x) {
        return getCenterPointOfCellColumn(convertWorldXToCellColumn(x));
    }

    // get the center point of the cell that inhabits a arbitrary Y point
    public static float getCenterOfCellByY(float y) {
        return getCenterPointOfCellRow(convertWorldYToCellRow(y));
    }

    public void dispose() {
        batch.dispose();
        map.dispose();
        images.dispose();
    }
}
