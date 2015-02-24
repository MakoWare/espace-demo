package com.makoware.explorationspace.Entity.Powerup;

import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Gun;
import com.makoware.explorationspace.Entity.Ships.Equipment.WeaponsSystems;

public abstract class WeaponsPowerup extends Powerup {
	public static final int PRIMARY = 0;
	public static final int SECONDARY = 1;
	
	
	protected int gunSide;
	
	
	public void applyTo(WeaponsSystems w){
		switch(gunSide){
		case PRIMARY:
			applyToPrimary(w.getPrimaryGun());
			break;
		case SECONDARY:
			applyToSecondary(w.getSecondaryGun());
			break;
		}
	}


	public abstract void applyToPrimary(Gun gun);
	
	public abstract void applyToSecondary(Gun gun);
}
