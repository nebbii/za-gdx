package com.nebbii.zagdx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class SpawnerPickupTest {
    @Test
    public void spawnerPickupCreatesPickupWithParent() {
        MapManager map = mock(MapManager.class);
        SpawnerPickup spawner = activeSpawner(new SpawnerPickup(), map);
        spawner.setPickupType("Pickup");
        spawner.setPosition(12f, 34f);

        spawner.activate();

        Pickup pickup = captureSpawnedPickup(map, spawner);

        assertEquals(Pickup.class, pickup.getClass());
        assertEquals(12f, pickup.getX());
        assertEquals(34f, pickup.getY());
        assertEquals(State.DEAD, spawner.getState());
    }

    @Test
    public void spawnerPickupRequiresPickupType() {
        SpawnerPickup spawner = activeSpawner(new SpawnerPickup(), mock(MapManager.class));

        assertThrows(IllegalStateException.class, spawner::activate);
    }

    @Test
    public void spawnerPickupRejectsNonPickupTypes() {
        SpawnerPickup spawner = activeSpawner(new SpawnerPickup(), mock(MapManager.class));
        spawner.setPickupType("Spawner");

        RuntimeException exception = assertThrows(RuntimeException.class, spawner::activate);

        assertNotNull(exception.getCause());
    }

    @Test
    public void spawnerPickupDefaultsToManualTrigger() {
        SpawnerPickup spawner = new SpawnerPickup();

        assertEquals(SpawnerPickup.Trigger.MANUAL, spawner.getTrigger());
    }

    @Test
    public void spawnerPickupManualTriggerWaitsForActivation() {
        MapManager map = mock(MapManager.class);
        SpawnerPickup spawner = activeSpawner(new SpawnerPickup(), map);
        spawner.setPickupType("Pickup");

        spawner.logic();

        verify(map, never()).addNewPickupWithParent(org.mockito.ArgumentMatchers.any(Pickup.class), same(spawner));
        assertEquals(State.ACTIVE, spawner.getState());
    }

    @Test
    public void spawnerPickupUsesPickupAfterNoEnemiesTriggerCondition() {
        MapManager map = mock(MapManager.class);
        when(map.activeActorsContain(Enemy.class)).thenReturn(false);

        SpawnerPickup spawner = activeSpawner(new SpawnerPickup(), map);
        spawner.setPickupType("Pickup");
        spawner.setTrigger("NO_ENEMIES");
        spawner.setPosition(56f, 78f);

        spawner.logic();

        Pickup pickup = captureSpawnedPickup(map, spawner);

        assertEquals(Pickup.class, pickup.getClass());
        assertEquals(56f, pickup.getX());
        assertEquals(78f, pickup.getY());
        assertEquals(State.DEAD, spawner.getState());
    }

    @Test
    public void spawnerPickupWaitsForNoEnemiesTriggerCondition() {
        MapManager map = mock(MapManager.class);
        when(map.activeActorsContain(Enemy.class)).thenReturn(true);

        SpawnerPickup spawner = activeSpawner(new SpawnerPickup(), map);
        spawner.setPickupType("Pickup");
        spawner.setTrigger("NO_ENEMIES");

        spawner.logic();

        verify(map, never()).addNewPickupWithParent(org.mockito.ArgumentMatchers.any(Pickup.class), same(spawner));
        assertEquals(State.ACTIVE, spawner.getState());
    }

    private <T extends Spawner> T activeSpawner(T spawner, MapManager map) {
        spawner.setMap(map);
        spawner.setState(State.ACTIVE);
        return spawner;
    }

    private Pickup captureSpawnedPickup(MapManager map, Spawner spawner) {
        ArgumentCaptor<Pickup> pickupCaptor = ArgumentCaptor.forClass(Pickup.class);
        verify(map).addNewPickupWithParent(pickupCaptor.capture(), same(spawner));
        return pickupCaptor.getValue();
    }
}
