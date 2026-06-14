package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundLoader {
    private Sound npcTalkingChestLine0;
    private Sound npcBeggarLine0;
    private Sound npcBeggarLine2;
    private Sound npcEnidLine0;
    private Sound npcEnidLine1;
    private Sound npcGlebbLine0;
    private Sound npcGlebbLine2;
    private Sound npcOghamLine0;
    private Sound npcOghamLine2;

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
        npcEnidLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/h29/voice/line0.wav")
        );
        npcEnidLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/h29/voice/line1.wav")
        );
        npcGlebbLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/j24/voice/line0.wav")
        );
        npcGlebbLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/j24/voice/line2.wav")
        );
        npcOghamLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/f26/voice/line0.wav")
        );
        npcOghamLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/f26/voice/line2.wav")
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

    public Sound getNpcOghamLine0() {
        return npcOghamLine0;
    }

      public Sound getNpcOghamLine2() {
        return npcOghamLine2;
    }

    public Sound getNpcGlebbLine0() {
		return npcGlebbLine0;
	}

    public Sound getNpcGlebbLine2() {
		return npcGlebbLine2;
	}

    public Sound getNpcEnidLine0() {
		return npcEnidLine0;
	}

	public Sound getNpcEnidLine1() {
		return npcEnidLine1;
	}

}
