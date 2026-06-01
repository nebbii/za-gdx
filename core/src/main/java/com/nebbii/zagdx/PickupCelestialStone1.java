package com.nebbii.zagdx;

/*
class env_celestialSign:
	def onTouchOrPushBlockStoppedMoving(self):
		save[SIGN_S2] = 1 # 0x1, b'\x00\x01'
		exitShrineAndPlayMovie(shrine=1)
*/
public class PickupCelestialStone1 extends PickupCelestialStone {
    public PickupCelestialStone1() {
        super(Treasure.CELESTIAL_SIGN_1);
    }
}
