package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyCrystalShardAnimation;

// TODO: Set actual original game accurate values
public class EnemyCrystalShard extends Enemy {
    public EnemyCrystalShardAnimation animation;

    /*
    enemy.crystalShard:
        [damage=54, defense=0]
        TODO: the CD-i Interactief guide's List of Foes states Crystal Shard (paired
        with a Shrine of Fire "Fireball" hazard not yet imported) is Immortal
        (Weakness: None) -- it should never die, spawning continuously from the room's
        walls. There is no isImmortal mechanic in the Enemy base class yet, so 9999
        health is a stopgap until one is added.
    */
    public EnemyCrystalShard() {
        super(ActorType.ENEMY, false);
        setWidth(32);
        setHeight(32);
        setHealth(9999);
        setDamage(54);
        setDefense(0);

        this.animation = new EnemyCrystalShardAnimation(this);

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
