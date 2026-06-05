package com.nebbii.zagdx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.utils.Array;

import org.junit.jupiter.api.Test;

public class DamageCalculatorTest {
    @Test
    public void defenseReducesDamage() {
        GameManager gameManager = new GameManager(null);

        Actor attacker = mock(Actor.class);
        Actor defender = mock(Actor.class);

        when(attacker.getDamage()).thenReturn(10);
        when(defender.getDefense()).thenReturn(5);
        when(defender.getWeaknesses()).thenReturn(new Array<>());
        when(defender.getBonusDamage()).thenReturn(0);

        int damage = gameManager.calculateDamage(attacker, defender);

        assertEquals(5, damage);
    }

    @Test
    public void damageCannotGoBelowZero() {
        GameManager gameManager = new GameManager(null);

        Actor attacker = mock(Actor.class);
        Actor defender = mock(Actor.class);

        when(attacker.getDamage()).thenReturn(5);
        when(defender.getDefense()).thenReturn(10);
        when(defender.getWeaknesses()).thenReturn(new Array<>());
        when(defender.getBonusDamage()).thenReturn(0);

        int damage = gameManager.calculateDamage(attacker, defender);

        assertEquals(0, damage);
    }

    @Test
    public void matchingWeaknessAddsBonusDamage() {
        GameManager gameManager = new GameManager(null);

        Actor attacker = new TestWeapon();
        // weaknesses only run on enemies
        Enemy defender = mock(Enemy.class);

        when(defender.getDefense()).thenReturn(5);
        when(defender.getWeaknesses()).thenReturn(Array.with("TestWeapon"));
        when(defender.getBonusDamage()).thenReturn(20);

        int damage = gameManager.calculateDamage(attacker, defender);

        assertEquals(25, damage);
    }

    @Test
    public void nonMatchingWeaknessDoesNotAddBonusDamage() {
        GameManager gameManager = new GameManager(null);

        Actor attacker = new TestWeapon();
        // weaknesses only run on enemies
        Enemy defender = mock(Enemy.class);

        when(defender.getDefense()).thenReturn(5);
        when(defender.getWeaknesses()).thenReturn(Array.with("SomeOtherWeapon"));
        when(defender.getBonusDamage()).thenReturn(20);

        int damage = gameManager.calculateDamage(attacker, defender);

        assertEquals(5, damage);
    }

    @Test
    public void goriyaDiesInThreeHitsFromWandProjectile() {
        GameManager gameManager = new GameManager(null);

        Actor wandProjectile = new TestWandProjectile(30);
        Enemy goriya = new TestGoriya();

        int damage1 = gameManager.calculateDamage(wandProjectile, goriya);
        goriya.onHit(damage1, 0f);

        assertFalse(goriya.getHealth() <= 0, "Goriya should survive hit 1. Health: " + goriya.getHealth());

        int damage2 = gameManager.calculateDamage(wandProjectile, goriya);
        goriya.onHit(damage2, 0f);

        assertFalse(goriya.getHealth() <= 0, "Goriya should survive hit 2. Health: " + goriya.getHealth());

        int damage3 = gameManager.calculateDamage(wandProjectile, goriya);
        goriya.onHit(damage3, 0f);

        assertTrue(goriya.getHealth() <= 0, "Goriya should be killed by hit 3. Health: " + goriya.getHealth());
    }

    @Test
    public void sardakRedTakesNoDamageFromBaseWandProjectileBecauseOfDefense() {
        GameManager gameManager = new GameManager(null);

        Actor wandProjectile = new TestWandProjectile(30);
        Enemy sardak = new TestSardakRed();

        int damage = gameManager.calculateDamage(wandProjectile, sardak);

        assertEquals(0, damage);

        sardak.onHit(damage, 0f);

        assertEquals(280, sardak.getHealth());
        assertFalse(sardak.isDead());
    }

    @Test
    public void sardakRedCanBeKilledByJadeRing() {
        GameManager gameManager = new GameManager(null);

        Actor jadeRing = new TestZeldaActionJadeRing(30);
        Enemy sardak = new TestSardakRed();

        int damage = gameManager.calculateDamage(jadeRing, sardak);

        assertEquals(70, damage);

        sardak.onHit(damage, 0f);
        assertFalse(sardak.getHealth() <= 0, "Sardak should survive hit 1. Health: " + sardak.getHealth());

        sardak.onHit(damage, 0f);
        assertFalse(sardak.getHealth() <= 0, "Sardak should survive hit 2. Health: " + sardak.getHealth());

        sardak.onHit(damage, 0f);
        assertFalse(sardak.getHealth() <= 0, "Sardak should survive hit 3. Health: " + sardak.getHealth());

        sardak.onHit(damage, 0f);
        assertTrue(sardak.getHealth() <= 0, "Sardak should be killed by hit 4. Health: " + sardak.getHealth());
    }
}
