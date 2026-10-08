package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyPurpleBirdAnimation;

// TODO: Set actual original game accurate values
public class EnemyPurpleBird extends Enemy {
    public EnemyPurpleBirdAnimation animation;

    /*
    enemy.purpleBird:
        [health=60, damage=52, defense=20, weakness=Wand]
    */
    public EnemyPurpleBird() {
        super(ActorType.ENEMY, false);
        setWidth(80);
        setHeight(54);
        setHealth(60);
        setDamage(52);
        setDefense(20);

        this.animation = new EnemyPurpleBirdAnimation(this);

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
