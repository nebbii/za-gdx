package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.EnemyMobyAnimation;

// TODO: Set actual original game accurate values
public class EnemyMoby extends Enemy {
    public EnemyMobyAnimation animation;

    /*
      enemy.moby has different stats on different cells:
        [damage=45, health=0, loot=Random] on cells g26, j25, j26, l18, m17, n16, q9, r6, s8
        [damage=45, health=(Not Spawned, or Projectile), loot=Random] on cells s7
    */
    public EnemyMoby() {
        super(ActorType.ENEMY, true);
        setHeight(26);
        setDamage(45);
        setHealth(1);

        this.animation = new EnemyMobyAnimation(this);

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
        if (knockback > 0) drawFlashOverlay(batch);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
    }
}
