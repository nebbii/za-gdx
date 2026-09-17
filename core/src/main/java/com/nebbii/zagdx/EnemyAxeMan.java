package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyAxeManAnimation;

// TODO: Set actual original game accurate values
public class EnemyAxeMan extends Enemy {
    public EnemyAxeManAnimation animation;

    /*
    enemy.axeMan:
        [health=60, damage=67, defense=36, weakness=Wand]
    */
    public EnemyAxeMan() {
        super(ActorType.ENEMY, true);
        setWidth(84);
        setHeight(65);
        setHealth(60);
        setDamage(67);
        setDefense(36);

        this.animation = new EnemyAxeManAnimation(this);

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
