package com.nebbii.zagdx;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.math.Rectangle;

class TestZeldaActionJadeRing extends ZeldaActionJadeRing {
    public TestZeldaActionJadeRing(int damage) {
        super(createZelda(damage), 0f, 0f);
    }

    private static Zelda createZelda(int damage) {
        Zelda zelda = mock(Zelda.class);

        Rectangle hitbox = new Rectangle(0f, 0f, 24f, 36f);

        when(zelda.getDamage()).thenReturn(damage);
        when(zelda.getDirection()).thenReturn(Direction.RIGHT);

        when(zelda.getHitbox()).thenReturn(hitbox);
        when(zelda.getCollisionBox()).thenReturn(hitbox);

        when(zelda.getCenterPointX()).thenReturn(12f);
        when(zelda.getCenterPointY()).thenReturn(18f);

        return zelda;
    }
}
