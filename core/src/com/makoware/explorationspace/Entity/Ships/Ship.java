package com.makoware.explorationspace.Entity.Ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.makoware.explorationspace.Entity.Ships.Equipment.Crew;
import com.makoware.explorationspace.Entity.Ships.Equipment.DefenseSystems;
import com.makoware.explorationspace.Entity.Ships.Equipment.FuelSystems;
import com.makoware.explorationspace.Entity.Ships.Equipment.PropulsionSystems;
import com.makoware.explorationspace.Entity.Ships.Equipment.SensorSystems;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Projectile;
import com.makoware.explorationspace.Entity.Ships.Equipment.WeaponsSystems;
import com.makoware.explorationspace.Entity.SpaceObject;


public abstract class Ship extends SpaceObject {
	@SuppressWarnings("unused")
	private static String tag = "Ship";
	/*
	 * have weapons systems, fuel, passengers, shields, sensor systems, propulsion systems (ie. impulse/warp), etc.
	 * 
	 */
	
//	protected Sprite sprite;
	protected int id;
	
	protected short category;
	protected short mask;
	
	protected WeaponsSystems weapons;
	protected FuelSystems fuel;
	protected Crew crew;
	protected DefenseSystems defense;
	protected SensorSystems sensors;
	protected PropulsionSystems prop;
	
	protected Vector2 vector;
	protected Vector2 goVec;
	
	public static Vector2 shipSpeed;
	
	public Ship(int id){
		this.id = id;
		weapons = new WeaponsSystems(id);
		fuel = new FuelSystems();
		crew = new Crew();
		defense = new DefenseSystems(fuel);
		sensors = new SensorSystems();
		prop = new PropulsionSystems(fuel);
		
		vector = new Vector2();
		goVec = new Vector2();
		shipSpeed = new Vector2();
		hasHealth2 = true;
	}
	
	
	@Override
	public void draw(SpriteBatch batch) {
		weapons.draw(batch);
		
		health2 = defense.getShield().getStrength();
		health1 = defense.getHull().getStrength();
		
		Vector2 v = body.getPosition();
		sprite.setPosition(v.x-(sprite.getWidth()/2f), v.y-(sprite.getHeight()/2f));
		sprite.draw(batch);
		
		
		super.draw(batch);
	}
	
	
	public void setVector(float x, float y){
		this.vector.set(x, y);
	}
	
	public void setVector(Vector2 v){
		this.setVector(v.x, v.y);
	}
	
	public Vector2 getVector(){
		return this.vector;
	}
	
//	public float getSpeed(){
//		return this.prop.getCurrentSpeed();
//	}

	public WeaponsSystems getWeapons() {
		return weapons;
	}

	public void setWeapons(WeaponsSystems weapons) {
		this.weapons = weapons;
	}

	public FuelSystems getFuel() {
		return fuel;
	}

	public void setFuel(FuelSystems fuel) {
		this.fuel = fuel;
	}

	public Crew getCrew() {
		return crew;
	}

	public void setCrew(Crew crew) {
		this.crew = crew;
	}

	public DefenseSystems getDefense() {
		return defense;
	}

	public void setDefense(DefenseSystems defense) {
		this.defense = defense;
	}

	public SensorSystems getSensors() {
		return sensors;
	}

	public void setSensors(SensorSystems sensors) {
		this.sensors = sensors;
	}

	public PropulsionSystems getProp() {
		return prop;
	}

	public void setProp(PropulsionSystems prop) {
		this.prop = prop;
	}

	public void boost(int dir){
		prop.boost(dir);
	}

	public void go(float delta) {
		
//		this.body.setLinearVelocity(this.vector.cpy().scl(getSpeed()));
		goVec.set(vector);
		this.body.setLinearVelocity(prop.go(goVec.nor()));
		
		this.weapons.update(delta);
		
		if(defense.isDead()){
			dead();
		}
		
	}
	
	@Override
	public boolean isDead() {
		return defense.isDead();
	}
	
	@Override
	public void removeFromWorld() {
		this.weapons.clear();
		super.removeFromWorld();
	}
	
	private void dead() {
		
	}

	@Override
	public void damage(Projectile p){
		this.defense.damage(p.getDamage());
//		this.hitBy = p.getOwner();
		p.explode();
		hitTime = 0;
//		Gdx.app.log("Ship", "Shields: "+defense.getShield().getStrength() + "  Hull: "+defense.getHull().getStrength());
	}

	public void firePrimary() {
//		weapons.targetVector().scl(body.getLinearVelocity());
//		Gdx.app.log(tag, ""+body.getLinearVelocity().len());
		if(body.getLinearVelocity().x!=0 || body.getLinearVelocity().y!=0)
			shipSpeed = body.getLinearVelocity();
		
		this.weapons.firePrimary(body.getPosition());
	}


	public void fireSecondary() {
		if(body.getLinearVelocity().x!=0 || body.getLinearVelocity().y!=0)
			shipSpeed = body.getLinearVelocity();
		
		this.weapons.fireSecondary(body.getPosition());
	}
	
	public void kill(){
		removeFromWorld();
	}

	public int getId(){
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
		this.weapons.setId(id);
	}

}
