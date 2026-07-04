package com.nebbii.zagdx;

import com.badlogic.gdx.audio.Sound;
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
    private Sound voiceLine;
    private boolean voiceLinePlayed;

    public EnemySardak() {
        super(ActorType.ENEMY, true);
        setWidth(48);
        setHeight(40);

        this.enemyState = EnemyState.SEARCH;
        this.voiceLinePlayed = false;
    }

    @Override
    public void logic() {
        super.logic();

        switch(enemyState) {
            case SEARCH:
                setSpeed(60f);
                break;
            case FIGHT:
                setSpeed(90f);
                break;
            default:
        }

        if (!isActive()) {
            stopVoiceLine();
            return;
        }

        playVoiceLine();
    }

    @Override
    public void draw(SpriteBatch batch) {
        /*
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
        */
    }

    protected void setVoiceLine(Sound voiceLine) {
        this.voiceLine = voiceLine;
    }

    private void playVoiceLine() {
        if (voiceLinePlayed) {
            return;
        }

        voiceLine.play();
        voiceLinePlayed = true;
    }

    private void stopVoiceLine() {
        if (voiceLine == null) {
            return;
        }

        voiceLine.stop();
    }

    @Override
    public void onDeath() {
        stopVoiceLine();
        setState(State.DEAD);
        map.addNewActor(new SpriteExplosion(getCenterPointX(), getCenterPointY()));
        map.getSaveManager().addLocationEntry(locationEntry, "permadead");
    }
}
