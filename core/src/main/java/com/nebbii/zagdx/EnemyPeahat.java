package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.nebbii.zagdx.animation.EnemyPeahatAnimation;

public class EnemyPeahat extends Enemy {
    public EnemyPeahatAnimation animation;
    public float timer;

    /*
      enemy.peahat:
        [health=60, damage=44, defense=10, bonusDamage=0] on cell e22
      enemy.peahat.projectile:
        [damage=46] on cell e22
    */
    public EnemyPeahat() {
        super(ActorType.ENEMY, true);
        setWidth(44);
        setHeight(44);
        setHealth(60);
        setDamage(44);
        setDefense(10);
        timer = MathUtils.random(0f, 2f);

        this.animation = new EnemyPeahatAnimation(this);

        this.enemyState = EnemyState.SEARCH;
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) return;

        switch(enemyState) {
        case SEARCH:
            setSpeed(50f);
            throwProjectileIfReady();
            break;
        case FIGHT:
            setSpeed(90f);
            throwProjectileIfReady();
            break;
        case STOP:
            if (timer > 4) {
                setEnemyState(EnemyState.SEARCH);
                timer = 0;
            }
            break;
        default:
        }

        timer += Gdx.graphics.getDeltaTime();
    }

    private void throwProjectileIfReady() {
        if (timer <= 3) return;

        map.addNewActor(new EnemyActionPeahatProjectile(this, getX(), getY()));
        setEnemyState(EnemyState.STOP);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
    }
}
