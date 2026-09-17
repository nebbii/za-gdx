package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyWallSpikeAnimation;

/*
enemy.wallSpike:
    [health=99, damage=60, defense=99, weakness=Wand]
    NOTE: extractor reported 5 separate single-frame groups (group0..group4); treated as
    an immobile hazard like EnemyMalmord/EnemySpikedBlock (non-solid, standard SEARCH/FIGHT
    movement retained since the codebase has no dedicated "stationary" enemy behavior yet).
*/
public class EnemyWallSpike extends Enemy {
    public EnemyWallSpikeAnimation animation;

    public EnemyWallSpike() {
        super(ActorType.ENEMY, false);
        setWidth(20);
        setHeight(60);
        setHealth(99);
        setDamage(60);
        setDefense(99);

        this.animation = new EnemyWallSpikeAnimation(this);

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
        return Array.with("ZeldaActionWand");
    }
}
