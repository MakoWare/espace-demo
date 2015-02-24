package com.makoware.explorationspace.Entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Entity {
	
	protected Body body;
	
	protected Sprite sprite;
	
	protected Vector2 position = new Vector2();
	
	protected boolean isDead = false;

	public abstract void draw(SpriteBatch batch);
	
	public abstract void addToWorld(World world);
	
	public abstract void removeFromWorld();

	public Vector2 getPosition() {
		if(body==null)
			return position;
		else
			return body.getPosition();
	}

	public abstract void dispose();

	public Body getBody() {
		return this.body;
	}
	
	public boolean isDead(){
		return isDead;
	}

}
