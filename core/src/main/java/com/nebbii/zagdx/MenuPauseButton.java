package com.nebbii.zagdx;

import com.badlogic.gdx.math.Rectangle;

public interface MenuPauseButton {
    Rectangle getCollisionBox();

    void onTouch();

    default boolean contains(float x, float y) {
        return getCollisionBox().contains(x, y);
    }
}
