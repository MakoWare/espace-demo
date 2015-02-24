package com.makoware.explorationspace.Screens;

import com.badlogic.gdx.Screen;
import com.makoware.explorationspace.ESController;
import com.makoware.explorationspace.ESModel;
import com.makoware.explorationspace.ESView;
import com.makoware.explorationspace.ESgame;
import com.makoware.explorationspace.Framework.AssetManager;

public class MainScreen implements Screen {
	public static final String tag = "MainScreen";
	
	private ESModel model;
	private ESView view;
	private ESController controller;
	
	
	@Override
	public void render(float delta) {
//		Gdx.app.log(tag, "render main Screen");
		
//		batch.begin
		
		model.update(delta);
		view.render(delta);
		

	}
	
	@Override
	public void resize(int width, int height) {

		model.resize(width,height);
	}

	@Override
	public void show() {
		loadAssets();
		
		model = new ESModel();
		controller = new ESController(model);
		view = new ESView(model);
		
		ESgame.input.setService(controller);
		
		
	}

	private void loadAssets() {

		AssetManager.load("ship", "data/gfx/ship.png");
		AssetManager.load("star", "data/gfx/star.png");
//		AssetManager.load("earth", "data/gfx/earth.png");
//		AssetManager.load("map", "data/gfx/map.png");
//		AssetManager.load("map_player", "data/gfx/map_player.png");
//		AssetManager.load("map_planet", "data/gfx/map_planet.png");
		AssetManager.load("health_bar", "data/gfx/health_bar.png");
		
		// HUD Textures
		AssetManager.load("border", "data/gfx/border.png");
		AssetManager.load("arrow", "data/gfx/arrow.png");
		AssetManager.load("silkscreen_25", "data/font/silkscreen_25.png");
		
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		controller.dispose();
		view.dispose();
		model.dispose();
	}

}
