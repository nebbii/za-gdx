package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.EnemyLeeverAnimation;

public class EnemyLeever extends Enemy {
    public EnemyLeeverAnimation animation;

    /*
      enemy.leever:
        [health=60, damage=40, defense=0] on cells h27, h28
    */
    public EnemyLeever() {
        super(ActorType.ENEMY, true);
        setWidth(28);
        setHeight(32);
        setHealth(60);
        setDamage(40);
        setDefense(0);

        this.animation = new EnemyLeeverAnimation(this);

        this.enemyState = EnemyState.SEARCH;
    }

    @Override
    public void logic() {
        super.logic();

        switch(enemyState) {
            case SEARCH:
                setSpeed(80f);
                break;
            case FIGHT:
                setSpeed(110f);
                break;
            default:
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
    }
}
