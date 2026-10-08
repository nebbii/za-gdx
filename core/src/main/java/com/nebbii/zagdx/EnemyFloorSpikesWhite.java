package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyFloorSpikesWhiteAnimation;

// TODO: Set actual original game accurate values
public class EnemyFloorSpikesWhite extends Enemy {
    public EnemyFloorSpikesWhiteAnimation animation;

    /*
    enemy.floorSpikes.white:
        [health=99, damage=58, defense=99, weakness=Wand]
    */
    public EnemyFloorSpikesWhite() {
        super(ActorType.ENEMY, false);
        setWidth(40);
        setHeight(40);
        setHealth(99);
        setDamage(58);
        setDefense(99);

        this.animation = new EnemyFloorSpikesWhiteAnimation(this);

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
