package com.nebbii.zagdx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ArchipelagoItemMapTest {
    @Test
    public void getsWeaponForArchipelagoItemId() {
        assertEquals(Weapon.BOOMERANG, ArchipelagoItemMap.getItem(2));
    }

    @Test
    public void getsTreasureForArchipelagoItemId() {
        assertEquals(Treasure.UNDERWORLD_MAP_1, ArchipelagoItemMap.getItem(12));
    }

    @Test
    public void getsRubyTreasureForRubyItemId() {
        assertEquals(Treasure.RUBIES, ArchipelagoItemMap.getItem(1));
    }

    @Test
    public void unknownItemIdReturnsNull() {
        assertNull(ArchipelagoItemMap.getItem(999));
    }
}
