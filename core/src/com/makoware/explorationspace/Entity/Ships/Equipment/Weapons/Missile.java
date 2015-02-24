package com.makoware.explorationspace.Entity.Ships.Equipment.Weapons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import com.makoware.explorationspace.Framework.AssetManager;

public class Missile extends Projectile implements Disposable {

	public Missile() {
		this.sprite = new Sprite(AssetManager.get("star"));
		this.sprite.setColor(Color.RED);
		
		damage = 10f;
		dstToLive = 100f;
		height = 0.2f;
		width = 0.4f;
		velocity = 30f;
	}

}
