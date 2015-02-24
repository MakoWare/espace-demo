package com.makoware.explorationspace.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.makoware.explorationspace.Entity.Powerup.Credit;
import com.makoware.explorationspace.Entity.Powerup.Powerup;
import com.makoware.explorationspace.Entity.Powerup.WeaponsPowerup;
import com.makoware.explorationspace.Entity.Ships.Ship;
import com.makoware.explorationspace.Framework.Callback;
import com.makoware.explorationspace.Framework.Message;

import java.text.DecimalFormat;


public class Player  {
	private static String tag = "Player";
//	private static final float SPEED = 70f;
	private Ship ship;
	
	private int cash = 0;
//	private double time = 2089.01;
	private int score = 0;
	private int distance;
	private DecimalFormat df = new DecimalFormat("#.##");

	private int id;
	
	
	
	public Player(){
//		this.vector = new Vector2(0,0);
	}
	
	public Player(int id, Ship ship){
		this();
		this.id = id;
		ship.setId(id);
		setShip(ship);
//		ship.body.setLinearDamping(0f);
	}
	
	public void up(int dir) {
		switch(dir){
		case 0:
			ship.getVector().y=1;
			break;
		case 1:
			ship.getVector().y=0;
			break;
		}
	}

	public void down(int dir) {
		switch(dir){
		case 0:
			ship.getVector().y=-1;
			break;
		case 1:
			ship.getVector().y=0;
			break;
		}
	}

	public void left(int dir) {
		switch(dir){
		case 0:
			ship.getVector().x=-1;
			break;
		case 1:
			ship.getVector().x=0;
			break;
		}
	}

	public void right(int dir) {
		switch(dir){
		case 0:
			ship.getVector().x=1;
			break;
		case 1:
			ship.getVector().x=0;
			break;
		}
	}
	
	public void setShip(Ship ship){
		this.ship = ship;
	}
	
	public Ship getShip(){
		return this.ship;
	}

	public void update(float delta) {
		this.ship.go(delta);
		distance = Math.max((int) (ship.getPosition().x-5),distance);
//		scoreUp(distance/3);
//		Gdx.app.log("Player", "damp: "+this.ship.body.getLinearDamping());
//		this.time += (delta/500d);
	}
	
	public void boost(int dir){
		ship.boost(dir);
	}
	
	public Vector2 getVector(){
		return this.ship.getVector();
	}

	public void setVector(float x, float y) {
		this.ship.getVector().set(x, y);
	}

	public void firePrimary() {
		this.ship.firePrimary();
		
	}
	
	public void fireSecondary() {
		this.ship.fireSecondary();
	}

	public String getCash() {
		return df.format(cash);
	}
	
	public void cashIn(float worth) {
		this.cash+=worth;
	}

	public String getLocation() {
		switch(getLocationID()){
		case 11:
			return "Inner Alpha";
		case 12:
			return "Outer Alpha";
		case 21:
			return "Inner Beta";
		case 22:
			return "Outer Beta";
		case 31:
			return "Inner Delta";
		case 32:
			return "Outer Delta";
		case 41:
			return "Inner Gamma";
		case 42:
			return "Outer Gamma";
		}
		return null;
	}
	
	private int getLocationID(){
		Vector2 v = ship.getPosition();
		int io = 0;
		int q = 0;
		if(v.angle()<45 || v.angle()>=315){
			q = 10;
		} else if(v.angle()>=45 && v.angle()<135){
			q = 20;
		} else if(v.angle()>=135 && v.angle()<225){
			q = 30;
		} else if(v.angle()>=225 && v.angle()<315){
			q = 40;
		}
		if(v.len()<25200)
			io = 1;
		else
			io = 2;
//			Gdx.app.log("Hud", "pos: "+v.toString()+"  dist: " + v.len());
		return io+q;
	}

	public Callback onPauseCallback() {
		return onPauseCallback;
	}
	
	private Callback onPauseCallback = new Callback(){

		@Override
		public void call(Message m) {
			setVector(0, 0);
			boost(1);
		}
		
	};

	public int getId() {
		return id;
	}
	
	public void scoreUp(int worth){
		this.score+=worth;
	}
	
	public int getScore(){
		return score+(distance/3);
	}
	
	public void setScore(int score){
		this.score = score;
	}

	public void applyPowerup(Powerup p) {
		if(p instanceof Credit){
			p = (Credit)p;
			cashIn(((Credit) p).getWorth());
		} else if(p instanceof WeaponsPowerup){
			Gdx.app.log(tag, "apply weapons powerup");
			((WeaponsPowerup) p).applyTo(ship.getWeapons());
		}
		
		
		
		p.dispose();
	}

	public int getDistance() {
		return distance;
	}

}
