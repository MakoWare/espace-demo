package com.makoware.explorationspace.Generators;

import com.makoware.explorationspace.Entity.Entity;
import com.makoware.explorationspace.Entity.Player;

import java.util.ArrayList;

public class ObjectGenerator {

	private ArrayList<Entity> ents;
	private Player player;
	
	private AttackUnitGen aGen;
	
	public ObjectGenerator(ArrayList<Entity> ents, Player player) {
		this.ents = ents;
		this.player = player;
		this.aGen = new AttackUnitGen(ents, player);
	}
	
	
	/* Notes on how this works.
	 * 
	 * this object will generate enemies and other space objects (ie. asteroids, comets?, junk?, powerups, etc..)
	 * 
	 * Powerups:
	 * 	Generated and given to objects to be revealed upon destruction of them.
	 * 	Also, at random intervals freely, in case of out of ammo and cannot destroy object for powerup.
	 * 		Weapsons Powerups:
	 * 			creation is related to player's need for ammo?
	 * 
	 * Enemies:
	 * 	Generated as attack units (array of enemies) at random-ish sectors of distance [ +  ][+   ][ +  ][  + ][+   ][+ + ];
	 * 	Super rare events where multiple units are generated per sector.
	 *  Difficulty depending on (some factor of) distance; (ie. difficulty+=distance/20;)
	 *  (coins awarded as destruction of attack units?).
	 *  
	 * 
	 */
	
	
	public void update(float delta){
		
		aGen.update(delta);
		
	}

}
