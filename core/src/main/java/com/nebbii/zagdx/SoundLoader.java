package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundLoader {
    private Sound npcTalkingChestLine0;
    private Sound npcBeggarLine0;
    private Sound npcBeggarLine2;

    public SoundLoader() {
        npcTalkingChestLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s108/voice/line0.wav")
        );
        npcBeggarLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/j22/voice/line0.wav")
        );
        npcBeggarLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/j22/voice/line2.wav")
        );
    }

    public void dispose() {
        npcTalkingChestLine0.dispose();
    }

    public Sound getNpcTalkingChestLine0() {
        return npcTalkingChestLine0;
    }

    public Sound getNpcBeggarLine0() {
		return npcBeggarLine0;
	}

    public Sound getNpcBeggarLine2() {
		return npcBeggarLine2;
	}
}
