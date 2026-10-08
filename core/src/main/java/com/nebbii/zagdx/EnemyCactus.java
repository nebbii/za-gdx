package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyCactusAnimation;

// TODO: Set actual original game accurate values
public class EnemyCactus extends Enemy {
    public EnemyCactusAnimation animation;

    /*
    enemy.cactus:
        [health=60, damage=57, defense=10, weakness=Wand]
    */
    public EnemyCactus() {
        super(ActorType.ENEMY, true);
        setWidth(40);
        setHeight(37);
        setHealth(60);
        setDamage(57);
        setDefense(10);

        this.animation = new EnemyCactusAnimation(this);

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
