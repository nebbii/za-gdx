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

    private static final float SEARCH_SPEED = 80f;
    private static final float FIGHT_SPEED = 110f;
    private static final float MIN_GOAL_RANGE = 60f;
    private static final float MAX_GOAL_RANGE = 130f;
    private static final float GOAL_HOLD_DURATION = 0.3f;
    private static final int GOAL_PICK_ATTEMPTS = 100;
    private static final float ARRIVAL_EPSILON = 0.001f;

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

        this.animation = new EnemyKeeseAnimation(this);

        this.enemyState = EnemyState.SEARCH;
        resetPathStateToCurrentPosition();
    }

    @Override
    public void logic() {
        if (!hasConfiguredPathMovement()) {
            super.logic();
            updateSpeedForState();
            return;
        }

        logicPathMovement();
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

    private void logicPathMovement() {
        if (getState() != State.ACTIVE) return;

        float delta = Gdx.graphics.getDeltaTime();
        knockback = Math.max(0f, knockback - delta);

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

        updateSpeedForState();

        if (returningToLastPassedPoint) {
            setEnemyState(EnemyState.FIGHT);
            setSpeed(FIGHT_SPEED);
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
            setSpeed(SEARCH_SPEED);
            return;
        }

        setEnemyState(EnemyState.FIGHT);
        setSpeed(FIGHT_SPEED);

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

    private void updateSpeedForState() {
        switch(enemyState) {
            case SEARCH:
                setSpeed(SEARCH_SPEED);
                break;
            case FIGHT:
                setSpeed(FIGHT_SPEED);
                break;
            default:
        }
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

        for (int i = 0; i < GOAL_PICK_ATTEMPTS; i++) {
            float goalDistance = MathUtils.random(
                Math.min(MIN_GOAL_RANGE, MAX_GOAL_RANGE),
                MAX_GOAL_RANGE
            );
            float goalAngle = MathUtils.random(MathUtils.PI2);
            float candidateGoalX = getX() + MathUtils.cos(goalAngle) * goalDistance;
            float candidateGoalY = getY() + MathUtils.sin(goalAngle) * goalDistance;

            if (isRandomGoalWithinCurrentScreen(candidateGoalX, candidateGoalY)) {
                goalX = candidateGoalX;
                goalY = candidateGoalY;
                return;
            }
        }

        float goalDistance = MathUtils.random(
            Math.min(MIN_GOAL_RANGE, MAX_GOAL_RANGE),
            MAX_GOAL_RANGE
        );
        float goalAngle = MathUtils.random(MathUtils.PI2);

        goalX = clampRandomGoalXToCurrentScreen(getX() + MathUtils.cos(goalAngle) * goalDistance);
        goalY = clampRandomGoalYToCurrentScreen(getY() + MathUtils.sin(goalAngle) * goalDistance);
    }

    private boolean isRandomGoalWithinCurrentScreen(float candidateGoalX, float candidateGoalY) {
        return candidateGoalX >= World.getCurrentScreenLeft(getCenterPointX())
            && candidateGoalX + getWidth() <= World.getCurrentScreenRight(getCenterPointX())
            && candidateGoalY >= World.getCurrentScreenBottom(getCenterPointY())
            && candidateGoalY + getHeight() <= World.getCurrentScreenTop(getCenterPointY());
    }

    private float clampRandomGoalXToCurrentScreen(float candidateGoalX) {
        return MathUtils.clamp(
            candidateGoalX,
            World.getCurrentScreenLeft(getCenterPointX()),
            World.getCurrentScreenRight(getCenterPointX()) - getWidth()
        );
    }

    private float clampRandomGoalYToCurrentScreen(float candidateGoalY) {
        return MathUtils.clamp(
            candidateGoalY,
            World.getCurrentScreenBottom(getCenterPointY()),
            World.getCurrentScreenTop(getCenterPointY()) - getHeight()
        );
    }

    private void handleReachedPathGoal() {
        if (pathMode == PathMode.RANDOM) {
            randomGoalHold = GOAL_HOLD_DURATION;
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

        if (distance <= ARRIVAL_EPSILON || distance <= speed * delta) {
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
