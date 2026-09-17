package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyGanonFairyAnimation;

// TODO: Set actual original game accurate values
public class EnemyGanonFairy extends Enemy {
    public EnemyGanonFairyAnimation animation;

    /*
    enemy.ganonFairy:
        [health=50 (from cast.json, extractor reported 0), damage=0, defense=0, weakness=Wand]
    */
    public EnemyGanonFairy() {
        super(ActorType.ENEMY, false);
        setWidth(52);
        setHeight(52);
        // TODO: verify health, extractor value looked wrong (maxHealth was 0 in 6/7 cells); using cast.json actor health=50 seen at underworld:gl7:2. damage/defense are consistently 0 across all cells, kept as-is (fairy companion, likely non-aggressive)
        setHealth(50);
        setDamage(0);
        setDefense(0);

        this.animation = new EnemyGanonFairyAnimation(this);

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
