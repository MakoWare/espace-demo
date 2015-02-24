package com.makoware.explorationspace.GUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.makoware.explorationspace.Entity.Planet;
import com.makoware.explorationspace.Input.ControllerService;

public class PlanetStatsOverlay extends Overlay implements ControllerService {

	
	private Rectangle box;
//	private ArrayList<MenuItem> items;
	private MenuItemsList items;

	public PlanetStatsOverlay(){
		super(true);
		items = new MenuItemsList(true);
	}
	
	@Override
	public boolean shouldPause() {
		return true;
	}
	
	@Override
	public void onShow(OverlayParam pa) {
		super.onShow(pa);
		this.box = new Rectangle( (5/8f)*HUD.width, 0.1f*HUD.height, HUD.width-((1/16f)*HUD.width), 0.9f*HUD.height);
		Planet p = pa.get("planet", Planet.class);
		items.add(new TextMenuItem(box,p.getName(), false, null));
		
		items.select();
	}

	@Override
	public void draw(SpriteBatch batch) {
		HUD.border.draw(batch, box.x, box.y, box.width-box.x, box.height-box.y);
		for(MenuItem i : items.items())
			i.draw(batch);
	}

	@Override
	public void up(int dir) {
		if(dir==0)
			items.up();
	}

	@Override
	public void down(int dir) {
		if(dir==0)
			items.down();
	}

	@Override
	public void left(int dir) {
	}

	@Override
	public void right(int dir) {
	}

	@Override
	public void LAxis(int axis, float val) {
	}

	@Override
	public void L1(int dir) {
	}

	@Override
	public void L2(int dir) {
	}

	@Override
	public void L3(int dir) {
	}

	@Override
	public void RAxis(int axis, float val) {
	}

	@Override
	public void R1(int dir) {
	}

	@Override
	public void R2(int dir) {
	}

	@Override
	public void R3(int dir) {
	}

	@Override
	public void triangle(int dir) {
	}

	@Override
	public void circle(int dir) {
		if(dir==0){
			hide();
			items.clear();
		}
	}

	@Override
	public void cross(int dir) {
		if(dir==0){
			items.click();
		}
	}

	@Override
	public void square(int dir) {
	}

	@Override
	public void start(int dir) {
	}

	@Override
	public void select(int dir) {
	}

	@Override
	public void ps(int dir) {
	}

	@Override
	public void drawService(SpriteBatch batch) {
	}

	@Override
	public boolean needsToDraw() {
		return false;
	}

}
