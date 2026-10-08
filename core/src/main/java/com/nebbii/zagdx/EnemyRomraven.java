package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyRomravenAnimation;

// TODO: Set actual original game accurate values
public class EnemyRomraven extends Enemy {
    public EnemyRomravenAnimation animation;

    /*
    enemy.romraven:
        [health=30 (from cast.json, extractor reported 0), damage=50, defense=15, weakness=Wand]
    */
    public EnemyRomraven() {
        super(ActorType.ENEMY, false);
        setWidth(60);
        setHeight(46);
        // TODO: verify health, extractor value looked wrong (maxHealth was 0); using
        // cast.json actor health=30 for s418/s710/s715 desc0 (consistent across samples)
        setHealth(30);
        setDamage(50);
        setDefense(15);

        this.animation = new EnemyRomravenAnimation(this);

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
