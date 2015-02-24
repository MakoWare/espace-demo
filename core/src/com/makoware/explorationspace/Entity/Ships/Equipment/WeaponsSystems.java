package com.makoware.explorationspace.Entity.Ships.Equipment;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Bullet;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Cache;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Gun;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Projectile;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.SimpleGun;

import java.util.ArrayList;
import java.util.HashMap;

public class WeaponsSystems {
	private static String tag = "WeaponsSystems";
	
	private Gun primary;
	private Gun secondary;
	
	private HashMap<String, Cache> stock;
	
	private ArrayList<Projectile> projectiles;
	
	private Vector2 targetVector = new Vector2();
	private Vector2 lastVector = new Vector2(1,0);
	
	private int id;

	private int iter;
	
	public WeaponsSystems(int id){
		this.id = id;
		primary = new SimpleGun();
		secondary = new SimpleGun();
		stock = new HashMap<String, Cache>();
		projectiles = new ArrayList<Projectile>();
		
		primary.load(Bullet.class, 100);
//		secondary.load(Bullet.class, 100);
	}
	
	public Vector2 targetVector() {
		return this.targetVector;
	}
	
	public void firePrimary(Vector2 origin){
		if(primary.isActive()){
			if(targetVector.x!=0 || targetVector.y!=0){
				lastVector.set(targetVector);
			} else {
				targetVector.set(lastVector);
			}
			Projectile p = primary.fire();
			if(p!=null){
				p.fire(id, origin,targetVector.nor());
				projectiles.add(p);
			}
		}
		
	}
	
	public void fireSecondary(Vector2 origin){
		if(secondary.isActive()){
			if(targetVector.x!=0 || targetVector.y!=0){
				lastVector.set(targetVector);
			} else {
				targetVector.set(lastVector);
			}
			Projectile p = secondary.fire();
			if(p!=null){
				p.fire(id, origin,targetVector.nor());
				projectiles.add(p);
			}
		}
	}


	public void update(float delta) {
//		Iterator<Projectile> itr = projectiles.iterator();
		iter = 0;
		while(iter<projectiles.size()){
			Projectile p = projectiles.get(iter);
			if(p!=null){
				if(!p.exploded())
					p.fly(delta);
				else{
					p.removeFromWorld();
					projectiles.remove(iter);
				}
			}
			iter++;
		}
	}

	public ArrayList<Projectile> getProjectiles() {
		return this.projectiles;
	}

	public void draw(SpriteBatch batch) {
//		Iterator<Projectile> itr = projectiles.iterator();
		iter = 0;
		while(iter<projectiles.size()){
			Projectile p = projectiles.get(iter);
			if(p!=null){
				if(!p.exploded())
					p.draw(batch);
				else{
					p.removeFromWorld();
					projectiles.remove(iter);
				}
			}
			iter++;
		}
	}

	public Gun getPrimaryGun() {
		return this.primary;
	}
	
	public Gun getSecondaryGun(){
		return this.secondary;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void clear() {
		iter=0;
		while(iter<projectiles.size()){
			Projectile p = projectiles.get(iter);
			if(p!=null){
				if(!p.exploded())
					p.explode();
				p.removeFromWorld();
				projectiles.remove(iter);
			}
			iter++;
		}
	}

}
