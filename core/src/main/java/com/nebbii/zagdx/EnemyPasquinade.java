package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyPasquinadeAnimation;

// TODO: Set actual original game accurate values
public class EnemyPasquinade extends Enemy {
    public EnemyPasquinadeAnimation animation;

    /*
    enemy.pasquinade:
        [health=70, damage=55, defense=24, weakness=Wand]
    */
    public EnemyPasquinade() {
        super(ActorType.ENEMY, true);
        setWidth(160);
        setHeight(125);
        setHealth(70);
        setDamage(55);
        setDefense(24);

        this.animation = new EnemyPasquinadeAnimation(this);

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
