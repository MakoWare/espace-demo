package com.makoware.explorationspace.Entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pools;
import com.makoware.explorationspace.Framework.AssetManager;

public class BackgroundStar extends Entity {
	
	private Sprite sprite;

	public BackgroundStar(){
		sprite = new Sprite(AssetManager.get("star"));
	}
	
	public BackgroundStar(float x, float y){
		this(x,y,2);
	}
	
	public BackgroundStar(float x, float y, float size) {
		this();
		sprite.setBounds(x, y, size, size);
	}

	@Override
	public Vector2 getPosition() {
		return Pools.obtain(Vector2.class).set(sprite.getX(), sprite.getY());
	}
	
	public float getX(){
		return sprite.getX();
	}
	
	public float getY(){
		return sprite.getY();
	}
	
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	@Override
	public void addToWorld(World world) {
	}

	@Override
	public void removeFromWorld() {
	}

	@Override
	public void dispose() {
	}

	

}
