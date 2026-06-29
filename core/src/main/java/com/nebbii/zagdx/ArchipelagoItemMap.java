package com.nebbii.zagdx;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ArchipelagoItemMap {
    public static final Map<Integer, Item> ID_TO_ITEM = createIdToItemMap();

    private ArchipelagoItemMap() {}

    public static Item getItem(int itemId) {
        if (!(ID_TO_ITEM.containsKey(itemId))) {
            return null;
        }

        return ID_TO_ITEM.get(itemId);
    }

    private static Map<Integer, Item> createIdToItemMap() {
        Map<Integer, Item> map = new HashMap<>();

        map.put(1, Treasure.RUBIES); // Blue Ruby
        map.put(2, Weapon.BOOMERANG); // Boomerang
        map.put(3, Treasure.CELESTIAL_SIGN_1); // Celestial Sign 1
        map.put(4, Treasure.COMPASS_1); // Compass 1
        map.put(5, Weapon.DAGGER); // Dagger
        map.put(6, Treasure.PITCHER_EMPTY); // Empty Pitcher
        map.put(7, Weapon.FIRESTORM); // Firestorm
        map.put(8, Treasure.PITCHER_FULL); // Full Pitcher
        map.put(9, Weapon.JADE_RING); // Jade Ring
        map.put(10, Treasure.LADDER); // Ladder
        map.put(11, Treasure.RED_BOOTS); // Red Boots
        map.put(12, Treasure.UNDERWORLD_MAP_1); // Underworld Map 1
        map.put(13, Treasure.VIAL_OF_WIND); // Vial of Wind
        map.put(14, Weapon.WAND); // Wand
        map.put(15, Treasure.RUBIES); // Yellow Ruby

        return Collections.unmodifiableMap(map);
    }
}
