package com.nebbii.zagdx;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ArchipelagoItemMap {
    public static final Map<String, Item> NAME_TO_ITEM = createNameToItemMap();

    private ArchipelagoItemMap() {}

    public static Item getItem(String itemName) {
        if (!(NAME_TO_ITEM.containsKey(itemName))) {
            return null;
        }

        return NAME_TO_ITEM.get(itemName);
    }

    private static Map<String, Item> createNameToItemMap() {
        Map<String, Item> map = new HashMap<>();

        map.put("Boomerang", Weapon.BOOMERANG);
        map.put("Celestial Sign 1", Treasure.CELESTIAL_SIGN_1);
        map.put("Compass 1", Treasure.COMPASS_1);
        map.put("Dagger", Weapon.DAGGER);
        map.put("Empty Pitcher", Treasure.PITCHER_EMPTY);
        map.put("Firestorm", Weapon.FIRESTORM);
        map.put("Full Pitcher", Treasure.PITCHER_FULL);
        map.put("Jade Ring", Weapon.JADE_RING);
        map.put("Ladder", Treasure.LADDER);
        map.put("Red Boots", Treasure.RED_BOOTS);
        map.put("Underworld Map 1", Treasure.UNDERWORLD_MAP_1);
        map.put("Vial of Wind", Treasure.VIAL_OF_WIND);
        map.put("Wand", Weapon.WAND);

        return Collections.unmodifiableMap(map);
    }
}
