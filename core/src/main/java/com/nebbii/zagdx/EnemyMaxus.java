package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyArcherAnimation;

// TODO: Set actual original game accurate values
public class EnemyArcher extends Enemy {
    public EnemyArcherAnimation animation;

    /*
    enemy.archer:
        [health=280, damage=72, defense=40, bonusDamage=70, weakness=none (weakToSpell_raw=Hammer, not mapped)]
    */
    public EnemyArcher() {
        super(ActorType.ENEMY, true);
        setWidth(60);
        setHeight(53);
        setHealth(280);
        setDamage(72);
        setDefense(40);
        setBonusDamage(70);

        this.animation = new EnemyArcherAnimation(this);

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
