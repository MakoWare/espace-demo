package com.makoware.explorationspace.Entity.Ships.Equipment.Weapons;

public abstract class Gun {
	
	

	protected Cache cache;
	protected Cache tempCache;
	
	protected boolean tempLoad;
	
	protected boolean active;
	protected boolean previouslyActive;
	
	public void load(Cache cache){
		this.cache = cache;
		this.active = true;
	}
	
	public void load(Class<? extends Projectile> type, int ammoCount){
		load(new Cache(type,ammoCount));
	}
	
	public void load(int ammoCount){
		this.cache.reload(ammoCount);
	}
	
	public Projectile fire(){
		Projectile p = null;
		if(cache.getAmmo()>0){
			p = cache.pull();
			if(cache.getAmmo()==0 && tempLoad)
				revert();
		} 
		return p;
	}

	public Cache getCache() {
		return this.cache;
	}

	public void tempLoad(Class<? extends Projectile> type, int ammoCount) {
		if(tempLoad){
			cache.reload(ammoCount);
		} else {
			previouslyActive = active;
			active = true;
			tempLoad = true;
			if(cache!=null)
				tempCache = new Cache(cache);
			
			load(type,ammoCount);
		}
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void revert(){
		tempLoad = false;
		active = previouslyActive;
		cache = tempCache;
	}

	

}
