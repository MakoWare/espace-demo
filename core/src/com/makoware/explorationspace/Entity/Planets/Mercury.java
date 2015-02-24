package com.makoware.explorationspace.Entity.Planets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.makoware.explorationspace.Entity.Planets.PlanetClassTypes.PlanetClassR;
import com.makoware.explorationspace.Framework.AssetManager;

public class Mercury extends PlanetClassR {

	public Mercury(){
		isKnown = true;
		this.sprite = new Sprite(AssetManager.get("earth"));
		this.sprite.setBounds(0, 0, 80, 80);
	}

}
