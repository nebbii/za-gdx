package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.Texture;

public class ZeldaAction extends Projectile {
    protected Zelda zelda;
    protected Texture image;

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public ZeldaAction(Actor actor, float x, float y) {
        super(actor, x, y);
        this.zelda = (Zelda) actor;
    }
}
