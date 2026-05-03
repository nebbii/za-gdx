package com.nebbii.zagdx;

public class EnemyAction extends Projectile {
    protected Enemy enemy;

    public EnemyAction(Actor actor, float x, float y) {
        super(actor, x, y);
        this.enemy = (Enemy) actor;
    }
}
