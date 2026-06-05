package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.EnemyKeeseAnimation;

public class EnemyKeese extends Enemy {
    public EnemyKeeseAnimation animation;

    private static final float MIN_GOAL_RANGE = 60f;
    private static final float MAX_GOAL_RANGE = 130f;

    private enum PathMode {
        NONE,
        SET,
        RANDOM
    }

    private PathMode pathMode = PathMode.NONE;
    private final ArrayList<ActorPathPointJsonEntry> path = new ArrayList<>();

    private boolean pathStarted;
    private float lastPassedX;
    private float lastPassedY;
    private float goalX;
    private float goalY;
    private int currentPathIndex;
    private boolean returningToLastPassedPoint;
    private float randomGoalHold;

    public EnemyKeese() {
        super(ActorType.ENEMY, false);
        setWidth(48);
        setHeight(26);
        setHealth(60);
        setDamage(45);
        setDefense(10);
        setSpeed(100f);

        this.animation = new EnemyKeeseAnimation(this);

        this.enemyState = EnemyState.SEARCH;
        resetPathStateToCurrentPosition();
    }

    @Override
    public void logic() {
        if (getState() != State.ACTIVE) return;

        if (!hasConfiguredPathMovement()) {
            super.logic();
            return;
        }

        float delta = Gdx.graphics.getDeltaTime();
        knockback = Math.max(0f, knockback - delta);
        invincibility = Math.max(0f, invincibility - Gdx.graphics.getDeltaTime());

        if (knockback > 0) {
            if (hurtWeakness && health > 0) {
                movePushback();
            }
            return;
        }

        if (health <= 0) {
            onDeath();
            return;
        }

        if (returningToLastPassedPoint) {
            setEnemyState(EnemyState.FIGHT);
            goalX = lastPassedX;
            goalY = lastPassedY;

            if (moveTowardGoal(delta)) {
                returningToLastPassedPoint = false;

                if (pathStarted) {
                    handleReachedPathGoal();
                }
            }
            return;
        }

        if (!pathStarted) {
            setEnemyState(EnemyState.SEARCH);
            return;
        }

        setEnemyState(EnemyState.FIGHT);

        if (pathMode == PathMode.RANDOM && randomGoalHold > 0f) {
            randomGoalHold = Math.max(0f, randomGoalHold - delta);

            if (randomGoalHold <= 0f) {
                chooseNextPathGoal();
            }
            return;
        }

        if (moveTowardGoal(delta)) {
            lastPassedX = getX();
            lastPassedY = getY();

            handleReachedPathGoal();
        }
    }

    public float getAnimationSpeed() {
        switch(enemyState) {
            case FIGHT:
                return 0.08f;
            case SEARCH:
            default:
                return 0.12f;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);

        if (hasConfiguredPathMovement() && !pathStarted) {
            batch.draw(animation.playFirstFrame(), animation.getX(), animation.getY());
        }
        else {
            batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
        }

        if (knockback > 0) endDrawFlashOverlay(batch);
    }

    @Override
    public Array<String> getWeaknesses() {
        return new Array<String>();
    }

    @Override
    public void onHit(int damage, float knockback) {
        if (this.knockback > 0) return;

        super.onHit(damage, knockback);

        if (hasConfiguredPathMovement() && hurtWeakness && getHealth() > 0) {
            returningToLastPassedPoint = true;
            randomGoalHold = 0f;
            goalX = lastPassedX;
            goalY = lastPassedY;
        }
    }

    public void setRelativePath(List<ActorPathPointJsonEntry> relativePath) {
        path.clear();

        if (relativePath != null) {
            for (ActorPathPointJsonEntry sourcePoint : relativePath) {
                if (sourcePoint == null) continue;

                ActorPathPointJsonEntry pathPoint = new ActorPathPointJsonEntry();
                pathPoint.x = sourcePoint.x;
                pathPoint.y = sourcePoint.y;
                path.add(pathPoint);
            }
        }

        if (path.isEmpty()) {
            pathMode = PathMode.NONE;
        }
        else {
            pathMode = PathMode.SET;
        }

        resetPathStateToCurrentPosition();
    }

    public void enableRandomPathMode() {
        path.clear();
        pathMode = PathMode.RANDOM;
        resetPathStateToCurrentPosition();
    }

    public boolean hasConfiguredPathMovement() {
        return pathMode != PathMode.NONE;
    }

    public void startPathIfNeeded() {
        if (!hasConfiguredPathMovement() || pathStarted) return;

        pathStarted = true;
        setEnemyState(EnemyState.FIGHT);

        if (returningToLastPassedPoint || knockback > 0) {
            randomGoalHold = 0f;
            goalX = lastPassedX;
            goalY = lastPassedY;
            return;
        }

        lastPassedX = getX();
        lastPassedY = getY();
        chooseNextPathGoal();
    }

