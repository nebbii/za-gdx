package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nebbii.zagdx.animation.ZeldaAnimation;

public class Zelda extends Rectangle implements Actor {
    private final boolean solid = true;

    private World world;
    private MapManager map; // zelda can spawn actors
    private boolean movedLastFrame;
    public ZeldaAnimation animation;

    private State state;
    private AnimState animState;

    private ActorType type;

    private boolean normalizeDiagonals = false;

    private float speed = 120f;
    private int health;
    private int maxHealth;

    private Item currentItem;

    private Rectangle hitbox;
    private float hitboxOffsetX = -8;
    private float hitboxOffsetY = 0;

    public Zelda(World world, MapManager map) {
        setWidth(6);
        setHeight(6);
        setState(State.ACTIVE);
        setAnimState(AnimState.STOPDOWN);
        setType(ActorType.PLAYER);
        setHealth(60);
        setMaxHealth(60);
        setCurrentItem(Treasure.NONE);

        animation = new ZeldaAnimation(this);
        hitbox = new Rectangle();
        hitbox.setWidth(24);
        hitbox.setHeight(36);

        this.world = world;
        this.map = map;
    }

    @Override
    public void logic() {
        if (isAttacking() && animation.isAnimationFinished()) {
            finishAction();
        }

        if (!getMovedLastFrame()) {
            if (getAnimState() == AnimState.MOVELEFT)  setAnimState(AnimState.STOPLEFT);
            if (getAnimState() == AnimState.MOVEDOWN)  setAnimState(AnimState.STOPDOWN);
            if (getAnimState() == AnimState.MOVEUP)    setAnimState(AnimState.STOPUP);
            if (getAnimState() == AnimState.MOVERIGHT) setAnimState(AnimState.STOPRIGHT);
        }

        setMovedLastFrame(false);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }

    public void move(float inputX, float inputY) {
        if (!isActive()) return;
        if (!isMoving() && !isStopped()) return;
        if (inputX == 0 && inputY == 0) return;

        setMovedLastFrame(true);

        if (inputX < 0) setAnimState(AnimState.MOVELEFT);
        if (inputY < 0) setAnimState(AnimState.MOVEDOWN);
        if (inputY > 0) setAnimState(AnimState.MOVEUP);
        if (inputX > 0) setAnimState(AnimState.MOVERIGHT);

        float deltaTime = Gdx.graphics.getDeltaTime();

        setX(getX() + inputX * deltaTime);
        setY(getY() + inputY * deltaTime);
    }

    public void action() {
        if (!isActive()) return;
        if (!isMoving() && !isStopped()) return;

        if (getCurrentItem() instanceof Treasure) {
            switch ((Treasure) getCurrentItem()) {
            case NONE:
                break;
            case PITCHER_EMPTY:
                SpawnerPitcherFull spawnerPitcherFull = (SpawnerPitcherFull) map.findActorByType(SpawnerPitcherFull.class);

                if (spawnerPitcherFull == null) return;
                if (!spawnerPitcherFull.isActive()) return;

                spawnerPitcherFull.activate(map, world.getGameManager());
                setCurrentItem(Treasure.NONE);
                break;
            case PITCHER_FULL:
                SpawnerVialOfWind spawnerVialOfWind = (SpawnerVialOfWind) map.findActorByType(SpawnerVialOfWind.class);

                if (spawnerVialOfWind == null) return;
                if (!spawnerVialOfWind.isActive()) return;

                spawnerVialOfWind.activate(map, world.getGameManager());
                setCurrentItem(Treasure.NONE);
                break;
            default:
            }

            return; // treasures never do the attack animation
        }

        if (getCurrentItem() instanceof Weapon) {
            switch ((Weapon) getCurrentItem()) {
            case WAND:
                break;
            default:
            }
        }

        switch (getAnimState()) {
        case STOPLEFT:
        case MOVELEFT:
            setAnimState(AnimState.ATTACKLEFT);
            break;
        case STOPDOWN:
        case MOVEDOWN:
            setAnimState(AnimState.ATTACKDOWN);
            break;
        case STOPUP:
        case MOVEUP:
            setAnimState(AnimState.ATTACKUP);
            break;
        case STOPRIGHT:
        case MOVERIGHT:
            setAnimState(AnimState.ATTACKRIGHT);
            break;
        default:
            throw new IllegalStateException("Zelda->action(): Unhandled animation state: " + getAnimState());
        }

        map.addActor(new ZeldaActionWand(this, getX(), getY()));
    }

    public void finishAction() {
        switch (getAnimState()) {
        case ATTACKLEFT:
            setAnimState(AnimState.STOPLEFT);
        break;
        case ATTACKDOWN:
            setAnimState(AnimState.STOPDOWN);
        break;
        case ATTACKUP:
            setAnimState(AnimState.STOPUP);
        break;
        case ATTACKRIGHT:
            setAnimState(AnimState.STOPRIGHT);
        break;
        default:
            throw new IllegalStateException("Zelda->finishAction(): Unhandled animation state: " + getAnimState());
        }
    }

    public Rectangle getHitbox() {
        hitbox.setX(this.x+hitboxOffsetX);
        hitbox.setY(this.y+hitboxOffsetY);

        return hitbox;
    }

    public Rectangle getCollisionBox() {
        return this;
    }

    public float getCenterPointX() {
        return getX() + getWidth() / 2;
    }

    public float getCenterPointY() {
        return getY() + getHeight() / 2;
    }

    public Item getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(Item item) {
        this.currentItem = item;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    public AnimState getAnimState() {
        return animState;
    }

    public void setAnimState(AnimState animState) {
        this.animState = animState;
    }

    public boolean getMovedLastFrame() {
        return movedLastFrame;
    }

    public boolean isActive() {
        return getState() == State.ACTIVE;
    }

    public boolean isDead() {
        return getState() == State.DEAD;
    }

    public boolean isStopped() {
        return getAnimState().name().startsWith("STOP");
    }

    public boolean isMoving() {
        return getAnimState().name().startsWith("MOVE");
    }

    public boolean isAttacking() {
        return getAnimState().name().startsWith("ATTACK");
    }

    public void setMovedLastFrame(boolean movedLastFrame) {
        this.movedLastFrame = movedLastFrame;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean isSolid() {
        return solid;
    }

    public ActorType getType() {
        return type;
    }

    public void setType(ActorType type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void increaseHealth(int count) {
        setHealth(Math.min(getHealth() + count, getMaxHealth()));
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean inVisibleCell() {
        boolean currentColumn =
            World.convertWorldXToCellColumn(x) == World.convertWorldXToCellColumn(world.getWorldCamera().getTargetX());
        boolean currentRow =
            World.convertWorldYToCellRow(y) == World.convertWorldYToCellRow(world.getWorldCamera().getTargetY());

        return currentColumn && currentRow;
    }
}
