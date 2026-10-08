package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyAlligatorManAnimation;

// TODO: Set actual original game accurate values
public class EnemyAlligatorMan extends Enemy {
    public EnemyAlligatorManAnimation animation;

    /*
    enemy.alligatorMan:
        [health=60, damage=45, defense=10, weakness=Wand]
    */
    public EnemyAlligatorMan() {
        super(ActorType.ENEMY, true);
        setWidth(76);
        setHeight(58);
        setHealth(60);
        setDamage(45);
        setDefense(10);

        this.animation = new EnemyAlligatorManAnimation(this);

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
