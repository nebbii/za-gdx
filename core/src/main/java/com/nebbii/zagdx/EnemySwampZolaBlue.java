package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemySwampZolaBlueAnimation;

// TODO: Set actual original game accurate values
// TODO: treated as an aquatic/amphibious enemy (non-solid), matching the convention used
// for other water-dwelling enemies such as EnemyPurpleFish and EnemyTurtle
public class EnemySwampZolaBlue extends Enemy {
    public EnemySwampZolaBlueAnimation animation;

    public EnemySwampZolaBlue() {
        super(ActorType.ENEMY, false);
        setWidth(100);
        setHeight(75);
        setHealth(60);
        setDamage(62);
        setDefense(30);

        this.animation = new EnemySwampZolaBlueAnimation(this);

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
