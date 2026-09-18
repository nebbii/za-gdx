package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyPolsVoiceAnimation;

// TODO: Set actual original game accurate values
public class EnemyPolsVoice extends Enemy {
    public EnemyPolsVoiceAnimation animation;

    /*
    enemy.polsVoice:
        [health=280, damage=51, defense=31, bonusDamage=70. cast.json's weakToSpell field
        reported OpalAmulet, but the CD-i Interactief guide's List of Foes states Pols
        Voice's weakness is the Noise spell. No matching ZeldaAction* class exists yet
        for either, so returning empty until Noise is implemented.]
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
