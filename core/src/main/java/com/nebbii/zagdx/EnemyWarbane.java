package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyWarbaneAnimation;

/*
enemy.warbane:
    [damage=86, defense=34, weakness=Wand]
    NOTE: extractor reported health=0, but cast.json reports health=50 for descIndex 0
    at underworld:gl7:0; using 50.
*/
public class EnemyWarbane extends Enemy {
    public EnemyWarbaneAnimation animation;

    public EnemyWarbane() {
        super(ActorType.ENEMY, true);
        setWidth(140);
        setHeight(93);
        setHealth(50);
        setDamage(86);
        setDefense(34);

        this.animation = new EnemyWarbaneAnimation(this);

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
