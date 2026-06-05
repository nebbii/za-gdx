package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.PickupFirestormAnimation;

public class PickupFirestorm extends Pickup {
    public PickupFirestormAnimation animation;
    private float drawTime;

    public PickupFirestorm() {
        super();

        setImage(World.images.getFirestorm());

        setWidth(36);
        setHeight(32);

        this.animation = new PickupFirestormAnimation(this);
        this.drawTime = 0f;
    }

    public void logic() {
        super.logic();
        drawTime += Gdx.graphics.getDeltaTime();
    }

    public void draw(SpriteBatch batch) {
        float bounceOffsetY = 0f;

        if (isPending()) {
            float decay = (float) Math.exp(-4 * drawTime);
            float bounce = (float) Math.pow(Math.abs(Math.sin(8f * Math.PI * drawTime)), 1.5f) * decay;
            bounceOffsetY = bounce * 10f;
        }

        batch.draw(
            animation.playCurrentAnimation(),
            animation.getX(),
            animation.getY() + bounceOffsetY,
            getWidth(),
            getHeight()
        );

        if (isActive()) {
            drawPrice(batch, animation.getX(), animation.getY(), getWidth(), getHeight());
        }
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);
        game.addWeapon(Weapon.FIRESTORM, true);
        this.setState(State.DEAD);
    }
}
