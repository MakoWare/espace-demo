package com.makoware.explorationspace.Entity.Ships.Equipment.Weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.makoware.explorationspace.Framework.AssetManager;

public class Bullet extends Projectile {


	public Bullet(){
		this.sprite = new Sprite(AssetManager.get("star"));
		
		damage = 10f;
		dstToLive = 100f;
		height = 0.2f;
		width = 0.4f;
		velocity = 30f;
	}

}
