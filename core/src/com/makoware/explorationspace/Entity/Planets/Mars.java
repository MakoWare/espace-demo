package com.makoware.explorationspace.Entity.Planets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.makoware.explorationspace.Entity.Planets.PlanetClassTypes.PlanetClassM;
import com.makoware.explorationspace.Framework.AssetManager;

public class Mars extends PlanetClassM {

	
	
	
	public Mars(){
		isKnown = true;
		name = "Mars";
		this.sprite = new Sprite(AssetManager.get("earth"));
		
		this.setBounds(12700, 6350, 10f);
	}

}
