package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.ZeldaAnimation;

public class Zelda extends Rectangle implements Actor {
    private final boolean solid = true;

    private World world;

    private MapManager map; // zelda can spawn actors
    private String locationEntry;

    private boolean movedLastFrame;
    public ZeldaAnimation animation;

    private State state;
    private AnimState animState;
    private float hurtDuration;
    private Direction hurtDirection;

    private ActorType type;
    private int drawOrder;

    private float speed = 120f;
    private int damage;
    private int bonusDamage;
    private int health;

    private Item currentItem;

    private Rectangle hitbox;
    private float hitboxOffsetX = -8;
    private float hitboxOffsetY = 0;

    private float spawnX = -1;
    private float spawnY = -1;

    public Zelda(World world, MapManager map) {
        setWidth(6);
        setHeight(6);
        setState(State.IDLE);
        setAnimState(AnimState.STOPDOWN);
        setType(ActorType.PLAYER);
        setHealth(60);
        setCurrentItem(Treasure.NONE);
        this.drawOrder = 3;

        animation = new ZeldaAnimation(this);
        hitbox = new Rectangle();
        hitbox.setWidth(24);
        hitbox.setHeight(36);

        this.damage = 0;
        this.bonusDamage = 0;
        this.world = world;
        this.map = map;
    }

    @Override
    public void logic() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        hurtDuration = Math.max(0f, hurtDuration - deltaTime);

