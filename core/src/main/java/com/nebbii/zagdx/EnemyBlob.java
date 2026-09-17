package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyBlobAnimation;

// TODO: Set actual original game accurate values
public class EnemyBlob extends Enemy {
    public EnemyBlobAnimation animation;

    /*
    enemy.blob:
        [health=60 (from cast.json, extractor reported 0), damage=0, defense=0, weakness=Wand]
    */
    public EnemyBlob() {
        super(ActorType.ENEMY, true);
        setWidth(40);
        setHeight(42);
        // TODO: verify health, extractor value looked wrong (maxHealth was 0); using cast.json health=60
        setHealth(60);
        setDamage(0);
        setDefense(0);

        this.animation = new EnemyBlobAnimation(this);

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
