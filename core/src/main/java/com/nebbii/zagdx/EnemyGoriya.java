package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.nebbii.zagdx.animation.EnemyGoriyaAnimation;

public class EnemyGoriya extends Enemy {
    public EnemyGoriyaAnimation animation;
    public float timer;

    // TODO: Set actual original game accurate values
    public EnemyGoriya() {
        super(ActorType.ENEMY, true);
        setWidth(48);
        setHeight(42);
        setHealth(60);
        setDamage(45);
        setBonusDamage(5);
        setDefense(10);
        timer = MathUtils.random(0f, 2f);

        this.animation = new EnemyGoriyaAnimation(this);

        this.enemyState = EnemyState.SEARCH;
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) return;

        switch(enemyState) {
        case SEARCH:
            setSpeed(80f);
            if (timer > 3) {
                map.addNewActor(new EnemyActionBoomerang(this, getX(), getY()));
                setEnemyState(EnemyState.STOP);
            }
            break;
        case FIGHT:
            setSpeed(110f);
            if (timer > 3) {
                map.addNewActor(new EnemyActionBoomerang(this, getX(), getY()));
                setEnemyState(EnemyState.STOP);
            }
            break;
        case STOP:
            if (timer > 4) {
                setEnemyState(EnemyState.SEARCH);
                timer = 0;
            }
            default:
        }

        timer += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (knockback > 0) drawFlashOverlay(batch);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
    }
}