        if (health <= 0) {
            onDeath();
        }

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
        if (hurtDuration > 0 && getAnimState() != AnimState.GAMEOVER) {
            float t = hurtDuration % 0.08f;

            if (t < 0.04f) {
                batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
            }
            else {
                animation.playCurrentAnimation();
            }
        }
        else {
            batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
        }
    }

    @Override
    public int getDrawOrder() {
        return drawOrder;
    }

    // TODO: Ice physics walking for certain rooms
    public void move(float inputX, float inputY) {
        if (!isActive()) return;
        if (!isMoving() && !isStopped()) return;
        if (inputX == 0 && inputY == 0) return;

        float deltaTime = Gdx.graphics.getDeltaTime();

        setMovedLastFrame(true);

        if (inputX < 0) setAnimState(AnimState.MOVELEFT);
        if (inputY < 0) setAnimState(AnimState.MOVEDOWN);
        if (inputY > 0) setAnimState(AnimState.MOVEUP);
        if (inputX > 0) setAnimState(AnimState.MOVERIGHT);

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

                if (spawnerPitcherFull != null && spawnerPitcherFull.isActive()) {
                    spawnerPitcherFull.activate(world.getGameManager());
                    unequipItem();
                }
                break;
            case PITCHER_FULL:
                SpawnerVialOfWind spawnerVialOfWind = (SpawnerVialOfWind) map.findActorByType(SpawnerVialOfWind.class);

                if (spawnerVialOfWind != null && spawnerVialOfWind.isActive()) {
                    spawnerVialOfWind.activate(world.getGameManager());
                    unequipItem();
                }
                break;
            case LADDER:
                SpawnerLadder spawnerLadder = (SpawnerLadder) map.findActorByType(SpawnerLadder.class);

                if (spawnerLadder != null && spawnerLadder.isActive()) {
                    spawnerLadder.activate(world.getGameManager());
                    unequipItem();
                }
                break;
            case COMPASS_1:
                map.updateSpawnLocation("overworld_entrance_earth");
                world.getGameManager().initializeFadeWarp();
                break;
            default:
            }

            return; // treasures never do the attack animation
        }
        else if (getCurrentItem() instanceof Weapon) {
            switch ((Weapon) getCurrentItem()) {
            case BOOMERANG:
                if (world.getGameManager().getRubies() > 0
                    && map.findActorByType(ZeldaActionBoomerang.class) == null) {
                    world.getGameManager().decreaseRubies(1, true);
                    map.addNewActor(new ZeldaActionBoomerang(this, getX(), getY()));
                }
                break;
            case JADE_RING:
                if (world.getGameManager().getRubies() >= 3
                    && map.findActorByType(ZeldaActionJadeRing.class) == null) {
                    world.getGameManager().decreaseRubies(3, true);
                    map.addNewActor(new ZeldaActionJadeRing(this, getX(), getY()));
                }
                break;
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

        map.addNewActor(new ZeldaActionWand(this, getX(), getY()));
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

    public void onHit(int damage, float knockback) {
        if (!isActive()) return;
        if (hurtDuration > 0) return;

        decreaseHealth(damage);
        increaseHurtDuration(1);
    }

    public void onDeath() {
        setAnimState(AnimState.GAMEOVER);
    }

    public void revive() {
        setHealth(getMaxHealth());
        setAnimState(AnimState.STOPDOWN);
    }

    public Direction getDirection() {
        switch(getAnimState()) {
            case STOPLEFT:
            case MOVELEFT:
            case ATTACKLEFT:
                return Direction.LEFT;
            case STOPDOWN:
            case MOVEDOWN:
            case ATTACKDOWN:
                return Direction.DOWN;
            case STOPUP:
            case MOVEUP:
            case ATTACKUP:
                return Direction.UP;
            case STOPRIGHT:
            case MOVERIGHT:
            case ATTACKRIGHT:
                return Direction.RIGHT;
            default:
                throw new IllegalStateException("Zelda->getDirection(): Unhandled animation state: " + getAnimState());
        }
    }

    public Direction getHurtDirection() {
        return hurtDirection;
    }

    public void setHurtDirection(Direction hurtDirection) {
        this.hurtDirection = hurtDirection;
    }

    public float getSpawnX() {
        return spawnX;
    }

    public float getSpawnY() {
        return spawnY;
    }

    public void setSpawnX(float spawnX) {
        this.spawnX = spawnX;
    }

    public void setSpawnY(float spawnY) {
        this.spawnY = spawnY;
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

    public Array<String> getWeaknesses() {
        return new Array<>();
    }

    // Using this to store scaling wand damage, as zelda has no contact damage
    public int getDamage() {
        damage = 30;

        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_1)) damage += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_2)) damage += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_3)) damage += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_4)) damage += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_5)) damage += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_6)) damage += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_7)) damage += 2;

        return damage;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }

    public int getDefense() {
        int defense = 30;

        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_1)) defense += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_2)) defense += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_3)) defense += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_4)) defense += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_5)) defense += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_6)) defense += 2;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_7)) defense += 2;

        return defense;
    }

    public void unequipItem() {
        setCurrentItem(Treasure.NONE);
        world.getGameManager().getSaveManager().setEquippedItem(Treasure.NONE);
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

    public void increaseHealth(int amount) {
        setHealth(Math.min(getHealth() + amount, getMaxHealth()));
    }

    public void decreaseHealth(int amount) {
        Gdx.app.log(this.getClass().getSimpleName(), "decreasing health (" + getHealth() + ") by " + amount);

        setHealth(Math.min(getHealth() - amount, getMaxHealth()));
    }

    public void increaseHurtDuration(float amount) {
        this.hurtDuration += amount;
    }

    public int getMaxHealth() {
        int maxHealth = 60;

        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_1)) maxHealth += 20;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_2)) maxHealth += 20;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_3)) maxHealth += 20;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_4)) maxHealth += 20;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_5)) maxHealth += 20;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_6)) maxHealth += 20;
        if (world.getGameManager().hasItem(Treasure.CELESTIAL_SIGN_7)) maxHealth += 20;

        return maxHealth;
    }

    public MapManager getMap() {
        return map;
    }

    public void setMap(MapManager map) {
        this.map = map;
    }

    public boolean inVisibleCell() {
        boolean currentColumn =
            World.convertWorldXToCellColumn(x) == World.convertWorldXToCellColumn(world.getWorldCamera().getTargetX());
        boolean currentRow =
            World.convertWorldYToCellRow(y) == World.convertWorldYToCellRow(world.getWorldCamera().getTargetY());

        return currentColumn && currentRow;
    }

    @Override
    public String getLocationEntry() {
        return locationEntry;
    }

    @Override
    public void setLocationEntry(String locationEntry) {
        this.locationEntry = locationEntry;
    }
}
