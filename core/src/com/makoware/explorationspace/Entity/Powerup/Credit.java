package com.makoware.explorationspace.Entity.Powerup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.makoware.explorationspace.Framework.AssetManager;

public class Credit extends Powerup {
	
	
	private int worth;
	
	private float width = 1.5f;
	private float height = 1.5f;
	
	public Credit(){
		this.worth = 1;
		this.sprite = new Sprite(AssetManager.get("star"));
		this.sprite.setColor(Color.YELLOW);
	}
	
	public Credit(int worth){
		this();
		this.worth = worth;
	}
	
	public Credit(int worth, float x, float y){
		this(worth);
		this.sprite.setBounds(x, y, width, height);
	}
	
	public Credit(float x, float y){
		this(1,x,y);
	}
	
	public int getWorth(){
		return this.worth;
	}

}
