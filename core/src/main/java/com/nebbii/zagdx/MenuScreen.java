package com.nebbii.zagdx;

import java.util.ArrayList;
import java.util.EnumMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

// TODO: Eventually swap out the dummy assets with real ones once the exporter supports it
public class MenuScreen implements Screen {
    static final int WORLD_WIDTH = 384;
    static final int WORLD_HEIGHT = 240;
    private static final ControlAction[] MENU_INPUTS = {
        ControlAction.MOVE_UP,
        ControlAction.MOVE_DOWN,
        ControlAction.MOVE_LEFT,
        ControlAction.MOVE_RIGHT,
        ControlAction.ACTION
    };

    protected SpriteBatch batch;
    protected ShapeRenderer shapes;

    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected final Vector2 touchPos = new Vector2();

    protected Texture background;
    protected Texture buttonTexture;
    public float fadeOpacity;

    protected final Core core;

    protected ArrayList<MenuButton> menuButtons;
    protected int selectedButtonIndex;
    private final EnumMap<ControlAction, Boolean> menuInputWasPressed = new EnumMap<>(ControlAction.class);

    public enum FadeToggle {
        IN,
        OUT
    }

    private FadeToggle fadeToggle;

    public MenuScreen(Core core) {
        this.core = core;
    }

    public void show() {
        batch = new SpriteBatch();
        shapes = new ShapeRenderer();

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply(true);

        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0f);
        camera.update();

        fadeToggle = FadeToggle.IN;
        fadeOpacity = 1f;

        buttonTexture = new Texture(Gdx.files.internal("menu-button.png"));
        menuButtons = new ArrayList<>();
        selectedButtonIndex = 0;
        menuInputWasPressed.clear();
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
    }

    public void logic() {
        if (isFading()) {
            rememberMenuInputState();
            return;
        }
        else if (fadeToggle == FadeToggle.OUT && core.getNextScreen() != null) {
            core.setScreen(core.getNextScreen());
            return;
        }

        handleMenuControls();
        handleTouchControls();
    }

    private void handleMenuControls() {
        for (ControlAction action : MENU_INPUTS) {
            boolean pressed = core.getControlInput().isActionPressed(action);
            boolean wasPressed = menuInputWasPressed.getOrDefault(action, false);

            if (pressed && !wasPressed) {
                handleMenuInput(action);
            }

            menuInputWasPressed.put(action, pressed);
        }
    }

    private void rememberMenuInputState() {
        if (core == null || core.getControlInput() == null) {
            return;
        }

        for (ControlAction action : MENU_INPUTS) {
            menuInputWasPressed.put(action, core.getControlInput().isActionPressed(action));
        }
    }

    private void handleTouchControls() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            ArrayList<MenuButton> buttons = getSelectableMenuButtons();

            for (int i = 0; i < buttons.size(); i++) {
                MenuButton button = buttons.get(i);

                if (button.contains(touchPos.x, touchPos.y)) {
                    selectedButtonIndex = i;
                    button.onTouch();
                    break;
                }
            }
        }
    }

    public boolean handleMenuInput(ControlAction action) {
        switch(action) {
        case MOVE_UP:
        case MOVE_DOWN:
        case MOVE_LEFT:
        case MOVE_RIGHT:
            return moveSelection(action);
        case ACTION:
            return activateSelectedButton();
        default:
            return false;
        }
    }

    public boolean moveSelection(ControlAction action) {
        MenuButton currentButton = getSelectedButton();

        if (currentButton == null) {
            selectedButtonIndex = 0;
            return false;
        }

        ArrayList<MenuButton> buttons = getSelectableMenuButtons();
        Rectangle currentBounds = currentButton.getCollisionBox();
        float currentCenterX = centerX(currentBounds);
        float currentCenterY = centerY(currentBounds);
        int bestIndex = -1;
        float bestDistance = Float.MAX_VALUE;

        for (int i = 0; i < buttons.size(); i++) {
            if (i == selectedButtonIndex) {
                continue;
            }

            Rectangle candidateBounds = buttons.get(i).getCollisionBox();
            float dx = centerX(candidateBounds) - currentCenterX;
            float dy = centerY(candidateBounds) - currentCenterY;

            if (!isInDirection(action, dx, dy)) {
                continue;
            }

            float distance = dx * dx + dy * dy;

            if (distance < bestDistance) {
                bestDistance = distance;
                bestIndex = i;
            }
        }

        if (bestIndex < 0) {
            return false;
        }

        selectedButtonIndex = bestIndex;
        return true;
    }

    public boolean activateSelectedButton() {
        MenuButton selectedButton = getSelectedButton();

        if (selectedButton == null) {
            return false;
        }

        selectedButton.onTouch();
        return true;
    }

    protected ArrayList<MenuButton> getSelectableMenuButtons() {
        return menuButtons;
    }

    public MenuButton getSelectedButton() {
        ArrayList<MenuButton> buttons = getSelectableMenuButtons();

        if (buttons.isEmpty()) {
            return null;
        }

        if (selectedButtonIndex < 0 || selectedButtonIndex >= buttons.size()) {
            selectedButtonIndex = 0;
        }

        return buttons.get(selectedButtonIndex);
    }

    protected void drawSelectionCursor() {
        MenuButton selectedButton = getSelectedButton();

        if (selectedButton == null) {
            return;
        }

        Rectangle bounds = selectedButton.getCollisionBox();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setColor(1f, 1f, 1f, 0.85f);
        shapes.rect(bounds.x - 2f, bounds.y - 2f, bounds.width + 4f, bounds.height + 4f);
        shapes.end();
    }

    private boolean isInDirection(ControlAction action, float dx, float dy) {
        switch(action) {
        case MOVE_UP:
            return dy > 0f;
        case MOVE_DOWN:
            return dy < 0f;
        case MOVE_LEFT:
            return dx < 0f;
        case MOVE_RIGHT:
            return dx > 0f;
        default:
            return false;
        }
    }

    private float centerX(Rectangle bounds) {
        return bounds.x + bounds.width / 2f;
    }

    private float centerY(Rectangle bounds) {
        return bounds.y + bounds.height / 2f;
    }

    public void draw(){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        ScreenUtils.clear(0f, 0f, 0f, 1f);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        for (MenuButton button : menuButtons) {
            button.draw(batch);
        }
        batch.end();

        drawSelectionCursor();
    }

    public void drawFade() {
        switch(fadeToggle) {
			case IN:
            if (fadeOpacity > 0) {
                fadeOpacity -= Gdx.graphics.getDeltaTime() * 2;
                if (fadeOpacity < 0) fadeOpacity = 0;
            }
				break;
			case OUT:
            if (fadeOpacity < 1) {
                fadeOpacity += Gdx.graphics.getDeltaTime() * 2;
                if (fadeOpacity > 1) fadeOpacity = 1;
            }
				break;
			default:
				break;
        }

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(0f, 0f, 0f, fadeOpacity);
        shapes.rect(0f, 0f, WORLD_WIDTH, WORLD_HEIGHT);
        shapes.end();
    }

    public boolean isFading() {
        switch(fadeToggle) {
        case IN:
            return fadeOpacity > 0f;
        case OUT:
            return fadeOpacity < 1f;
        default:
            return fadeOpacity > 0f && fadeOpacity < 1f;
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        buttonTexture.dispose();
    }

    public FadeToggle getFadeToggle() {
        return fadeToggle;
    }

    public void setFadeToggle(FadeToggle fadeToggle) {
        this.fadeToggle = fadeToggle;
    }

    public Texture getButtonTexture() {
        return buttonTexture;
    }
}
