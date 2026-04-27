package com.nebbii.zagdx;

import java.util.HashMap;

public class SpawnData {
    public HashMap<String, SpawnJsonEntry> spawns = new HashMap<>();

    public SpawnJsonEntry get(String key) {
        return spawns.get(key);
    }
}
