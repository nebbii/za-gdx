package com.nebbii.zagdx;

import java.util.function.Supplier;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuTextButton implements MenuButton {
    private static final float LABEL_PADDING = 4f;

    private final Rectangle collisionBox;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private final Supplier<String> labelSupplier;
    private final Runnable touchHandler;

    public MenuTextButton(String label, BitmapFont font, float x, float y, float width, float height, Runnable touchHandler) {
        this(() -> label, font, x, y, width, height, touchHandler);
    }

    public MenuTextButton(Supplier<String> labelSupplier, BitmapFont font, float x, float y, float width, float height, Runnable touchHandler) {
        this.collisionBox = new Rectangle(x, y, width, height);
        this.font = font;
        this.layout = new GlyphLayout();
        this.labelSupplier = labelSupplier;
        this.touchHandler = touchHandler;
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void draw(SpriteBatch batch) {
        String label = labelSupplier.get();
        float originalScaleX = font.getData().scaleX;
        float originalScaleY = font.getData().scaleY;

        layout.setText(font, label);

        float maxWidth = collisionBox.width - LABEL_PADDING * 2f;
        if (layout.width > maxWidth && layout.width > 0f) {
            float scale = maxWidth / layout.width;
            font.getData().setScale(originalScaleX * scale, originalScaleY * scale);
            layout.setText(font, label);
        }

        float x = collisionBox.x + (collisionBox.width - layout.width) / 2f;
        float y = collisionBox.y + (collisionBox.height + layout.height) / 2f;

        font.draw(batch, label, x, y);
        font.getData().setScale(originalScaleX, originalScaleY);
    }

    @Override
    public void onTouch() {
        touchHandler.run();
    }
}
