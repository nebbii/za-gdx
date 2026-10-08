package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyLavaLizardAnimation;

// TODO: Set actual original game accurate values
public class EnemyLavaLizard extends Enemy {
    public EnemyLavaLizardAnimation animation;

    /*
    enemy.lavaLizard:
        [health=60, damage=81, defense=36, weakness=Wand]
    */
    public EnemyLavaLizard() {
        super(ActorType.ENEMY, true);
        setWidth(100);
        setHeight(63);
        setHealth(60);
        setDamage(81);
        setDefense(36);

        this.animation = new EnemyLavaLizardAnimation(this);

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
