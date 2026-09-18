package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyPurpleFishAnimation;

// TODO: Set actual original game accurate values
public class EnemyPurpleFish extends Enemy {
    public EnemyPurpleFishAnimation animation;

    /*
    enemy.purpleFish:
        [health=60, damage=62, defense=35, weakness=Wand]
    */
    public EnemyPurpleFish() {
        super(ActorType.ENEMY, false);
        setWidth(80);
        setHeight(44);
        setHealth(60);
        setDamage(62);
        setDefense(35);

        this.animation = new EnemyPurpleFishAnimation(this);

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
