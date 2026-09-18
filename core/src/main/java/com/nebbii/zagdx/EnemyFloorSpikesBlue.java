package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyFloorSpikesBlueAnimation;

// TODO: Set actual original game accurate values
public class EnemyFloorSpikesBlue extends Enemy {
    public EnemyFloorSpikesBlueAnimation animation;

    /*
    enemy.floorSpikes.blue:
        [health=99, damage=58, defense=99 (extractor reported 0 for both; matched to sibling EnemyFloorSpikes/EnemyFloorSpikesWhite family values, since cast.json also reported health=0 for this immobile hazard)]
    */
    public EnemyFloorSpikesBlue() {
        super(ActorType.ENEMY, false);
        setWidth(40);
        setHeight(40);
        // TODO: verify health/defense, extractor (and cast.json) reported 0; using sibling EnemyFloorSpikes/EnemyFloorSpikesWhite values (99/99) for consistency
        setHealth(99);
        setDamage(58);
        setDefense(99);

        this.animation = new EnemyFloorSpikesBlueAnimation(this);

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
