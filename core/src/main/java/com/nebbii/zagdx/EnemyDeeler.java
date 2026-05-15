package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.EnemyDeelerAnimation;

// TODO: Set actual original game accurate values
public class EnemyDeeler extends Enemy {
    public EnemyDeelerAnimation animation;


    /*
      enemy.deeler has different stats on different cells:
      [damage=57, defense=20, health=20, loot=Random] on cells b8, e9, h10, i10
      [damage=40, health=0, loot=Random] on cells f7, g29, g30, g31, h10, j10, m10, m13, n13, o12
    */
    public EnemyDeeler() {
        super(ActorType.ENEMY, true);
        setHeight(26);
        setHealth(20);
        setDamage(40);
        setDefense(20);

        this.animation = new EnemyDeelerAnimation(this);

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
