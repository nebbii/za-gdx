package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class ArchipelagoConfigScreen extends MenuScreen {
    private static final float LABEL_X = 42f;
    private static final float FIELD_X = 128f;
    private static final float FIELD_WIDTH = 210f;
    private static final float FIELD_HEIGHT = 20f;

    private static final TagOption[] TAG_OPTIONS = {
        new TagOption("DeathLink", "DeathLink")
    };

    private enum EditField {
        NONE,
        SERVER,
        SLOT_NAME
    }

    private BitmapFont font;
    private GlyphLayout layout;
    private ArchipelagoConfigManager configManager;
    private ArchipelagoConfig config;
    private EditField editField = EditField.NONE;
    private String editOriginalValue = "";
    private String status = "";

    public ArchipelagoConfigScreen(Core core) {
        super(core);
    }

    @Override
    public void show() {
        super.show();

        font = new BitmapFont();
        layout = new GlyphLayout();
        background = new Texture(Gdx.files.internal("blank-menu-screen-2.png"));
        configManager = new ArchipelagoConfigManager();

        if (configManager.isEnabled()) {
            config = configManager.load();
        }

        rebuildButtons();
    }

    @Override
    public void logic() {
        if (!isFading() && editField != EditField.NONE) {
            handleTextEdit();
        }

        super.logic();
    }

    @Override
    public void draw() {
        super.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Archipelago", 144, 222);

        if (configManager.isEnabled()) {
            drawEnabledLabels();
        }

        drawStatus();
        batch.end();

        drawFade();
    }

    private void rebuildButtons() {
        menuButtons.clear();

        if (configManager.isEnabled()) {
            addEnabledButtons();
        }
        else {
            addDisabledButtons();
        }

        menuButtons.add(new MenuTextButton("Back", font, 18, 12, 70, 20, () -> {
            setFadeToggle(FadeToggle.OUT);
            core.setNextScreen(new SettingsMenuScreen(core));
        }));
    }

    private void addDisabledButtons() {
        menuButtons.add(new MenuTextButton("Enable", font, 142, 112, 100, 24, () -> {
            config = configManager.enable();
            editField = EditField.NONE;
            status = "";
            rebuildButtons();
        }));
    }

    private void addEnabledButtons() {
        menuButtons.add(new MenuTextButton("Disable", font, 244, 12, 94, 20, () -> {
            configManager.disable();
            config = null;
            editField = EditField.NONE;
            status = "";
            rebuildButtons();
        }));

        menuButtons.add(new MenuTextButton(() -> textFieldLabel(EditField.SERVER), font, FIELD_X, 164, FIELD_WIDTH, FIELD_HEIGHT, () -> {
            startEdit(EditField.SERVER);
        }));

        menuButtons.add(new MenuTextButton(() -> textFieldLabel(EditField.SLOT_NAME), font, FIELD_X, 134, FIELD_WIDTH, FIELD_HEIGHT, () -> {
            startEdit(EditField.SLOT_NAME);
        }));

        for (int i = 0; i < TAG_OPTIONS.length; i++) {
            TagOption option = TAG_OPTIONS[i];
            float y = 100 - i * 22;

            menuButtons.add(new MenuTextButton(() -> tagLabel(option), font, FIELD_X, y, 142, FIELD_HEIGHT, () -> {
                toggleTag(option);
            }));
        }

        menuButtons.add(new MenuTextButton("Save", font, 146, 52, 92, 24, () -> {
            finishEdit();
            configManager.save(config);
            status = "Saved";
        }));
    }

    private void drawEnabledLabels() {
        font.draw(batch, "Enabled", 162, 202);
        font.draw(batch, "Server", LABEL_X, 179);
        font.draw(batch, "Slot", LABEL_X, 149);
        font.draw(batch, "Tags", LABEL_X, 115);
    }

    private void drawStatus() {
        if (status == null || status.length() == 0) {
            return;
        }

        layout.setText(font, status);
        font.draw(batch, status, 192 - layout.width / 2f, 35);
    }

    private void startEdit(EditField field) {
        editField = field;
        editOriginalValue = fieldValue(field);
        status = "Editing";
    }

    private void finishEdit() {
        editField = EditField.NONE;
        editOriginalValue = "";
    }

    private void cancelEdit() {
        setFieldValue(editField, editOriginalValue);
        finishEdit();
        status = "";
    }

    private void handleTextEdit() {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_ENTER)) {
            finishEdit();
            status = "";
            return;
        }

        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            cancelEdit();
            return;
        }

        if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE) || Gdx.input.isKeyJustPressed(Keys.DEL)) {
            String value = fieldValue(editField);

            if (value.length() > 0) {
                setFieldValue(editField, value.substring(0, value.length() - 1));
            }
        }

        if (Gdx.input.isKeyJustPressed(Keys.FORWARD_DEL)) {
            setFieldValue(editField, "");
        }

        for (int keyCode = 1; keyCode <= Keys.MAX_KEYCODE; keyCode++) {
            if (!Gdx.input.isKeyJustPressed(keyCode)) {
                continue;
            }

            Character character = characterForKey(keyCode);

            if (character != null) {
                setFieldValue(editField, fieldValue(editField) + character);
            }
        }
    }

    private Character characterForKey(int keyCode) {
        boolean shift = Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT);

        if (keyCode >= Keys.A && keyCode <= Keys.Z) {
            char character = (char) ('a' + keyCode - Keys.A);
            return shift ? Character.toUpperCase(character) : character;
        }

        if (keyCode >= Keys.NUM_0 && keyCode <= Keys.NUM_9) {
            return shiftedNumber(keyCode - Keys.NUM_0, shift);
        }

        if (keyCode >= Keys.NUMPAD_0 && keyCode <= Keys.NUMPAD_9) {
            return (char) ('0' + keyCode - Keys.NUMPAD_0);
        }

        switch (keyCode) {
            case Keys.SPACE:
                return ' ';
            case Keys.PERIOD:
            case Keys.NUMPAD_DOT:
                return shift ? '>' : '.';
            case Keys.COMMA:
            case Keys.NUMPAD_COMMA:
                return shift ? '<' : ',';
            case Keys.COLON:
                return ':';
            case Keys.SEMICOLON:
                return shift ? ':' : ';';
            case Keys.SLASH:
            case Keys.NUMPAD_DIVIDE:
                return shift ? '?' : '/';
            case Keys.BACKSLASH:
                return shift ? '|' : '\\';
            case Keys.MINUS:
            case Keys.NUMPAD_SUBTRACT:
                return shift ? '_' : '-';
            case Keys.EQUALS:
            case Keys.NUMPAD_EQUALS:
                return shift ? '+' : '=';
            case Keys.LEFT_BRACKET:
                return shift ? '{' : '[';
            case Keys.RIGHT_BRACKET:
                return shift ? '}' : ']';
            case Keys.APOSTROPHE:
                return shift ? '"' : '\'';
            case Keys.GRAVE:
                return shift ? '~' : '`';
            case Keys.AT:
                return '@';
            case Keys.PLUS:
            case Keys.NUMPAD_ADD:
                return '+';
            case Keys.STAR:
            case Keys.NUMPAD_MULTIPLY:
                return '*';
            default:
                return null;
        }
    }

    private Character shiftedNumber(int number, boolean shift) {
        if (!shift) {
            return (char) ('0' + number);
        }

        switch (number) {
            case 0:
                return ')';
            case 1:
                return '!';
            case 2:
                return '@';
            case 3:
                return '#';
            case 4:
                return '$';
            case 5:
                return '%';
            case 6:
                return '^';
            case 7:
                return '&';
            case 8:
                return '*';
            case 9:
                return '(';
            default:
                return null;
        }
    }

    private String textFieldLabel(EditField field) {
        String value = fieldValue(field);

        if (editField == field) {
            return value + "_";
        }

        if (value == null || value.length() == 0) {
            return "(blank)";
        }

        return value;
    }

    private String fieldValue(EditField field) {
        if (config == null) {
            return "";
        }

        if (field == EditField.SERVER) {
            return nullToEmpty(config.server);
        }

        if (field == EditField.SLOT_NAME) {
            return nullToEmpty(config.slotName);
        }

        return "";
    }

    private void setFieldValue(EditField field, String value) {
        if (config == null) {
            return;
        }

        if (field == EditField.SERVER) {
            config.server = value;
        }
        else if (field == EditField.SLOT_NAME) {
            config.slotName = value;
        }
    }

    private void toggleTag(TagOption option) {
        configManager.setTagEnabled(config, option.tag, !configManager.hasTag(config, option.tag));
        status = "";
    }

    private String tagLabel(TagOption option) {
        return (configManager.hasTag(config, option.tag) ? "[X] " : "[ ] ") + option.label;
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }

    private static class TagOption {
        final String tag;
        final String label;

        TagOption(String tag, String label) {
            this.tag = tag;
            this.label = label;
        }
    }
}
