package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemySwampZolaGreenAnimation;

// TODO: Set actual original game accurate values
// TODO: treated as an aquatic/amphibious enemy (non-solid), matching the convention used
// for other water-dwelling enemies such as EnemyPurpleFish and EnemyTurtle
public class EnemySwampZolaGreen extends Enemy {
    public EnemySwampZolaGreenAnimation animation;

    public EnemySwampZolaGreen() {
        super(ActorType.ENEMY, false);
        setWidth(88);
        setHeight(71);
        setHealth(60);
        setDamage(50);
        setDefense(20);

        this.animation = new EnemySwampZolaGreenAnimation(this);

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
