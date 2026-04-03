package com.nebbii.zagdx;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;

public class MapLayerObjects {
    private MapObjects objects;

    public MapLayerObjects() {
    }

    public void loadOverworld(MapLoader mapLoader, String layerName) {
        MapLayer layer = mapLoader.getMapOverworld().getLayers().get(layerName);
        objects = layer.getObjects();
    }

    public void drawBoundingBoxes(ShapeRenderer shapes, OrthographicCamera camera, Color color) {
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setColor(color);

        for (PolygonMapObject object : getPolygonObjects()) {
            float[] vertices = object.getPolygon().getTransformedVertices();

            for (int i = 0; i < vertices.length; i += 2) {
                int next = (i + 2) % vertices.length;

                float x1 = vertices[i];
                float y1 = vertices[i + 1];
                float x2 = vertices[next];
                float y2 = vertices[next + 1];

                shapes.line(x1, y1, x2, y2);
            }
        }

        shapes.end();
    }

    public MapObjects getObjects() {
        return objects;
    }

    public List<PolygonMapObject> getPolygonObjects() {
        List<PolygonMapObject> polygons = new ArrayList<>();

        for (MapObject object : objects) {
            if (object instanceof PolygonMapObject) {
                polygons.add((PolygonMapObject) object);
            }
        }

        return polygons;
    }
}
