package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyAgwandaAnimation;

// TODO: Set actual original game accurate values
public class EnemyAgwanda extends Enemy {
    public EnemyAgwandaAnimation animation;

    /*
    enemy.agwanda:
        [health=50 (from cast.json, extractor reported 0), damage=86, defense=30, weakness=Wand]
    */
    public EnemyAgwanda() {
        super(ActorType.ENEMY, true);
        setWidth(112);
        setHeight(112);
        // TODO: verify health, extractor value looked wrong (maxHealth was 0); using cast.json health=50
        setHealth(50);
        setDamage(86);
        setDefense(30);

        this.animation = new EnemyAgwandaAnimation(this);

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
