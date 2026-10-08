package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyBagoBagoAnimation;

// TODO: Set actual original game accurate values
public class EnemyBagoBago extends Enemy {
    public EnemyBagoBagoAnimation animation;

    /*
    enemy.bagoBago:
        [health=60, damage=50, defense=10, weakness=Wand]
    */
    public EnemyBagoBago() {
        super(ActorType.ENEMY, true);
        setWidth(52);
        setHeight(46);
        setHealth(60);
        setDamage(50);
        setDefense(10);

        this.animation = new EnemyBagoBagoAnimation(this);

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
