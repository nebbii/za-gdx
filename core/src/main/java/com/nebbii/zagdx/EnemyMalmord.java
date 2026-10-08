package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyMalmordAnimation;

// TODO: Set actual original game accurate values
public class EnemyMalmord extends Enemy {
    public EnemyMalmordAnimation animation;

    /*
    enemy.malmord:
        [health=1, damage=80, defense=0, weakness=Wand]
        NOTE: extractor/cast.json both report health=1 for this actor (animationType "Immobile"
        at underworld:gl4:0). Left as-is since it is not zero, but this is a suspiciously low
        value for an "Enemy" kind actor - verify against original game before trusting it.
    */
    public EnemyMalmord() {
        super(ActorType.ENEMY, false);
        setWidth(136);
        setHeight(95);
        setHealth(1);
        setDamage(80);
        setDefense(0);

        this.animation = new EnemyMalmordAnimation(this);

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
