package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyMaxusAnimation;

// TODO: Set actual original game accurate values
public class EnemyMaxus extends Enemy {
    public EnemyMaxusAnimation animation;

    /*
    This class is generated from the cast.json commonName "enemy.archer" at
    underworld:s722, a generic behavioral label rather than a real name. That cell sits
    directly between two enemy.warbane cells (s721, s723), and the CD-i Interactief
    guide describes a bow-wielding miniboss named Maxus guarding Warbane's chamber
    right before it in the Shrine of Fire -- renamed to EnemyMaxus accordingly.

    [health=280, damage=72, defense=40, bonusDamage=70. cast.json's weakToSpell field
    reported Hammer (no matching class either way), but the guide's List of Foes states
    Maxus's weakness is a Gold Necklace. No ZeldaActionGoldNecklace class exists yet in
    this codebase, so returning empty until that item is implemented.]
    */
    public EnemyMaxus() {
        super(ActorType.ENEMY, true);
        setWidth(60);
        setHeight(53);
        setHealth(280);
        setDamage(72);
        setDefense(40);
        setBonusDamage(70);

        this.animation = new EnemyMaxusAnimation(this);

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
