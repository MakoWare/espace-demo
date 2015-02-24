package com.makoware.explorationspace.Entity.Ships.Equipment.Engines;

public abstract class Engines {
	
	protected float impulse;
	
	protected float maxFactor;
	protected float minFactor;
	
	protected float factorScale;

	
	protected boolean isBoosting;
	
	protected float efficiency; // Efficiency 
	
	protected float velocity;
	
	public Engines(){
		
	}
	
	public boolean isBoosting(){
		return this.isBoosting;
	}


	public float getSpeed() {
		return velocity;
	}


	public void boost(int dir) {
		if(dir==0){
			velocity = minFactor;
			isBoosting = true;
		} else {
			velocity = impulse;
			isBoosting = false;
		}
	}


	public void boostUp() {
		velocity+=factorScale;
		if(velocity>maxFactor)
			velocity = maxFactor;
	}
	
	public void boostDown() {
		velocity-=factorScale;
		if(velocity<minFactor)
			velocity = minFactor;
	}
	
//	public void consume();
	

}
