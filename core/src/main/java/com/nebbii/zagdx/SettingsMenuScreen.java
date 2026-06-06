package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

// sloppy poorly aligned menu screen sorryyy
public class SettingsMenuScreen extends MenuScreen {
    private BitmapFont font;

    public SettingsMenuScreen(Core core) {
        super(core);
    }

    @Override
    public void show() {
        super.show();

        font = new BitmapFont();
        background = new Texture(Gdx.files.internal("blank-menu-screen-2.png"));

        menuButtons.add(new MenuTextButton("Controls", font, 142, 149, 100, 24, () -> {
            setFadeToggle(FadeToggle.OUT);
            core.setNextScreen(new ControlBindingScreen(core));
        }));

        menuButtons.add(new MenuTextButton("Archipelago", font, 142, 119, 100, 24, () -> {
            setFadeToggle(FadeToggle.OUT);
            core.setNextScreen(new ControlBindingScreen(core));
        }));

        menuButtons.add(new MenuTextButton("Back", font, 142, 82, 100, 24, () -> {
            setFadeToggle(FadeToggle.OUT);
            core.setNextScreen(new MainMenuScreen(core));
        }));
    }

    @Override
    public void draw() {
        super.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Settings", 165, 200);
        batch.end();

        drawFade();
    }
}
