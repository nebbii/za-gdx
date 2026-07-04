package com.nebbii.zagdx;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ArchipelagoItemMap {
    public static final Map<Long, Item> ID_TO_ITEM = createIdToItemMap();
    public static final Map<Long, String> ID_TO_PICKUP_ITEM_NAME = createIdToPickupMap();

    private ArchipelagoItemMap() {}

    public static Item getItem(long itemId) {
        if (!(ID_TO_ITEM.containsKey(itemId))) {
            return null;
        }

        return ID_TO_ITEM.get(itemId);
    }

    public static String getPickup(long itemId) {
        if (!(ID_TO_PICKUP_ITEM_NAME.containsKey(itemId))) {
            return null;
        }

        return ID_TO_PICKUP_ITEM_NAME.get(itemId);
    }

    private static Map<Long, Item> createIdToItemMap() {
        Map<Long, Item> map = new HashMap<>();

        map.put(1L, Treasure.RUBIES); // Blue Ruby
        map.put(2L, Weapon.BOOMERANG); // Boomerang
        map.put(3L, Treasure.CELESTIAL_SIGN_1); // Celestial Sign 1
        map.put(4L, Treasure.COMPASS_1); // Compass 1
        map.put(5L, Weapon.DAGGER); // Dagger
        map.put(6L, Treasure.PITCHER_EMPTY); // Empty Pitcher
        map.put(7L, Weapon.FIRESTORM); // Firestorm
        map.put(8L, Treasure.PITCHER_FULL); // Full Pitcher
        map.put(9L, Weapon.JADE_RING); // Jade Ring
        map.put(10L, Treasure.LADDER); // Ladder
        map.put(11L, Treasure.RED_BOOTS); // Red Boots
        map.put(12L, Treasure.UNDERWORLD_MAP_1); // Underworld Map 1
        map.put(13L, Treasure.VIAL_OF_WIND); // Vial of Wind
        map.put(14L, Weapon.WAND); // Wand
        map.put(15L, Treasure.RUBIES); // Yellow Ruby
        map.put(16L, Treasure.CANDLE); // Candle
        map.put(17L, Treasure.MAGIC_SHIELD); // Magic Shield
        map.put(18L, Weapon.CALM); // Wand

        return Collections.unmodifiableMap(map);
    }

    private static Map<Long, String> createIdToPickupMap() {
        Map<Long, String> map = new HashMap<>();

        map.put(1L, "PickupRuby");
        map.put(2L, "PickupBoomerang");
        map.put(3L, "PickupCelestialStone1");
        map.put(4L, "PickupCompass1");
        map.put(5L, "PickupDagger");
        map.put(6L, "PickupPitcherEmpty");
        map.put(7L, "PickupFirestorm");
        map.put(8L, "PickupPitcherFull");
        map.put(9L, "PickupJadeRing");
        map.put(10L, "PickupLadder");
        map.put(11L, "PickupRedBoots");
        map.put(12L, "PickupUnderworldMap1");
        map.put(13L, "PickupVialOfWind");
        map.put(14L, "PickupWand");
        map.put(15L, "PickupRuby");
        map.put(16L, "PickupCandle");
        map.put(17L, "PickupMagicShield");
        map.put(18L, "PickupCalm");

        return Collections.unmodifiableMap(map);
    }
}
