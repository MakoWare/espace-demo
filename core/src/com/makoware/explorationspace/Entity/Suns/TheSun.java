package com.makoware.explorationspace.Entity.Suns;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.makoware.explorationspace.Entity.Entity;
import com.makoware.explorationspace.Framework.AssetManager;

public class TheSun extends Entity{
	
	public TheSun(){
		this.sprite = new Sprite(AssetManager.get("earth"));
		this.sprite.setBounds(0, 0, 20, 20);
		this.position.set(0, 0);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		sprite.setPosition(position.x, position.y);
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
