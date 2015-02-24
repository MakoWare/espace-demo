package com.makoware.explorationspace;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.makoware.explorationspace.Entity.Player;
import com.makoware.explorationspace.Input.TouchControllerService;

public class ESController implements TouchControllerService{
	private String tag = "ESController";
	private ESModel model;
	
	private Player player;

	private float deadzone = 0.1f;
	
	public ESController(ESModel model) {
		this.model = model;
		this.player = model.getPlayer();
	}

	@Override
	public void up(int dir) {
		player.up(dir);
	}

	@Override
	public void down(int dir) {
		player.down(dir);
	}

	@Override
	public void left(int dir) {
		player.left(dir);
	}

	@Override
	public void right(int dir) {
		player.right(dir);
	}

	@Override
	public void LAxis(int axis, float val) {
		
		switch(axis){
		case 0:
			if(val<-deadzone  || val>deadzone){
				player.getVector().x = val;
			} else {
				player.getVector().x = 0;
			}
			break;
		case 1:
			if(val<-deadzone  || val>deadzone){
				player.getVector().y = -val;
			} else {
				player.getVector().y = 0;
			}
			break;
		}
		
		
		
//		player.setVector(x,y);
	}

	@Override
	public void L1(int dir) {
		if(dir==0)
			player.fireSecondary();
	}

	@Override
	public void L2(int dir) {
		if(player.getShip().getProp().isBoosting())
			player.getShip().getProp().boostDown(dir);
	}

	@Override
	public void L3(int dir) {
		player.getShip().getProp().boost(dir);
	}

	@Override
	public void RAxis(int axis, float val) {
		
		switch(axis){
		case 3:
//			if(val<-deadzone || val>deadzone)
//				model.setZoomDir((int) (val/Math.abs(val)));
//			else
//				model.setZoomDir(0);
			
			if(val<-deadzone || val>deadzone){
				player.getShip().getWeapons().targetVector().y = -val;
//				Gdx.app.log(tag, "y: "+val);
			} else {
				player.getShip().getWeapons().targetVector().y = 0;
			}
			break;
		case 4:
			
			if(val<-deadzone || val>deadzone){
				player.getShip().getWeapons().targetVector().x = val;
//				Gdx.app.log(tag, "x: "+val);
			} else {
				player.getShip().getWeapons().targetVector().x = 0;
			}
			break;
		}
	}

	@Override
	public void R1(int dir) {
		if(dir==0){
			player.firePrimary();
		}

	}

	@Override
	public void R2(int dir) {
		if(player.getShip().getProp().isBoosting())
			player.getShip().getProp().boostUp(dir);
	}

	@Override
	public void R3(int dir) {
		
	}

	@Override
	public void triangle(int dir) {
	}

	@Override
	public void circle(int dir) {
//		if(dir==0)
//			model.getHUD().show("map", new OverlayParam()
//				.put("playerLocation", player.getShip().getPosition())
//				.put("planets", model.getPlanets()));
	}

	@Override
	public void cross(int dir) {
//		if(dir==0){
//			for(Planet p : model.getPlanets()){
//				if(p.contains(player.getShip().getPosition())){
//					model.getHUD().show("planetStats", new OverlayParam().put("planet", p));					
//				}
//			}
//		}
	}

	@Override
	public void square(int dir) {
	}

	@Override
	public void start(int dir) {
		ps(dir);
	}

	@Override
	public void select(int dir) {
		ps(dir);
	}

	@Override
	public void ps(int dir) {
		if(dir==0){
			model.pause();
			model.getHUD().show("pause");
		} else {
			model.unpause();
		}
	}

	@Override
	public void drawService(SpriteBatch batch) {
	}

	@Override
	public boolean needsToDraw() {
		return false;
	}

	@Override
	public void touchDown(int screenX, int screenY, int pointer, int button) {
	}

	@Override
	public void touchUp(int screenX, int screenY, int pointer, int button) {
	}

	@Override
	public void touchDragged(int screenX, int screenY, int pointer) {
	}

	public void dispose() {
	}


}
