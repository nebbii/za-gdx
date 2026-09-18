package com.nebbii.zagdx;

import java.util.EnumMap;

import com.badlogic.gdx.graphics.Texture;

public class ImageLoader {
    private Texture none;

    public enum ZeldaAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT,
        ATTACKUP,
        ATTACKRIGHT,
        ATTACKDOWN,
        ATTACKLEFT,
        GAMEOVER
    }

    /* Enemies */
    public enum EnemyGoriyaAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyPeahatAnimationGroup {
        WALK
    }

    public enum EnemyTumblebotAnimationGroup {
        ROLLUP,
        ROLLRIGHT,
        ROLLDOWN,
        ROLLLEFT
    }

    public enum EnemyLeeverAnimationGroup {
        WALK
    }

    public enum EnemyMobyAnimationGroup {
        FLYUP,
        FLYRIGHT,
        FLYDOWN,
        FLYLEFT
    }

    public enum EnemyMoblinAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyDeelerAnimationGroup {
        WALK
    }

    public enum EnemyTektiteAnimationGroup {
        WALK
    }

    public enum EnemyKeeseAnimationGroup {
        FLY
    }

    public enum EnemyLlortAnimationGroup {
        WALK,
        ATTACK
    }

    public enum EnemySardakRedAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemySardakBlueAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemySardakYellowAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyAgwandaAnimationGroup {
        WALK
    }

    public enum EnemyAlligatorManAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyArcherAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyAvianaAnimationGroup {
        WALK,
        ATTACK
    }

    public enum EnemyAxeManAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyBagoBagoAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyBlobAnimationGroup {
        WALK
    }

    public enum EnemyBollaAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyCactusAnimationGroup {
        IDLE
    }

    public enum EnemyCrockarockAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyCrystalShardAnimationGroup {
        IDLE
    }

    public enum EnemyDragonflyAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyFloorSpikesAnimationGroup {
        IDLE
    }

    public enum EnemyFloorSpikesBlueAnimationGroup {
        IDLE
    }

    public enum EnemyFloorSpikesWhiteAnimationGroup {
        IDLE
    }

    public enum EnemyGanonAnimationGroup {
        WALK,
        ATTACK,
        IDLE
    }

    public enum EnemyGanonFairyAnimationGroup {
        IDLE
    }

    public enum EnemyGiantSquidAnimationGroup {
        WALK
    }

    public enum EnemyGibdoAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyGolemAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyGreenBirdAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyGuardAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyIronKnuckleAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyJackAnimationGroup {
        IDLE
    }

    public enum EnemyJackarooAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyKannisAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyKelpiAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyKnightBlueAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyKnightGreenAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyKnightRedAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyLanmolaAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyLavaLizardAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyLoccasinAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyLowderAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyMalmordAnimationGroup {
        IDLE
    }

    public enum EnemyMimicMoleAnimationGroup {
        WALK
    }

    public enum EnemyMolluskaAnimationGroup {
        WALK
    }

    public enum EnemyOctorokAnimationGroup {
        WALK
    }

    public enum EnemyPasquinadeAnimationGroup {
        IDLE,
        WALK,
        ATTACK
    }

    public enum EnemyPatraAnimationGroup {
        WALK
    }

    public enum EnemyPolsVoiceAnimationGroup {
        WALK
    }

    public enum EnemyPurpleBirdAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyPurpleFishAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyRomravenAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyRopeAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemySeaMonsterAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemySpearThrowerAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemySpikedBlockAnimationGroup {
        IDLE
    }

    public enum EnemyStalfosAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemySwampZolaBlueAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemySwampZolaGreenAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyTinyFishAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyTornadoAnimationGroup {
        IDLE
    }

    public enum EnemyTumbleHeadAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyTumbleSkullAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyTurtleAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyUrsoreAnimationGroup {
        WALKRIGHT,
        IDLERIGHT,
        ATTACK,
        IDLELEFT,
        WALKLEFT
    }

    public enum EnemyVaporaAnimationGroup {
        WALK
    }

    public enum EnemyVireAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyVoltaAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyWallSpikeAnimationGroup {
        IDLE
    }

    public enum EnemyWallmasterAnimationGroup {
        IDLE,
        ATTACK,
        RETREAT
    }

    public enum EnemyWarbaneAnimationGroup {
        WALK
    }

    public enum EnemyWizzrobeAnimationGroup {
        WALKUP,
        WALKRIGHT,
        WALKDOWN,
        WALKLEFT
    }

    public enum EnemyZolAnimationGroup {
        WALK
    }

    public enum NpcTalkingChestAnimationGroup {
        IDLE
    }

    public enum NpcOghamAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcBeggarAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcEnidAnimationGroup {
        IDLE
    }

    public enum NpcExhaustedTravelerAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcBlueLadyAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcLotharAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcKrebbAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcArcheryMinigameOwnerAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcBitterbeckAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcBlacksmithAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcDressyWomanAnimationGroup {
        IDLE
    }

    public enum NpcEricAndIanAnimationGroup {
        IDLE
    }

    public enum NpcFaustAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcFriendlyGoblinAnimationGroup {
        IDLE
    }

    public enum NpcGhostDogAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcGhostFarmerAnimationGroup {
        IDLE
    }

    public enum NpcDenGoblinAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcGreatFairyAnimationGroup {
        IDLE
    }

    public enum NpcGreenKnightSpectatorAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcGwynlaAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcHoodedWomanAnimationGroup {
        IDLE
    }

    public enum NpcHorseAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcHouseKeeperAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcKronThePeglegAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcLivingFarmerAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcLonlynAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcLoungerAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcMadameKriggleAnimationGroup {
        IDLE
    }

    public enum NpcEtheraAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcBeachcomberAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcYelenaAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcGiantSwampRatAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcMysticAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcOldSailorAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcPickpocketAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcPurpleStallOwnerAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcQuarryMinerAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcRandomCapeGuyAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcRebelLeftAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcRebelRightAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcSailorAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcSailor2AnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcKnaveAnimationGroup {
        IDLE
    }

    public enum NpcFatShopkeeperAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcShopkeeperDogAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcShurmakAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcSirramBewAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcSkaterDudeAnimationGroup {
        IDLE
    }

    public enum NpcSleepingManAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcStallOwner2AnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcTalkingMushroomAnimationGroup {
        IDLE
    }

    public enum NpcThabulAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcThirstyChildAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcToobarAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcTownMerchantAnimationGroup {
        IDLE
    }

    public enum NpcRycoAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcDockhandAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcQuietFishermanAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcWaldensopAnimationGroup {
        IDLE
    }

    public enum NpcForeignWomanAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcMerribalAnimationGroup {
        IDLE
    }

    public enum NpcSquireGripAnimationGroup {
        IDLE
    }

    public enum NpcPurpleRavenAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcBrideAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcNimoneeAnimationGroup {
        IDLE
    }

    public enum NpcStrongmanAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcFortuneTellerAnimationGroup {
        IDLE
    }

    public enum NpcTournamentSpectatorAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcTwinsFatherAnimationGroup {
        IDLE
    }

    public enum NpcWaiterAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcDeerHunterAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcDebblinOfDurodAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcLodgeOwnerAnimationGroup {
        IDLE0,
        IDLE1,
        IDLE2
    }

    public enum NpcWimbichAnimationGroup {
        IDLEUP,
        IDLERIGHT,
        IDLEDOWN,
        IDLELEFT
    }

    public enum NpcYalzanAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcYellowStallOwnerAnimationGroup {
        IDLE0,
        IDLE1
    }

    public enum NpcYvonneAnimationGroup {
        IDLE
    }

    public enum NpcZeldaSlidingAnimationGroup {
        IDLE
    }

    public enum SpriteLlortLaserAnimationGroup {
        IDLE
    }

    public enum SpriteLlortGateBottomAnimationGroup {
        IDLE
    }

    public enum SpriteLlortGateTopAnimationGroup {
        IDLE
    }

    public enum SpriteExplosionAnimationGroup {
        IDLE
    }

    public enum SpriteSparkleAnimationGroup {
        IDLE
    }

    public enum SpriteCampfireAnimationGroup {
        IDLE
    }

    private EnumMap<ZeldaAnimationGroup, Texture[]> zelda;
    private EnumMap<EnemyGoriyaAnimationGroup, Texture[]> enemyGoriya;
    private EnumMap<EnemyPeahatAnimationGroup, Texture[]> enemyPeahat;
    private EnumMap<EnemyTumblebotAnimationGroup, Texture[]> enemyTumblebot;
    private EnumMap<EnemyLeeverAnimationGroup, Texture[]> enemyLeever;
    private EnumMap<EnemyMobyAnimationGroup, Texture[]> enemyMoby;
    private EnumMap<EnemyMoblinAnimationGroup, Texture[]> enemyMoblin;
    private EnumMap<EnemyDeelerAnimationGroup, Texture[]> enemyDeeler;
    private EnumMap<EnemyTektiteAnimationGroup, Texture[]> enemyTektite;
    private EnumMap<EnemyKeeseAnimationGroup, Texture[]> enemyKeese;
    private EnumMap<EnemyLlortAnimationGroup, Texture[]> enemyLlort;
    private EnumMap<EnemySardakRedAnimationGroup, Texture[]> enemySardakRed;
    private EnumMap<EnemySardakBlueAnimationGroup, Texture[]> enemySardakBlue;
    private EnumMap<EnemySardakYellowAnimationGroup, Texture[]> enemySardakYellow;
    private EnumMap<EnemyAgwandaAnimationGroup, Texture[]> enemyAgwanda;
    private EnumMap<EnemyAlligatorManAnimationGroup, Texture[]> enemyAlligatorMan;
    private EnumMap<EnemyArcherAnimationGroup, Texture[]> enemyArcher;
    private EnumMap<EnemyAvianaAnimationGroup, Texture[]> enemyAviana;
    private EnumMap<EnemyAxeManAnimationGroup, Texture[]> enemyAxeMan;
    private EnumMap<EnemyBagoBagoAnimationGroup, Texture[]> enemyBagoBago;
    private EnumMap<EnemyBlobAnimationGroup, Texture[]> enemyBlob;
    private EnumMap<EnemyBollaAnimationGroup, Texture[]> enemyBolla;
    private EnumMap<EnemyCactusAnimationGroup, Texture[]> enemyCactus;
    private EnumMap<EnemyCrockarockAnimationGroup, Texture[]> enemyCrockarock;
    private EnumMap<EnemyCrystalShardAnimationGroup, Texture[]> enemyCrystalShard;
    private EnumMap<EnemyDragonflyAnimationGroup, Texture[]> enemyDragonfly;
    private EnumMap<EnemyFloorSpikesAnimationGroup, Texture[]> enemyFloorSpikes;
    private EnumMap<EnemyFloorSpikesBlueAnimationGroup, Texture[]> enemyFloorSpikesBlue;
    private EnumMap<EnemyFloorSpikesWhiteAnimationGroup, Texture[]> enemyFloorSpikesWhite;
    private EnumMap<EnemyGanonAnimationGroup, Texture[]> enemyGanon;
    private EnumMap<EnemyGanonFairyAnimationGroup, Texture[]> enemyGanonFairy;
    private EnumMap<EnemyGiantSquidAnimationGroup, Texture[]> enemyGiantSquid;
    private EnumMap<EnemyGibdoAnimationGroup, Texture[]> enemyGibdo;
    private EnumMap<EnemyGolemAnimationGroup, Texture[]> enemyGolem;
    private EnumMap<EnemyGreenBirdAnimationGroup, Texture[]> enemyGreenBird;
    private EnumMap<EnemyGuardAnimationGroup, Texture[]> enemyGuard;
    private EnumMap<EnemyIronKnuckleAnimationGroup, Texture[]> enemyIronKnuckle;
    private EnumMap<EnemyJackAnimationGroup, Texture[]> enemyJack;
    private EnumMap<EnemyJackarooAnimationGroup, Texture[]> enemyJackaroo;
    private EnumMap<EnemyKannisAnimationGroup, Texture[]> enemyKannis;
    private EnumMap<EnemyKelpiAnimationGroup, Texture[]> enemyKelpi;
    private EnumMap<EnemyKnightBlueAnimationGroup, Texture[]> enemyKnightBlue;
    private EnumMap<EnemyKnightGreenAnimationGroup, Texture[]> enemyKnightGreen;
    private EnumMap<EnemyKnightRedAnimationGroup, Texture[]> enemyKnightRed;
    private EnumMap<EnemyLanmolaAnimationGroup, Texture[]> enemyLanmola;
    private EnumMap<EnemyLavaLizardAnimationGroup, Texture[]> enemyLavaLizard;
    private EnumMap<EnemyLoccasinAnimationGroup, Texture[]> enemyLoccasin;
    private EnumMap<EnemyLowderAnimationGroup, Texture[]> enemyLowder;
    private EnumMap<EnemyMalmordAnimationGroup, Texture[]> enemyMalmord;
    private EnumMap<EnemyMimicMoleAnimationGroup, Texture[]> enemyMimicMole;
    private EnumMap<EnemyMolluskaAnimationGroup, Texture[]> enemyMolluska;
    private EnumMap<EnemyOctorokAnimationGroup, Texture[]> enemyOctorok;
    private EnumMap<EnemyPasquinadeAnimationGroup, Texture[]> enemyPasquinade;
    private EnumMap<EnemyPatraAnimationGroup, Texture[]> enemyPatra;
    private EnumMap<EnemyPolsVoiceAnimationGroup, Texture[]> enemyPolsVoice;
    private EnumMap<EnemyPurpleBirdAnimationGroup, Texture[]> enemyPurpleBird;
    private EnumMap<EnemyPurpleFishAnimationGroup, Texture[]> enemyPurpleFish;
    private EnumMap<EnemyRomravenAnimationGroup, Texture[]> enemyRomraven;
    private EnumMap<EnemyRopeAnimationGroup, Texture[]> enemyRope;
    private EnumMap<EnemySeaMonsterAnimationGroup, Texture[]> enemySeaMonster;
    private EnumMap<EnemySpearThrowerAnimationGroup, Texture[]> enemySpearThrower;
    private EnumMap<EnemySpikedBlockAnimationGroup, Texture[]> enemySpikedBlock;
    private EnumMap<EnemyStalfosAnimationGroup, Texture[]> enemyStalfos;
    private EnumMap<EnemySwampZolaBlueAnimationGroup, Texture[]> enemySwampZolaBlue;
    private EnumMap<EnemySwampZolaGreenAnimationGroup, Texture[]> enemySwampZolaGreen;
    private EnumMap<EnemyTinyFishAnimationGroup, Texture[]> enemyTinyFish;
    private EnumMap<EnemyTornadoAnimationGroup, Texture[]> enemyTornado;
    private EnumMap<EnemyTumbleHeadAnimationGroup, Texture[]> enemyTumbleHead;
    private EnumMap<EnemyTumbleSkullAnimationGroup, Texture[]> enemyTumbleSkull;
    private EnumMap<EnemyTurtleAnimationGroup, Texture[]> enemyTurtle;
    private EnumMap<EnemyUrsoreAnimationGroup, Texture[]> enemyUrsore;
    private EnumMap<EnemyVaporaAnimationGroup, Texture[]> enemyVapora;
    private EnumMap<EnemyVireAnimationGroup, Texture[]> enemyVire;
    private EnumMap<EnemyVoltaAnimationGroup, Texture[]> enemyVolta;
    private EnumMap<EnemyWallSpikeAnimationGroup, Texture[]> enemyWallSpike;
    private EnumMap<EnemyWallmasterAnimationGroup, Texture[]> enemyWallmaster;
    private EnumMap<EnemyWarbaneAnimationGroup, Texture[]> enemyWarbane;
    private EnumMap<EnemyWizzrobeAnimationGroup, Texture[]> enemyWizzrobe;
    private EnumMap<EnemyZolAnimationGroup, Texture[]> enemyZol;

    /* NPCs */
    private Texture[] npcGlebb;
    private EnumMap<NpcTalkingChestAnimationGroup, Texture[]> npcTalkingChest;
    private EnumMap<NpcOghamAnimationGroup, Texture[]> npcOgham;
    private EnumMap<NpcBeggarAnimationGroup, Texture[]> npcBeggar;
    private EnumMap<NpcEnidAnimationGroup, Texture[]> npcEnid;
    private EnumMap<NpcExhaustedTravelerAnimationGroup, Texture[]> npcExhaustedTraveler;
    private EnumMap<NpcBlueLadyAnimationGroup, Texture[]> npcBlueLady;
    private EnumMap<NpcLotharAnimationGroup, Texture[]> npcLothar;
    private EnumMap<NpcKrebbAnimationGroup, Texture[]> npcKrebb;
    private EnumMap<NpcArcheryMinigameOwnerAnimationGroup, Texture[]> npcArcheryMinigameOwner;
    private EnumMap<NpcBitterbeckAnimationGroup, Texture[]> npcBitterbeck;
    private EnumMap<NpcBlacksmithAnimationGroup, Texture[]> npcBlacksmith;
    private EnumMap<NpcDressyWomanAnimationGroup, Texture[]> npcDressyWoman;
    private EnumMap<NpcEricAndIanAnimationGroup, Texture[]> npcEricAndIan;
    private EnumMap<NpcFaustAnimationGroup, Texture[]> npcFaust;
    private EnumMap<NpcFriendlyGoblinAnimationGroup, Texture[]> npcFriendlyGoblin;
    private EnumMap<NpcGhostDogAnimationGroup, Texture[]> npcGhostDog;
    private EnumMap<NpcGhostFarmerAnimationGroup, Texture[]> npcGhostFarmer;
    private EnumMap<NpcDenGoblinAnimationGroup, Texture[]> npcDenGoblin;
    private EnumMap<NpcGreatFairyAnimationGroup, Texture[]> npcGreatFairy;
    private EnumMap<NpcGreenKnightSpectatorAnimationGroup, Texture[]> npcGreenKnightSpectator;
    private EnumMap<NpcGwynlaAnimationGroup, Texture[]> npcGwynla;
    private EnumMap<NpcHoodedWomanAnimationGroup, Texture[]> npcHoodedWoman;
    private EnumMap<NpcHorseAnimationGroup, Texture[]> npcHorse;
    private EnumMap<NpcHouseKeeperAnimationGroup, Texture[]> npcHouseKeeper;
    private EnumMap<NpcKronThePeglegAnimationGroup, Texture[]> npcKronThePegleg;
    private EnumMap<NpcLivingFarmerAnimationGroup, Texture[]> npcLivingFarmer;
    private EnumMap<NpcLonlynAnimationGroup, Texture[]> npcLonlyn;
    private EnumMap<NpcLoungerAnimationGroup, Texture[]> npcLounger;
    private EnumMap<NpcMadameKriggleAnimationGroup, Texture[]> npcMadameKriggle;
    private EnumMap<NpcEtheraAnimationGroup, Texture[]> npcEthera;
    private EnumMap<NpcBeachcomberAnimationGroup, Texture[]> npcBeachcomber;
    private EnumMap<NpcYelenaAnimationGroup, Texture[]> npcYelena;
    private EnumMap<NpcGiantSwampRatAnimationGroup, Texture[]> npcGiantSwampRat;
    private EnumMap<NpcMysticAnimationGroup, Texture[]> npcMystic;
    private EnumMap<NpcOldSailorAnimationGroup, Texture[]> npcOldSailor;
    private EnumMap<NpcPickpocketAnimationGroup, Texture[]> npcPickpocket;
    private EnumMap<NpcPurpleStallOwnerAnimationGroup, Texture[]> npcPurpleStallOwner;
    private EnumMap<NpcQuarryMinerAnimationGroup, Texture[]> npcQuarryMiner;
    private EnumMap<NpcRandomCapeGuyAnimationGroup, Texture[]> npcRandomCapeGuy;
    private EnumMap<NpcRebelLeftAnimationGroup, Texture[]> npcRebelLeft;
    private EnumMap<NpcRebelRightAnimationGroup, Texture[]> npcRebelRight;
    private EnumMap<NpcSailorAnimationGroup, Texture[]> npcSailor;
    private EnumMap<NpcSailor2AnimationGroup, Texture[]> npcSailor2;
    private EnumMap<NpcKnaveAnimationGroup, Texture[]> npcKnave;
    private EnumMap<NpcFatShopkeeperAnimationGroup, Texture[]> npcFatShopkeeper;
    private EnumMap<NpcShopkeeperDogAnimationGroup, Texture[]> npcShopkeeperDog;
    private EnumMap<NpcShurmakAnimationGroup, Texture[]> npcShurmak;
    private EnumMap<NpcSirramBewAnimationGroup, Texture[]> npcSirramBew;
    private EnumMap<NpcSkaterDudeAnimationGroup, Texture[]> npcSkaterDude;
    private EnumMap<NpcSleepingManAnimationGroup, Texture[]> npcSleepingMan;
    private EnumMap<NpcStallOwner2AnimationGroup, Texture[]> npcStallOwner2;
    private EnumMap<NpcTalkingMushroomAnimationGroup, Texture[]> npcTalkingMushroom;
    private EnumMap<NpcThabulAnimationGroup, Texture[]> npcThabul;
    private EnumMap<NpcThirstyChildAnimationGroup, Texture[]> npcThirstyChild;
    private EnumMap<NpcToobarAnimationGroup, Texture[]> npcToobar;
    private EnumMap<NpcTownMerchantAnimationGroup, Texture[]> npcTownMerchant;
    private EnumMap<NpcRycoAnimationGroup, Texture[]> npcRyco;
    private EnumMap<NpcDockhandAnimationGroup, Texture[]> npcDockhand;
    private EnumMap<NpcQuietFishermanAnimationGroup, Texture[]> npcQuietFisherman;
    private EnumMap<NpcWaldensopAnimationGroup, Texture[]> npcWaldensop;
    private EnumMap<NpcForeignWomanAnimationGroup, Texture[]> npcForeignWoman;
    private EnumMap<NpcMerribalAnimationGroup, Texture[]> npcMerribal;
    private EnumMap<NpcSquireGripAnimationGroup, Texture[]> npcSquireGrip;
    private EnumMap<NpcPurpleRavenAnimationGroup, Texture[]> npcPurpleRaven;
    private EnumMap<NpcBrideAnimationGroup, Texture[]> npcBride;
    private EnumMap<NpcNimoneeAnimationGroup, Texture[]> npcNimonee;
    private EnumMap<NpcStrongmanAnimationGroup, Texture[]> npcStrongman;
    private EnumMap<NpcFortuneTellerAnimationGroup, Texture[]> npcFortuneTeller;
    private EnumMap<NpcTournamentSpectatorAnimationGroup, Texture[]> npcTournamentSpectator;
    private EnumMap<NpcTwinsFatherAnimationGroup, Texture[]> npcTwinsFather;
    private EnumMap<NpcWaiterAnimationGroup, Texture[]> npcWaiter;
    private EnumMap<NpcDeerHunterAnimationGroup, Texture[]> npcDeerHunter;
    private EnumMap<NpcDebblinOfDurodAnimationGroup, Texture[]> npcDebblinOfDurod;
    private EnumMap<NpcLodgeOwnerAnimationGroup, Texture[]> npcLodgeOwner;
    private EnumMap<NpcWimbichAnimationGroup, Texture[]> npcWimbich;
    private EnumMap<NpcYalzanAnimationGroup, Texture[]> npcYalzan;
    private EnumMap<NpcYellowStallOwnerAnimationGroup, Texture[]> npcYellowStallOwner;
    private EnumMap<NpcYvonneAnimationGroup, Texture[]> npcYvonne;
    private EnumMap<NpcZeldaSlidingAnimationGroup, Texture[]> npcZeldaSliding;

    /* Map stuff */
    private Texture spriteLadder;
    private EnumMap<SpriteLlortLaserAnimationGroup, Texture[]> spriteLlortLaser;
    private EnumMap<SpriteLlortGateBottomAnimationGroup, Texture[]> spriteLlortGateBottom;
    private EnumMap<SpriteLlortGateTopAnimationGroup, Texture[]> spriteLlortGateTop;
    private EnumMap<SpriteExplosionAnimationGroup, Texture[]> spriteExplosion;
    private EnumMap<SpriteSparkleAnimationGroup, Texture[]> spriteSparkle;
    private EnumMap<SpriteCampfireAnimationGroup, Texture[]> spriteCampfire;

    /* Treasures */
    private Texture bone;
    private Texture candle;
    private Texture candlePrice;
    private Texture ladder;
    private Texture magicShield;
    private Texture magicShieldPrice;
    private Texture pitcherEmpty;
    private Texture pitcherFull;
    private Texture vialOfWind;
    private Texture redBoots;
    private Texture underworldMap1;
    private Texture underworldMap2;
    private Texture underworldMap3;
    private Texture underworldMap4;
    private Texture underworldMap5;
    private Texture underworldMap6;
    private Texture underworldMap7;
    private Texture compass1;
    private Texture compass2;
    private Texture compass3;
    private Texture compass4;
    private Texture compass5;
    private Texture compass6;
    private Texture compass7;
    private Texture celestialStone1;
    private Texture celestialStone2;
    private Texture celestialStone3;
    private Texture celestialStone4;
    private Texture celestialStone5;
    private Texture celestialStone6;
    private Texture celestialStone7;

    /* Weapons */
    private Texture wand;
    private Texture boomerang;
    private Texture calm;
    private Texture calmPrice;
    private Texture dagger;
    private Texture[] firestorm;
    private Texture jadeRing;

    /* Pickups */
    private Texture rubyBlue;
    private Texture rubyYellow;
    private Texture heart;
    private Texture archipelagoColor;

    /* HUD */
    private Texture[] hudNumbers;
    private Texture hudHeartEmpty;
    private Texture hudHeartHalf;
    private Texture hudHeartFull;

    /* Projectiles */
    private Texture[] friendlyBoomerang;
    private Texture[][] friendlyCalm;
    private Texture[] friendlyDagger;
    private Texture[][] friendlyFirestorm;
    private Texture[] friendlyJadeRing;
    private Texture[] enemyBoomerang;
    private Texture[] enemySpear;
    private Texture[] enemyLlortAxe;
    private Texture enemyPeahatProjectile;

    private Texture itemScreen;
    private Texture glow;

    public ImageLoader() {
        none = new Texture("invisible.png");
        itemScreen = new Texture("dummy-pause-screen.png"); // TODO: swap with real asset
        glow = new Texture("glow.png");

        /* Actors */
        zelda = new EnumMap<>(ZeldaAnimationGroup.class);
        zelda.put(ZeldaAnimationGroup.WALKUP,
            loadTextureArray("export/common/zelda/sprites/group0", 5));
        zelda.put(ZeldaAnimationGroup.WALKRIGHT,
            loadTextureArray("export/common/zelda/sprites/group1", 5));
        zelda.put(ZeldaAnimationGroup.WALKDOWN,
            loadTextureArray("export/common/zelda/sprites/group2", 5));
        zelda.put(ZeldaAnimationGroup.WALKLEFT,
            loadTextureArray("export/common/zelda/sprites/group3", 5));
        zelda.put(ZeldaAnimationGroup.ATTACKUP,
            loadTextureArray("export/common/zelda/sprites/group4", 3));
        zelda.put(ZeldaAnimationGroup.ATTACKRIGHT,
            loadTextureArray("export/common/zelda/sprites/group5", 3));
        zelda.put(ZeldaAnimationGroup.ATTACKDOWN,
            loadTextureArray("export/common/zelda/sprites/group6", 3));
        zelda.put(ZeldaAnimationGroup.ATTACKLEFT,
            loadTextureArray("export/common/zelda/sprites/group7", 3));

        Texture[] zeldaGameover = new Texture[4];
        zeldaGameover[0] = new Texture("export/common/zelda/sprites/group0/sprite0.png");
        zeldaGameover[1] = new Texture("export/common/zelda/sprites/group1/sprite0.png");
        zeldaGameover[2] = new Texture("export/common/zelda/sprites/group2/sprite0.png");
        zeldaGameover[3] = new Texture("export/common/zelda/sprites/group3/sprite0.png");
        zelda.put(ZeldaAnimationGroup.GAMEOVER, zeldaGameover);

        enemyGoriya = new EnumMap<>(EnemyGoriyaAnimationGroup.class);
        enemyGoriya.put(EnemyGoriyaAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/d24/sprites/desc0/group0", 5));
        enemyGoriya.put(EnemyGoriyaAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/d24/sprites/desc0/group1", 5));
        enemyGoriya.put(EnemyGoriyaAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/d24/sprites/desc0/group2", 5));
        enemyGoriya.put(EnemyGoriyaAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/d24/sprites/desc0/group3", 5));

        enemyPeahat = new EnumMap<>(EnemyPeahatAnimationGroup.class);
        enemyPeahat.put(EnemyPeahatAnimationGroup.WALK,
            loadTextureArray("export/overworld/e22/sprites/desc0/group0", 5));

        enemyTumblebot = new EnumMap<>(EnemyTumblebotAnimationGroup.class);
        enemyTumblebot.put(EnemyTumblebotAnimationGroup.ROLLUP,
            loadTextureArray("export/overworld/h21/sprites/desc0/group0", 8));
        enemyTumblebot.put(EnemyTumblebotAnimationGroup.ROLLRIGHT,
            loadTextureArray("export/overworld/h21/sprites/desc0/group1", 8));
        enemyTumblebot.put(EnemyTumblebotAnimationGroup.ROLLDOWN,
            loadTextureArray("export/overworld/h21/sprites/desc0/group2", 8));
        enemyTumblebot.put(EnemyTumblebotAnimationGroup.ROLLLEFT,
            loadTextureArray("export/overworld/h21/sprites/desc0/group3", 8));

        enemyLeever = new EnumMap<>(EnemyLeeverAnimationGroup.class);
        enemyLeever.put(EnemyLeeverAnimationGroup.WALK,
            loadTextureArray("export/overworld/h27/sprites/desc0/group0", 5));

        enemyMoby = new EnumMap<>(EnemyMobyAnimationGroup.class);
        enemyMoby.put(EnemyMobyAnimationGroup.FLYUP,
            loadTextureArray("export/overworld/g26/sprites/desc0/group0", 5));
        enemyMoby.put(EnemyMobyAnimationGroup.FLYRIGHT,
            loadTextureArray("export/overworld/g26/sprites/desc0/group1", 5));
        enemyMoby.put(EnemyMobyAnimationGroup.FLYDOWN,
            loadTextureArray("export/overworld/g26/sprites/desc0/group2", 5));
        enemyMoby.put(EnemyMobyAnimationGroup.FLYLEFT,
            loadTextureArray("export/overworld/g26/sprites/desc0/group3", 5));

        enemyMoblin = new EnumMap<>(EnemyMoblinAnimationGroup.class);
        enemyMoblin.put(EnemyMoblinAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/g27/sprites/desc0/group0", 5));
        enemyMoblin.put(EnemyMoblinAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/g27/sprites/desc0/group1", 5));
        enemyMoblin.put(EnemyMoblinAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/g27/sprites/desc0/group2", 5));
        enemyMoblin.put(EnemyMoblinAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/g27/sprites/desc0/group3", 5));

        enemyDeeler = new EnumMap<>(EnemyDeelerAnimationGroup.class);
        enemyDeeler.put(EnemyDeelerAnimationGroup.WALK,
            loadTextureArray("export/overworld/g29/sprites/desc0/group0", 3));

        enemyTektite = new EnumMap<>(EnemyTektiteAnimationGroup.class);
        enemyTektite.put(EnemyTektiteAnimationGroup.WALK,
            loadTextureArray("export/underworld/s118/sprites/desc1/group0", 5));

        enemyKeese = new EnumMap<>(EnemyKeeseAnimationGroup.class);
        enemyKeese.put(EnemyKeeseAnimationGroup.FLY,
            loadTextureArray("export/underworld/s106/sprites/desc0/group0", 5));

        enemyLlort = new EnumMap<>(EnemyLlortAnimationGroup.class);
        enemyLlort.put(EnemyLlortAnimationGroup.WALK,
            loadTextureArray("export/underworld/s121/sprites/desc0/group0", 5));
        enemyLlort.put(EnemyLlortAnimationGroup.ATTACK,
            loadTextureArray("export/underworld/s121/sprites/desc0/group1", 7));

        enemySardakRed = new EnumMap<>(EnemySardakRedAnimationGroup.class);
        enemySardakRed.put(EnemySardakRedAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s107/sprites/desc0/group0", 5));
        enemySardakRed.put(EnemySardakRedAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s107/sprites/desc0/group1", 5));
        enemySardakRed.put(EnemySardakRedAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s107/sprites/desc0/group2", 5));
        enemySardakRed.put(EnemySardakRedAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s107/sprites/desc0/group3", 5));

        enemySardakBlue = new EnumMap<>(EnemySardakBlueAnimationGroup.class);
        enemySardakBlue.put(EnemySardakBlueAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s112/sprites/desc0/group0", 5));
        enemySardakBlue.put(EnemySardakBlueAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s112/sprites/desc0/group1", 5));
        enemySardakBlue.put(EnemySardakBlueAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s112/sprites/desc0/group2", 5));
        enemySardakBlue.put(EnemySardakBlueAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s112/sprites/desc0/group3", 5));

        enemySardakYellow = new EnumMap<>(EnemySardakYellowAnimationGroup.class);
        enemySardakYellow.put(EnemySardakYellowAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s120/sprites/desc0/group0", 5));
        enemySardakYellow.put(EnemySardakYellowAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s120/sprites/desc0/group1", 5));
        enemySardakYellow.put(EnemySardakYellowAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s120/sprites/desc0/group2", 5));
        enemySardakYellow.put(EnemySardakYellowAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s120/sprites/desc0/group3", 5));

        enemyAgwanda = new EnumMap<>(EnemyAgwandaAnimationGroup.class);
        enemyAgwanda.put(EnemyAgwandaAnimationGroup.WALK,
            loadTextureArray("export/underworld/gl5/sprites/desc0/group0", 8));

        enemyAlligatorMan = new EnumMap<>(EnemyAlligatorManAnimationGroup.class);
        enemyAlligatorMan.put(EnemyAlligatorManAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/r20/sprites/desc0/group0", 5));
        enemyAlligatorMan.put(EnemyAlligatorManAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/r20/sprites/desc0/group1", 5));
        enemyAlligatorMan.put(EnemyAlligatorManAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/r20/sprites/desc0/group2", 5));
        enemyAlligatorMan.put(EnemyAlligatorManAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/r20/sprites/desc0/group3", 5));

        enemyArcher = new EnumMap<>(EnemyArcherAnimationGroup.class);
        enemyArcher.put(EnemyArcherAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s722/sprites/desc0/group0", 5));
        enemyArcher.put(EnemyArcherAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s722/sprites/desc0/group1", 5));
        enemyArcher.put(EnemyArcherAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s722/sprites/desc0/group2", 4));
        enemyArcher.put(EnemyArcherAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s722/sprites/desc0/group3", 5));

        enemyAviana = new EnumMap<>(EnemyAvianaAnimationGroup.class);
        enemyAviana.put(EnemyAvianaAnimationGroup.WALK,
            loadTextureArray("export/underworld/gl3/sprites/desc0/group0", 5));
        enemyAviana.put(EnemyAvianaAnimationGroup.ATTACK,
            loadTextureArray("export/underworld/gl3/sprites/desc0/group1", 5));

        enemyAxeMan = new EnumMap<>(EnemyAxeManAnimationGroup.class);
        enemyAxeMan.put(EnemyAxeManAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/x14/sprites/desc0/group0", 5));
        enemyAxeMan.put(EnemyAxeManAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/x14/sprites/desc0/group1", 5));
        enemyAxeMan.put(EnemyAxeManAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/x14/sprites/desc0/group2", 5));
        enemyAxeMan.put(EnemyAxeManAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/x14/sprites/desc0/group3", 5));

        enemyBagoBago = new EnumMap<>(EnemyBagoBagoAnimationGroup.class);
        enemyBagoBago.put(EnemyBagoBagoAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/c8/sprites/desc2/group0", 3));
        enemyBagoBago.put(EnemyBagoBagoAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/c8/sprites/desc2/group1", 3));
        enemyBagoBago.put(EnemyBagoBagoAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/c8/sprites/desc2/group2", 3));
        enemyBagoBago.put(EnemyBagoBagoAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/c8/sprites/desc2/group3", 3));

        enemyBlob = new EnumMap<>(EnemyBlobAnimationGroup.class);
        enemyBlob.put(EnemyBlobAnimationGroup.WALK,
            loadTextureArray("export/overworld/r17/sprites/desc2/group0", 7));

        enemyBolla = new EnumMap<>(EnemyBollaAnimationGroup.class);
        enemyBolla.put(EnemyBollaAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s720/sprites/desc0/group0", 5));
        enemyBolla.put(EnemyBollaAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s720/sprites/desc0/group1", 5));
        enemyBolla.put(EnemyBollaAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s720/sprites/desc0/group2", 5));
        enemyBolla.put(EnemyBollaAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s720/sprites/desc0/group3", 5));

        enemyCactus = new EnumMap<>(EnemyCactusAnimationGroup.class);
        enemyCactus.put(EnemyCactusAnimationGroup.IDLE,
            loadTextureArray("export/overworld/v7/sprites/desc0/group0", 3));

        enemyCrockarock = new EnumMap<>(EnemyCrockarockAnimationGroup.class);
        enemyCrockarock.put(EnemyCrockarockAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/a7/sprites/desc0/group0", 3));
        enemyCrockarock.put(EnemyCrockarockAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/a7/sprites/desc0/group1", 3));
        enemyCrockarock.put(EnemyCrockarockAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/a7/sprites/desc0/group2", 3));
        enemyCrockarock.put(EnemyCrockarockAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/a7/sprites/desc0/group3", 3));

        enemyCrystalShard = new EnumMap<>(EnemyCrystalShardAnimationGroup.class);
        enemyCrystalShard.put(EnemyCrystalShardAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s305/sprites/desc1/group0", 4));

        enemyDragonfly = new EnumMap<>(EnemyDragonflyAnimationGroup.class);
        enemyDragonfly.put(EnemyDragonflyAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/gl4/sprites/desc1/group0", 3));
        enemyDragonfly.put(EnemyDragonflyAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/gl4/sprites/desc1/group1", 3));
        enemyDragonfly.put(EnemyDragonflyAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/gl4/sprites/desc1/group2", 3));
        enemyDragonfly.put(EnemyDragonflyAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/gl4/sprites/desc1/group3", 3));

        enemyFloorSpikes = new EnumMap<>(EnemyFloorSpikesAnimationGroup.class);
        enemyFloorSpikes.put(EnemyFloorSpikesAnimationGroup.IDLE,
            loadTextureArray("export/overworld/l8/sprites/desc0/group0", 8));

        enemyFloorSpikesBlue = new EnumMap<>(EnemyFloorSpikesBlueAnimationGroup.class);
        enemyFloorSpikesBlue.put(EnemyFloorSpikesBlueAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s316/sprites/desc3/group0", 8));

        enemyFloorSpikesWhite = new EnumMap<>(EnemyFloorSpikesWhiteAnimationGroup.class);
        enemyFloorSpikesWhite.put(EnemyFloorSpikesWhiteAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s406/sprites/desc3/group0", 8));

        enemyGanon = new EnumMap<>(EnemyGanonAnimationGroup.class);
        enemyGanon.put(EnemyGanonAnimationGroup.WALK,
            loadTextureArray("export/underworld/gl8/sprites/desc0/group0", 4));
        enemyGanon.put(EnemyGanonAnimationGroup.ATTACK,
            loadTextureArray("export/underworld/gl8/sprites/desc0/group1", 4));
        enemyGanon.put(EnemyGanonAnimationGroup.IDLE,
            loadTextureArray("export/underworld/gl8/sprites/desc0/group2", 1));

        enemyGanonFairy = new EnumMap<>(EnemyGanonFairyAnimationGroup.class);
        enemyGanonFairy.put(EnemyGanonFairyAnimationGroup.IDLE,
            loadTextureArray("export/overworld/gl1/sprites/desc2/group0", 4));

        enemyGiantSquid = new EnumMap<>(EnemyGiantSquidAnimationGroup.class);
        enemyGiantSquid.put(EnemyGiantSquidAnimationGroup.WALK,
            loadTextureArray("export/overworld/s20/sprites/desc0/group0", 10));

        enemyGibdo = new EnumMap<>(EnemyGibdoAnimationGroup.class);
        enemyGibdo.put(EnemyGibdoAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/s415/sprites/desc0/group0", 5));
        enemyGibdo.put(EnemyGibdoAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/s415/sprites/desc0/group1", 5));
        enemyGibdo.put(EnemyGibdoAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/s415/sprites/desc0/group2", 5));
        enemyGibdo.put(EnemyGibdoAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/s415/sprites/desc0/group3", 5));

        enemyGolem = new EnumMap<>(EnemyGolemAnimationGroup.class);
        enemyGolem.put(EnemyGolemAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/m25/sprites/desc0/group0", 6));
        enemyGolem.put(EnemyGolemAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/m25/sprites/desc0/group1", 7));
        enemyGolem.put(EnemyGolemAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/m25/sprites/desc0/group2", 7));
        enemyGolem.put(EnemyGolemAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/m25/sprites/desc0/group3", 7));

        enemyGreenBird = new EnumMap<>(EnemyGreenBirdAnimationGroup.class);
        enemyGreenBird.put(EnemyGreenBirdAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/k28/sprites/desc0/group0", 3));
        enemyGreenBird.put(EnemyGreenBirdAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/k28/sprites/desc0/group1", 3));
        enemyGreenBird.put(EnemyGreenBirdAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/k28/sprites/desc0/group2", 3));
        enemyGreenBird.put(EnemyGreenBirdAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/k28/sprites/desc0/group3", 3));

        enemyGuard = new EnumMap<>(EnemyGuardAnimationGroup.class);
        enemyGuard.put(EnemyGuardAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/p8/sprites/desc0/group0", 5));
        enemyGuard.put(EnemyGuardAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/p8/sprites/desc0/group1", 5));
        enemyGuard.put(EnemyGuardAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/p8/sprites/desc0/group2", 5));
        enemyGuard.put(EnemyGuardAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/p8/sprites/desc0/group3", 5));

        enemyIronKnuckle = new EnumMap<>(EnemyIronKnuckleAnimationGroup.class);
        enemyIronKnuckle.put(EnemyIronKnuckleAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/s613/sprites/desc0/group0", 5));
        enemyIronKnuckle.put(EnemyIronKnuckleAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/s613/sprites/desc0/group1", 5));
        enemyIronKnuckle.put(EnemyIronKnuckleAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/s613/sprites/desc0/group2", 5));
        enemyIronKnuckle.put(EnemyIronKnuckleAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/s613/sprites/desc0/group3", 5));

        enemyJack = new EnumMap<>(EnemyJackAnimationGroup.class);
        enemyJack.put(EnemyJackAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s406/sprites/desc1/group0", 4));

        enemyJackaroo = new EnumMap<>(EnemyJackarooAnimationGroup.class);
        enemyJackaroo.put(EnemyJackarooAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s202/sprites/desc1/group0", 5));
        enemyJackaroo.put(EnemyJackarooAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s202/sprites/desc1/group1", 5));
        enemyJackaroo.put(EnemyJackarooAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s202/sprites/desc1/group2", 5));
        enemyJackaroo.put(EnemyJackarooAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s202/sprites/desc1/group3", 5));

        enemyKannis = new EnumMap<>(EnemyKannisAnimationGroup.class);
        enemyKannis.put(EnemyKannisAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s716/sprites/desc0/group0", 5));
        enemyKannis.put(EnemyKannisAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s716/sprites/desc0/group1", 5));
        enemyKannis.put(EnemyKannisAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s716/sprites/desc0/group2", 5));
        enemyKannis.put(EnemyKannisAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s716/sprites/desc0/group3", 5));

        enemyKelpi = new EnumMap<>(EnemyKelpiAnimationGroup.class);
        enemyKelpi.put(EnemyKelpiAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s508/sprites/desc0/group0", 5));
        enemyKelpi.put(EnemyKelpiAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s508/sprites/desc0/group1", 5));
        enemyKelpi.put(EnemyKelpiAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s508/sprites/desc0/group2", 5));
        enemyKelpi.put(EnemyKelpiAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s508/sprites/desc0/group3", 5));

        enemyKnightBlue = new EnumMap<>(EnemyKnightBlueAnimationGroup.class);
        enemyKnightBlue.put(EnemyKnightBlueAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s620/sprites/desc0/group0", 4));
        enemyKnightBlue.put(EnemyKnightBlueAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s620/sprites/desc0/group1", 4));
        enemyKnightBlue.put(EnemyKnightBlueAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s620/sprites/desc0/group2", 4));
        enemyKnightBlue.put(EnemyKnightBlueAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s620/sprites/desc0/group3", 4));

        enemyKnightGreen = new EnumMap<>(EnemyKnightGreenAnimationGroup.class);
        enemyKnightGreen.put(EnemyKnightGreenAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s621/sprites/desc0/group0", 4));
        enemyKnightGreen.put(EnemyKnightGreenAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s621/sprites/desc0/group1", 4));
        enemyKnightGreen.put(EnemyKnightGreenAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s621/sprites/desc0/group2", 4));
        enemyKnightGreen.put(EnemyKnightGreenAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s621/sprites/desc0/group3", 4));

        enemyKnightRed = new EnumMap<>(EnemyKnightRedAnimationGroup.class);
        enemyKnightRed.put(EnemyKnightRedAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s612/sprites/desc0/group0", 4));
        enemyKnightRed.put(EnemyKnightRedAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s612/sprites/desc0/group1", 4));
        enemyKnightRed.put(EnemyKnightRedAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s612/sprites/desc0/group2", 4));
        enemyKnightRed.put(EnemyKnightRedAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s612/sprites/desc0/group3", 4));

        enemyLanmola = new EnumMap<>(EnemyLanmolaAnimationGroup.class);
        enemyLanmola.put(EnemyLanmolaAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/aa16/sprites/desc0/group0", 4));
        enemyLanmola.put(EnemyLanmolaAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/aa16/sprites/desc0/group1", 4));
        enemyLanmola.put(EnemyLanmolaAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/aa16/sprites/desc0/group2", 4));
        enemyLanmola.put(EnemyLanmolaAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/aa16/sprites/desc0/group3", 4));

        // TODO: extractor reported 8 frames per direction, but only 6 sprite files exist on disk; using the real counts
        enemyLavaLizard = new EnumMap<>(EnemyLavaLizardAnimationGroup.class);
        enemyLavaLizard.put(EnemyLavaLizardAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s705/sprites/desc0/group0", 6));
        enemyLavaLizard.put(EnemyLavaLizardAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s705/sprites/desc0/group1", 6));
        enemyLavaLizard.put(EnemyLavaLizardAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s705/sprites/desc0/group2", 6));
        enemyLavaLizard.put(EnemyLavaLizardAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s705/sprites/desc0/group3", 6));

        // TODO: extractor reported 2 frames per direction, but 3 sprite files exist on disk; using the real counts
        enemyLoccasin = new EnumMap<>(EnemyLoccasinAnimationGroup.class);
        enemyLoccasin.put(EnemyLoccasinAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s304/sprites/desc0/group0", 3));
        enemyLoccasin.put(EnemyLoccasinAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s304/sprites/desc0/group1", 3));
        enemyLoccasin.put(EnemyLoccasinAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s304/sprites/desc0/group2", 3));
        enemyLoccasin.put(EnemyLoccasinAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s304/sprites/desc0/group3", 3));

        // TODO: extractor reported 1 frame per direction, but 2 sprite files exist on disk; using the real counts
        enemyLowder = new EnumMap<>(EnemyLowderAnimationGroup.class);
        enemyLowder.put(EnemyLowderAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/ac17/sprites/desc0/group0", 2));
        enemyLowder.put(EnemyLowderAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/ac17/sprites/desc0/group1", 2));
        enemyLowder.put(EnemyLowderAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/ac17/sprites/desc0/group2", 2));
        enemyLowder.put(EnemyLowderAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/ac17/sprites/desc0/group3", 2));

        // TODO: extractor reported 1 frame, but 2 sprite files exist on disk; using the real count
        enemyMalmord = new EnumMap<>(EnemyMalmordAnimationGroup.class);
        enemyMalmord.put(EnemyMalmordAnimationGroup.IDLE,
            loadTextureArray("export/underworld/gl4/sprites/desc0/group0", 2));

        // TODO: extractor reported 4 frames, but 5 sprite files exist on disk; using the real count
        enemyMimicMole = new EnumMap<>(EnemyMimicMoleAnimationGroup.class);
        enemyMimicMole.put(EnemyMimicMoleAnimationGroup.WALK,
            loadTextureArray("export/overworld/l9/sprites/desc0/group0", 5));

        enemyMolluska = new EnumMap<>(EnemyMolluskaAnimationGroup.class);
        enemyMolluska.put(EnemyMolluskaAnimationGroup.WALK,
            loadTextureArray("export/overworld/o13/sprites/desc0/group0", 4));

        // TODO: extractor reported 8 frames, but 6 sprite files exist on disk; using the real count
        enemyOctorok = new EnumMap<>(EnemyOctorokAnimationGroup.class);
        enemyOctorok.put(EnemyOctorokAnimationGroup.WALK,
            loadTextureArray("export/overworld/l11/sprites/desc0/group0", 6));

        // TODO: extractor reported 1/6/6 frames for group0/1/2, but 2/7/7 sprite files exist on disk; using the real counts
        enemyPasquinade = new EnumMap<>(EnemyPasquinadeAnimationGroup.class);
        enemyPasquinade.put(EnemyPasquinadeAnimationGroup.IDLE,
            loadTextureArray("export/underworld/gl2/sprites/desc0/group0", 2));
        enemyPasquinade.put(EnemyPasquinadeAnimationGroup.WALK,
            loadTextureArray("export/underworld/gl2/sprites/desc0/group1", 7));
        enemyPasquinade.put(EnemyPasquinadeAnimationGroup.ATTACK,
            loadTextureArray("export/underworld/gl2/sprites/desc0/group2", 7));

        // TODO: extractor reported 8 frames, but 6 sprite files exist on disk; using the real count
        enemyPatra = new EnumMap<>(EnemyPatraAnimationGroup.class);
        enemyPatra.put(EnemyPatraAnimationGroup.WALK,
            loadTextureArray("export/overworld/s214/sprites/desc0/group0", 6));

        enemyPolsVoice = new EnumMap<>(EnemyPolsVoiceAnimationGroup.class);
        enemyPolsVoice.put(EnemyPolsVoiceAnimationGroup.WALK,
            loadTextureArray("export/underworld/s207/sprites/desc0/group0", 4));

        enemyPurpleBird = new EnumMap<>(EnemyPurpleBirdAnimationGroup.class);
        enemyPurpleBird.put(EnemyPurpleBirdAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/w5/sprites/desc2/group0", 4));
        enemyPurpleBird.put(EnemyPurpleBirdAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/w5/sprites/desc2/group1", 4));
        enemyPurpleBird.put(EnemyPurpleBirdAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/w5/sprites/desc2/group2", 4));
        enemyPurpleBird.put(EnemyPurpleBirdAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/w5/sprites/desc2/group3", 4));

        // TODO: extractor reported 8 frames per direction, but only 6 sprite files exist on disk; using the real counts
        enemyPurpleFish = new EnumMap<>(EnemyPurpleFishAnimationGroup.class);
        enemyPurpleFish.put(EnemyPurpleFishAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s522/sprites/desc0/group0", 6));
        enemyPurpleFish.put(EnemyPurpleFishAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s522/sprites/desc0/group1", 6));
        enemyPurpleFish.put(EnemyPurpleFishAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s522/sprites/desc0/group2", 6));
        enemyPurpleFish.put(EnemyPurpleFishAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s522/sprites/desc0/group3", 6));

        // TODO: extractor reported 8 frames per direction, but only 6 sprite files exist on disk; using the real counts
        enemyRomraven = new EnumMap<>(EnemyRomravenAnimationGroup.class);
        enemyRomraven.put(EnemyRomravenAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s418/sprites/desc0/group0", 6));
        enemyRomraven.put(EnemyRomravenAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s418/sprites/desc0/group1", 6));
        enemyRomraven.put(EnemyRomravenAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s418/sprites/desc0/group2", 6));
        enemyRomraven.put(EnemyRomravenAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s418/sprites/desc0/group3", 6));

        // TODO: extractor reported 5/5/4/5 frames for up/right/down/left, but 6/6/4/6 sprite files exist on disk; using the real counts
        enemyRope = new EnumMap<>(EnemyRopeAnimationGroup.class);
        enemyRope.put(EnemyRopeAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/j15/sprites/desc0/group0", 6));
        enemyRope.put(EnemyRopeAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/j15/sprites/desc0/group1", 6));
        enemyRope.put(EnemyRopeAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/j15/sprites/desc0/group2", 4));
        enemyRope.put(EnemyRopeAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/j15/sprites/desc0/group3", 6));

        // TODO: extractor reported 10/8/10/8 frames for up/right/down/left, but 6/5/6/5 sprite files exist on disk; using the real counts
        enemySeaMonster = new EnumMap<>(EnemySeaMonsterAnimationGroup.class);
        enemySeaMonster.put(EnemySeaMonsterAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/o28/sprites/desc0/group0", 6));
        enemySeaMonster.put(EnemySeaMonsterAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/o28/sprites/desc0/group1", 5));
        enemySeaMonster.put(EnemySeaMonsterAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/o28/sprites/desc0/group2", 6));
        enemySeaMonster.put(EnemySeaMonsterAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/o28/sprites/desc0/group3", 5));

        // TODO: extractor reported 8 frames per direction, but only 5 sprite files exist on disk; using the real counts
        enemySpearThrower = new EnumMap<>(EnemySpearThrowerAnimationGroup.class);
        enemySpearThrower.put(EnemySpearThrowerAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s703/sprites/desc0/group0", 5));
        enemySpearThrower.put(EnemySpearThrowerAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s703/sprites/desc0/group1", 5));
        enemySpearThrower.put(EnemySpearThrowerAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s703/sprites/desc0/group2", 5));
        enemySpearThrower.put(EnemySpearThrowerAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s703/sprites/desc0/group3", 5));

        enemySpikedBlock = new EnumMap<>(EnemySpikedBlockAnimationGroup.class);
        enemySpikedBlock.put(EnemySpikedBlockAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s403/sprites/desc0/group0", 1));

        // TODO: extractor reported 8 frames per direction, but only 5 sprite files exist on disk; using the real counts
        enemyStalfos = new EnumMap<>(EnemyStalfosAnimationGroup.class);
        enemyStalfos.put(EnemyStalfosAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s203/sprites/desc2/group0", 5));
        enemyStalfos.put(EnemyStalfosAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s203/sprites/desc2/group1", 5));
        enemyStalfos.put(EnemyStalfosAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s203/sprites/desc2/group2", 5));
        enemyStalfos.put(EnemyStalfosAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s203/sprites/desc2/group3", 5));

        // TODO: extractor reported 8 frames per direction, but only 5 sprite files exist on disk; using the real counts
        enemySwampZolaBlue = new EnumMap<>(EnemySwampZolaBlueAnimationGroup.class);
        enemySwampZolaBlue.put(EnemySwampZolaBlueAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/m26/sprites/desc0/group0", 5));
        enemySwampZolaBlue.put(EnemySwampZolaBlueAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/m26/sprites/desc0/group1", 5));
        enemySwampZolaBlue.put(EnemySwampZolaBlueAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/m26/sprites/desc0/group2", 5));
        enemySwampZolaBlue.put(EnemySwampZolaBlueAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/m26/sprites/desc0/group3", 5));

        // TODO: extractor reported 8 frames per direction, but only 5 sprite files exist on disk; using the real counts
        enemySwampZolaGreen = new EnumMap<>(EnemySwampZolaGreenAnimationGroup.class);
        enemySwampZolaGreen.put(EnemySwampZolaGreenAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/j11/sprites/desc0/group0", 5));
        enemySwampZolaGreen.put(EnemySwampZolaGreenAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/j11/sprites/desc0/group1", 5));
        enemySwampZolaGreen.put(EnemySwampZolaGreenAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/j11/sprites/desc0/group2", 5));
        enemySwampZolaGreen.put(EnemySwampZolaGreenAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/j11/sprites/desc0/group3", 5));

        // TODO: extractor reported 4 frames per direction, but only 3 sprite files exist on disk; using the real counts
        enemyTinyFish = new EnumMap<>(EnemyTinyFishAnimationGroup.class);
        enemyTinyFish.put(EnemyTinyFishAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/p14/sprites/desc1/group0", 3));
        enemyTinyFish.put(EnemyTinyFishAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/p14/sprites/desc1/group1", 3));
        enemyTinyFish.put(EnemyTinyFishAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/p14/sprites/desc1/group2", 3));
        enemyTinyFish.put(EnemyTinyFishAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/p14/sprites/desc1/group3", 3));

        enemyTornado = new EnumMap<>(EnemyTornadoAnimationGroup.class);
        enemyTornado.put(EnemyTornadoAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s302/sprites/desc0/group0", 3));

        enemyTumbleHead = new EnumMap<>(EnemyTumbleHeadAnimationGroup.class);
        enemyTumbleHead.put(EnemyTumbleHeadAnimationGroup.WALKUP,
            loadTextureArray("export/overworld/r15/sprites/desc1/group0", 8));
        enemyTumbleHead.put(EnemyTumbleHeadAnimationGroup.WALKRIGHT,
            loadTextureArray("export/overworld/r15/sprites/desc1/group1", 8));
        enemyTumbleHead.put(EnemyTumbleHeadAnimationGroup.WALKDOWN,
            loadTextureArray("export/overworld/r15/sprites/desc1/group2", 8));
        enemyTumbleHead.put(EnemyTumbleHeadAnimationGroup.WALKLEFT,
            loadTextureArray("export/overworld/r15/sprites/desc1/group3", 8));

        // TODO: extractor reported 6 frames per direction, but only 4 sprite files exist on disk; using the real counts
        enemyTumbleSkull = new EnumMap<>(EnemyTumbleSkullAnimationGroup.class);
        enemyTumbleSkull.put(EnemyTumbleSkullAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s310/sprites/desc0/group0", 4));
        enemyTumbleSkull.put(EnemyTumbleSkullAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s310/sprites/desc0/group1", 4));
        enemyTumbleSkull.put(EnemyTumbleSkullAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s310/sprites/desc0/group2", 4));
        enemyTumbleSkull.put(EnemyTumbleSkullAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s310/sprites/desc0/group3", 4));

        // TODO: extractor reported 8 frames per direction, but only 5 sprite files exist on disk; using the real counts
        enemyTurtle = new EnumMap<>(EnemyTurtleAnimationGroup.class);
        enemyTurtle.put(EnemyTurtleAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s519/sprites/desc0/group0", 5));
        enemyTurtle.put(EnemyTurtleAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s519/sprites/desc0/group1", 5));
        enemyTurtle.put(EnemyTurtleAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s519/sprites/desc0/group2", 5));
        enemyTurtle.put(EnemyTurtleAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s519/sprites/desc0/group3", 5));

        // TODO: extractor reported 8/2/4/2/8 frames for group0..group4, but only 5/2/3/2/5 sprite files exist on disk; using the real counts
        enemyUrsore = new EnumMap<>(EnemyUrsoreAnimationGroup.class);
        enemyUrsore.put(EnemyUrsoreAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s628/sprites/desc0/group0", 5));
        enemyUrsore.put(EnemyUrsoreAnimationGroup.IDLERIGHT,
            loadTextureArray("export/underworld/s628/sprites/desc0/group1", 2));
        enemyUrsore.put(EnemyUrsoreAnimationGroup.ATTACK,
            loadTextureArray("export/underworld/s628/sprites/desc0/group2", 3));
        enemyUrsore.put(EnemyUrsoreAnimationGroup.IDLELEFT,
            loadTextureArray("export/underworld/s628/sprites/desc0/group3", 2));
        enemyUrsore.put(EnemyUrsoreAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s628/sprites/desc0/group4", 5));

        enemyVapora = new EnumMap<>(EnemyVaporaAnimationGroup.class);
        enemyVapora.put(EnemyVaporaAnimationGroup.WALK,
            loadTextureArray("export/underworld/s307/sprites/desc0/group0", 6));

        // TODO: extractor reported 8 frames per direction, but only 5 sprite files exist on disk; using the real counts
        enemyVire = new EnumMap<>(EnemyVireAnimationGroup.class);
        enemyVire.put(EnemyVireAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s204/sprites/desc0/group0", 5));
        enemyVire.put(EnemyVireAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s204/sprites/desc0/group1", 5));
        enemyVire.put(EnemyVireAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s204/sprites/desc0/group2", 5));
        enemyVire.put(EnemyVireAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s204/sprites/desc0/group3", 5));

        // TODO: extractor reported 8/8/8/7 frames for up/right/down/left, but 5/5/5/4 sprite files exist on disk; using the real counts
        enemyVolta = new EnumMap<>(EnemyVoltaAnimationGroup.class);
        enemyVolta.put(EnemyVoltaAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s316/sprites/desc0/group0", 5));
        enemyVolta.put(EnemyVoltaAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s316/sprites/desc0/group1", 5));
        enemyVolta.put(EnemyVoltaAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s316/sprites/desc0/group2", 5));
        enemyVolta.put(EnemyVoltaAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s316/sprites/desc0/group3", 4));

        // NOTE: extractor reported 5 separate 1-frame groups (group0..group4); combined into a
        // single 5-frame IDLE animation, one texture per group.
        enemyWallSpike = new EnumMap<>(EnemyWallSpikeAnimationGroup.class);
        Texture[] enemyWallSpikeIdle = new Texture[5];
        enemyWallSpikeIdle[0] = new Texture("export/underworld/s502/sprites/desc0/group0/sprite0.png");
        enemyWallSpikeIdle[1] = new Texture("export/underworld/s502/sprites/desc0/group1/sprite0.png");
        enemyWallSpikeIdle[2] = new Texture("export/underworld/s502/sprites/desc0/group2/sprite0.png");
        enemyWallSpikeIdle[3] = new Texture("export/underworld/s502/sprites/desc0/group3/sprite0.png");
        enemyWallSpikeIdle[4] = new Texture("export/underworld/s502/sprites/desc0/group4/sprite0.png");
        enemyWallSpike.put(EnemyWallSpikeAnimationGroup.IDLE, enemyWallSpikeIdle);

        // TODO: extractor reported 3/1/1 frames for group0/1/2, but 3/3/2 sprite files exist on
        // disk; using the real counts.
        enemyWallmaster = new EnumMap<>(EnemyWallmasterAnimationGroup.class);
        enemyWallmaster.put(EnemyWallmasterAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s222/sprites/desc0/group0", 3));
        enemyWallmaster.put(EnemyWallmasterAnimationGroup.ATTACK,
            loadTextureArray("export/underworld/s222/sprites/desc0/group1", 3));
        enemyWallmaster.put(EnemyWallmasterAnimationGroup.RETREAT,
            loadTextureArray("export/underworld/s222/sprites/desc0/group2", 2));

        // TODO: extractor reported 8 frames, but only 5 sprite files exist on disk; using the real count
        enemyWarbane = new EnumMap<>(EnemyWarbaneAnimationGroup.class);
        enemyWarbane.put(EnemyWarbaneAnimationGroup.WALK,
            loadTextureArray("export/underworld/gl7/sprites/desc0/group0", 5));

        // TODO: extractor reported 8 frames per direction, but only 5 sprite files exist on disk; using the real counts
        enemyWizzrobe = new EnumMap<>(EnemyWizzrobeAnimationGroup.class);
        enemyWizzrobe.put(EnemyWizzrobeAnimationGroup.WALKUP,
            loadTextureArray("export/underworld/s209/sprites/desc0/group0", 5));
        enemyWizzrobe.put(EnemyWizzrobeAnimationGroup.WALKRIGHT,
            loadTextureArray("export/underworld/s209/sprites/desc0/group1", 5));
        enemyWizzrobe.put(EnemyWizzrobeAnimationGroup.WALKDOWN,
            loadTextureArray("export/underworld/s209/sprites/desc0/group2", 5));
        enemyWizzrobe.put(EnemyWizzrobeAnimationGroup.WALKLEFT,
            loadTextureArray("export/underworld/s209/sprites/desc0/group3", 5));

        // TODO: extractor reported 4 frames, but only 3 sprite files exist on disk; using the real count
        enemyZol = new EnumMap<>(EnemyZolAnimationGroup.class);
        enemyZol.put(EnemyZolAnimationGroup.WALK,
            loadTextureArray("export/underworld/s503/sprites/desc0/group0", 3));

        /* NPCs */
        npcGlebb = loadTextureArray("export/overworld/j24/sprites/desc0/group0", 5);

        npcTalkingChest = new EnumMap<>(NpcTalkingChestAnimationGroup.class);
        npcTalkingChest.put(NpcTalkingChestAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s108/sprites/desc0/group0", 4));

        npcOgham = new EnumMap<>(NpcOghamAnimationGroup.class);
        npcOgham.put(NpcOghamAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/f26/sprites/desc0/group0", 3));
        npcOgham.put(NpcOghamAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/f26/sprites/desc0/group1", 3));
        npcOgham.put(NpcOghamAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/f26/sprites/desc0/group2", 3));

        npcBeggar = new EnumMap<>(NpcBeggarAnimationGroup.class);
        npcBeggar.put(NpcBeggarAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/j22/sprites/desc0/group0", 5));
        npcBeggar.put(NpcBeggarAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/j22/sprites/desc0/group1", 5));

        npcEnid = new EnumMap<>(NpcEnidAnimationGroup.class);
        npcEnid.put(NpcEnidAnimationGroup.IDLE,
            loadTextureArray("export/overworld/h29/sprites/desc0/group0", 3));

        npcExhaustedTraveler = new EnumMap<>(NpcExhaustedTravelerAnimationGroup.class);
        npcExhaustedTraveler.put(NpcExhaustedTravelerAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/j22a/sprites/desc0/group0", 3));
        npcExhaustedTraveler.put(NpcExhaustedTravelerAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/j22a/sprites/desc0/group1", 3));

        npcBlueLady = new EnumMap<>(NpcBlueLadyAnimationGroup.class);
        npcBlueLady.put(NpcBlueLadyAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/j22a/sprites/desc1/group0", 3));
        npcBlueLady.put(NpcBlueLadyAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/j22a/sprites/desc1/group1", 3));

        npcLothar = new EnumMap<>(NpcLotharAnimationGroup.class);
        npcLothar.put(NpcLotharAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/j22a/sprites/desc2/group0", 5));
        npcLothar.put(NpcLotharAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/j22a/sprites/desc2/group1", 5));

        npcKrebb = new EnumMap<>(NpcKrebbAnimationGroup.class);
        npcKrebb.put(NpcKrebbAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/j22a/sprites/desc4/group0", 5));
        npcKrebb.put(NpcKrebbAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/j22a/sprites/desc4/group1", 5));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcArcheryMinigameOwner = new EnumMap<>(NpcArcheryMinigameOwnerAnimationGroup.class);
        npcArcheryMinigameOwner.put(NpcArcheryMinigameOwnerAnimationGroup.IDLE0,
            loadTextureArray("export/underworld/s606/sprites/desc0/group0", 3));
        npcArcheryMinigameOwner.put(NpcArcheryMinigameOwnerAnimationGroup.IDLE1,
            loadTextureArray("export/underworld/s606/sprites/desc0/group1", 3));
        npcArcheryMinigameOwner.put(NpcArcheryMinigameOwnerAnimationGroup.IDLE2,
            loadTextureArray("export/underworld/s606/sprites/desc0/group2", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcBitterbeck = new EnumMap<>(NpcBitterbeckAnimationGroup.class);
        npcBitterbeck.put(NpcBitterbeckAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/y5/sprites/desc0/group0", 3));
        npcBitterbeck.put(NpcBitterbeckAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/y5/sprites/desc0/group1", 3));

        // TODO: extractor reported 8 frames per direction, but only 5 sprite files exist on disk
        // per direction; using the real counts.
        npcBlacksmith = new EnumMap<>(NpcBlacksmithAnimationGroup.class);
        npcBlacksmith.put(NpcBlacksmithAnimationGroup.IDLEUP,
            loadTextureArray("export/overworld/k14/sprites/desc3/group0", 5));
        npcBlacksmith.put(NpcBlacksmithAnimationGroup.IDLERIGHT,
            loadTextureArray("export/overworld/k14/sprites/desc3/group1", 5));
        npcBlacksmith.put(NpcBlacksmithAnimationGroup.IDLEDOWN,
            loadTextureArray("export/overworld/k14/sprites/desc3/group2", 5));
        npcBlacksmith.put(NpcBlacksmithAnimationGroup.IDLELEFT,
            loadTextureArray("export/overworld/k14/sprites/desc3/group3", 5));

        // TODO: extractor reported 4 frames, but only 3 sprite files exist on disk; using the real count
        npcDressyWoman = new EnumMap<>(NpcDressyWomanAnimationGroup.class);
        npcDressyWoman.put(NpcDressyWomanAnimationGroup.IDLE,
            loadTextureArray("export/overworld/l15/sprites/desc0/group0", 3));

        // TODO: extractor reported 4 frames, but only 3 sprite files exist on disk; using the real count
        npcEricAndIan = new EnumMap<>(NpcEricAndIanAnimationGroup.class);
        npcEricAndIan.put(NpcEricAndIanAnimationGroup.IDLE,
            loadTextureArray("export/overworld/l14/sprites/desc0/group0", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcFaust = new EnumMap<>(NpcFaustAnimationGroup.class);
        npcFaust.put(NpcFaustAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/u18/sprites/desc0/group0", 3));
        npcFaust.put(NpcFaustAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/u18/sprites/desc0/group1", 3));

        // TODO: extractor reported 8 frames, but only 5 sprite files exist on disk; using the real count
        npcFriendlyGoblin = new EnumMap<>(NpcFriendlyGoblinAnimationGroup.class);
        npcFriendlyGoblin.put(NpcFriendlyGoblinAnimationGroup.IDLE,
            loadTextureArray("export/overworld/h9/sprites/desc0/group0", 5));

        // TODO: extractor reported 8 frames per direction, but only 5 sprite files exist on disk
        // per direction; using the real counts.
        npcGhostDog = new EnumMap<>(NpcGhostDogAnimationGroup.class);
        npcGhostDog.put(NpcGhostDogAnimationGroup.IDLEUP,
            loadTextureArray("export/overworld/s6/sprites/desc1/group0", 5));
        npcGhostDog.put(NpcGhostDogAnimationGroup.IDLERIGHT,
            loadTextureArray("export/overworld/s6/sprites/desc1/group1", 5));
        npcGhostDog.put(NpcGhostDogAnimationGroup.IDLEDOWN,
            loadTextureArray("export/overworld/s6/sprites/desc1/group2", 5));
        npcGhostDog.put(NpcGhostDogAnimationGroup.IDLELEFT,
            loadTextureArray("export/overworld/s6/sprites/desc1/group3", 5));

        // TODO: extractor reported 4 frames, but only 3 sprite files exist on disk; using the real count
        npcGhostFarmer = new EnumMap<>(NpcGhostFarmerAnimationGroup.class);
        npcGhostFarmer.put(NpcGhostFarmerAnimationGroup.IDLE,
            loadTextureArray("export/overworld/j17/sprites/desc0/group0", 3));

        // TODO: extractor reported 8 frames per direction, but only 5 sprite files exist on disk
        // per direction; using the real counts.
        npcDenGoblin = new EnumMap<>(NpcDenGoblinAnimationGroup.class);
        npcDenGoblin.put(NpcDenGoblinAnimationGroup.IDLEUP,
            loadTextureArray("export/overworld/g9/sprites/desc0/group0", 5));
        npcDenGoblin.put(NpcDenGoblinAnimationGroup.IDLERIGHT,
            loadTextureArray("export/overworld/g9/sprites/desc0/group1", 5));
        npcDenGoblin.put(NpcDenGoblinAnimationGroup.IDLEDOWN,
            loadTextureArray("export/overworld/g9/sprites/desc0/group2", 5));
        npcDenGoblin.put(NpcDenGoblinAnimationGroup.IDLELEFT,
            loadTextureArray("export/overworld/g9/sprites/desc0/group3", 5));

        // TODO: extractor reported 4 frames, but only 3 sprite files exist on disk; using the real count
        npcGreatFairy = new EnumMap<>(NpcGreatFairyAnimationGroup.class);
        npcGreatFairy.put(NpcGreatFairyAnimationGroup.IDLE,
            loadTextureArray("export/overworld/ac18/sprites/desc0/group0", 3));

        // TODO: extractor reported 4 frames per direction, but only 3 sprite files exist on disk
        // per direction; using the real counts.
        npcGreenKnightSpectator = new EnumMap<>(NpcGreenKnightSpectatorAnimationGroup.class);
        npcGreenKnightSpectator.put(NpcGreenKnightSpectatorAnimationGroup.IDLEUP,
            loadTextureArray("export/underworld/s622/sprites/desc0/group0", 3));
        npcGreenKnightSpectator.put(NpcGreenKnightSpectatorAnimationGroup.IDLERIGHT,
            loadTextureArray("export/underworld/s622/sprites/desc0/group1", 3));
        npcGreenKnightSpectator.put(NpcGreenKnightSpectatorAnimationGroup.IDLEDOWN,
            loadTextureArray("export/underworld/s622/sprites/desc0/group2", 3));
        npcGreenKnightSpectator.put(NpcGreenKnightSpectatorAnimationGroup.IDLELEFT,
            loadTextureArray("export/underworld/s622/sprites/desc0/group3", 3));

        // TODO: extractor reported 8/8/8/4 frames for up/right/down/left, but 5/5/5/4 sprite files
        // exist on disk; using the real counts.
        npcGwynla = new EnumMap<>(NpcGwynlaAnimationGroup.class);
        npcGwynla.put(NpcGwynlaAnimationGroup.IDLEUP,
            loadTextureArray("export/overworld/r9/sprites/desc0/group0", 5));
        npcGwynla.put(NpcGwynlaAnimationGroup.IDLERIGHT,
            loadTextureArray("export/overworld/r9/sprites/desc0/group1", 5));
        npcGwynla.put(NpcGwynlaAnimationGroup.IDLEDOWN,
            loadTextureArray("export/overworld/r9/sprites/desc0/group2", 5));
        npcGwynla.put(NpcGwynlaAnimationGroup.IDLELEFT,
            loadTextureArray("export/overworld/r9/sprites/desc0/group3", 4));

        // TODO: extractor reported 4 frames, but only 3 sprite files exist on disk; using the real count
        npcHoodedWoman = new EnumMap<>(NpcHoodedWomanAnimationGroup.class);
        npcHoodedWoman.put(NpcHoodedWomanAnimationGroup.IDLE,
            loadTextureArray("export/overworld/v16/sprites/desc0/group0", 3));

        npcHorse = new EnumMap<>(NpcHorseAnimationGroup.class);
        npcHorse.put(NpcHorseAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/n11/sprites/desc1/group0", 3));
        npcHorse.put(NpcHorseAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/n11/sprites/desc1/group1", 3));

        // TODO: extractor reported 4/5 frames for group0/1, but 3/3 sprite files exist on disk;
        // using the real counts.
        npcHouseKeeper = new EnumMap<>(NpcHouseKeeperAnimationGroup.class);
        npcHouseKeeper.put(NpcHouseKeeperAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/w10/sprites/desc0/group0", 3));
        npcHouseKeeper.put(NpcHouseKeeperAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/w10/sprites/desc0/group1", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcKronThePegleg = new EnumMap<>(NpcKronThePeglegAnimationGroup.class);
        npcKronThePegleg.put(NpcKronThePeglegAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/p12/sprites/desc0/group0", 3));
        npcKronThePegleg.put(NpcKronThePeglegAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/p12/sprites/desc0/group1", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcLivingFarmer = new EnumMap<>(NpcLivingFarmerAnimationGroup.class);
        npcLivingFarmer.put(NpcLivingFarmerAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/l17/sprites/desc0/group0", 3));
        npcLivingFarmer.put(NpcLivingFarmerAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/l17/sprites/desc0/group1", 3));
        npcLivingFarmer.put(NpcLivingFarmerAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/l17/sprites/desc0/group2", 3));

        // TODO: extractor reported 8 frames per group, but only 5 sprite files exist on disk per
        // group; using the real counts.
        npcLonlyn = new EnumMap<>(NpcLonlynAnimationGroup.class);
        npcLonlyn.put(NpcLonlynAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/r9/sprites/desc1/group0", 5));
        npcLonlyn.put(NpcLonlynAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/r9/sprites/desc1/group1", 5));
        npcLonlyn.put(NpcLonlynAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/r9/sprites/desc1/group2", 5));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcLounger = new EnumMap<>(NpcLoungerAnimationGroup.class);
        npcLounger.put(NpcLoungerAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/k13/sprites/desc0/group0", 3));
        npcLounger.put(NpcLoungerAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/k13/sprites/desc0/group1", 3));
        npcLounger.put(NpcLoungerAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/k13/sprites/desc0/group2", 3));

        // TODO: extractor reported 4 frames, but only 3 sprite files exist on disk; using the real count
        npcMadameKriggle = new EnumMap<>(NpcMadameKriggleAnimationGroup.class);
        npcMadameKriggle.put(NpcMadameKriggleAnimationGroup.IDLE,
            loadTextureArray("export/overworld/k13b/sprites/desc0/group0", 3));

        // TODO: extractor reported 4 frames per direction, but only 3 sprite files exist on disk
        // per direction; using the real counts.
        npcEthera = new EnumMap<>(NpcEtheraAnimationGroup.class);
        npcEthera.put(NpcEtheraAnimationGroup.IDLEUP,
            loadTextureArray("export/overworld/s6/sprites/desc0/group0", 3));
        npcEthera.put(NpcEtheraAnimationGroup.IDLERIGHT,
            loadTextureArray("export/overworld/s6/sprites/desc0/group1", 3));
        npcEthera.put(NpcEtheraAnimationGroup.IDLEDOWN,
            loadTextureArray("export/overworld/s6/sprites/desc0/group2", 3));
        npcEthera.put(NpcEtheraAnimationGroup.IDLELEFT,
            loadTextureArray("export/overworld/s6/sprites/desc0/group3", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcBeachcomber = new EnumMap<>(NpcBeachcomberAnimationGroup.class);
        npcBeachcomber.put(NpcBeachcomberAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/t20/sprites/desc0/group0", 3));
        npcBeachcomber.put(NpcBeachcomberAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/t20/sprites/desc0/group1", 3));
        npcBeachcomber.put(NpcBeachcomberAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/t20/sprites/desc0/group2", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcYelena = new EnumMap<>(NpcYelenaAnimationGroup.class);
        npcYelena.put(NpcYelenaAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/u16/sprites/desc0/group0", 3));
        npcYelena.put(NpcYelenaAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/u16/sprites/desc0/group1", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcGiantSwampRat = new EnumMap<>(NpcGiantSwampRatAnimationGroup.class);
        npcGiantSwampRat.put(NpcGiantSwampRatAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/v14/sprites/desc0/group0", 3));
        npcGiantSwampRat.put(NpcGiantSwampRatAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/v14/sprites/desc0/group1", 3));

        // TODO: extractor reported 5 frames per direction, but 6 sprite files exist on disk per
        // direction; using the real counts.
        npcMystic = new EnumMap<>(NpcMysticAnimationGroup.class);
        npcMystic.put(NpcMysticAnimationGroup.IDLEUP,
            loadTextureArray("export/overworld/v11/sprites/desc0/group0", 6));
        npcMystic.put(NpcMysticAnimationGroup.IDLERIGHT,
            loadTextureArray("export/overworld/v11/sprites/desc0/group1", 6));
        npcMystic.put(NpcMysticAnimationGroup.IDLEDOWN,
            loadTextureArray("export/overworld/v11/sprites/desc0/group2", 6));
        npcMystic.put(NpcMysticAnimationGroup.IDLELEFT,
            loadTextureArray("export/overworld/v11/sprites/desc0/group3", 6));

        // TODO: extractor reported 4/4/3 frames per group, but 4/4/4 sprite files exist on disk;
        // using the real counts.
        npcOldSailor = new EnumMap<>(NpcOldSailorAnimationGroup.class);
        npcOldSailor.put(NpcOldSailorAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/p20a/sprites/desc0/group0", 4));
        npcOldSailor.put(NpcOldSailorAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/p20a/sprites/desc0/group1", 4));
        npcOldSailor.put(NpcOldSailorAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/p20a/sprites/desc0/group2", 4));

        npcPickpocket = new EnumMap<>(NpcPickpocketAnimationGroup.class);
        npcPickpocket.put(NpcPickpocketAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/l27a/sprites/desc1/group0", 4));
        npcPickpocket.put(NpcPickpocketAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/l27a/sprites/desc1/group1", 4));
        npcPickpocket.put(NpcPickpocketAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/l27a/sprites/desc1/group2", 4));

        npcPurpleStallOwner = new EnumMap<>(NpcPurpleStallOwnerAnimationGroup.class);
        npcPurpleStallOwner.put(NpcPurpleStallOwnerAnimationGroup.IDLE0,
            loadTextureArray("export/underworld/s609/sprites/desc0/group0", 4));
        npcPurpleStallOwner.put(NpcPurpleStallOwnerAnimationGroup.IDLE1,
            loadTextureArray("export/underworld/s609/sprites/desc0/group1", 4));

        npcQuarryMiner = new EnumMap<>(NpcQuarryMinerAnimationGroup.class);
        npcQuarryMiner.put(NpcQuarryMinerAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/aa15/sprites/desc0/group0", 4));
        npcQuarryMiner.put(NpcQuarryMinerAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/aa15/sprites/desc0/group1", 4));

        // TODO: extractor reported 8 frames per direction, but only 6 sprite files exist on disk
        // per direction; using the real counts.
        npcRandomCapeGuy = new EnumMap<>(NpcRandomCapeGuyAnimationGroup.class);
        npcRandomCapeGuy.put(NpcRandomCapeGuyAnimationGroup.IDLEUP,
            loadTextureArray("export/overworld/l13/sprites/desc0/group0", 6));
        npcRandomCapeGuy.put(NpcRandomCapeGuyAnimationGroup.IDLERIGHT,
            loadTextureArray("export/overworld/l13/sprites/desc0/group1", 6));
        npcRandomCapeGuy.put(NpcRandomCapeGuyAnimationGroup.IDLEDOWN,
            loadTextureArray("export/overworld/l13/sprites/desc0/group2", 6));
        npcRandomCapeGuy.put(NpcRandomCapeGuyAnimationGroup.IDLELEFT,
            loadTextureArray("export/overworld/l13/sprites/desc0/group3", 6));

        npcRebelRight = new EnumMap<>(NpcRebelRightAnimationGroup.class);
        npcRebelRight.put(NpcRebelRightAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/z13/sprites/desc0/group0", 4));
        npcRebelRight.put(NpcRebelRightAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/z13/sprites/desc0/group1", 4));

        npcRebelLeft = new EnumMap<>(NpcRebelLeftAnimationGroup.class);
        npcRebelLeft.put(NpcRebelLeftAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/z13/sprites/desc1/group0", 4));
        npcRebelLeft.put(NpcRebelLeftAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/z13/sprites/desc1/group1", 4));

        // TODO: extractor reported 4/4/3 frames per group, but 4/4/4 sprite files exist on disk;
        // using the real counts.
        npcSailor = new EnumMap<>(NpcSailorAnimationGroup.class);
        npcSailor.put(NpcSailorAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/l27/sprites/desc0/group0", 4));
        npcSailor.put(NpcSailorAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/l27/sprites/desc0/group1", 4));
        npcSailor.put(NpcSailorAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/l27/sprites/desc0/group2", 4));

        npcSailor2 = new EnumMap<>(NpcSailor2AnimationGroup.class);
        npcSailor2.put(NpcSailor2AnimationGroup.IDLE0,
            loadTextureArray("export/overworld/l28/sprites/desc1/group0", 4));
        npcSailor2.put(NpcSailor2AnimationGroup.IDLE1,
            loadTextureArray("export/overworld/l28/sprites/desc1/group1", 4));
        npcSailor2.put(NpcSailor2AnimationGroup.IDLE2,
            loadTextureArray("export/overworld/l28/sprites/desc1/group2", 4));

        npcKnave = new EnumMap<>(NpcKnaveAnimationGroup.class);
        npcKnave.put(NpcKnaveAnimationGroup.IDLE,
            loadTextureArray("export/overworld/k13a/sprites/desc0/group0", 4));

        npcFatShopkeeper = new EnumMap<>(NpcFatShopkeeperAnimationGroup.class);
        npcFatShopkeeper.put(NpcFatShopkeeperAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/k13a/sprites/desc1/group0", 4));
        npcFatShopkeeper.put(NpcFatShopkeeperAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/k13a/sprites/desc1/group1", 4));
        npcFatShopkeeper.put(NpcFatShopkeeperAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/k13a/sprites/desc1/group2", 4));

        // TODO: extractor reported 8 frames per direction, but only 6 sprite files exist on disk
        // per direction; using the real counts.
        npcShopkeeperDog = new EnumMap<>(NpcShopkeeperDogAnimationGroup.class);
        npcShopkeeperDog.put(NpcShopkeeperDogAnimationGroup.IDLEUP,
            loadTextureArray("export/overworld/k13/sprites/desc1/group0", 6));
        npcShopkeeperDog.put(NpcShopkeeperDogAnimationGroup.IDLERIGHT,
            loadTextureArray("export/overworld/k13/sprites/desc1/group1", 6));
        npcShopkeeperDog.put(NpcShopkeeperDogAnimationGroup.IDLEDOWN,
            loadTextureArray("export/overworld/k13/sprites/desc1/group2", 6));
        npcShopkeeperDog.put(NpcShopkeeperDogAnimationGroup.IDLELEFT,
            loadTextureArray("export/overworld/k13/sprites/desc1/group3", 6));

        // TODO: extractor reported 14 frames per group, but only 9 sprite files exist on disk per
        // group; using the real counts.
        npcShurmak = new EnumMap<>(NpcShurmakAnimationGroup.class);
        npcShurmak.put(NpcShurmakAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/p15/sprites/desc0/group0", 9));
        npcShurmak.put(NpcShurmakAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/p15/sprites/desc0/group1", 9));

        npcSirramBew = new EnumMap<>(NpcSirramBewAnimationGroup.class);
        npcSirramBew.put(NpcSirramBewAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/v16a/sprites/desc0/group0", 4));
        npcSirramBew.put(NpcSirramBewAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/v16a/sprites/desc0/group1", 4));

        npcSkaterDude = new EnumMap<>(NpcSkaterDudeAnimationGroup.class);
        npcSkaterDude.put(NpcSkaterDudeAnimationGroup.IDLE,
            loadTextureArray("export/overworld/g11/sprites/desc0/group0", 1));

        npcSleepingMan = new EnumMap<>(NpcSleepingManAnimationGroup.class);
        npcSleepingMan.put(NpcSleepingManAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/n17/sprites/desc0/group0", 5));
        npcSleepingMan.put(NpcSleepingManAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/n17/sprites/desc0/group1", 5));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcStallOwner2 = new EnumMap<>(NpcStallOwner2AnimationGroup.class);
        npcStallOwner2.put(NpcStallOwner2AnimationGroup.IDLE0,
            loadTextureArray("export/underworld/s603/sprites/desc1/group0", 3));
        npcStallOwner2.put(NpcStallOwner2AnimationGroup.IDLE1,
            loadTextureArray("export/underworld/s603/sprites/desc1/group1", 3));

        // TODO: extractor reported 8 frames, but only 5 sprite files exist on disk; using the
        // real count.
        npcTalkingMushroom = new EnumMap<>(NpcTalkingMushroomAnimationGroup.class);
        npcTalkingMushroom.put(NpcTalkingMushroomAnimationGroup.IDLE,
            loadTextureArray("export/overworld/k20/sprites/desc0/group0", 5));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcThabul = new EnumMap<>(NpcThabulAnimationGroup.class);
        npcThabul.put(NpcThabulAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/v5/sprites/desc0/group0", 3));
        npcThabul.put(NpcThabulAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/v5/sprites/desc0/group1", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcThirstyChild = new EnumMap<>(NpcThirstyChildAnimationGroup.class);
        npcThirstyChild.put(NpcThirstyChildAnimationGroup.IDLE0,
            loadTextureArray("export/underworld/s605/sprites/desc0/group0", 3));
        npcThirstyChild.put(NpcThirstyChildAnimationGroup.IDLE1,
            loadTextureArray("export/underworld/s605/sprites/desc0/group1", 3));
        npcThirstyChild.put(NpcThirstyChildAnimationGroup.IDLE2,
            loadTextureArray("export/underworld/s605/sprites/desc0/group2", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcToobar = new EnumMap<>(NpcToobarAnimationGroup.class);
        npcToobar.put(NpcToobarAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/l27a/sprites/desc0/group0", 3));
        npcToobar.put(NpcToobarAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/l27a/sprites/desc0/group1", 3));

        // TODO: extractor reported 4 frames, but only 3 sprite files exist on disk; using the
        // real count.
        npcTownMerchant = new EnumMap<>(NpcTownMerchantAnimationGroup.class);
        npcTownMerchant.put(NpcTownMerchantAnimationGroup.IDLE,
            loadTextureArray("export/overworld/k14/sprites/desc0/group0", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcRyco = new EnumMap<>(NpcRycoAnimationGroup.class);
        npcRyco.put(NpcRycoAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/e7/sprites/desc0/group0", 3));
        npcRyco.put(NpcRycoAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/e7/sprites/desc0/group1", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcDockhand = new EnumMap<>(NpcDockhandAnimationGroup.class);
        npcDockhand.put(NpcDockhandAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/l27/sprites/desc1/group0", 3));
        npcDockhand.put(NpcDockhandAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/l27/sprites/desc1/group1", 3));
        npcDockhand.put(NpcDockhandAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/l27/sprites/desc1/group2", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcQuietFisherman = new EnumMap<>(NpcQuietFishermanAnimationGroup.class);
        npcQuietFisherman.put(NpcQuietFishermanAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/m27/sprites/desc0/group0", 3));
        npcQuietFisherman.put(NpcQuietFishermanAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/m27/sprites/desc0/group1", 3));
        npcQuietFisherman.put(NpcQuietFishermanAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/m27/sprites/desc0/group2", 3));

        // TODO: extractor reported 4 frames, but only 3 sprite files exist on disk; using the
        // real count.
        npcWaldensop = new EnumMap<>(NpcWaldensopAnimationGroup.class);
        npcWaldensop.put(NpcWaldensopAnimationGroup.IDLE,
            loadTextureArray("export/overworld/n11/sprites/desc0/group0", 3));

        // TODO: extractor reported 4 frames per group, but only 3 sprite files exist on disk per
        // group; using the real counts.
        npcForeignWoman = new EnumMap<>(NpcForeignWomanAnimationGroup.class);
        npcForeignWoman.put(NpcForeignWomanAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/p20a/sprites/desc1/group0", 3));
        npcForeignWoman.put(NpcForeignWomanAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/p20a/sprites/desc1/group1", 3));

        // TODO: extractor reported 8 frames, but only 5 sprite files exist on disk; using the
        // real count.
        npcMerribal = new EnumMap<>(NpcMerribalAnimationGroup.class);
        npcMerribal.put(NpcMerribalAnimationGroup.IDLE,
            loadTextureArray("export/overworld/r10/sprites/desc0/group0", 5));

        npcSquireGrip = new EnumMap<>(NpcSquireGripAnimationGroup.class);
        npcSquireGrip.put(NpcSquireGripAnimationGroup.IDLE,
            loadTextureArray("export/overworld/r10/sprites/desc1/group0", 2));

        // TODO: extractor reported 8 frames per group, but only 6 sprite files exist on disk per
        // group; using the real counts.
        npcPurpleRaven = new EnumMap<>(NpcPurpleRavenAnimationGroup.class);
        npcPurpleRaven.put(NpcPurpleRavenAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/s301/sprites/desc0/group0", 6));
        npcPurpleRaven.put(NpcPurpleRavenAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/s301/sprites/desc0/group1", 6));

        npcBride = new EnumMap<>(NpcBrideAnimationGroup.class);
        npcBride.put(NpcBrideAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/s601/sprites/desc0/group0", 4));
        npcBride.put(NpcBrideAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/s601/sprites/desc0/group1", 4));

        npcNimonee = new EnumMap<>(NpcNimoneeAnimationGroup.class);
        npcNimonee.put(NpcNimoneeAnimationGroup.IDLE,
            loadTextureArray("export/overworld/z1/sprites/desc0/group0", 4));

        // TODO: extractor reported 5 frames per group, but 6 sprite files exist on disk per
        // group; using the real counts.
        npcStrongman = new EnumMap<>(NpcStrongmanAnimationGroup.class);
        npcStrongman.put(NpcStrongmanAnimationGroup.IDLEUP,
            loadTextureArray("export/underworld/s603/sprites/desc0/group0", 6));
        npcStrongman.put(NpcStrongmanAnimationGroup.IDLERIGHT,
            loadTextureArray("export/underworld/s603/sprites/desc0/group1", 6));
        npcStrongman.put(NpcStrongmanAnimationGroup.IDLEDOWN,
            loadTextureArray("export/underworld/s603/sprites/desc0/group2", 6));
        npcStrongman.put(NpcStrongmanAnimationGroup.IDLELEFT,
            loadTextureArray("export/underworld/s603/sprites/desc0/group3", 6));

        npcFortuneTeller = new EnumMap<>(NpcFortuneTellerAnimationGroup.class);
        npcFortuneTeller.put(NpcFortuneTellerAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s603/sprites/desc2/group0", 4));

        npcTournamentSpectator = new EnumMap<>(NpcTournamentSpectatorAnimationGroup.class);
        npcTournamentSpectator.put(NpcTournamentSpectatorAnimationGroup.IDLE0,
            loadTextureArray("export/underworld/s611/sprites/desc0/group0", 4));
        npcTournamentSpectator.put(NpcTournamentSpectatorAnimationGroup.IDLE1,
            loadTextureArray("export/underworld/s611/sprites/desc0/group1", 4));

        npcTwinsFather = new EnumMap<>(NpcTwinsFatherAnimationGroup.class);
        npcTwinsFather.put(NpcTwinsFatherAnimationGroup.IDLE,
            loadTextureArray("export/overworld/l14a/sprites/desc0/group0", 4));

        npcWaiter = new EnumMap<>(NpcWaiterAnimationGroup.class);
        npcWaiter.put(NpcWaiterAnimationGroup.IDLEUP,
            loadTextureArray("export/overworld/n11a/sprites/desc1/group0", 4));
        npcWaiter.put(NpcWaiterAnimationGroup.IDLERIGHT,
            loadTextureArray("export/overworld/n11a/sprites/desc1/group1", 4));
        npcWaiter.put(NpcWaiterAnimationGroup.IDLEDOWN,
            loadTextureArray("export/overworld/n11a/sprites/desc1/group2", 4));
        npcWaiter.put(NpcWaiterAnimationGroup.IDLELEFT,
            loadTextureArray("export/overworld/n11a/sprites/desc1/group3", 4));

        npcDeerHunter = new EnumMap<>(NpcDeerHunterAnimationGroup.class);
        npcDeerHunter.put(NpcDeerHunterAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/n11a/sprites/desc0/group0", 4));
        npcDeerHunter.put(NpcDeerHunterAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/n11a/sprites/desc0/group1", 4));
        npcDeerHunter.put(NpcDeerHunterAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/n11a/sprites/desc0/group2", 4));

        npcDebblinOfDurod = new EnumMap<>(NpcDebblinOfDurodAnimationGroup.class);
        npcDebblinOfDurod.put(NpcDebblinOfDurodAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/n11a/sprites/desc2/group0", 4));
        npcDebblinOfDurod.put(NpcDebblinOfDurodAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/n11a/sprites/desc2/group1", 4));
        npcDebblinOfDurod.put(NpcDebblinOfDurodAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/n11a/sprites/desc2/group2", 4));

        npcLodgeOwner = new EnumMap<>(NpcLodgeOwnerAnimationGroup.class);
        npcLodgeOwner.put(NpcLodgeOwnerAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/n11a/sprites/desc3/group0", 4));
        npcLodgeOwner.put(NpcLodgeOwnerAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/n11a/sprites/desc3/group1", 4));
        npcLodgeOwner.put(NpcLodgeOwnerAnimationGroup.IDLE2,
            loadTextureArray("export/overworld/n11a/sprites/desc3/group2", 4));

        // TODO: extractor reported 8 frames per group, but only 6 sprite files exist on disk per
        // group; using the real counts.
        npcWimbich = new EnumMap<>(NpcWimbichAnimationGroup.class);
        npcWimbich.put(NpcWimbichAnimationGroup.IDLEUP,
            loadTextureArray("export/overworld/l13/sprites/desc1/group0", 6));
        npcWimbich.put(NpcWimbichAnimationGroup.IDLERIGHT,
            loadTextureArray("export/overworld/l13/sprites/desc1/group1", 6));
        npcWimbich.put(NpcWimbichAnimationGroup.IDLEDOWN,
            loadTextureArray("export/overworld/l13/sprites/desc1/group2", 6));
        npcWimbich.put(NpcWimbichAnimationGroup.IDLELEFT,
            loadTextureArray("export/overworld/l13/sprites/desc1/group3", 6));

        npcYalzan = new EnumMap<>(NpcYalzanAnimationGroup.class);
        npcYalzan.put(NpcYalzanAnimationGroup.IDLE0,
            loadTextureArray("export/overworld/q15/sprites/desc0/group0", 5));
        npcYalzan.put(NpcYalzanAnimationGroup.IDLE1,
            loadTextureArray("export/overworld/q15/sprites/desc0/group1", 4));

        npcYellowStallOwner = new EnumMap<>(NpcYellowStallOwnerAnimationGroup.class);
        npcYellowStallOwner.put(NpcYellowStallOwnerAnimationGroup.IDLE0,
            loadTextureArray("export/underworld/s618/sprites/desc0/group0", 4));
        npcYellowStallOwner.put(NpcYellowStallOwnerAnimationGroup.IDLE1,
            loadTextureArray("export/underworld/s618/sprites/desc0/group1", 4));

        // TODO: extractor reported 8 frames, but only 6 sprite files exist on disk; using the
        // real count.
        npcYvonne = new EnumMap<>(NpcYvonneAnimationGroup.class);
        npcYvonne.put(NpcYvonneAnimationGroup.IDLE,
            loadTextureArray("export/overworld/l14/sprites/desc1/group0", 6));

        // TODO: extractor reported 3 frames, but 4 sprite files exist on disk; using the real
        // count.
        npcZeldaSliding = new EnumMap<>(NpcZeldaSlidingAnimationGroup.class);
        npcZeldaSliding.put(NpcZeldaSlidingAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s202/sprites/desc0/group0", 4));

        /* Map stuff */
        spriteLadder = new Texture("export/underworld/s102/sprites/desc0/group0/sprite0.png");

        spriteLlortGateBottom = new EnumMap<>(SpriteLlortGateBottomAnimationGroup.class);
        spriteLlortGateBottom.put(SpriteLlortGateBottomAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s121/sprites/desc3/group0", 1));

        spriteLlortGateTop = new EnumMap<>(SpriteLlortGateTopAnimationGroup.class);
        spriteLlortGateTop.put(SpriteLlortGateTopAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s121/sprites/desc2/group0", 1));

        spriteLlortLaser = new EnumMap<>(SpriteLlortLaserAnimationGroup.class);
        spriteLlortLaser.put(SpriteLlortLaserAnimationGroup.IDLE,
            loadTextureArray("export/underworld/s120/sprites/desc2/group0", 4));

        spriteExplosion = new EnumMap<>(SpriteExplosionAnimationGroup.class);
        spriteExplosion.put(SpriteExplosionAnimationGroup.IDLE,
            loadTextureArray("export/common/zinitVideo/record1_zelda_palette", 6));

        spriteSparkle = new EnumMap<>(SpriteSparkleAnimationGroup.class);
        spriteSparkle.put(SpriteSparkleAnimationGroup.IDLE,
            loadTextureArray("export/common/zinitVideo/record2_zelda_palette", 6));

        spriteCampfire = new EnumMap<>(SpriteCampfireAnimationGroup.class);
        spriteCampfire.put(SpriteCampfireAnimationGroup.IDLE,
            loadTextureArray("export/overworld/h29/sprites/desc1/group0", 4));

        /* Items */
        rubyBlue = new Texture("export/common/hudSprites/3.png");
        rubyYellow = new Texture("export/common/hudSprites/4.png");
        heart = new Texture("export/common/hudSprites/5.png");

        /* HUD */
        hudNumbers = loadTextureArray("export/common/zinitVideo/record3_hud_palette", 10); // path subject to change in the future
        hudHeartEmpty = new Texture("export/common/hudSprites/0.png");
        hudHeartHalf = new Texture("export/common/hudSprites/1.png");
        hudHeartFull = new Texture("export/common/hudSprites/2.png");

        /* Treasures */
        archipelagoColor = new Texture("archipelago/images/zelda-ap-icon.png");
        bone = new Texture("export/overworld/k13a/sprites/desc2/group0/sprite0.png");
        candle = new Texture("export/overworld/f26/sprites/desc1/group0/sprite0.png");
        candlePrice = new Texture("export/overworld/f26/sprites/desc1/group1/sprite0.png");
        ladder = new Texture("export/overworld/f28/sprites/desc0/group0/sprite0.png");
        magicShield = new Texture("export/overworld/f26/sprites/desc3/group0/sprite0.png");
        magicShieldPrice = new Texture("export/overworld/f26/sprites/desc3/group1/sprite0.png");
        pitcherEmpty = new Texture("export/overworld/j24/sprites/desc1/group0/sprite0.png");
        pitcherFull = new Texture("export/overworld/e20/sprites/desc0/group0/sprite0.png");
        redBoots = new Texture("export/underworld/s116/sprites/desc1/group0/sprite0.png");
        vialOfWind = new Texture("export/overworld/j24/sprites/desc2/group0/sprite0.png");

        underworldMap1 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png");
        underworldMap2 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image
        underworldMap3 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image
        underworldMap4 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image
        underworldMap5 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image
        underworldMap6 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image
        underworldMap7 = new Texture("export/underworld/s104/sprites/desc1/group0/sprite0.png"); // TODO: get correct map image

        compass1 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png");
        compass2 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image
        compass3 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image
        compass4 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image
        compass5 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image
        compass6 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image
        compass7 = new Texture("export/underworld/s105/sprites/desc1/group0/sprite0.png"); // TODO: get correct compass image

        celestialStone1 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png");
        celestialStone2 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image
        celestialStone3 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image
        celestialStone4 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image
        celestialStone5 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image
        celestialStone6 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image
        celestialStone7 = new Texture("export/underworld/s122/sprites/desc0/group0/sprite0.png"); // TODO: get correct celestial stone image

        /* Weapons */
        wand = new Texture("export/overworld/h23/sprites/desc0/group0/sprite0.png");
        boomerang = new Texture("export/overworld/d24/sprites/desc2/group0/sprite0.png");
        calm = new Texture("export/overworld/j22a/sprites/desc6/group0/sprite0.png");
        calmPrice = new Texture("export/overworld/j22a/sprites/desc6/group1/sprite0.png");
        dagger = new Texture("export/overworld/j22a/sprites/desc5/group0/sprite0.png");
        firestorm = loadTextureArray("export/overworld/j22/sprites/desc2/group0", 3);
        jadeRing = new Texture("export/underworld/s108/sprites/desc1/group0/sprite0.png");

        /* Projectiles */
        friendlyBoomerang = loadTextureArray("export/common/weapons/Boomerang/group0", 4);
        friendlyCalm = new Texture[4][];
        friendlyCalm[0] = loadTextureArray("export/common/weapons/Calm/group0", 3);
        friendlyCalm[1] = loadTextureArray("export/common/weapons/Calm/group1", 3);
        friendlyCalm[2] = loadTextureArray("export/common/weapons/Calm/group2", 3);
        friendlyCalm[3] = loadTextureArray("export/common/weapons/Calm/group3", 3);
        friendlyDagger = new Texture[4];
        friendlyDagger[0] = new Texture("export/common/weapons/Dagger/group0/sprite0.png");
        friendlyDagger[1] = new Texture("export/common/weapons/Dagger/group1/sprite0.png");
        friendlyDagger[2] = new Texture("export/common/weapons/Dagger/group2/sprite0.png");
        friendlyDagger[3] = new Texture("export/common/weapons/Dagger/group3/sprite0.png");
        friendlyFirestorm = new Texture[4][];
        friendlyFirestorm[0] = loadTextureArray("export/common/weapons/Firestorm/group0", 4);
        friendlyFirestorm[1] = loadTextureArray("export/common/weapons/Firestorm/group1", 4);
        friendlyFirestorm[2] = loadTextureArray("export/common/weapons/Firestorm/group2", 4);
        friendlyFirestorm[3] = loadTextureArray("export/common/weapons/Firestorm/group3", 4);
        friendlyJadeRing = new Texture[4];
        friendlyJadeRing[0] = new Texture("export/common/weapons/JadeRing/group0/sprite0.png");
        friendlyJadeRing[1] = new Texture("export/common/weapons/JadeRing/group1/sprite0.png");
        friendlyJadeRing[2] = new Texture("export/common/weapons/JadeRing/group2/sprite0.png");
        friendlyJadeRing[3] = new Texture("export/common/weapons/JadeRing/group3/sprite0.png");
        enemyBoomerang = loadTextureArray("export/overworld/d24/sprites/desc1/group0", 4);
        enemySpear = new Texture[4];
        enemySpear[0] = new Texture("export/underworld/s120/sprites/desc1/group0/sprite0.png");
        enemySpear[1] = new Texture("export/underworld/s120/sprites/desc1/group1/sprite0.png");
        enemySpear[2] = new Texture("export/underworld/s120/sprites/desc1/group2/sprite0.png");
        enemySpear[3] = new Texture("export/underworld/s120/sprites/desc1/group3/sprite0.png");
        enemyLlortAxe = loadTextureArray("export/underworld/s121/sprites/desc1/group0", 4);
        enemyPeahatProjectile = new Texture("export/overworld/e22/sprites/desc1/group0/sprite0.png");
    }

    public void dispose() {
        none.dispose();
        itemScreen.dispose();
        glow.dispose();

        /* Actors */
        for (Texture[] textures : zelda.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyGoriya.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyPeahat.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyTumblebot.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyLeever.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyMoby.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyMoblin.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyDeeler.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyTektite.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyKeese.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyLlort.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySardakRed.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySardakBlue.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySardakYellow.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyAgwanda.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyAlligatorMan.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyArcher.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyAviana.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyAxeMan.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyBagoBago.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyBlob.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyBolla.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyCactus.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyCrockarock.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyCrystalShard.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyDragonfly.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyFloorSpikes.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyFloorSpikesBlue.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyFloorSpikesWhite.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyGanon.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyGanonFairy.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyGiantSquid.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyGibdo.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyGolem.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyGreenBird.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyGuard.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyIronKnuckle.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyJack.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyJackaroo.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyKannis.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyKelpi.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyKnightBlue.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyKnightGreen.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyKnightRed.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyLanmola.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyLavaLizard.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyLoccasin.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyLowder.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyMalmord.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyMimicMole.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyMolluska.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyOctorok.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyPasquinade.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyPatra.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyPolsVoice.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyPurpleBird.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyPurpleFish.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyRomraven.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyRope.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySeaMonster.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySpearThrower.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySpikedBlock.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyStalfos.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySwampZolaBlue.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemySwampZolaGreen.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyTinyFish.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyTornado.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyTumbleHead.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyTumbleSkull.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyTurtle.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyUrsore.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyVapora.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyVire.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyVolta.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyWallSpike.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyWallmaster.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyWarbane.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyWizzrobe.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : enemyZol.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture texture : npcGlebb) {
            texture.dispose();
        }

        for (Texture[] textures : npcTalkingChest.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcOgham.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcBeggar.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcEnid.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcExhaustedTraveler.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcBlueLady.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcLothar.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcKrebb.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcArcheryMinigameOwner.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcBitterbeck.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcBlacksmith.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcDressyWoman.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcEricAndIan.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcFaust.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcFriendlyGoblin.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcGhostDog.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcGhostFarmer.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcDenGoblin.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcGreatFairy.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcGreenKnightSpectator.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcGwynla.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcHoodedWoman.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcHorse.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcHouseKeeper.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcKronThePegleg.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcLivingFarmer.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcLonlyn.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcLounger.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcMadameKriggle.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcEthera.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcBeachcomber.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcYelena.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcGiantSwampRat.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcMystic.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcOldSailor.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcPickpocket.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcPurpleStallOwner.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcQuarryMiner.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcRandomCapeGuy.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcRebelLeft.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcRebelRight.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcSailor.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcSailor2.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcKnave.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcFatShopkeeper.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcShopkeeperDog.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcShurmak.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcSirramBew.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcSkaterDude.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcSleepingMan.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcStallOwner2.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcTalkingMushroom.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcThabul.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcThirstyChild.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcToobar.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcTownMerchant.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcRyco.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcDockhand.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcQuietFisherman.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcWaldensop.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcForeignWoman.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcMerribal.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcSquireGrip.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcPurpleRaven.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcBride.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcNimonee.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcStrongman.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcFortuneTeller.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcTournamentSpectator.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcTwinsFather.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcWaiter.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcDeerHunter.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcDebblinOfDurod.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcLodgeOwner.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcWimbich.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcYalzan.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcYellowStallOwner.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcYvonne.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : npcZeldaSliding.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        /* Map stuff */
        spriteLadder.dispose();

        for (Texture[] textures : spriteLlortGateBottom.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : spriteLlortGateTop.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : spriteLlortLaser.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : spriteExplosion.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : spriteSparkle.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture[] textures : spriteCampfire.values()) {
            if (textures == null) continue;

            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        /* Items */
        rubyBlue.dispose();
        rubyYellow.dispose();
        heart.dispose();
        archipelagoColor.dispose();

        /* Hud */
        for (Texture texture : hudNumbers) {
            if (texture != null) {
                texture.dispose();
            }
        }

        hudHeartEmpty.dispose();
        hudHeartHalf.dispose();
        hudHeartFull.dispose();

        /* Treasures */
        bone.dispose();
        candle.dispose();
        candlePrice.dispose();
        ladder.dispose();
        magicShield.dispose();
        magicShieldPrice.dispose();
        pitcherEmpty.dispose();
        pitcherFull.dispose();
        vialOfWind.dispose();
        redBoots.dispose();
        jadeRing.dispose();
        underworldMap1.dispose();
        underworldMap2.dispose();
        underworldMap3.dispose();
        underworldMap4.dispose();
        underworldMap5.dispose();
        underworldMap6.dispose();
        underworldMap7.dispose();
        compass1.dispose();
        compass2.dispose();
        compass3.dispose();
        compass4.dispose();
        compass5.dispose();
        compass6.dispose();
        compass7.dispose();
        celestialStone1.dispose();
        celestialStone2.dispose();
        celestialStone3.dispose();
        celestialStone4.dispose();
        celestialStone5.dispose();
        celestialStone6.dispose();
        celestialStone7.dispose();

        /* Weapons */
        wand.dispose();
        boomerang.dispose();
        calm.dispose();
        calmPrice.dispose();
        dagger.dispose();
        for (Texture texture : firestorm) {
            if (texture != null) {
                texture.dispose();
            }
        }
        jadeRing.dispose();

        /* Projectiles */
        for (Texture texture : friendlyBoomerang) {
            if (texture != null) {
                texture.dispose();
            }
        }

        for (Texture[] textures : friendlyCalm) {
            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture texture : friendlyDagger) {
            if (texture != null) {
                texture.dispose();
            }
        }

        for (Texture[] textures : friendlyFirestorm) {
            for (Texture texture : textures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }

        for (Texture texture : friendlyJadeRing) {
            if (texture != null) {
                texture.dispose();
            }
        }

        for (Texture texture : enemyBoomerang) {
            if (texture != null) {
                texture.dispose();
            }
        }

        for (Texture texture : enemySpear) {
            if (texture != null) {
                texture.dispose();
            }
        }

        for (Texture texture : enemyLlortAxe) {
            if (texture != null) {
                texture.dispose();
            }
        }

        enemyPeahatProjectile.dispose();
    }

    public Texture getImageByItem(Item item) {
        if (item instanceof Treasure) {
            switch((Treasure) item) {
            case BLACK_ORB:
            case BONE:
                return getBone();
            case CANDLE:
                return getCandle();
            case CELESTIAL_SIGN_1:
                return getCelestialStone(1);
            case CELESTIAL_SIGN_2:
                return getCelestialStone(2);
            case CELESTIAL_SIGN_3:
                return getCelestialStone(3);
            case CELESTIAL_SIGN_4:
                return getCelestialStone(4);
            case CELESTIAL_SIGN_5:
                return getCelestialStone(5);
            case CELESTIAL_SIGN_6:
                return getCelestialStone(6);
            case CELESTIAL_SIGN_7:
                return getCelestialStone(7);
            case COAL:
                return getLadder();
            case COMPASS_1:
                return getCompass(1);
            case COMPASS_2:
                return getCompass(2);
            case COMPASS_3:
                return getCompass(3);
            case COMPASS_4:
                return getCompass(4);
            case COMPASS_5:
                return getCompass(5);
            case COMPASS_6:
                return getCompass(6);
            case COMPASS_7:
                return getCompass(7);
            case CRYSTALHEART:
            case DIAMOND:
            case FAIRY:
            case FLUTE:
            case GOLDEN_BOOTS:
            case HARP:
            case KEYS:
            case KNIFE:
            case LADDER:
                return getLadder();
            case LIFE_HEART:
            case LIFE_POTION:
            case MAGIC_SHIELD:
                return getMagicShield();
            case NONE:
                return getNone();
            case PITCHER_EMPTY:
                return getPitcherEmpty();
            case PITCHER_FULL:
                return getPitcherFull();
            case PLANK:
            case RAFT:
            case RED_BOOTS:
                return getRedBoots();
            case RED_RIBBON:
            case RUG:
            case RUBIES:
                return getRubyYellow(); // TODO: replace once proper sprite is exported
            case TICKET:
            case UNDERWORLD_MAP_1:
                return getUnderworldMap(1);
            case UNDERWORLD_MAP_2:
                return getUnderworldMap(2);
            case UNDERWORLD_MAP_3:
                return getUnderworldMap(3);
            case UNDERWORLD_MAP_4:
                return getUnderworldMap(4);
            case UNDERWORLD_MAP_5:
                return getUnderworldMap(5);
            case UNDERWORLD_MAP_6:
                return getUnderworldMap(6);
            case UNDERWORLD_MAP_7:
                return getUnderworldMap(7);
            case VIAL_OF_WIND:
                return getVialOfWind();
            case ZOLA_REPELLENT:
            default:
                throw new IllegalStateException("ImageLoader->getImageByName(): unimplemented treasure");
            }
        } else if (item instanceof Weapon) {
            switch((Weapon) item) {
            case WAND:
                return getWand();
            case BOOMERANG:
                return getBoomerang();
            case CALM:
                return getCalm();
            case DAGGER:
                return getDagger();
            case BOW_AND_ARROW:
            case BROADSWORD:
            case FEATHER:
            case FIRESTORM:
                return getFirestorm();
            case GOLD_NECKLACE:
            case HAMMER:
            case JADE_AMULET:
            case JADE_RING:
                return getJadeRing();
            case JOUST:
            case NOISE:
            case NONE:
                return getNone();
            case PYROS:
            case RINGS_OF_FIRE:
            case ROAR_STICK:
            case SHORT_AXE:
            case TURQUOISE_RING:
            default:
                throw new IllegalStateException("ImageLoader->getImageByName(): unimplemented weapon");
            }
        }
        return none;
    }

    private Texture[] loadTextureArray(String folderPath, int frameCount) {
        Texture[] textures = new Texture[frameCount];

        for (int i = 0; i < frameCount; i++) {
            textures[i] = new Texture(folderPath + "/sprite" + i + ".png");
        }

        return textures;
    }

    public Texture[] getZeldaAnimation(ZeldaAnimationGroup anim) {
        return zelda.get(anim);
    }

    public Texture[] getEnemyGoriyaAnimation(EnemyGoriyaAnimationGroup anim) {
        return enemyGoriya.get(anim);
    }

    public Texture[] getEnemyPeahatAnimation(EnemyPeahatAnimationGroup anim) {
        return enemyPeahat.get(anim);
    }

    public Texture[] getEnemyTumblebotAnimation(EnemyTumblebotAnimationGroup anim) {
        return enemyTumblebot.get(anim);
    }

    public Texture[] getEnemyLeeverAnimation(EnemyLeeverAnimationGroup anim) {
        return enemyLeever.get(anim);
    }

    public Texture[] getEnemyMobyAnimation(EnemyMobyAnimationGroup anim) {
        return enemyMoby.get(anim);
    }

    public Texture[] getEnemyMoblinAnimation(EnemyMoblinAnimationGroup anim) {
        return enemyMoblin.get(anim);
    }

    public Texture[] getEnemyDeelerAnimation(EnemyDeelerAnimationGroup anim) {
        return enemyDeeler.get(anim);
    }

    public Texture[] getEnemyTektiteAnimation(EnemyTektiteAnimationGroup anim) {
        return enemyTektite.get(anim);
    }

    public Texture[] getEnemyKeeseAnimation(EnemyKeeseAnimationGroup anim) {
        return enemyKeese.get(anim);
    }

    public Texture[] getEnemyLlortAnimation(EnemyLlortAnimationGroup anim) {
        return enemyLlort.get(anim);
    }

    public Texture[] getEnemySardakRedAnimation(EnemySardakRedAnimationGroup anim) {
        return enemySardakRed.get(anim);
    }

    public Texture[] getEnemySardakBlueAnimation(EnemySardakBlueAnimationGroup anim) {
        return enemySardakBlue.get(anim);
    }

    public Texture[] getEnemySardakYellowAnimation(EnemySardakYellowAnimationGroup anim) {
        return enemySardakYellow.get(anim);
    }

    public Texture[] getEnemyAgwandaAnimation(EnemyAgwandaAnimationGroup anim) {
        return enemyAgwanda.get(anim);
    }

    public Texture[] getEnemyAlligatorManAnimation(EnemyAlligatorManAnimationGroup anim) {
        return enemyAlligatorMan.get(anim);
    }

    public Texture[] getEnemyArcherAnimation(EnemyArcherAnimationGroup anim) {
        return enemyArcher.get(anim);
    }

    public Texture[] getEnemyAvianaAnimation(EnemyAvianaAnimationGroup anim) {
        return enemyAviana.get(anim);
    }

    public Texture[] getEnemyAxeManAnimation(EnemyAxeManAnimationGroup anim) {
        return enemyAxeMan.get(anim);
    }

    public Texture[] getEnemyBagoBagoAnimation(EnemyBagoBagoAnimationGroup anim) {
        return enemyBagoBago.get(anim);
    }

    public Texture[] getEnemyBlobAnimation(EnemyBlobAnimationGroup anim) {
        return enemyBlob.get(anim);
    }

    public Texture[] getEnemyBollaAnimation(EnemyBollaAnimationGroup anim) {
        return enemyBolla.get(anim);
    }

    public Texture[] getEnemyCactusAnimation(EnemyCactusAnimationGroup anim) {
        return enemyCactus.get(anim);
    }

    public Texture[] getEnemyCrockarockAnimation(EnemyCrockarockAnimationGroup anim) {
        return enemyCrockarock.get(anim);
    }

    public Texture[] getEnemyCrystalShardAnimation(EnemyCrystalShardAnimationGroup anim) {
        return enemyCrystalShard.get(anim);
    }

    public Texture[] getEnemyDragonflyAnimation(EnemyDragonflyAnimationGroup anim) {
        return enemyDragonfly.get(anim);
    }

    public Texture[] getEnemyFloorSpikesAnimation(EnemyFloorSpikesAnimationGroup anim) {
        return enemyFloorSpikes.get(anim);
    }

    public Texture[] getEnemyFloorSpikesBlueAnimation(EnemyFloorSpikesBlueAnimationGroup anim) {
        return enemyFloorSpikesBlue.get(anim);
    }

    public Texture[] getEnemyFloorSpikesWhiteAnimation(EnemyFloorSpikesWhiteAnimationGroup anim) {
        return enemyFloorSpikesWhite.get(anim);
    }

    public Texture[] getEnemyGanonAnimation(EnemyGanonAnimationGroup anim) {
        return enemyGanon.get(anim);
    }

    public Texture[] getEnemyGanonFairyAnimation(EnemyGanonFairyAnimationGroup anim) {
        return enemyGanonFairy.get(anim);
    }

    public Texture[] getEnemyGiantSquidAnimation(EnemyGiantSquidAnimationGroup anim) {
        return enemyGiantSquid.get(anim);
    }

    public Texture[] getEnemyGibdoAnimation(EnemyGibdoAnimationGroup anim) {
        return enemyGibdo.get(anim);
    }

    public Texture[] getEnemyGolemAnimation(EnemyGolemAnimationGroup anim) {
        return enemyGolem.get(anim);
    }

    public Texture[] getEnemyGreenBirdAnimation(EnemyGreenBirdAnimationGroup anim) {
        return enemyGreenBird.get(anim);
    }

    public Texture[] getEnemyGuardAnimation(EnemyGuardAnimationGroup anim) {
        return enemyGuard.get(anim);
    }

    public Texture[] getEnemyIronKnuckleAnimation(EnemyIronKnuckleAnimationGroup anim) {
        return enemyIronKnuckle.get(anim);
    }

    public Texture[] getEnemyJackAnimation(EnemyJackAnimationGroup anim) {
        return enemyJack.get(anim);
    }

    public Texture[] getEnemyJackarooAnimation(EnemyJackarooAnimationGroup anim) {
        return enemyJackaroo.get(anim);
    }

    public Texture[] getEnemyKannisAnimation(EnemyKannisAnimationGroup anim) {
        return enemyKannis.get(anim);
    }

    public Texture[] getEnemyKelpiAnimation(EnemyKelpiAnimationGroup anim) {
        return enemyKelpi.get(anim);
    }

    public Texture[] getEnemyKnightBlueAnimation(EnemyKnightBlueAnimationGroup anim) {
        return enemyKnightBlue.get(anim);
    }

    public Texture[] getEnemyKnightGreenAnimation(EnemyKnightGreenAnimationGroup anim) {
        return enemyKnightGreen.get(anim);
    }

    public Texture[] getEnemyKnightRedAnimation(EnemyKnightRedAnimationGroup anim) {
        return enemyKnightRed.get(anim);
    }

    public Texture[] getEnemyLanmolaAnimation(EnemyLanmolaAnimationGroup anim) {
        return enemyLanmola.get(anim);
    }

    public Texture[] getEnemyLavaLizardAnimation(EnemyLavaLizardAnimationGroup anim) {
        return enemyLavaLizard.get(anim);
    }

    public Texture[] getEnemyLoccasinAnimation(EnemyLoccasinAnimationGroup anim) {
        return enemyLoccasin.get(anim);
    }

    public Texture[] getEnemyLowderAnimation(EnemyLowderAnimationGroup anim) {
        return enemyLowder.get(anim);
    }

    public Texture[] getEnemyMalmordAnimation(EnemyMalmordAnimationGroup anim) {
        return enemyMalmord.get(anim);
    }

    public Texture[] getEnemyMimicMoleAnimation(EnemyMimicMoleAnimationGroup anim) {
        return enemyMimicMole.get(anim);
    }

    public Texture[] getEnemyMolluskaAnimation(EnemyMolluskaAnimationGroup anim) {
        return enemyMolluska.get(anim);
    }

    public Texture[] getEnemyOctorokAnimation(EnemyOctorokAnimationGroup anim) {
        return enemyOctorok.get(anim);
    }

    public Texture[] getEnemyPasquinadeAnimation(EnemyPasquinadeAnimationGroup anim) {
        return enemyPasquinade.get(anim);
    }

    public Texture[] getEnemyPatraAnimation(EnemyPatraAnimationGroup anim) {
        return enemyPatra.get(anim);
    }

    public Texture[] getEnemyPolsVoiceAnimation(EnemyPolsVoiceAnimationGroup anim) {
        return enemyPolsVoice.get(anim);
    }

    public Texture[] getEnemyPurpleBirdAnimation(EnemyPurpleBirdAnimationGroup anim) {
        return enemyPurpleBird.get(anim);
    }

    public Texture[] getEnemyPurpleFishAnimation(EnemyPurpleFishAnimationGroup anim) {
        return enemyPurpleFish.get(anim);
    }

    public Texture[] getEnemyRomravenAnimation(EnemyRomravenAnimationGroup anim) {
        return enemyRomraven.get(anim);
    }

    public Texture[] getEnemyRopeAnimation(EnemyRopeAnimationGroup anim) {
        return enemyRope.get(anim);
    }

    public Texture[] getEnemySeaMonsterAnimation(EnemySeaMonsterAnimationGroup anim) {
        return enemySeaMonster.get(anim);
    }

    public Texture[] getEnemySpearThrowerAnimation(EnemySpearThrowerAnimationGroup anim) {
        return enemySpearThrower.get(anim);
    }

    public Texture[] getEnemySpikedBlockAnimation(EnemySpikedBlockAnimationGroup anim) {
        return enemySpikedBlock.get(anim);
    }

    public Texture[] getEnemyStalfosAnimation(EnemyStalfosAnimationGroup anim) {
        return enemyStalfos.get(anim);
    }

    public Texture[] getEnemySwampZolaBlueAnimation(EnemySwampZolaBlueAnimationGroup anim) {
        return enemySwampZolaBlue.get(anim);
    }

    public Texture[] getEnemySwampZolaGreenAnimation(EnemySwampZolaGreenAnimationGroup anim) {
        return enemySwampZolaGreen.get(anim);
    }

    public Texture[] getEnemyTinyFishAnimation(EnemyTinyFishAnimationGroup anim) {
        return enemyTinyFish.get(anim);
    }

    public Texture[] getEnemyTornadoAnimation(EnemyTornadoAnimationGroup anim) {
        return enemyTornado.get(anim);
    }

    public Texture[] getEnemyTumbleHeadAnimation(EnemyTumbleHeadAnimationGroup anim) {
        return enemyTumbleHead.get(anim);
    }

    public Texture[] getEnemyTumbleSkullAnimation(EnemyTumbleSkullAnimationGroup anim) {
        return enemyTumbleSkull.get(anim);
    }

    public Texture[] getEnemyTurtleAnimation(EnemyTurtleAnimationGroup anim) {
        return enemyTurtle.get(anim);
    }

    public Texture[] getEnemyUrsoreAnimation(EnemyUrsoreAnimationGroup anim) {
        return enemyUrsore.get(anim);
    }

    public Texture[] getEnemyVaporaAnimation(EnemyVaporaAnimationGroup anim) {
        return enemyVapora.get(anim);
    }

    public Texture[] getEnemyVireAnimation(EnemyVireAnimationGroup anim) {
        return enemyVire.get(anim);
    }

    public Texture[] getEnemyVoltaAnimation(EnemyVoltaAnimationGroup anim) {
        return enemyVolta.get(anim);
    }

    public Texture[] getEnemyWallSpikeAnimation(EnemyWallSpikeAnimationGroup anim) {
        return enemyWallSpike.get(anim);
    }

    public Texture[] getEnemyWallmasterAnimation(EnemyWallmasterAnimationGroup anim) {
        return enemyWallmaster.get(anim);
    }

    public Texture[] getEnemyWarbaneAnimation(EnemyWarbaneAnimationGroup anim) {
        return enemyWarbane.get(anim);
    }

    public Texture[] getEnemyWizzrobeAnimation(EnemyWizzrobeAnimationGroup anim) {
        return enemyWizzrobe.get(anim);
    }

    public Texture[] getEnemyZolAnimation(EnemyZolAnimationGroup anim) {
        return enemyZol.get(anim);
    }

    public Texture getNone() {
        return none;
    }

    public Texture[] getNpcGlebb() {
        return npcGlebb;
    }

    public Texture[] getNpcTalkingChestAnimation(NpcTalkingChestAnimationGroup anim) {
        return npcTalkingChest.get(anim);
    }

    public Texture[] getNpcOghamAnimation(NpcOghamAnimationGroup anim) {
        return npcOgham.get(anim);
    }

    public Texture[] getNpcBeggarAnimation(NpcBeggarAnimationGroup anim) {
        return npcBeggar.get(anim);
    }

    public Texture[] getNpcEnidAnimation(NpcEnidAnimationGroup anim) {
        return npcEnid.get(anim);
    }

    public Texture[] getNpcExhaustedTravelerAnimation(NpcExhaustedTravelerAnimationGroup anim) {
        return npcExhaustedTraveler.get(anim);
    }

    public Texture[] getNpcBlueLadyAnimation(NpcBlueLadyAnimationGroup anim) {
        return npcBlueLady.get(anim);
    }

    public Texture[] getNpcLotharAnimation(NpcLotharAnimationGroup anim) {
        return npcLothar.get(anim);
    }

    public Texture[] getNpcKrebbAnimation(NpcKrebbAnimationGroup anim) {
        return npcKrebb.get(anim);
    }

    public Texture[] getNpcArcheryMinigameOwnerAnimation(NpcArcheryMinigameOwnerAnimationGroup anim) {
        return npcArcheryMinigameOwner.get(anim);
    }

    public Texture[] getNpcBitterbeckAnimation(NpcBitterbeckAnimationGroup anim) {
        return npcBitterbeck.get(anim);
    }

    public Texture[] getNpcBlacksmithAnimation(NpcBlacksmithAnimationGroup anim) {
        return npcBlacksmith.get(anim);
    }

    public Texture[] getNpcDressyWomanAnimation(NpcDressyWomanAnimationGroup anim) {
        return npcDressyWoman.get(anim);
    }

    public Texture[] getNpcEricAndIanAnimation(NpcEricAndIanAnimationGroup anim) {
        return npcEricAndIan.get(anim);
    }

    public Texture[] getNpcFaustAnimation(NpcFaustAnimationGroup anim) {
        return npcFaust.get(anim);
    }

    public Texture[] getNpcFriendlyGoblinAnimation(NpcFriendlyGoblinAnimationGroup anim) {
        return npcFriendlyGoblin.get(anim);
    }

    public Texture[] getNpcGhostDogAnimation(NpcGhostDogAnimationGroup anim) {
        return npcGhostDog.get(anim);
    }

    public Texture[] getNpcGhostFarmerAnimation(NpcGhostFarmerAnimationGroup anim) {
        return npcGhostFarmer.get(anim);
    }

    public Texture[] getNpcDenGoblinAnimation(NpcDenGoblinAnimationGroup anim) {
        return npcDenGoblin.get(anim);
    }

    public Texture[] getNpcGreatFairyAnimation(NpcGreatFairyAnimationGroup anim) {
        return npcGreatFairy.get(anim);
    }

    public Texture[] getNpcGreenKnightSpectatorAnimation(NpcGreenKnightSpectatorAnimationGroup anim) {
        return npcGreenKnightSpectator.get(anim);
    }

    public Texture[] getNpcGwynlaAnimation(NpcGwynlaAnimationGroup anim) {
        return npcGwynla.get(anim);
    }

    public Texture[] getNpcHoodedWomanAnimation(NpcHoodedWomanAnimationGroup anim) {
        return npcHoodedWoman.get(anim);
    }

    public Texture[] getNpcHorseAnimation(NpcHorseAnimationGroup anim) {
        return npcHorse.get(anim);
    }

    public Texture[] getNpcHouseKeeperAnimation(NpcHouseKeeperAnimationGroup anim) {
        return npcHouseKeeper.get(anim);
    }

    public Texture[] getNpcKronThePeglegAnimation(NpcKronThePeglegAnimationGroup anim) {
        return npcKronThePegleg.get(anim);
    }

    public Texture[] getNpcLivingFarmerAnimation(NpcLivingFarmerAnimationGroup anim) {
        return npcLivingFarmer.get(anim);
    }

    public Texture[] getNpcLonlynAnimation(NpcLonlynAnimationGroup anim) {
        return npcLonlyn.get(anim);
    }

    public Texture[] getNpcLoungerAnimation(NpcLoungerAnimationGroup anim) {
        return npcLounger.get(anim);
    }

    public Texture[] getNpcMadameKriggleAnimation(NpcMadameKriggleAnimationGroup anim) {
        return npcMadameKriggle.get(anim);
    }

    public Texture[] getNpcEtheraAnimation(NpcEtheraAnimationGroup anim) {
        return npcEthera.get(anim);
    }

    public Texture[] getNpcBeachcomberAnimation(NpcBeachcomberAnimationGroup anim) {
        return npcBeachcomber.get(anim);
    }

    public Texture[] getNpcYelenaAnimation(NpcYelenaAnimationGroup anim) {
        return npcYelena.get(anim);
    }

    public Texture[] getNpcGiantSwampRatAnimation(NpcGiantSwampRatAnimationGroup anim) {
        return npcGiantSwampRat.get(anim);
    }

    public Texture[] getNpcMysticAnimation(NpcMysticAnimationGroup anim) {
        return npcMystic.get(anim);
    }

    public Texture[] getNpcOldSailorAnimation(NpcOldSailorAnimationGroup anim) {
        return npcOldSailor.get(anim);
    }

    public Texture[] getNpcPickpocketAnimation(NpcPickpocketAnimationGroup anim) {
        return npcPickpocket.get(anim);
    }

    public Texture[] getNpcPurpleStallOwnerAnimation(NpcPurpleStallOwnerAnimationGroup anim) {
        return npcPurpleStallOwner.get(anim);
    }

    public Texture[] getNpcQuarryMinerAnimation(NpcQuarryMinerAnimationGroup anim) {
        return npcQuarryMiner.get(anim);
    }

    public Texture[] getNpcRandomCapeGuyAnimation(NpcRandomCapeGuyAnimationGroup anim) {
        return npcRandomCapeGuy.get(anim);
    }

    public Texture[] getNpcRebelLeftAnimation(NpcRebelLeftAnimationGroup anim) {
        return npcRebelLeft.get(anim);
    }

    public Texture[] getNpcRebelRightAnimation(NpcRebelRightAnimationGroup anim) {
        return npcRebelRight.get(anim);
    }

    public Texture[] getNpcSailorAnimation(NpcSailorAnimationGroup anim) {
        return npcSailor.get(anim);
    }

    public Texture[] getNpcSailor2Animation(NpcSailor2AnimationGroup anim) {
        return npcSailor2.get(anim);
    }

    public Texture[] getNpcKnaveAnimation(NpcKnaveAnimationGroup anim) {
        return npcKnave.get(anim);
    }

    public Texture[] getNpcFatShopkeeperAnimation(NpcFatShopkeeperAnimationGroup anim) {
        return npcFatShopkeeper.get(anim);
    }

    public Texture[] getNpcShopkeeperDogAnimation(NpcShopkeeperDogAnimationGroup anim) {
        return npcShopkeeperDog.get(anim);
    }

    public Texture[] getNpcShurmakAnimation(NpcShurmakAnimationGroup anim) {
        return npcShurmak.get(anim);
    }

    public Texture[] getNpcSirramBewAnimation(NpcSirramBewAnimationGroup anim) {
        return npcSirramBew.get(anim);
    }

    public Texture[] getNpcSkaterDudeAnimation(NpcSkaterDudeAnimationGroup anim) {
        return npcSkaterDude.get(anim);
    }

    public Texture[] getNpcSleepingManAnimation(NpcSleepingManAnimationGroup anim) {
        return npcSleepingMan.get(anim);
    }

    public Texture[] getNpcStallOwner2Animation(NpcStallOwner2AnimationGroup anim) {
        return npcStallOwner2.get(anim);
    }

    public Texture[] getNpcTalkingMushroomAnimation(NpcTalkingMushroomAnimationGroup anim) {
        return npcTalkingMushroom.get(anim);
    }

    public Texture[] getNpcThabulAnimation(NpcThabulAnimationGroup anim) {
        return npcThabul.get(anim);
    }

    public Texture[] getNpcThirstyChildAnimation(NpcThirstyChildAnimationGroup anim) {
        return npcThirstyChild.get(anim);
    }

    public Texture[] getNpcToobarAnimation(NpcToobarAnimationGroup anim) {
        return npcToobar.get(anim);
    }

    public Texture[] getNpcTownMerchantAnimation(NpcTownMerchantAnimationGroup anim) {
        return npcTownMerchant.get(anim);
    }

    public Texture[] getNpcRycoAnimation(NpcRycoAnimationGroup anim) {
        return npcRyco.get(anim);
    }

    public Texture[] getNpcDockhandAnimation(NpcDockhandAnimationGroup anim) {
        return npcDockhand.get(anim);
    }

    public Texture[] getNpcQuietFishermanAnimation(NpcQuietFishermanAnimationGroup anim) {
        return npcQuietFisherman.get(anim);
    }

    public Texture[] getNpcWaldensopAnimation(NpcWaldensopAnimationGroup anim) {
        return npcWaldensop.get(anim);
    }

    public Texture[] getNpcForeignWomanAnimation(NpcForeignWomanAnimationGroup anim) {
        return npcForeignWoman.get(anim);
    }

    public Texture[] getNpcMerribalAnimation(NpcMerribalAnimationGroup anim) {
        return npcMerribal.get(anim);
    }

    public Texture[] getNpcSquireGripAnimation(NpcSquireGripAnimationGroup anim) {
        return npcSquireGrip.get(anim);
    }

    public Texture[] getNpcPurpleRavenAnimation(NpcPurpleRavenAnimationGroup anim) {
        return npcPurpleRaven.get(anim);
    }

    public Texture[] getNpcBrideAnimation(NpcBrideAnimationGroup anim) {
        return npcBride.get(anim);
    }

    public Texture[] getNpcNimoneeAnimation(NpcNimoneeAnimationGroup anim) {
        return npcNimonee.get(anim);
    }

    public Texture[] getNpcStrongmanAnimation(NpcStrongmanAnimationGroup anim) {
        return npcStrongman.get(anim);
    }

    public Texture[] getNpcFortuneTellerAnimation(NpcFortuneTellerAnimationGroup anim) {
        return npcFortuneTeller.get(anim);
    }

    public Texture[] getNpcTournamentSpectatorAnimation(NpcTournamentSpectatorAnimationGroup anim) {
        return npcTournamentSpectator.get(anim);
    }

    public Texture[] getNpcTwinsFatherAnimation(NpcTwinsFatherAnimationGroup anim) {
        return npcTwinsFather.get(anim);
    }

    public Texture[] getNpcWaiterAnimation(NpcWaiterAnimationGroup anim) {
        return npcWaiter.get(anim);
    }

    public Texture[] getNpcDeerHunterAnimation(NpcDeerHunterAnimationGroup anim) {
        return npcDeerHunter.get(anim);
    }

    public Texture[] getNpcDebblinOfDurodAnimation(NpcDebblinOfDurodAnimationGroup anim) {
        return npcDebblinOfDurod.get(anim);
    }

    public Texture[] getNpcLodgeOwnerAnimation(NpcLodgeOwnerAnimationGroup anim) {
        return npcLodgeOwner.get(anim);
    }

    public Texture[] getNpcWimbichAnimation(NpcWimbichAnimationGroup anim) {
        return npcWimbich.get(anim);
    }

    public Texture[] getNpcYalzanAnimation(NpcYalzanAnimationGroup anim) {
        return npcYalzan.get(anim);
    }

    public Texture[] getNpcYellowStallOwnerAnimation(NpcYellowStallOwnerAnimationGroup anim) {
        return npcYellowStallOwner.get(anim);
    }

    public Texture[] getNpcYvonneAnimation(NpcYvonneAnimationGroup anim) {
        return npcYvonne.get(anim);
    }

    public Texture[] getNpcZeldaSlidingAnimation(NpcZeldaSlidingAnimationGroup anim) {
        return npcZeldaSliding.get(anim);
    }

    public Texture getRubyBlue() {
        return rubyBlue;
    }

    public Texture getRubyYellow() {
        return rubyYellow;
    }

    public Texture getHeart() {
        return heart;
    }

    public Texture[] getHudNumbers() {
        return hudNumbers;
    }

    public Texture getHudNumber(int number) {
        if (number < 0 || number >= hudNumbers.length) {
            throw new IllegalArgumentException("HUD number out of range: " + number);
        }

        return hudNumbers[number];
    }

    public Texture getHudHeartEmpty() {
        return hudHeartEmpty;
    }

    public Texture getHudHeartHalf() {
        return hudHeartHalf;
    }

    public Texture getHudHeartFull() {
        return hudHeartFull;
    }

    public Texture getWand() {
        return wand;
    }

    public Texture getBoomerang() {
        return boomerang;
    }

    public Texture getCalm() {
        return calm;
    }

    public Texture getCalmPrice() {
        return calmPrice;
    }

    public Texture getDagger() {
        return dagger;
    }

    public Texture getArchipelagoColor() {
        return archipelagoColor;
    }

    public Texture getFirestorm() {
        return firestorm[0];
    }

    public Texture[] getFirestormAnimation() {
        return firestorm;
    }

    public Texture getBone() {
        return bone;
    }

    public Texture getCandle() {
        return candle;
    }

    public Texture getCandlePrice() {
        return candlePrice;
    }

    public Texture getLadder() {
        return ladder;
    }

    public Texture getMagicShield() {
        return magicShield;
    }

    public Texture getMagicShieldPrice() {
        return magicShieldPrice;
    }

    public Texture getSpriteLadder() {
        return spriteLadder;
    }

    public Texture[] getSpriteLlortGateBottomAnimation(SpriteLlortGateBottomAnimationGroup anim) {
        return spriteLlortGateBottom.get(anim);
    }

    public Texture[] getSpriteLlortGateBottom() {
        return getSpriteLlortGateBottomAnimation(SpriteLlortGateBottomAnimationGroup.IDLE);
    }

    public Texture[] getSpriteLlortGateTopAnimation(SpriteLlortGateTopAnimationGroup anim) {
        return spriteLlortGateTop.get(anim);
    }

    public Texture[] getSpriteLlortGateTop() {
        return getSpriteLlortGateTopAnimation(SpriteLlortGateTopAnimationGroup.IDLE);
    }

    public Texture[] getSpriteLlortLaserAnimation(SpriteLlortLaserAnimationGroup anim) {
        return spriteLlortLaser.get(anim);
    }

    public Texture[] getSpriteLlortLaser() {
        return getSpriteLlortLaserAnimation(SpriteLlortLaserAnimationGroup.IDLE);
    }

    public Texture[] getSpriteExplosionAnimation(SpriteExplosionAnimationGroup anim) {
        return spriteExplosion.get(anim);
    }

    public Texture[] getSpriteExplosion() {
        return getSpriteExplosionAnimation(SpriteExplosionAnimationGroup.IDLE);
    }

    public Texture[] getSpriteSparkleAnimation(SpriteSparkleAnimationGroup anim) {
        return spriteSparkle.get(anim);
    }

    public Texture[] getSpriteSparkle() {
        return getSpriteSparkleAnimation(SpriteSparkleAnimationGroup.IDLE);
    }

    public Texture[] getSpriteCampfireAnimation(SpriteCampfireAnimationGroup anim) {
        return spriteCampfire.get(anim);
    }

    public Texture[] getSpriteCampfire() {
        return getSpriteCampfireAnimation(SpriteCampfireAnimationGroup.IDLE);
    }

    public Texture getPitcherEmpty() {
        return pitcherEmpty;
    }

    public Texture getPitcherFull() {
        return pitcherFull;
    }

    public Texture getVialOfWind() {
        return vialOfWind;
    }

    public Texture getItemScreen() {
      return itemScreen;
    }

    public Texture getGlow() {
        return glow;
    }

    public Texture[] getFriendlyBoomerang() {
        return friendlyBoomerang;
    }

    public Texture[][] getFriendlyCalm() {
        return friendlyCalm;
    }

    public Texture[] getFriendlyDagger() {
        return friendlyDagger;
    }

    public Texture[][] getFriendlyFirestorm() {
        return friendlyFirestorm;
    }

    public Texture[] getFriendlyJadeRing() {
        return friendlyJadeRing;
    }

    public Texture[] getEnemyBoomerang() {
        return enemyBoomerang;
    }

    public Texture[] getEnemySpear() {
        return enemySpear;
    }

    public Texture[] getEnemyLlortAxe() {
        return enemyLlortAxe;
    }

    public Texture getEnemyPeahatProjectile() {
        return enemyPeahatProjectile;
    }

    public Texture getJadeRing() {
        return jadeRing;
    }

    public Texture getRedBoots() {
        return redBoots;
    }

    public Texture getCelestialStone(int index) {
        switch(index) {
        case 1:
            return celestialStone1;
        case 2:
            return celestialStone2;
        case 3:
            return celestialStone3;
        case 4:
            return celestialStone4;
        case 5:
            return celestialStone5;
        case 6:
            return celestialStone6;
        case 7:
            return celestialStone7;
        default:
            throw new IllegalStateException(this.getClass().getSimpleName() + ": celestial stone out of index");
        }
    }

    public Texture getCompass(int index) {
        switch(index) {
        case 1:
            return compass1;
        case 2:
            return compass2;
        case 3:
            return compass3;
        case 4:
            return compass4;
        case 5:
            return compass5;
        case 6:
            return compass6;
        case 7:
            return compass7;
        default:
            throw new IllegalStateException(this.getClass().getSimpleName() + ": compass out of index");
        }
    }

    public Texture getUnderworldMap(int index) {
        switch(index) {
        case 1:
            return underworldMap1;
        case 2:
            return underworldMap2;
        case 3:
            return underworldMap3;
        case 4:
            return underworldMap4;
        case 5:
            return underworldMap5;
        case 6:
            return underworldMap6;
        case 7:
            return underworldMap7;
        default:
            throw new IllegalStateException(this.getClass().getSimpleName() + ": underworld map out of index");
        }
    }
}
