package com.nebbii.zagdx;

import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class MapJsonLoader {
    public static ActorJsonEntry[] load(String path) {
        Json json = new Json();
        FileHandle file = Gdx.files.internal(path);
        return json.fromJson(ActorJsonEntry[].class, file);
    }
}
