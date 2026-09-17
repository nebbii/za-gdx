package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyWallmasterAnimation;

/*
enemy.wallmaster:
    NOTE: extractor/cast.json both report health=0 and damage=0 for this actor
    (animationType "UnknownType1" at underworld:s222:0, a 3-phase setGroup(0)->setGroup(1)->
    setGroup(2) sequence resembling appear/grab/retreat rather than 4-way walking).
    Left as a suspiciously low/zero value case similar to EnemyMalmord; using sane
    placeholder combat stats below - verify against the original game before trusting them.
*/
// TODO: Set actual original game accurate values
public class EnemyWallmaster extends Enemy {
    public EnemyWallmasterAnimation animation;

    public EnemyWallmaster() {
        super(ActorType.ENEMY, false);
        setWidth(108);
        setHeight(72);
        setHealth(40);
        setDamage(30);
        setDefense(10);

        this.animation = new EnemyWallmasterAnimation(this);

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
