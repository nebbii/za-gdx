package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyLlortAnimation;

public class EnemyLlort extends Enemy {
    public EnemyLlortAnimation animation;

    private static class PathStep {
        private final float x;
        private final float y;
        private final float duration;
        private final boolean attackBefore;

        private PathStep(float x, float y, float duration, boolean attackBefore) {
            if (duration <= 0f) {
                throw new IllegalArgumentException("Llort path step duration must be positive");
            }

            this.x = x;
            this.y = y;
            this.duration = duration;
            this.attackBefore = attackBefore;
        }
    }

    private Rectangle hitbox;
    private float hitboxOffsetX = -8;
    private float hitboxOffsetY = 0;

    private float startX;
    private float startY;
    private float stepStartX;
    private float stepStartY;
    private float goalX;
    private float goalY;

    private int currentMoveStep = -1;

    private float currentStepElapsed;
    private boolean axesThrown;

    private final PathStep[] pathCoordinates = {
        new PathStep(-100f,  25f, 0.8f, false),
        new PathStep( 40f, -40f, 0.5f, true),
        new PathStep( 104f, 0f, 0.7f, false),
        new PathStep(  40f,  40f, 0.5f, false),
        new PathStep( -60, -60f, 0.5f, true),
        new PathStep( -24f, 0f, 0.5f, false),
        new PathStep(  24f, -12f, 0.3f, true),
        new PathStep(  50, 0f, 0.8f, false),
        new PathStep(  -20f, 0f, 0.2f, false),
        new PathStep(  20, -20f, 0.2f, false),
        //new PathStep(  10, 72f, 0.8f, false),
        //new PathStep(  50f,  -90f, 0.7f, false),
        new PathStep(-188f,   0f, 1.5f, false),
        new PathStep(  95f,  92f, 1f, false),
        new PathStep(  10f,  40f, 1f, true),
        new PathStep(  -50f,  0f, 1.2f, false),
        new PathStep(  80f,  0f, 1.2f, false),
        new PathStep(  -40f,  -50f, 0.3f, false)
    };

    public EnemyLlort() {
        super(ActorType.BOSS, true);

        setWidth(16);
        setHeight(16);

        hitbox = new Rectangle();
        hitbox.setWidth(52);
        hitbox.setHeight(72);

        hitboxOffsetX = this.x + this.getWidth() / 2 - hitbox.getWidth() / 2;
        hitboxOffsetY = this.y;

        setHealth(60);
        setDamage(50);
        setDefense(24);
        setSpeed(110f);

        setStartX(getX());
        setStartY(getY());

        currentStepElapsed = 0f;
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
        invincibility = Math.max(0f, invincibility - delta);

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

            if (enemyState == EnemyState.FIGHT) {
                throwAxesOnFrame(5);
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
    }

    private void move(float delta) {
        if (pathCoordinates.length == 0) return;

        float remainingDelta = delta;

        while (remainingDelta > 0f && enemyState == EnemyState.SEARCH) {
            if (currentMoveStep < 0) {
                beginMoveStep(0);
                if (enemyState != EnemyState.SEARCH) return;
            }


            PathStep step = pathCoordinates[currentMoveStep];
            float stepRemaining = step.duration - currentStepElapsed;
            float elapsedThisFrame = Math.min(remainingDelta, stepRemaining);

            moveAlongStep(step, elapsedThisFrame);
            currentStepElapsed += elapsedThisFrame;
            remainingDelta -= elapsedThisFrame;

            if (currentStepElapsed < step.duration) {
                return;
            }

            beginMoveStep((currentMoveStep + 1) % pathCoordinates.length);
        }
    }

    private void beginMoveStep(int stepIndex) {
        currentMoveStep = stepIndex;
        currentStepElapsed = 0f;
        stepStartX = getX();
        stepStartY = getY();

        PathStep step = pathCoordinates[currentMoveStep];
        Gdx.app.log(getClass().getSimpleName(), "next coordinates: X: " + pathCoordinates[currentMoveStep].x + " Y: " + pathCoordinates[currentMoveStep].y);

        setGoalX(stepStartX + step.x);
        setGoalY(stepStartY + step.y);

        if (step.attackBefore) {
            beginAttack();
        }
    }

    private void moveAlongStep(PathStep step, float delta) {
        float dx = getGoalX() - getX();
        float dy = getGoalY() - getY();
        float distance = (float)Math.sqrt(dx * dx + dy * dy);

        if (distance <= 0.001) {
            setX(getGoalX());
            setY(getGoalY());
            return;
        }

        float stepDistance = (float)Math.sqrt(step.x * step.x + step.y * step.y);
        float moveDistance = stepDistance / step.duration * delta;

        if (distance <= moveDistance) {
            setX(getGoalX());
            setY(getGoalY());
            return;
        }

        setX(getX() + dx / distance * moveDistance);
        setY(getY() + dy / distance * moveDistance);
    }

    private void beginAttack() {
        axesThrown = false;
        animation.setStateTime(0);
        setEnemyState(EnemyState.FIGHT);
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

    @Override
    public void onHit(int damage, float knockback) {
        super.onHit(damage, knockback);
        increaseInvincibility(knockback * 2);
    }

    @Override
    public void onDeath() {
        setState(State.DEAD);
        map.addNewActor(new SpriteExplosion(getCenterPointX(), getCenterPointY()));
        map.getSaveManager().addLocationEntry(locationEntry, "permadead");
    }

    @Override
    public Array<String> getWeaknesses() {
        return Array.with("ZeldaActionWand");
    }


    @Override
    public Rectangle getHitbox() {
        hitbox.setX(this.x+hitboxOffsetX);
        hitbox.setY(this.y+hitboxOffsetY);

        return hitbox;
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
