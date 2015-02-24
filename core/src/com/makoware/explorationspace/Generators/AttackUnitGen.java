package com.makoware.explorationspace.Generators;

import com.makoware.explorationspace.Entity.Entity;
import com.makoware.explorationspace.Entity.Player;
import com.makoware.explorationspace.Entity.Ships.AlienShip;

import java.util.ArrayList;
import java.util.Random;

public class AttackUnitGen {
	
	private ArrayList<Entity> ents;
	
	
	private Random rand;
	
	private int dX = 50;
	private int nextInt = 100;
	private int index = 100;
	
	private int distance;

	private Player player;

	public AttackUnitGen(ArrayList<Entity> ents, Player player) {
		this.ents = ents;
		rand = new Random();
		this.player = player;
	}
	
	public void update(float delta){
		distance = player.getDistance();
		if(distance>nextInt){
			nextInt = rand.nextInt(dX)+index;
			index+=dX;
//			getUnit();
			getEnemies(rand.nextInt(2)+2, (distance/200));
		}
	}

	private void getEnemies(int num, int diff) {
		Entity e;
		for(int i=0; i<num; i++){
			e = EnemyGen.getEnemy(diff);
			((AlienShip)e).assignTarget(player);
		}
	}

}
