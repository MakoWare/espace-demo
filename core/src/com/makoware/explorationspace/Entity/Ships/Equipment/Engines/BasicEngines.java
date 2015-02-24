package com.makoware.explorationspace.Entity.Ships.Equipment.Engines;


public class BasicEngines extends Engines {
	
	
	public BasicEngines(){
		super();
		
		
		impulse = 20f;
		minFactor = 30f;
		factorScale = 5f;
		maxFactor = 55f;
		velocity = impulse;
	}

}
