package com.makoware.explorationspace.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class Planet extends Entity {
	
	protected boolean supportsLife;
	protected boolean hasResources;
	protected boolean isKnown;
	
	protected Circle circle = new Circle();
	protected String name = "Planet";
	
	@Override
	public void draw(SpriteBatch batch) {
		sprite.setPosition(position.x, position.y);
		sprite.draw(batch);
	}

	public void setPosition(float x, float y) {
		this.position.set(x,y);
	}

	public void setBounds(float x, float y, float r) {
		this.position.set(x,y);
		this.sprite.setBounds(x, y, r, r);
		this.circle.set(x+r/2, y+r/2, r/2);
	}
	
	public boolean isKnown(){
		return this.isKnown;
	}

	public boolean contains(Vector2 point) {
		return this.circle.contains(point);
	}

	public String getName() {
		return this.name;
	}

}
