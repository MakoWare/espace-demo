package com.makoware.explorationspace.Entity.Ships.Equipment.Weapons;


public class Cache {
	
	
	
	private Class<? extends Projectile> type;
	private int ammo;
	private int capacity;

	public Cache(Cache copy){
		this.type = copy.type;
		this.ammo = copy.ammo;
		this.capacity = copy.capacity;
	}
	
	public Cache(Class<? extends Projectile> type, int ammoCount) {
		this.type = type;
		this.ammo = ammoCount;
		this.capacity = ammoCount;
	}
	
	public int getAmmo(){
		return this.ammo;
	}
	
	public Class<? extends Projectile> getType(){
		return type;
	}
	
	public int getCapacity(){
		return this.capacity;
	}
	
	public void setCapacity(int cap){
		this.capacity = cap;
	}
	
	public void reload(){
		reload(capacity);
	}

	public void reload(int ammoCount) {
		this.ammo = ammoCount;
	}
	
	public Projectile pull(){
		this.ammo--;
		Projectile p = null;
		try {
			p = type.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return p;
//		return Pools.obtain(type);
	}
	
}
