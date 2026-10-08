package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyBollaAnimation;

// TODO: Set actual original game accurate values
public class EnemyBolla extends Enemy {
    public EnemyBollaAnimation animation;

    /*
    enemy.bolla:
        [health=60, damage=81, defense=30, weakness=Wand]
    */
    public EnemyBolla() {
        super(ActorType.ENEMY, true);
        setWidth(64);
        setHeight(58);
        setHealth(60);
        setDamage(81);
        setDefense(30);

        this.animation = new EnemyBollaAnimation(this);

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
