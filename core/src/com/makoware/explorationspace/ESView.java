package com.makoware.explorationspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.makoware.explorationspace.Entity.BackgroundStar;
import com.makoware.explorationspace.Entity.Entity;
import com.makoware.explorationspace.GUI.HUD;
import com.makoware.explorationspace.GUI.HudOverlay;
import com.makoware.explorationspace.GUI.MCamera;
import com.makoware.explorationspace.GUI.PauseMenu;

public class ESView {
	private static String tag = "ESView";
	private ESModel model;
	
	private SpriteBatch batch;
	
	private MCamera cam;
	
	private Box2DDebugRenderer rend;
	
	private HUD hud;
	
//	private Iterator<BackgroundStar> starsItr;
//	private Iterator<Entity> entItr;
	
	private int iter;

	public ESView(ESModel model) {
		this.model = model;
		
		this.cam = model.getCamera();
		this.hud = model.getHUD();
		this.rend = new Box2DDebugRenderer();
		this.batch = new SpriteBatch();
		
		this.hud.addOverlay("pause", new PauseMenu());
		this.hud.addOverlay("hud", new HudOverlay());
		this.hud.show("hud", model.getHudParams());
		
//		starsItr = model.getBackgroundStars().iterator();
//		entItr = model.getEnts().iterator();
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// draw the background stars
		batch.setProjectionMatrix(cam.calculateParallaxMatrix(0.2f, 0.2f));
		batch.begin();
//		starsItr = model.getBackgroundStars().iterator();
		iter = 0;
		while(iter<model.getBackgroundStars().size()){
			BackgroundStar s = model.getBackgroundStars().get(iter);
			if(s!=null)
				s.draw(batch);
			iter++;
		}
		batch.end();
		
		// draw the planets
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
//		Iterator<Planet> planetItr = model.getPlanets().iterator();
//		while(planetItr.hasNext()){
//			Planet p = planetItr.next();
//			p.draw(batch);
//		}
		
		// draw the other stuff
//		entItr = model.getEnts().iterator();
		iter = 0;
		while(iter<model.getEnts().size()){
			Entity e = model.getEnts().get(iter);
			if(e!=null){
				if(e.isDead()){
					e.removeFromWorld();
					model.getEnts().remove(iter);
				} else{
					e.draw(batch);
				}
			}
			iter++;
		}
		
		// draw the player
		model.getPlayer().getShip().draw(batch);
		
		batch.end();
		
		// draw the HUD
		HUD.begin(batch);
		hud.draw(batch);
		
		// if need be, draw on screen controls
		if(ESgame.input.getService() != null && ESgame.input.getService().needsToDraw())
			ESgame.input.getService().drawService(batch);
		
		HUD.end(batch);
		
//		rend.render(model.getWorld(), cam.combined);
	}
	
	public void dispose() {
		batch.dispose();
		rend.dispose();
		Gdx.app.log(tag, "dispose");
	}



	
}