    public boolean isPathStarted() {
        return pathStarted;
    }

    public float getLastPassedX() {
        return lastPassedX;
    }

    public float getLastPassedY() {
        return lastPassedY;
    }

    public float getGoalX() {
        return goalX;
    }

    public float getGoalY() {
        return goalY;
    }

    private void chooseNextPathGoal() {
        switch(pathMode) {
            case SET:
                chooseNextSetPathGoal();
                break;
            case RANDOM:
                chooseNextRandomPathGoal();
                break;
            default:
                break;
        }
    }

    private void chooseNextSetPathGoal() {
        if (path.isEmpty()) return;

        ActorPathPointJsonEntry pathPoint = path.get(currentPathIndex);
        goalX = lastPassedX + pathPoint.x;
        goalY = lastPassedY + pathPoint.y;
    }

    private void chooseNextRandomPathGoal() {
        randomGoalHold = 0f;

        for (int i = 0; i < 100; i++) {
            float goalDistance = MathUtils.random(
                Math.min(MIN_GOAL_RANGE, MAX_GOAL_RANGE),
                MAX_GOAL_RANGE
            );
            float goalAngle = MathUtils.random(MathUtils.PI2);
            float attemptedGoalX = getX() + MathUtils.cos(goalAngle) * goalDistance;
            float attemptedGoalY = getY() + MathUtils.sin(goalAngle) * goalDistance;

            if (isGoalWithinScreen(attemptedGoalX, attemptedGoalY)) {
                goalX = attemptedGoalX;
                goalY = attemptedGoalY;
                return;
            }
        }

        float goalDistance = MathUtils.random(
            Math.min(MIN_GOAL_RANGE, MAX_GOAL_RANGE),
            MAX_GOAL_RANGE
        );
        float goalAngle = MathUtils.random(MathUtils.PI2);

        goalX = clampRandomGoalXToScreen(getX() + MathUtils.cos(goalAngle) * goalDistance);
        goalY = clampRandomGoalYToScreen(getY() + MathUtils.sin(goalAngle) * goalDistance);
    }

    private boolean isGoalWithinScreen(float attemptedGoalX, float attemptedGoalY) {
        return attemptedGoalX >= World.getScreenLeft(getCenterPointX())
            && attemptedGoalX + getWidth() <= World.getScreenRight(getCenterPointX())
            && attemptedGoalY >= World.getScreenBottom(getCenterPointY())
            && attemptedGoalY + getHeight() <= World.getScreenTop(getCenterPointY());
    }

    private float clampRandomGoalXToScreen(float attemptedGoalX) {
        return MathUtils.clamp(
            attemptedGoalX,
            World.getScreenLeft(getCenterPointX()),
            World.getScreenRight(getCenterPointX()) - getWidth()
        );
    }

    private float clampRandomGoalYToScreen(float attemptedGoalY) {
        return MathUtils.clamp(
            attemptedGoalY,
            World.getScreenBottom(getCenterPointY()),
            World.getScreenTop(getCenterPointY()) - getHeight()
        );
    }

    private void handleReachedPathGoal() {
        if (pathMode == PathMode.RANDOM) {
            randomGoalHold = 0.3f;
            return;
        }

        if (pathMode == PathMode.SET) {
            currentPathIndex = (currentPathIndex + 1) % path.size();
        }

        chooseNextPathGoal();
    }

    private boolean moveTowardGoal(float delta) {
        float dx = goalX - getX();
        float dy = goalY - getY();
        float distance = (float)Math.sqrt(dx * dx + dy * dy);

        if (distance <= 0.001f || distance <= speed * delta) {
            setX(goalX);
            setY(goalY);
            return true;
        }

        updateDirectionTowardGoal(dx, dy);

        setX(getX() + dx / distance * speed * delta);
        setY(getY() + dy / distance * speed * delta);

        return false;
    }

    private void updateDirectionTowardGoal(float dx, float dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            setDirection(dx < 0 ? Direction.LEFT : Direction.RIGHT);
        }
        else {
            setDirection(dy < 0 ? Direction.DOWN : Direction.UP);
        }
    }

    private void resetPathStateToCurrentPosition() {
        pathStarted = false;
        lastPassedX = getX();
        lastPassedY = getY();
        goalX = getX();
        goalY = getY();
        currentPathIndex = 0;
        returningToLastPassedPoint = false;
        randomGoalHold = 0f;
        setEnemyState(EnemyState.SEARCH);
    }
}
