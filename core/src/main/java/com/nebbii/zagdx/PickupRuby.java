package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// "There are only two types in the entire game; blue Rupees (5) and yellow Rupees (10)."
public class PickupRuby extends Pickup {
    public enum RubyType {
        BLUE,
        YELLOW
    }

    private RubyType rubyType;
    private Texture image;

    public PickupRuby(RubyType rubyType) {
        super();

        this.rubyType = rubyType;

        switch(getRubyType()) {
        case BLUE:
            setImage(World.images.getRubyBlue());
            break;
        case YELLOW:
            setImage(World.images.getRubyBlue());
            break;
        default:
            throw new IllegalStateException("PickupRuby: Unhandled ruby type: " + getRubyType());
        }

        setWidth(10);
        setHeight(14);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getImage(), getX(), getY(), getWidth(), getHeight());
    }

    public void onPickup(GameManager game) {
        switch(getRubyType()) {
        case BLUE:
            game.increaseRubies(5);
            break;
        case YELLOW:
            game.increaseRubies(10);
            break;
        default:
            throw new IllegalStateException("PickupRuby: Unhandled ruby type: " + getRubyType());
        }

        this.setState(State.DEAD);
    }

    private RubyType getRubyType() {
        return rubyType;
    }
}
