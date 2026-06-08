package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

// "There are only two types in the entire game; blue Rupees (5) and yellow Rupees (10)."
public class PickupRuby extends Pickup {
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
            setImage(World.images.getRubyYellow());
            break;
        default:
            throw new IllegalStateException("PickupRuby: Unhandled ruby type: " + getRubyType());
        }

        setWidth(10);
        setHeight(14);
    }

    public void logic() {
        super.logic();

        if (getDuration() > 6) {
            map.addNewActor(new SpriteSparkle(getCenterPointX(), getCenterPointY()));
            setState(State.DEAD);
        }
    }

    public void draw(SpriteBatch batch) {
        if (!(getDuration() > 5 && getDuration() % 0.16f > 0.08f)) {
            super.draw(batch);
        }
    }

    public void onPickup(GameManager game) {
        switch(getRubyType()) {
        case BLUE:
            game.increaseRubies(5, true);
            break;
        case YELLOW:
            game.increaseRubies(10, true);
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
