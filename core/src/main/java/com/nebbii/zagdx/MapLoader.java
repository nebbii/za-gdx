package com.nebbii.zagdx;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapLoader {
    private TiledMap mapOverworld;
    private TiledMap mapShrineOfEarth;

    public MapLoader() {
        mapOverworld = new TmxMapLoader().load("tiled/overworld.tmx");
        mapShrineOfEarth = new TmxMapLoader().load("tiled/shrine_of_earth.tmx");
    }

    public TiledMap getMapOverworld() {
        return mapOverworld;
    }

    public TiledMap getMapShrineOfEarth() {
        return mapShrineOfEarth;
    }

    public void dispose() {
        mapOverworld.dispose();
    }
}
