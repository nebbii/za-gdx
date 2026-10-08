package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyKnightRedAnimation;

// TODO: Set actual original game accurate values
public class EnemyKnightRed extends Enemy {
    public EnemyKnightRedAnimation animation;

    /*
    enemy.knight.red:
        [cast.json's weakToSpell field reported JadeRing, but the CD-i Interactief guide
        explicitly states the Red Knight "must use the jousting stick found in the
        seventh region" -- JadeRing is actually the guide-confirmed weakness for the
        unrelated Sardak enemies, suggesting the extractor's data got shuffled between
        enemies. No ZeldaActionJoustingStick-equivalent class exists yet in this
        codebase, so returning empty until that item is implemented, rather than
        shipping the extractor's wrong value.]
    */
    public EnemyKnightRed() {
        super(ActorType.ENEMY, true);
        setWidth(116);
        setHeight(102);
        setHealth(280);
        setDamage(73);
        setDefense(35);
        setBonusDamage(70);

        this.animation = new EnemyKnightRedAnimation(this);

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
        return new Array<String>();
    }
}
