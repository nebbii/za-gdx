package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyTinyFishAnimation;

/*
enemy.tinyFish:
    [health=0, damage=55, defense=0, weakness=Wand]
    NOTE: extractor and cast.json both report maxHealth=0 for this actor (overworld:p14:1)
    and no spawned instance in cast.json uses this descIndex to cross-check a real value.
    Falling back to a small placeholder health value - verify against the original game.
*/
// TODO: Set actual original game accurate health value
public class EnemyTinyFish extends Enemy {
    public EnemyTinyFishAnimation animation;

    public EnemyTinyFish() {
        super(ActorType.ENEMY, false);
        setWidth(16);
        setHeight(16);
        setHealth(20);
        setDamage(55);
        setDefense(0);

        this.animation = new EnemyTinyFishAnimation(this);

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
