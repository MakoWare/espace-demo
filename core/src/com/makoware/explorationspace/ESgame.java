package com.makoware.explorationspace;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.makoware.explorationspace.Framework.AppType;
import com.makoware.explorationspace.Framework.AssetManager;
import com.makoware.explorationspace.Input.AndroidControllerInput;
import com.makoware.explorationspace.Input.DesktopControllerInput;
import com.makoware.explorationspace.Input.InputMethod;
import com.makoware.explorationspace.Input.OuyaControllerInput;
import com.makoware.explorationspace.Screens.MainScreen;

import java.lang.reflect.Field;

public class ESgame extends Game {
	public static final String tag = "ESgame";
	public static String version = "0.0.1";
	
	public static InputMethod input;
	
	public static AppType type;

	@Override
	public void create(){
		checkPlatformType();	
		setScreen(new MainScreen());
	}

	private void checkPlatformType() {
		ApplicationType type = Gdx.app.getType();

		if(type==ApplicationType.Android){
			try {
				
				Class<?> buildClass = Class.forName("android.os.Build");
				Field deviceField = buildClass.getDeclaredField("DEVICE");
				
				if( deviceField.get(null).toString().contains("ouya") ){
					input = new OuyaControllerInput();
					ESgame.type = AppType.Ouya;
				} else {
					input = new AndroidControllerInput();
					ESgame.type = AppType.Android;
				}
				
			} catch(Exception e) {
			}
			
		} else if(type==ApplicationType.Desktop){
			input = new DesktopControllerInput();
			ESgame.type = AppType.Desktop;
		}		
		
		Gdx.input.setInputProcessor(input);
//		Controllers.addListener(input.getFirstController());
	}
	
	@Override
	public void pause() {
		super.pause();
		Gdx.app.log("Game", "pause, quit");
//		Gdx.app.exit();
//		dispose();
	}
	
	
	
	@Override
	public void dispose() {
		super.dispose();
		AssetManager.dispose();
		Gdx.app.log("Game", "dispose, quit");
	}
	

}
