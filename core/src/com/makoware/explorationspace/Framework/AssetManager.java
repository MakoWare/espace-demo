package com.makoware.explorationspace.Framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import java.util.HashMap;

public class AssetManager {
	private static String tag = "AssetManager";
	private static HashMap<String,Texture> assets = new HashMap<String,Texture>();
	
	
	public static void load(String name, String internalPath){
		load(name,internalPath,TextureFilter.Linear);
	}
	
	public static void load(String name, String internalPath, TextureFilter min){
		load(name,internalPath,min,min);
	}
	
	public static void load(String name, String internalPath, TextureFilter min, TextureFilter mag){
		Texture t = new Texture(internalPath);
		t.setFilter(min, mag);
		assets.put(name, t);
	}
	
	public static Texture get(String name){
		Texture t = assets.get(name);
		try{
			if(t==null)
				throw new Exception("Texture: "+name+" is null");
		} catch (Exception e){
			Gdx.app.log(tag, e.getMessage());
		}
		
		return t;
	}
	
	public static void dispose(){
//		Gdx.app.log(tag, "dispose assets in manager");
		for(Texture t : assets.values()){
//			Gdx.app.log(tag, "dispose texture");
			t.dispose();
		}
	}

}
