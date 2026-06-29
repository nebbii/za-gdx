package com.nebbii.zagdx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ArchipelagoItemMapTest {
    @Test
    public void getsWeaponForArchipelagoItemName() {
        assertEquals(Weapon.BOOMERANG, ArchipelagoItemMap.getItem("Boomerang"));
    }

    @Test
    public void getsTreasureForArchipelagoItemName() {
        assertEquals(Treasure.UNDERWORLD_MAP_1, ArchipelagoItemMap.getItem("Underworld Map 1"));
    }

    @Test
    public void unknownItemNameReturnsNull() {
        assertNull(ArchipelagoItemMap.getItem("Unknown Item"));
    }
}
