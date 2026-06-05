package com.nebbii.zagdx;

public class SpawnerPickup extends Spawner {
    public enum Trigger {
        MANUAL,
        NO_ENEMIES
    }

    private Trigger trigger;

    public SpawnerPickup() {
        super();
        setTrigger(Trigger.MANUAL);
    }

    @Override
    public void logic() {
        super.logic();
        if (!isActive()) return;

        switch (trigger) {
        case MANUAL:
            break;
        case NO_ENEMIES:
            if (!map.activeActorsContain(Enemy.class)) {
                activate();
            }
            break;
        default:
            throw new IllegalStateException("Unhandled spawner pickup trigger: " + trigger);
        }
    }

    @Override
    public void activate() {
        if (!isActive()) return;

        placePickupWithParent();
        setState(State.DEAD);
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger == null ? Trigger.MANUAL : trigger;
    }

    public void setTrigger(String trigger) {
        if (trigger == null || trigger.trim().isEmpty()) {
            setTrigger(Trigger.MANUAL);
            return;
        }

        setTrigger(Trigger.valueOf(trigger.trim().toUpperCase()));
    }
}
