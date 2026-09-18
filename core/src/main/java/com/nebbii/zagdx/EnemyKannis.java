package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyKannisAnimation;

// TODO: Set actual original game accurate values
public class EnemyKannis extends Enemy {
    public EnemyKannisAnimation animation;

    /*
    enemy.kannis:
        [health=8 (from cast.json, extractor reported 0), damage=96, defense=40, weakness=Wand]
    */
    public EnemyKannis() {
        super(ActorType.ENEMY, true);
        setWidth(104);
        setHeight(85);
        // TODO: verify health, extractor value looked wrong (maxHealth was 0); using cast.json actor health=8, consistent across all 3 known cells (s716/s717/s718 desc0)
        setHealth(8);
        setDamage(96);
        setDefense(40);

        this.animation = new EnemyKannisAnimation(this);

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
