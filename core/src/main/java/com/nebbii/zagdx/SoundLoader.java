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
    private Sound npcExhaustedTravelerLine5;
    private Sound npcBlueLadyLine11;
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
    private Sound npcArcheryMinigameOwnerLine0;
    private Sound npcBitterbeckLine0;
    private Sound npcBlacksmithLine0;
    private Sound npcBlacksmithLine1;
    private Sound npcDressyWomanLine1;
    private Sound npcFriendlyGoblinLine0;
    private Sound npcDenGoblinLine0;
    private Sound npcHoodedWomanLine0;
    private Sound npcHoodedWomanLine1;
    private Sound npcHoodedWomanLine2;
    private Sound npcHouseKeeperLine0;
    private Sound npcHouseKeeperLine1;
    private Sound npcKronThePeglegLine1;
    private Sound npcLivingFarmerLine0;
    private Sound npcMadameKriggleLine0;
    private Sound npcMadameKriggleLine1;
    private Sound npcMadameKriggleLine2;
    private Sound npcMadameKriggleLine3;
    private Sound npcBeachcomberLine0;
    private Sound npcBeachcomberLine1;
    private Sound npcYelenaLine0;
    private Sound npcYelenaLine1;
    private Sound npcYelenaLine2;
    private Sound npcYelenaLine3;
    private Sound npcYelenaLine4;
    private Sound npcGiantSwampRatLine0;

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
        npcExhaustedTravelerLine5 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/j22a/voice/line5.wav")
        );
        npcBlueLadyLine11 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/j22a/voice/line11.wav")
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
        npcArcheryMinigameOwnerLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s606/voice/line0.wav")
        );
        npcBitterbeckLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/y5/voice/line0.wav")
        );
        npcBlacksmithLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/l13a/voice/line0.wav")
        );
        npcBlacksmithLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/l13a/voice/line1.wav")
        );
        npcDressyWomanLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/l15/voice/line1.wav")
        );
        npcFriendlyGoblinLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/h9/voice/line0.wav")
        );
        npcDenGoblinLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/g9/voice/line0.wav")
        );
        npcHoodedWomanLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v16/voice/line0.wav")
        );
        npcHoodedWomanLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v16/voice/line1.wav")
        );
        npcHoodedWomanLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v16/voice/line2.wav")
        );
        npcHouseKeeperLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/w10/voice/line0.wav")
        );
        npcHouseKeeperLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/w10/voice/line1.wav")
        );
        npcKronThePeglegLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/p12/voice/line1.wav")
        );
        npcLivingFarmerLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/l17/voice/line0.wav")
        );
        npcMadameKriggleLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k13b/voice/line0.wav")
        );
        npcMadameKriggleLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k13b/voice/line1.wav")
        );
        npcMadameKriggleLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k13b/voice/line2.wav")
        );
        npcMadameKriggleLine3 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k13b/voice/line3.wav")
        );
        npcBeachcomberLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/t20/voice/line0.wav")
        );
        npcBeachcomberLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/t20/voice/line1.wav")
        );
        npcYelenaLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/u16/voice/line0.wav")
        );
        npcYelenaLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/u16/voice/line1.wav")
        );
        npcYelenaLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/u16/voice/line2.wav")
        );
        npcYelenaLine3 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/u16/voice/line3.wav")
        );
        npcYelenaLine4 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/u16/voice/line4.wav")
        );
        npcGiantSwampRatLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v14/voice/line0.wav")
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
        npcExhaustedTravelerLine5.dispose();
        npcBlueLadyLine11.dispose();
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
        npcArcheryMinigameOwnerLine0.dispose();
        npcBitterbeckLine0.dispose();
        npcBlacksmithLine0.dispose();
        npcBlacksmithLine1.dispose();
        npcDressyWomanLine1.dispose();
        npcFriendlyGoblinLine0.dispose();
        npcDenGoblinLine0.dispose();
        npcHoodedWomanLine0.dispose();
        npcHoodedWomanLine1.dispose();
        npcHoodedWomanLine2.dispose();
        npcHouseKeeperLine0.dispose();
        npcHouseKeeperLine1.dispose();
        npcKronThePeglegLine1.dispose();
        npcLivingFarmerLine0.dispose();
        npcMadameKriggleLine0.dispose();
        npcMadameKriggleLine1.dispose();
        npcMadameKriggleLine2.dispose();
        npcMadameKriggleLine3.dispose();
        npcBeachcomberLine0.dispose();
        npcBeachcomberLine1.dispose();
        npcYelenaLine0.dispose();
        npcYelenaLine1.dispose();
        npcYelenaLine2.dispose();
        npcYelenaLine3.dispose();
        npcYelenaLine4.dispose();
        npcGiantSwampRatLine0.dispose();
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

    public Sound getNpcExhaustedTravelerLine5() {
        return npcExhaustedTravelerLine5;
    }

    public Sound getNpcBlueLadyLine11() {
        return npcBlueLadyLine11;
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

    public Sound getNpcArcheryMinigameOwnerLine0() {
        return npcArcheryMinigameOwnerLine0;
    }

    public Sound getNpcBitterbeckLine0() {
        return npcBitterbeckLine0;
    }

    public Sound getNpcBlacksmithLine0() {
        return npcBlacksmithLine0;
    }

    public Sound getNpcBlacksmithLine1() {
        return npcBlacksmithLine1;
    }

    public Sound getNpcDressyWomanLine1() {
        return npcDressyWomanLine1;
    }

    public Sound getNpcFriendlyGoblinLine0() {
        return npcFriendlyGoblinLine0;
    }

    public Sound getNpcDenGoblinLine0() {
        return npcDenGoblinLine0;
    }

    public Sound getNpcHoodedWomanLine0() {
        return npcHoodedWomanLine0;
    }

    public Sound getNpcHoodedWomanLine1() {
        return npcHoodedWomanLine1;
    }

    public Sound getNpcHoodedWomanLine2() {
        return npcHoodedWomanLine2;
    }

    public Sound getNpcHouseKeeperLine0() {
        return npcHouseKeeperLine0;
    }

    public Sound getNpcHouseKeeperLine1() {
        return npcHouseKeeperLine1;
    }

    public Sound getNpcKronThePeglegLine1() {
        return npcKronThePeglegLine1;
    }

    public Sound getNpcLivingFarmerLine0() {
        return npcLivingFarmerLine0;
    }

    public Sound getNpcMadameKriggleLine0() {
        return npcMadameKriggleLine0;
    }

    public Sound getNpcMadameKriggleLine1() {
        return npcMadameKriggleLine1;
    }

    public Sound getNpcMadameKriggleLine2() {
        return npcMadameKriggleLine2;
    }

    public Sound getNpcMadameKriggleLine3() {
        return npcMadameKriggleLine3;
    }

    public Sound getNpcBeachcomberLine0() {
        return npcBeachcomberLine0;
    }

    public Sound getNpcBeachcomberLine1() {
        return npcBeachcomberLine1;
    }

    public Sound getNpcYelenaLine0() {
        return npcYelenaLine0;
    }

    public Sound getNpcYelenaLine1() {
        return npcYelenaLine1;
    }

    public Sound getNpcYelenaLine2() {
        return npcYelenaLine2;
    }

    public Sound getNpcYelenaLine3() {
        return npcYelenaLine3;
    }

    public Sound getNpcYelenaLine4() {
        return npcYelenaLine4;
    }

    public Sound getNpcGiantSwampRatLine0() {
        return npcGiantSwampRatLine0;
    }

}
