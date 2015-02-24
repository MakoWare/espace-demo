package com.makoware.explorationspace.Entity.Powerup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.makoware.explorationspace.Framework.AssetManager;


public class GenericPowerup extends Powerup {

	
	public GenericPowerup() {
		
		this.sprite = new Sprite(AssetManager.get("star"));
		this.sprite.setColor(Color.BLUE);
		this.sprite.setBounds(10, 5, 1, 1);
		
	}
	
	
	
	
	
}
