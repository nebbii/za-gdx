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

    private enum PathMode { NONE, SET, RANDOM }

    private PathMode pathMode = PathMode.NONE;
    private final ArrayList<ActorPathPointJsonEntry> path = new ArrayList<>();

    private float anchorX;
    private float anchorY;
    private float goalX;
    private float goalY;

    private int currentPathIndex;
    private boolean returningToAnchor;
    private float goalHold;

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
        resetToCurrentPosition();
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
        invincibility = Math.max(0f, invincibility - delta);

        if (knockback > 0) {
            if (hurtWeakness && health > 0) movePushback();
            return;
        }

        if (health <= 0) {
            onDeath();
            return;
        }

        if (goalX == getX() && goalY == getY() && goalHold <= 0f && !returningToAnchor) {
            anchorX = getX();
            anchorY = getY();
            chooseNextGoal();
        }

        if (goalHold > 0f) {
            goalHold = Math.max(0f, goalHold - delta);
            if (goalHold <= 0f) chooseNextGoal();
            return;
        }

        if (moveTowardGoal(delta)) {
            anchorX = getX();
            anchorY = getY();

            if (returningToAnchor) {
                returningToAnchor = false;
            }
            handleReachedGoal();
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
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
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
            returningToAnchor = true;
            goalHold = 0f;
            goalX = anchorX;
            goalY = anchorY;
        }
    }

    public void setRelativePath(List<ActorPathPointJsonEntry> relativePath) {
        path.clear();

        if (relativePath != null) {
            for (ActorPathPointJsonEntry src : relativePath) {
                if (src == null) continue;
                ActorPathPointJsonEntry p = new ActorPathPointJsonEntry();
                p.x = src.x;
                p.y = src.y;
                p.command = src.command;
                path.add(p);
            }
        }

        pathMode = path.isEmpty() ? PathMode.NONE : PathMode.SET;
        resetToCurrentPosition();
    }

    public void enableRandomPathMode() {
        path.clear();
        pathMode = PathMode.RANDOM;
        resetToCurrentPosition();
    }

    public boolean hasConfiguredPathMovement() {
        return pathMode != PathMode.NONE;
    }

    private void chooseNextGoal() {
        switch (pathMode) {
            case SET:    chooseNextSetGoal();    break;
            case RANDOM: chooseNextRandomGoal(); break;
            default:                             break;
        }
    }

    private void chooseNextSetGoal() {
        if (path.isEmpty()) return;

        ActorPathPointJsonEntry p = path.get(currentPathIndex);
        goalX = anchorX + p.x;
        goalY = anchorY + p.y;
    }

    private void chooseNextRandomGoal() {
        for (int i = 0; i < 100; i++) {
            float dist = MathUtils.random(60f, 130f);
            float angle = MathUtils.random(MathUtils.PI2);
            float tx = getX() + MathUtils.cos(angle) * dist;
            float ty = getY() + MathUtils.sin(angle) * dist;

            if (isGoalWithinScreen(tx, ty)) {
                goalX = tx;
                goalY = ty;
                return;
            }
        }

        float dist = MathUtils.random(60f, 130f);
        float angle = MathUtils.random(MathUtils.PI2);
        goalX = clampGoalX(getX() + MathUtils.cos(angle) * dist);
        goalY = clampGoalY(getY() + MathUtils.sin(angle) * dist);
    }

    private boolean isGoalWithinScreen(float x, float y) {
        return x >= World.getScreenLeft(getCenterPointX())
            && x + getWidth() <= World.getScreenRight(getCenterPointX())
            && y >= World.getScreenBottom(getCenterPointY())
            && y + getHeight() <= World.getScreenTop(getCenterPointY());
    }

    private float clampGoalX(float x) {
        return MathUtils.clamp(x,
            World.getScreenLeft(getCenterPointX()),
            World.getScreenRight(getCenterPointX()) - getWidth());
    }

    private float clampGoalY(float y) {
        return MathUtils.clamp(y,
            World.getScreenBottom(getCenterPointY()),
            World.getScreenTop(getCenterPointY()) - getHeight());
    }

    private void handleReachedGoal() {
        if (pathMode == PathMode.RANDOM) {
            goalHold = 0.3f;
            return;
        }

        if (pathMode == PathMode.SET) {
            applySetPathCommand(path.get(currentPathIndex));
            currentPathIndex = (currentPathIndex + 1) % path.size();
            if (goalHold <= 0f) chooseNextSetGoal();
        }
    }

    private void applySetPathCommand(ActorPathPointJsonEntry p) {
        if (p.command != null && p.command.trim().startsWith("stopMovement")) {
            goalHold = MathUtils.random(0f, 5f);
        }
    }

    private boolean moveTowardGoal(float delta) {
        float dx = goalX - getX();
        float dy = goalY - getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance <= speed * delta) {
            setX(goalX);
            setY(goalY);
            return true;
        }

        if (Math.abs(dx) > Math.abs(dy)) {
            setDirection(dx < 0 ? Direction.LEFT : Direction.RIGHT);
        } else {
            setDirection(dy < 0 ? Direction.DOWN : Direction.UP);
        }

        setX(getX() + dx / distance * speed * delta);
        setY(getY() + dy / distance * speed * delta);
        return false;
    }

    private void resetToCurrentPosition() {
        anchorX = getX();
        anchorY = getY();
        goalX = getX();
        goalY = getY();
        currentPathIndex = 0;
        returningToAnchor = false;
        goalHold = 0f;
    }

    public boolean isHoldingGoal() {
		return goalHold > 0f;
	}

    public float getGoalX() { return goalX; }
    public float getGoalY() { return goalY; }
}
