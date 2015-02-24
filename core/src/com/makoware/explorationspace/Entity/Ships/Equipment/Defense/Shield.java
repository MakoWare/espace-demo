package com.makoware.explorationspace.Entity.Ships.Equipment.Defense;

public abstract class Shield {
	
	protected int strength;
	protected float resistance;
	
	public void damage(float damage){
		strength -= (int)(damage*resistance);
		if(strength<0)
			strength = 0;
	}
	
	public int getStrength(){
		return strength;
	}
}
