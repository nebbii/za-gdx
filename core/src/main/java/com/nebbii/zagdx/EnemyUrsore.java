package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyUrsoreAnimation;

/*
enemy.ursore:
    [health=0 (extractor/cast.json description), damage=80, defense=34, weakness=Wand]
    NOTE: cast.json reports maxHealth_maybe=0 for this description, but the spawned
    instance at underworld:s628:0 (matching descIndex 0/size 128x83) has a real
    health=130 - using that cross-checked value instead of the placeholder 0.
*/
// TODO: Set actual original game accurate values
public class EnemyUrsore extends Enemy {
    public EnemyUrsoreAnimation animation;

    public EnemyUrsore() {
        super(ActorType.ENEMY, true);
        setWidth(128);
        setHeight(83);
        setHealth(130);
        setDamage(80);
        setDefense(34);

        this.animation = new EnemyUrsoreAnimation(this);

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
