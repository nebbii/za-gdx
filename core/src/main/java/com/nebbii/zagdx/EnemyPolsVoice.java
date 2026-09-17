package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyPolsVoiceAnimation;

// TODO: Set actual original game accurate values
// TODO: extractor reports weakness "OpalAmulet" for this actor, but no matching
// ZeldaAction* class exists in this codebase yet - returning no weaknesses until one is added
public class EnemyPolsVoice extends Enemy {
    public EnemyPolsVoiceAnimation animation;

    /*
    enemy.polsVoice:
        [health=280, damage=51, defense=31, bonusDamage=70, weakness=OpalAmulet (unmapped)]
    */
    public EnemyPolsVoice() {
        super(ActorType.ENEMY, true);
        setWidth(60);
        setHeight(60);
        setHealth(280);
        setDamage(51);
        setDefense(31);
        setBonusDamage(70);

        this.animation = new EnemyPolsVoiceAnimation(this);

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
        return new Array<String>();
    }
}
