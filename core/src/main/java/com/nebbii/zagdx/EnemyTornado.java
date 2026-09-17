package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyTornadoAnimation;

/*
enemy.tornado:
    [health=280, damage=52, defense=32, bonusDamage=72, weakness reported as Feather by
    extractor, but no ZeldaActionFeather class exists yet in the codebase; leaving
    weaknesses empty until that spell is implemented]
    NOTE: treated as an immobile hazard like EnemyMalmord (non-solid, standard
    SEARCH/FIGHT movement retained since the codebase has no dedicated "stationary"
    enemy behavior yet).
*/
// TODO: Set actual original game accurate values
public class EnemyTornado extends Enemy {
    public EnemyTornadoAnimation animation;

    public EnemyTornado() {
        super(ActorType.ENEMY, false);
        setWidth(32);
        setHeight(30);
        setHealth(280);
        setDamage(52);
        setDefense(32);
        setBonusDamage(72);

        this.animation = new EnemyTornadoAnimation(this);

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

    @Override
    public Array<String> getWeaknesses() {
        return new Array<String>();
    }
}
