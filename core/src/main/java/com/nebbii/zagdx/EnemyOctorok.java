package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyOctorokAnimation;

// TODO: Set actual original game accurate values
public class EnemyOctorok extends Enemy {
    public EnemyOctorokAnimation animation;

    /*
    enemy.octorok:
        [health=60, damage=61, defense=23, weakness=Wand]
    */
    public EnemyOctorok() {
        super(ActorType.ENEMY, false);
        setWidth(144);
        setHeight(70);
        setHealth(60);
        setDamage(61);
        setDefense(23);

        this.animation = new EnemyOctorokAnimation(this);

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
