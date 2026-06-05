package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class HudNumberRenderer {
    public static final float DIGIT_SPACING = 11f;

    private HudNumberRenderer() {
    }

    public static void draw(SpriteBatch batch, int number, int minDigits, float x, float y) {
        draw(batch, formatNumber(number, minDigits), x, y);
    }

    public static void drawCentered(SpriteBatch batch, int number, int minDigits, float x, float y, float width, float height) {
        String text = formatNumber(number, minDigits);
        float textWidth = getTextWidth(text);
        float textHeight = getTextHeight(text);

        draw(batch, text, x + (width - textWidth) / 2f, y + (height - textHeight) / 2f);
    }

    private static void draw(SpriteBatch batch, String text, float x, float y) {
        int digitIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            int digit = Character.getNumericValue(text.charAt(i));

            if (digit < 0 || digit > 9) {
                continue;
            }

            Texture digitTexture = World.images.getHudNumber(digit);
            batch.draw(digitTexture, x + digitIndex * DIGIT_SPACING, y);
            digitIndex++;
        }
    }

    private static String formatNumber(int number, int minDigits) {
        String text = String.valueOf(number);

        while (text.length() < minDigits) {
            text = "0" + text;
        }

        return text;
    }

    private static float getTextWidth(String text) {
        int digitCount = 0;
        float lastDigitWidth = 0f;

        for (int i = 0; i < text.length(); i++) {
            int digit = Character.getNumericValue(text.charAt(i));

            if (digit < 0 || digit > 9) {
                continue;
            }

            digitCount++;
            lastDigitWidth = World.images.getHudNumber(digit).getWidth();
        }

        if (digitCount == 0) {
            return 0f;
        }

        return (digitCount - 1) * DIGIT_SPACING + lastDigitWidth;
    }

    private static float getTextHeight(String text) {
        float textHeight = 0f;

        for (int i = 0; i < text.length(); i++) {
            int digit = Character.getNumericValue(text.charAt(i));

            if (digit < 0 || digit > 9) {
                continue;
            }

            textHeight = Math.max(textHeight, World.images.getHudNumber(digit).getHeight());
        }

        return textHeight;
    }
}
