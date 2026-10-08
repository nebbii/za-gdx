package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyJackAnimation;

// TODO: Set actual original game accurate values
// TODO: the CD-i Interactief guide's List of Foes states Jack is Immortal (Weakness:
// None) -- it should never die. There is no isImmortal mechanic in the Enemy base
// class yet, so 9999 health is a stopgap until one is added; replace this with a real
// immortality flag rather than trusting this number.
public class EnemyJack extends Enemy {
    public EnemyJackAnimation animation;

    public EnemyJack() {
        super(ActorType.ENEMY, true);
        setWidth(40);
        setHeight(40);
        setHealth(9999);
        setDamage(48);
        setDefense(99);

        this.animation = new EnemyJackAnimation(this);

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
