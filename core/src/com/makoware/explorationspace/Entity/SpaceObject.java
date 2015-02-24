package com.makoware.explorationspace.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.makoware.explorationspace.ESModel;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Projectile;
import com.makoware.explorationspace.Framework.AssetManager;

public abstract class SpaceObject extends Entity {
	
	protected Sprite shields = new Sprite(AssetManager.get("health_bar"));
	protected Sprite hull = new Sprite(shields);
	
	protected float hitTimeOut = 5f;
	protected float hitTime = 99;
	
	protected int worth;
	
	protected boolean hasHealth2 = false;
	protected float health1 = 100;
	protected float health2 = 100;
	
//	protected int strength = 100;
	protected float resistance;
	
	@Override
	public void draw(SpriteBatch batch) {
		if(hitTime<hitTimeOut){
			Vector2 v = body.getPosition();
			hitTime+=Gdx.graphics.getDeltaTime();
			if(hasHealth2){
				shields.setColor(getColor(health2));
				shields.setBounds(v.x-(sprite.getWidth()*1.8f)/2f, v.y+sprite.getHeight()+0.15f, (sprite.getWidth()*1.8f)*(health2/100f), 0.3f);
				shields.draw(batch);
			}
			hull.setColor(getColor(health1));
			hull.setBounds(v.x-(sprite.getWidth()*1.8f)/2f, v.y+sprite.getHeight()-0.4f, (sprite.getWidth()*1.8f)*(health1/100f), 0.3f);
			hull.draw(batch);
		}
	}
	
	public Color getColor(float num){
		if(num>40)
			return Color.GREEN;
		else if(num>15)
			return Color.YELLOW;
		else
			return Color.RED;
	}

	public void damage(Projectile p){
		Gdx.app.log("SpaceObject", "damage");
		p.explode();
		hitTime = 0;
		if(hasHealth2){
			health2 -= (int)(p.getDamage()*resistance);
			if(health2<0)
				health2 = 0;
		}
		if(health2<=0 || !hasHealth2){
			health1 -= (int)(p.getDamage()*resistance);
			if(health1<0)
				health1 = 0;
		}
	}

	@Override
	public boolean isDead() {
		return health1==0;
	}

	public int worth() {
		return worth;
	}
	
	@Override
	public void removeFromWorld() {
		if(body!=null)
			ESModel.world.destroyBody(body);
	}
	
}
