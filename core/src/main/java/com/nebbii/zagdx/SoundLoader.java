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
    private Sound npcMysticLine0;
    private Sound npcOldSailorLine0;
    private Sound npcRebelRightLine0;
    private Sound npcSailorLine0;
    private Sound npcKnaveLine0;
    private Sound npcKnaveLine1;
    private Sound npcKnaveLine4;
    private Sound npcFatShopkeeperLine5;
    private Sound npcFatShopkeeperLine6;
    private Sound npcShurmakLine8;
    private Sound npcSirramBewLine0;
    private Sound npcSirramBewLine1;
    private Sound npcSirramBewLine2;
    private Sound npcSirramBewLine3;
    private Sound npcSleepingManLine0;
    private Sound npcThirstyChildLine0;
    private Sound npcThirstyChildLine1;
    private Sound npcRycoLine0;
    private Sound npcWaldensopLine0;
    private Sound npcWaldensopLine1;
    private Sound npcWaldensopLine2;
    private Sound npcSkaterDudeLine0;
    private Sound npcStallOwner2Line0;
    private Sound npcTalkingMushroomLine0;
    private Sound npcThabulLine0;
    private Sound npcThabulLine1;
    private Sound npcToobarLine0;
    private Sound npcToobarLine1;
    private Sound npcToobarLine2;
    private Sound npcTownMerchantLine6;
    private Sound npcTownMerchantLine7;
    private Sound npcTownMerchantLine8;
    private Sound npcForeignWomanLine5;
    private Sound npcMerribalLine0;
    private Sound npcSquireGripLine1;
    private Sound npcSquireGripLine3;

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
        npcMysticLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v11/voice/line0.wav")
        );
        npcOldSailorLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/p20a/voice/line0.wav")
        );
        npcRebelRightLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/z13/voice/line0.wav")
        );
        npcSailorLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/l27/voice/line0.wav")
        );
        npcKnaveLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k13a/voice/line0.wav")
        );
        npcKnaveLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k13a/voice/line1.wav")
        );
        npcKnaveLine4 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k13a/voice/line4.wav")
        );
        npcFatShopkeeperLine5 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k13a/voice/line5.wav")
        );
        npcFatShopkeeperLine6 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k13a/voice/line6.wav")
        );
        npcShurmakLine8 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/p15/voice/line8.wav")
        );
        npcSirramBewLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v16a/voice/line0.wav")
        );
        npcSirramBewLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v16a/voice/line1.wav")
        );
        npcSirramBewLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v16a/voice/line2.wav")
        );
        npcSirramBewLine3 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v16a/voice/line3.wav")
        );
        npcSleepingManLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/n17/voice/line0.wav")
        );
        npcThirstyChildLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s605/voice/line0.wav")
        );
        npcThirstyChildLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s605/voice/line1.wav")
        );
        npcRycoLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/e7/voice/line0.wav")
        );
        npcWaldensopLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/n11/voice/line0.wav")
        );
        npcWaldensopLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/n11/voice/line1.wav")
        );
        npcWaldensopLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/n11/voice/line2.wav")
        );
        npcSkaterDudeLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/g11/voice/line0.wav")
        );
        npcStallOwner2Line0 = Gdx.audio.newSound(
            Gdx.files.internal("export/underworld/s603/voice/line0.wav")
        );
        npcTalkingMushroomLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k20/voice/line0.wav")
        );
        npcThabulLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v5/voice/line0.wav")
        );
        npcThabulLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/v5/voice/line1.wav")
        );
        npcToobarLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/l27a/voice/line0.wav")
        );
        npcToobarLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/l27a/voice/line1.wav")
        );
        npcToobarLine2 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/l27a/voice/line2.wav")
        );
        npcTownMerchantLine6 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k14/voice/line6.wav")
        );
        npcTownMerchantLine7 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k14/voice/line7.wav")
        );
        npcTownMerchantLine8 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/k14/voice/line8.wav")
        );
        npcForeignWomanLine5 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/p20a/voice/line5.wav")
        );
        npcMerribalLine0 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/r10/voice/line0.wav")
        );
        npcSquireGripLine1 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/r10/voice/line1.wav")
        );
        npcSquireGripLine3 = Gdx.audio.newSound(
            Gdx.files.internal("export/overworld/r10/voice/line3.wav")
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
        npcMysticLine0.dispose();
        npcOldSailorLine0.dispose();
        npcRebelRightLine0.dispose();
        npcSailorLine0.dispose();
        npcKnaveLine0.dispose();
        npcKnaveLine1.dispose();
        npcKnaveLine4.dispose();
        npcFatShopkeeperLine5.dispose();
        npcFatShopkeeperLine6.dispose();
        npcShurmakLine8.dispose();
        npcSirramBewLine0.dispose();
        npcSirramBewLine1.dispose();
        npcSirramBewLine2.dispose();
        npcSirramBewLine3.dispose();
        npcSleepingManLine0.dispose();
        npcThirstyChildLine0.dispose();
        npcThirstyChildLine1.dispose();
        npcRycoLine0.dispose();
        npcWaldensopLine0.dispose();
        npcWaldensopLine1.dispose();
        npcWaldensopLine2.dispose();
        npcSkaterDudeLine0.dispose();
        npcStallOwner2Line0.dispose();
        npcTalkingMushroomLine0.dispose();
        npcThabulLine0.dispose();
        npcThabulLine1.dispose();
        npcToobarLine0.dispose();
        npcToobarLine1.dispose();
        npcToobarLine2.dispose();
        npcTownMerchantLine6.dispose();
        npcTownMerchantLine7.dispose();
        npcTownMerchantLine8.dispose();
        npcForeignWomanLine5.dispose();
        npcMerribalLine0.dispose();
        npcSquireGripLine1.dispose();
        npcSquireGripLine3.dispose();
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

    public Sound getNpcMysticLine0() {
        return npcMysticLine0;
    }

    public Sound getNpcOldSailorLine0() {
        return npcOldSailorLine0;
    }

    public Sound getNpcRebelRightLine0() {
        return npcRebelRightLine0;
    }

    public Sound getNpcSailorLine0() {
        return npcSailorLine0;
    }

    public Sound getNpcKnaveLine0() {
        return npcKnaveLine0;
    }

    public Sound getNpcKnaveLine1() {
        return npcKnaveLine1;
    }

    public Sound getNpcKnaveLine4() {
        return npcKnaveLine4;
    }

    public Sound getNpcFatShopkeeperLine5() {
        return npcFatShopkeeperLine5;
    }

    public Sound getNpcFatShopkeeperLine6() {
        return npcFatShopkeeperLine6;
    }

    public Sound getNpcShurmakLine8() {
        return npcShurmakLine8;
    }

    public Sound getNpcSirramBewLine0() {
        return npcSirramBewLine0;
    }

    public Sound getNpcSirramBewLine1() {
        return npcSirramBewLine1;
    }

    public Sound getNpcSirramBewLine2() {
        return npcSirramBewLine2;
    }

    public Sound getNpcSirramBewLine3() {
        return npcSirramBewLine3;
    }

    public Sound getNpcSleepingManLine0() {
        return npcSleepingManLine0;
    }

    public Sound getNpcThirstyChildLine0() {
        return npcThirstyChildLine0;
    }

    public Sound getNpcThirstyChildLine1() {
        return npcThirstyChildLine1;
    }

    public Sound getNpcRycoLine0() {
        return npcRycoLine0;
    }

    public Sound getNpcWaldensopLine0() {
        return npcWaldensopLine0;
    }

    public Sound getNpcWaldensopLine1() {
        return npcWaldensopLine1;
    }

    public Sound getNpcWaldensopLine2() {
        return npcWaldensopLine2;
    }

    public Sound getNpcSkaterDudeLine0() {
        return npcSkaterDudeLine0;
    }

    public Sound getNpcStallOwner2Line0() {
        return npcStallOwner2Line0;
    }

    public Sound getNpcTalkingMushroomLine0() {
        return npcTalkingMushroomLine0;
    }

    public Sound getNpcThabulLine0() {
        return npcThabulLine0;
    }

    public Sound getNpcThabulLine1() {
        return npcThabulLine1;
    }

    public Sound getNpcToobarLine0() {
        return npcToobarLine0;
    }

    public Sound getNpcToobarLine1() {
        return npcToobarLine1;
    }

    public Sound getNpcToobarLine2() {
        return npcToobarLine2;
    }

    public Sound getNpcTownMerchantLine6() {
        return npcTownMerchantLine6;
    }

    public Sound getNpcTownMerchantLine7() {
        return npcTownMerchantLine7;
    }

    public Sound getNpcTownMerchantLine8() {
        return npcTownMerchantLine8;
    }

    public Sound getNpcForeignWomanLine5() {
        return npcForeignWomanLine5;
    }

    public Sound getNpcMerribalLine0() {
        return npcMerribalLine0;
    }

    public Sound getNpcSquireGripLine1() {
        return npcSquireGripLine1;
    }

    public Sound getNpcSquireGripLine3() {
        return npcSquireGripLine3;
    }

}
