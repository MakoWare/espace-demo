package com.makoware.explorationspace.Entity.Ships.Equipment;

import com.badlogic.gdx.math.Vector2;
import com.makoware.explorationspace.Entity.Ships.Equipment.Engines.Engines;

public class PropulsionSystems {
	
	
	private FuelSystems fuel;
	
	private Engines engines;
//	
//	private final float IMPULSE = 20f;
//	private final float FACTOR_1 = 40f;
//	private final float FACTOR_2 = 60f;
//	private final float FACTOR_3 = 80f;
//	private final float FACTOR_4 = 100f;
//	private final float FACTOR_5 = 120f;
//	private float currentSpeed = IMPULSE;

	public PropulsionSystems(FuelSystems fuel) {
		this.fuel = fuel;
	}

//	public float getCurrentSpeed() {
//		return this.currentSpeed;
//	}

	public void boost(int dir) {
		engines.boost(dir);
		
	}
	
	public boolean isBoosting(){
		return this.engines.isBoosting();
	}

	public void boostUp(int dir) {
		if(dir==0){
			engines.boostUp();
		}
	}
	
	public void boostDown(int dir) {
		if(dir==0){
			engines.boostDown();
		}
	}

	public Vector2 go(Vector2 vector) {
		return vector.scl(engines.getSpeed());
	}

	public void setEngines(Engines e) {
		this.engines = e;
	}
	
	public Engines getEngines(){
		return this.engines;
	}

}
