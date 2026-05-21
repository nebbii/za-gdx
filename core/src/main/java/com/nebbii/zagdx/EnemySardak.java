package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// TODO: Set actual original game accurate values
public class EnemySardak extends Enemy {
    /*
    enemy.sardak.red:
        [health=280, damage=50, defense=30, weakness=JadeRing, bonusDamage=70]
    enemy.sardak.blue:
        [health=80, damage=50, defense=30, weakness=JadeRing, bonusDamage=70]
    enemy.sardak.yellow:
        [health=280, damage=50, defense=30, weakness=JadeRing, bonusDamage=70]
    */
    public EnemySardak() {
        super(ActorType.ENEMY, true);
        setWidth(48);
        setHeight(40);

        this.enemyState = EnemyState.SEARCH;
    }

    @Override
    public void logic() {
        super.logic();

        switch(enemyState) {
            case SEARCH:
                setSpeed(70f);
                break;
            case FIGHT:
                setSpeed(90f);
                break;
            default:
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        /*
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
        */
    }
}
