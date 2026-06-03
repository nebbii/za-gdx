package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyLlortAnimation;

public class EnemyLlort extends Enemy {
    public EnemyLlortAnimation animation;

    private float startX;
    private float startY;
    private float goalX;
    private float goalY;

    private int currentMoveStep = -1;

    private float timer;
    private float interval = 1.5f;
    private boolean axesThrown;

    private final float[][] pathCoordinates = {
        {-104f,  10f},
        { 104f, -40f},
        {  84f,  40f},
        { -84f, -60f},
        {  96f, -12f},
        {-188f,   0f},
        {  92f,  72f}
    };

    public EnemyLlort() {
        super(ActorType.BOSS, true);

        setWidth(48);
        setHeight(64);
        setHealth(60);
        setDamage(50);
        setDefense(24);
        setSpeed(110f);

        setStartX(getX());
        setStartY(getY());
        timer = 0;
        axesThrown = false;

        this.animation = new EnemyLlortAnimation(this);

        this.enemyState = EnemyState.SEARCH;
    }

    @Override
    public void logic() {
        float delta = Gdx.graphics.getDeltaTime();

        if (getState() != State.ACTIVE) return;
        if (health <= 0) onDeath();

        knockback = Math.max(0f, knockback - delta);

        if (knockback <= 0) {
            switch(enemyState) {
            case SEARCH:
                move(delta);
                break;
            case FIGHT:
                attack(delta);
                break;
            default:

            }
            animation.play();
            if (enemyState == EnemyState.FIGHT) throwAxesOnFrame(5);
        }

    }

    @Override
    public void draw(SpriteBatch batch) {
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
    }

    private void move(float delta) {
        int step = (int)(timer / interval) % pathCoordinates.length;

        if (step != currentMoveStep) {
            currentMoveStep = step;

            setGoalX(getX() + pathCoordinates[step][0]);
            setGoalY(getY() + pathCoordinates[step][1]);

            animation.setStateTime(0);
            axesThrown = false;
            setEnemyState(EnemyState.FIGHT);
        }

        moveTowardGoal(delta);

        timer += delta;
    }

    private void attack(float delta) {
        if (animation.isAttackAnimationFinished()) {
            setEnemyState(EnemyState.SEARCH);
            axesThrown = false;
        }
    }

    private void throwAxesOnFrame(int frame) {
        if (axesThrown) return;
        if (animation.getCurrentAttackFrameIndex() < frame) return;

        map.addNewActor(new EnemyProjectileLlortAxe(this, Direction.LEFT));
        map.addNewActor(new EnemyProjectileLlortAxe(this, Direction.RIGHT));
        axesThrown = true;
    }

    private void moveTowardGoal(float delta) {
        float dx = getGoalX() - getX();
        float dy = getGoalY() - getY();

        float distance = (float)Math.sqrt(dx * dx + dy * dy);

        if (distance == 0f) return;

        float directionX = dx / distance;
        float directionY = dy / distance;

        setX(getX() + directionX * speed * delta);
        setY(getY() + directionY * speed * delta);
    }

    @Override
    public void onDeath() {
        setState(State.DEAD);
        map.getSaveManager().addLocationEntry(locationEntry, "permadead");
    }

    @Override
    public Array<String> getWeaknesses() {
        return Array.with("ZeldaActionWand");
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getGoalX() {
        return goalX;
    }

    public void setGoalX(float goalX) {
        this.goalX = goalX;
    }

    public float getGoalY() {
        return goalY;
    }

    public void setGoalY(float goalY) {
        this.goalY = goalY;
    }
}
