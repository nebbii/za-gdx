package com.nebbii.zagdx;

public class ZeldaAction extends Projectile {
    protected Zelda zelda;

    public ZeldaAction(Actor actor, float x, float y) {
        super(actor, x, y);
        this.zelda = (Zelda) actor;
    }
}
