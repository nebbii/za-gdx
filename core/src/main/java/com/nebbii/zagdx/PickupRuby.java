package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// "There are only two types in the entire game; blue Rupees (5) and yellow Rupees (10)."
public class PickupRuby extends Pickup {
    private RubyType rubyType = RubyType.BLUE;
    private boolean expires = false;

    public PickupRuby() {
        super();

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


    public PickupRuby(RubyType rubyType, boolean expires) {
        super();

        this.rubyType = rubyType;
        this.expires = expires;

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

        if (expires && getDuration() > 6) {
            map.addNewActor(new SpriteSparkle(getCenterPointX(), getCenterPointY()));
            setState(State.DEAD);
        }
    }

    public void draw(SpriteBatch batch) {
        if (!expires || !(getDuration() > 5 && getDuration() % 0.16f > 0.08f)) {
            super.draw(batch);
        }
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);

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
