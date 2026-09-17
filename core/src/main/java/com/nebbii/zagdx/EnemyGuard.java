package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyGuardAnimation;

// TODO: Set actual original game accurate values
public class EnemyGuard extends Enemy {
    public EnemyGuardAnimation animation;

    /*
    enemy.guard:
        [health=? damage=? defense=? all reported 0 by extractor and confirmed 0 in cast.json for both cells - likely a non-aggressive/placeholder guard; using placeholder stats]
    */
    public EnemyGuard() {
        super(ActorType.ENEMY, true);
        setWidth(92);
        setHeight(75);
        // TODO: stats reported 0/0/0 (health/defense/damage) in both known cells (overworld:p8:0 and overworld:z17:0); using placeholder values consistent with similar mid-tier overworld enemies
        setHealth(60);
        setDamage(55);
        setDefense(25);

        this.animation = new EnemyGuardAnimation(this);

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
