package com.nebbii.zagdx;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapLoader {
    private TiledMap mapOverworld;

    public MapLoader() {
        mapOverworld = new TmxMapLoader().load("tiled/overworld.tmx");
    }

    public TiledMap getMapOverworld() {
        return mapOverworld;
    }

    public void dispose() {
        mapOverworld.dispose();
    }
}
