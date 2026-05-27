package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemySardakYellowAnimation;

// TODO: Set actual original game accurate values
public class EnemySardakYellow extends EnemySardak {
    public EnemySardakYellowAnimation animation;
    public float timer;

    /*
    enemy.sardak.yellow:
        [health=280, damage=50, defense=30, weakness=JadeRing, bonusDamage=70]
    */
    public EnemySardakYellow() {
        super();
        setHealth(280);
        setDamage(50);
        setDefense(30);
        setBonusDamage(70);
        timer = MathUtils.random(0f, 2f);

        this.animation = new EnemySardakYellowAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();

        if (!isActive()) return;

        switch(enemyState) {
        case SEARCH:
            setSpeed(80f);
            if (timer > 3) {
                map.addNewActor(new EnemyActionSpear(this, getX(), getY()));
                setEnemyState(EnemyState.STOP);
            }
            break;
        case FIGHT:
            setSpeed(110f);
            if (timer > 3) {
                map.addNewActor(new EnemyActionSpear(this, getX(), getY()));
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
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
    }

    @Override
    public Array<String> getWeaknesses() {
        return Array.with("ZeldaActionJadeRing");
    }
}
