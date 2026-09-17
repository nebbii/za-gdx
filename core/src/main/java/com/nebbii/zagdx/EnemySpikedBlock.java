package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemySpikedBlockAnimation;

/*
enemy.spikedBlock:
    [health=99, damage=58, defense=99, weakness=Wand]
    NOTE: single static sprite frame; treated as an immobile hazard like EnemyMalmord
    (non-solid, standard SEARCH/FIGHT movement retained since the codebase has no
    dedicated "stationary" enemy behavior yet).
*/
// TODO: Set actual original game accurate values
public class EnemySpikedBlock extends Enemy {
    public EnemySpikedBlockAnimation animation;

    public EnemySpikedBlock() {
        super(ActorType.ENEMY, false);
        setWidth(32);
        setHeight(32);
        setHealth(99);
        setDamage(58);
        setDefense(99);

        this.animation = new EnemySpikedBlockAnimation(this);

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
