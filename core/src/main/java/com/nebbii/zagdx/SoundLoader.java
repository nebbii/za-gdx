package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundLoader {
    private Sound npcTalkingChestLine0;

    public SoundLoader() {
        npcTalkingChestLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s108/voice/line0.wav")
        );
    }

    public void dispose() {
        npcTalkingChestLine0.dispose();
    }

    public Sound getNpcTalkingChestLine0() {
        return npcTalkingChestLine0;
    }
}
