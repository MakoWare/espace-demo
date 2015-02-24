package com.makoware.explorationspace.Entity.Powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Gun;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Missile;
import com.makoware.explorationspace.Framework.AssetManager;

public class MissileLauncher extends WeaponsPowerup {
	
	public MissileLauncher(float x, float y){
		this.sprite = new Sprite(AssetManager.get("star"));
		this.sprite.setColor(Color.CYAN);
		this.sprite.setBounds(x, y, 1.5f, 1.5f);
		
		this.gunSide = WeaponsPowerup.SECONDARY;
	}
	
	

	@Override
	public void applyToPrimary(Gun gun) {
		
		
	}

	@Override
	public void applyToSecondary(Gun gun) {
		Gdx.app.log("MissileLauncher", "apply missile launcher to primary");
		gun.tempLoad(Missile.class, 10);
	}

}
