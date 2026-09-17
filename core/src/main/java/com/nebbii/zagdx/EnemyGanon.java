package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyGanonAnimation;

// TODO: Set actual original game accurate values
public class EnemyGanon extends Enemy {
    public EnemyGanonAnimation animation;

    /*
    enemy.ganon:
        [health=60 (from cast.json, extractor reported 0), damage=82, defense=40, weakness=Wand]
    */
    public EnemyGanon() {
        super(ActorType.BOSS, true);
        setWidth(140);
        setHeight(110);
        // TODO: verify health, extractor value looked wrong (maxHealth was 0); using cast.json actor health=60 for gl8 desc0
        setHealth(60);
        setDamage(82);
        setDefense(40);

        this.animation = new EnemyGanonAnimation(this);

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
