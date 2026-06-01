package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.EnemyTektiteAnimation;

// TODO: Set actual original game accurate values
public class EnemyTektite extends Enemy {
    public EnemyTektiteAnimation animation;

    /*
      enemy.tektite:
        [health=60, damage=51, defense=5] on cells j16, k15, k16, l16, m14, m15, r5, r8, v10, v12, v9, x9
        [health=60, damage=40, defense=0] on cells q10, s101a, s103, s104, s116
    */
    public EnemyTektite() {
        super(ActorType.ENEMY, true);
        setHeight(26);
        setHealth(60);
        setDamage(40);
        setDefense(0);

        this.animation = new EnemyTektiteAnimation(this);

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
}
