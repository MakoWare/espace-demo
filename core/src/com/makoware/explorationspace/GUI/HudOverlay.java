package com.makoware.explorationspace.GUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.makoware.explorationspace.Entity.Player;
import com.makoware.explorationspace.Entity.Ships.Equipment.DefenseSystems;
import com.makoware.explorationspace.Entity.Ships.Equipment.FuelSystems;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Gun;
import com.makoware.explorationspace.Entity.Ships.Ship;

public class HudOverlay extends Overlay {
	
	
	private Gun primary;
	private Gun secondary;
	private DefenseSystems defense;
	private FuelSystems energy;
	private Ship ship;
	private Player player;
	private float timeX;
	
	private float textHeight;
	private float drawX;
	
	private TextBounds tempBounds;

	public HudOverlay(){
		this(false);
		
		
	}

	public HudOverlay(boolean requestInput) {
		super(requestInput);
	}
	
	@Override
	public boolean shouldPause() {
		return false;
	}

	/*
	@Override
	public void draw(SpriteBatch batch) {
		
		HUD.font.setScale(1.0f);
		TextBounds sa = new TextBounds(HUD.font.getBounds("Secondary: "+secondary.getAmmo()));
		TextBounds sh = new TextBounds(HUD.font.getBounds("Shields: "+defense.getShield().getStrength()));
		TextBounds  h = new TextBounds(HUD.font.getBounds("Hull: "+defense.getHull().getStrength()));
		TextBounds 	e = new TextBounds(HUD.font.getBounds("Energy: "+energy.getEnergy()));
		TextBounds pa = new TextBounds(HUD.font.getBounds("Primary: "+primary.getAmmo()));
		
		
		float dx = (((HUD.width-HUD.x)-(pa.width+sh.width+h.width+e.width+sa.width))/4f);
//		float dx = 0f;
//		Gdx.app.log("Hud", "total: "+(pa.width+sh.width+h.width+e.width+sa.width)+"  hud.w: "+HUD.width);
		
		setColor(secondary.getAmmo());
		HUD.font.draw(batch, "Secondary: "+secondary.getAmmo(), HUD.x, sa.height);
		setColor(defense.getShield().getStrength());
		HUD.font.draw(batch, "Shields: "+defense.getShield().getStrength(), HUD.x+(sa.width+dx), sa.height);
		setColor(defense.getHull().getStrength());
		HUD.font.draw(batch, "Hull: "+defense.getHull().getStrength(), HUD.x+(sa.width+dx+sh.width+dx), sa.height);
		setColor(energy.getEnergy());
		HUD.font.draw(batch, "Energy: "+energy.getEnergy(), HUD.x+(sa.width+dx+sh.width+dx+h.width+dx), sa.height);
		setColor(primary.getAmmo());
		HUD.font.draw(batch, "Primary: "+primary.getAmmo(), HUD.x+(sa.width+dx+sh.width+dx+h.width+dx+e.width+dx), sa.height);
		
//		TextBounds loc = new TextBounds(HUD.font.getBounds("Location: "+s));

		HUD.font.setColor(Color.WHITE);
		HUD.font.draw(batch, "Quadrant: "+player.getLocation(), HUD.x, HUD.height);
		TextBounds c = new TextBounds(HUD.font.getBounds("$ "+player.getCash()));
		HUD.font.draw(batch, "$ "+player.getCash(), (HUD.width-HUD.x)/2f-(c.width/2f), HUD.height);
		
//		HUD.font.draw(batch, "Space-Time: "+player.getTime(), HUD.width-timeX, HUD.height);
	}
*/
	@Override
	public void draw(SpriteBatch batch) {
		HUD.font.setScale(1.0f);
		textHeight = HUD.font.getBounds("X").height;
//		float space = HUD.font.getBounds(" ").width;
		drawX = 0;
		
		HUD.font.setColor(Color.WHITE);
		HUD.font.draw(batch, "Score: "+player.getScore()+"   Credits: "+player.getCash(), HUD.x, HUD.height);
		if(primary.isActive()){
			drawX = HUD.font.draw(batch, ""+primary.getCache().getType().getSimpleName()+" (", HUD.x, HUD.height-textHeight).width;
			setColor(primary.getCache().getAmmo());
			drawX = HUD.font.draw(batch, ""+primary.getCache().getAmmo(), HUD.x+drawX, HUD.height-textHeight).width+drawX;
			HUD.font.setColor(Color.WHITE);
			HUD.font.draw(batch, "/"+primary.getCache().getCapacity()+")", HUD.x+drawX, HUD.height-textHeight);
		}
		if(secondary.isActive()){
			drawX = HUD.font.draw(batch, ""+secondary.getCache().getType().getSimpleName()+" (", HUD.x, HUD.height-(2*textHeight)).width;
			setColor(secondary.getCache().getAmmo());
			drawX = HUD.font.draw(batch, ""+secondary.getCache().getAmmo(), HUD.x+drawX, HUD.height-(2*textHeight)).width+drawX;
			HUD.font.setColor(Color.WHITE);
			HUD.font.draw(batch, "/"+secondary.getCache().getCapacity()+")", HUD.x+drawX, HUD.height-(2*textHeight));
		}
		
		tempBounds = HUD.font.getBounds("Distance: "+player.getDistance());
		HUD.font.draw(batch, "Distance: "+player.getDistance(), (HUD.width-HUD.x)/2-tempBounds.width/2, HUD.height);
	}
	
	private void setColor(int num) {
		if(num>30)
			HUD.font.setColor(0, 1, 0, 1);
		else if(num>10)
			HUD.font.setColor(0.9843137255f,0.8352941176f,0,1);
		else
			HUD.font.setColor(1,0,0,1);
	}

	@Override
	public void onShow(OverlayParam p) {
		super.onShow(p);
		
		this.player = p.get("player", Player.class);
		this.ship = player.getShip();
		this.timeX = new TextBounds(HUD.font.getBounds("Space-Time: XXXXX.XX")).width;
		
		this.primary = ship.getWeapons().getPrimaryGun();
		this.secondary = ship.getWeapons().getSecondaryGun();
		this.defense = ship.getDefense();
		this.energy = ship.getFuel();
		
		
	}

}
