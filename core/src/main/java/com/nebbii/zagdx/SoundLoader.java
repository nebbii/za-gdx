package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundLoader {
    private Sound npcTalkingChestLine0;
    private Sound npcBeggarLine0;
    private Sound npcBeggarLine2;
    private Sound npcEnidLine0;
    private Sound npcEnidLine1;
    private Sound npcKrebbLine0;
    private Sound npcKrebbLine1;
    private Sound npcLotharLine8;
    private Sound npcLotharLine9;
    private Sound npcGlebbLine0;
    private Sound npcGlebbLine2;
    private Sound npcOghamLine0;
    private Sound npcOghamLine2;
    private Sound enemySardakRedLine0;
    private Sound enemySardakBlueLine0;
    private Sound enemySardakYellowLine0;
    private Sound enemyLlortLine0;
    private Sound enemySardakRedLine2;
    private Sound enemySardakBlueLine2;
    private Sound enemySardakYellowLine2;

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
        npcKrebbLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/j22a/voice/line0.wav")
        );
        npcKrebbLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/j22a/voice/line1.wav")
        );
        npcLotharLine8 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/j22a/voice/line8.wav")
        );
        npcLotharLine9 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/j22a/voice/line9.wav")
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
        enemySardakRedLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s107/voice/line0.wav")
        );
        enemySardakBlueLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s112/voice/line0.wav")
        );
        enemySardakYellowLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s120/voice/line0.wav")
        );
        enemyLlortLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s121/voice/line0.wav")
        );
        enemySardakRedLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s107/voice/line2.wav")
        );
        enemySardakBlueLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s112/voice/line2.wav")
        );
        enemySardakYellowLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s120/voice/line2.wav")
        );
    }

    public void dispose() {
        npcTalkingChestLine0.dispose();
        npcBeggarLine0.dispose();
        npcBeggarLine2.dispose();
        npcEnidLine0.dispose();
        npcEnidLine1.dispose();
        npcKrebbLine0.dispose();
        npcKrebbLine1.dispose();
        npcLotharLine8.dispose();
        npcLotharLine9.dispose();
        npcGlebbLine0.dispose();
        npcGlebbLine2.dispose();
        npcOghamLine0.dispose();
        npcOghamLine2.dispose();
        enemySardakRedLine0.dispose();
        enemySardakBlueLine0.dispose();
        enemySardakYellowLine0.dispose();
        enemyLlortLine0.dispose();
        enemySardakRedLine2.dispose();
        enemySardakBlueLine2.dispose();
        enemySardakYellowLine2.dispose();
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

    public Sound getNpcKrebbLine0() {
        return npcKrebbLine0;
    }

    public Sound getNpcKrebbLine1() {
      return npcKrebbLine1;
    }

    public Sound getNpcLotharLine8() {
        return npcLotharLine8;
    }

    public Sound getNpcLotharLine9() {
        return npcLotharLine9;
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

    public Sound getEnemySardakRedLine0() {
        return enemySardakRedLine0;
    }

    public Sound getEnemySardakBlueLine0() {
        return enemySardakBlueLine0;
    }

    public Sound getEnemySardakYellowLine0() {
        return enemySardakYellowLine0;
    }

    public Sound getEnemyLlortLine0() {
        return enemyLlortLine0;
    }

    public Sound getEnemySardakRedLine2() {
        return enemySardakRedLine2;
    }

    public Sound getEnemySardakBlueLine2() {
        return enemySardakBlueLine2;
    }

    public Sound getEnemySardakYellowLine2() {
        return enemySardakYellowLine2;
    }

}
