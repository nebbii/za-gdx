package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;

public class MapRenderer {
    private OrthogonalTiledMapRenderer renderer;

    private MapManager map;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private ShapeRenderer shapes;

    private BitmapFont font;

	public MapRenderer(MapManager map, SpriteBatch batch, OrthographicCamera camera) {
        this.map = map;
        this.camera = camera;
        this.batch = batch;

        this.shapes = new ShapeRenderer();

        this.font = new BitmapFont();
    }

    public void draw() {
        renderer.setView(camera);

        switch(map.getCurrentLayerToggle()) {
        case MAIN:
        case COLLISION:
        case OVERLAP:
            renderer.render(new int[]{0});
            break;
        case COLLISIONOVERLAP:
            renderer.render(new int[]{0});
            drawGrid();
            break;
        case PAINT:
        case OVERLAPPAINT:
        case ALL:
            renderer.render(new int[]{0, 1});
            break;
        default:
            break;
        }
    }

    public void drawGrid() {
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeType.Line);
        shapes.setColor(Color.BLACK);
        for(int x = 0; x < getMapWidthInCells(); x++) {
            shapes.line(x*World.WORLD_WIDTH, 0, x*World.WORLD_WIDTH, getMapWidthInCells()*World.WORLD_WIDTH);
        }
        for(int y = 0; y < getMapHeightInCells(); y++) {
            shapes.line(0, y*World.WORLD_HEIGHT, getMapHeightInCells()*World.WORLD_HEIGHT, y*World.WORLD_HEIGHT);
        }
        shapes.end();
    }

    public void drawGridLabels() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (int x = 0; x < getMapWidthInCells(); x++) {
            for (int y = 0; y < getMapHeightInCells(); y++) {
                float centerX = x * World.WORLD_WIDTH + World.WORLD_WIDTH / 2f;
                float centerY = y * World.WORLD_HEIGHT + World.WORLD_HEIGHT / 2f;

                GlyphLayout layout = new GlyphLayout(font, x + ", " + y);
                font.draw(batch, layout, centerX - layout.width / 2f, centerY + layout.height / 2f);
            }
        }

        batch.end();
    }

    public void loadOverworld(MapLoader mapLoader) {
        renderer = new OrthogonalTiledMapRenderer(mapLoader.getMapOverworld(), 1f, batch);
    }

    public int getMapWidthInCells() {
        return renderer.getMap().getProperties().get("width", Integer.class);
    }

    public int getMapHeightInCells() {
        return renderer.getMap().getProperties().get("height", Integer.class);
    }
}
