package com.makoware.explorationspace.Entity.Ships.Equipment;

import com.makoware.explorationspace.Entity.Ships.Equipment.Defense.DeflectorShield;
import com.makoware.explorationspace.Entity.Ships.Equipment.Defense.Hull;
import com.makoware.explorationspace.Entity.Ships.Equipment.Defense.Shield;

public class DefenseSystems {
	
	private Shield shield;
	private Shield hull;
	
	

	public DefenseSystems(FuelSystems fuel) {
		this.shield = new DeflectorShield();
		this.hull = new Hull();
	}
	
	public void damage(float damage){
		if(shield.getStrength()>0)
			this.shield.damage(damage);
		else
			this.hull.damage(damage);
	}
	
	public Shield getShield(){
		return this.shield;
	}
	
	public Shield getHull(){
		return this.hull;
	}
	
	public boolean isDead() {
		return shield.getStrength()<=0 && hull.getStrength()<=0;
	}

}
