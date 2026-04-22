package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class MapJsonLoader {
    public static <Type> Type load(String path, Class<Type> type) {
        Json json = new Json();
        FileHandle file = Gdx.files.internal(path);
        return json.fromJson(type, file);
    }
}
