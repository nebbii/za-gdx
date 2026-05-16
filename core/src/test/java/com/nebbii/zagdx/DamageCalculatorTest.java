package com.nebbii.zagdx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.Test;

public class DamageCalculatorTest {
    @Test
    public void defenseReducesDamage() {
        GameManager gameManager = new GameManager(null);

        Actor attacker = mock(Actor.class);
        Actor defender = mock(Actor.class);

        when(attacker.getDamage()).thenReturn(10);
        when(defender.getDefense()).thenReturn(5);
        when(defender.getWeaknesses()).thenReturn(new String[0]);
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
        when(defender.getWeaknesses()).thenReturn(new String[0]);
        when(defender.getBonusDamage()).thenReturn(0);

        int damage = gameManager.calculateDamage(attacker, defender);

        assertEquals(0, damage);
    }

    @Test
    public void matchingWeaknessAddsBonusDamage() {
        GameManager gameManager = new GameManager(null);

        Actor attacker = new TestWeapon();
        Actor defender = mock(Actor.class);

        when(defender.getDefense()).thenReturn(5);
        when(defender.getWeaknesses()).thenReturn(new String[] { "TestWeapon" });
        when(defender.getBonusDamage()).thenReturn(20);

        int damage = gameManager.calculateDamage(attacker, defender);

        assertEquals(25, damage);
    }

    @Test
    public void nonMatchingWeaknessDoesNotAddBonusDamage() {
        GameManager gameManager = new GameManager(null);

        Actor attacker = new TestWeapon();
        Actor defender = mock(Actor.class);

        when(defender.getDefense()).thenReturn(5);
        when(defender.getWeaknesses()).thenReturn(new String[] { "SomeOtherWeapon" });
        when(defender.getBonusDamage()).thenReturn(20);

        int damage = gameManager.calculateDamage(attacker, defender);

        assertEquals(5, damage);
    }

    private static class TestWeapon implements Actor {
        @Override
        public int getDamage() {
            return 10;
        }

        @Override
        public String[] getWeaknesses() {
            return new String[0];
        }

        @Override
        public int getBonusDamage() {
            return 0;
        }

        @Override
        public int getDefense() {
            return 0;
        }

        @Override
        public void logic() {
        }

        @Override
        public void draw(SpriteBatch batch) {
        }

        @Override
        public int getDrawOrder() {
            return 0;
        }

        @Override
        public Rectangle getCollisionBox() {
            return null;
        }

        @Override
        public Rectangle getHitbox() {
            return null;
        }

        @Override
        public float getCenterPointX() {
            return 0;
        }

        @Override
        public float getCenterPointY() {
            return 0;
        }

        @Override
        public State getState() {
            return null;
        }

        @Override
        public void setState(State state) {
        }

        @Override
        public Direction getDirection() {
            return null;
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public boolean isDead() {
            return false;
        }

        @Override
        public ActorType getType() {
            return null;
        }

        @Override
        public void setType(ActorType type) {
        }

        @Override
        public boolean isSolid() {
            return false;
        }

        @Override
        public MapManager getMap() {
            return null;
        }

        @Override
        public void setMap(MapManager map) {
        }

        @Override
        public String getLocationEntry() {
            return null;
        }

        @Override
        public void setLocationEntry(String locationEntry) {
        }
    }
}
