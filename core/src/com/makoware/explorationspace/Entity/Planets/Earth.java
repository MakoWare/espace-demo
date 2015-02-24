package com.makoware.explorationspace.Entity.Planets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.makoware.explorationspace.Entity.Planets.PlanetClassTypes.PlanetClassE;
import com.makoware.explorationspace.Framework.AssetManager;

public final class Earth extends PlanetClassE {
	
	
	public Earth(){
		isEarth = true;
		isKnown = true;
		this.name = "Earth";
		this.sprite = new Sprite(AssetManager.get("earth"));
		this.setBounds(12600, 6300, 12);
	}
}
