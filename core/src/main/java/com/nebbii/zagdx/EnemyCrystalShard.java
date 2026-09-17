package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyCrystalShardAnimation;

// TODO: Set actual original game accurate values
public class EnemyCrystalShard extends Enemy {
    public EnemyCrystalShardAnimation animation;

    /*
    enemy.crystalShard:
        [health=40 (extractor reported 0, no matching descIndex in cast.json; fallback to a sane value for a small hazard), damage=54, defense=0, weakness=Wand]
    */
    public EnemyCrystalShard() {
        super(ActorType.ENEMY, false);
        setWidth(32);
        setHeight(32);
        // TODO: verify health, extractor value looked wrong (maxHealth was 0) and cast.json had no matching descIndex 1 entry; using a placeholder consistent with other small hazards
        setHealth(40);
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
        return Array.with("ZeldaActionWand");
    }
}
