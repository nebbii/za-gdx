package com.nebbii.zagdx;

import java.util.List;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nebbii.zagdx.GameManager.FadeToggle;

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

    private MapManager mapManager;

    private ShapeRenderer shapes;

    private WorldCollision worldCollision;
    private Rectangle[] worldBorders;

    private MenuPause menuPause;

    private BitmapFont font;

    public World(SpriteBatch batch) {
        camera = (OrthographicCamera) worldViewport.getCamera();
        input = new GameInput(this);

        mapManager = new MapManager(this, batch, camera);
        worldCamera = new WorldCamera(camera, mapManager);
        gameManager = new GameManager(this);

        shapes = new ShapeRenderer();

        worldCollision = new WorldCollision(mapManager, gameManager);

        worldBorders = new Rectangle[4];
        for (int i = 0; i < worldBorders.length; i++) {
            worldBorders[i] = new Rectangle();
        }

        font = new BitmapFont();

        menuPause = new MenuPause(gameManager);

        interfaceCamera = new OrthographicCamera();
        interfaceViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, interfaceCamera);

        WorldShaders.init();
        this.batch = batch;

        mapManager.loadMapByName("overworld");
        updateWorldBorders();
    }

    public void logic() {
        switch(gameManager.getGameState()) {
        case PLAY:
            worldCollision.logic();
        case MOVE:
        case FADE_GAMEOVER:
        case FADE_WARP:
        case FADE_IN:
        case PAUSE_MAP:
        case PAUSE_ITEMS:
            input.logic();
            mapManager.logic();
            gameManager.logic();
            worldCamera.logic();
            updateWorldBorders();
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
        case PAUSE_MAP:
        case PAUSE_ITEMS:
            drawGame();
            drawItemScreen();
            drawHud();
            drawDebugText();
            break;
        case FADE_GAMEOVER:
        case FADE_WARP:
        case FADE_IN:
            drawGame();
            drawItemScreen();
            drawHud();
            drawDebugText();
            drawFadeOverlay();
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
        mapManager.renderer.draw();
        mapManager.mask.beginMask(camera);

        for (PolygonMapObject object : mapManager.overlay.getPolygonObjects()) {
            mapManager.mask.maskPolygon(object.getPolygon());
        }

        mapManager.mask.endMask();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        mapManager.drawActors(batch);
        batch.end();

        mapManager.mask.disable();

        // debug
        mapManager.drawBoundingBoxes(shapes, camera);
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
    private void drawHud() {
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

    private void drawFadeOverlay() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        float opacity;

        if (gameManager.getFadeToggle() == FadeToggle.IN) {
            opacity = 1 - (gameManager.getFade() / gameManager.getFadeCap());
        }
        else {
            opacity = gameManager.getFade() / gameManager.getFadeCap();
        }

        shapes.setProjectionMatrix(interfaceCamera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(0f, 0f, 0f, opacity);
        shapes.rect(0f, 0f, WORLD_WIDTH, WORLD_HEIGHT);
        shapes.end();
    }

    // taken from my previous game
    private void drawDebugText() {
        List<String> debugLines = new ArrayList<>();

        debugLines.add("X: " + mapManager.getZelda().getX());
        debugLines.add("Y: " + mapManager.getZelda().getY());
        //debugLines.add("rX: " + getRelativePositionX(mapManager.getZelda().getX()));
        //debugLines.add("rY: " + getRelativePositionY(mapManager.getZelda().getY()));
        debugLines.add("cellColumn: " + worldCamera.getTargetCellColumn());
        debugLines.add("cellRow: " + worldCamera.getTargetCellRow());
        debugLines.add("Equip: " + mapManager.getZelda().getCurrentItem().toString());
        //debugLines.add("State: " + mapManager.getZelda().getState());
        debugLines.add("GameState: " + gameManager.getGameState());

        batch.setProjectionMatrix(interfaceCamera.combined);
        batch.begin();
        for (int i = debugLines.size() - 1, line = 0; i >= 0; i--, line++) {
            font.draw(batch, debugLines.get(i), 20f, 20f + line * 20f);
        }
        batch.end();
    }

    public void drawWorldBorders(Color color) {
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Line);

        for (Rectangle border : getWorldBorders()) {
            shapes.setColor(color);

            shapes.rect(border.x, border.y, border.width, border.height);
        }

        shapes.end();
    }

    private void updateWorldBorders() {
        int cellColumn = worldCamera.getTargetCellColumn();
        int cellRow = worldCamera.getTargetCellRow();

        float screenX = cellColumn * WORLD_WIDTH;
        float screenY = cellRow * WORLD_HEIGHT;

        Rectangle left = worldBorders[0];
        Rectangle right = worldBorders[1];
        Rectangle bottom = worldBorders[2];
        Rectangle top = worldBorders[3];

        left.set(
            screenX - 1f,
            screenY,
            1f,
            WORLD_HEIGHT
        );

        right.set(
            screenX + WORLD_WIDTH,
            screenY,
            1f,
            WORLD_HEIGHT
        );

        bottom.set(
            screenX,
            screenY - 1f,
            WORLD_WIDTH,
            1f
        );

        top.set(
            screenX,
            screenY + WORLD_HEIGHT,
            WORLD_WIDTH,
            1f
        );
    }

    public Rectangle[] getWorldBorders() {
        return worldBorders;
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
        return mapManager;
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
        mapManager.dispose();
        images.dispose();
    }
}
