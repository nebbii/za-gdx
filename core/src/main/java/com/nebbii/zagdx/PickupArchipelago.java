package com.nebbii.zagdx;

public class PickupArchipelago extends Pickup {
    public PickupArchipelago() {
        super();

        setImage(World.images.getArchipelagoColor());

        baseOffsetX = 0;
        baseOffsetY = 0;

        setWidth(31);
        setHeight(31);
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);
        this.setState(State.DEAD);
    }
}
