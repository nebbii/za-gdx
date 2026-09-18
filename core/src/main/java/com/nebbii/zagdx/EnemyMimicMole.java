package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyMimicMoleAnimation;

// TODO: Set actual original game accurate values
public class EnemyMimicMole extends Enemy {
    public EnemyMimicMoleAnimation animation;

    /*
    enemy.mimicMole:
        [health=60, damage=45, defense=5, weakness=Wand]
    */
    public EnemyMimicMole() {
        super(ActorType.ENEMY, true);
        setWidth(64);
        setHeight(54);
        setHealth(60);
        setDamage(45);
        setDefense(5);

        this.animation = new EnemyMimicMoleAnimation(this);

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
