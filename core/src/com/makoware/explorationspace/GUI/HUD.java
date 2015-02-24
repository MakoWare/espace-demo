package com.makoware.explorationspace.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.makoware.explorationspace.Framework.AssetManager;
import com.makoware.explorationspace.Framework.Callback;

import java.util.ArrayList;
import java.util.HashMap;

public class HUD {
	private String tag = "HUD";
	private HashMap<String,Overlay> overlays;
	
	private ArrayList<String> list;
	
//	public static Matrix4 hudMatrix = new Matrix4();
	public static OrthographicCamera hudCam;
	public static MCamera camera;
	
	public static int x; 
	public static int y;
	public static int width;
	public static int height;

	public static NinePatch border;
	
	public static BitmapFont font;
	private Texture fontTex;
	
	public static Texture arrow;
	
	private int pauseCount = 0;

	private Callback pause;
	private Callback unpause;
	private int iter;
	
	public HUD(){
		this.overlays = new HashMap<String,Overlay>();
		this.list = new ArrayList<String>();
		
		border = new NinePatch(AssetManager.get("border"), 5,5,5,5);
		
		arrow = AssetManager.get("arrow");
		arrow.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		fontTex = AssetManager.get("silkscreen_25");
		fontTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font = new BitmapFont(Gdx.files.internal("data/font/silkscreen_25.fnt"), new TextureRegion(fontTex), false);
//		this.font.setScale(0.1f);
//		font.setScale(0.1f, 0.1f);
		font.setColor(Color.WHITE);
	}
	
	public HUD(MCamera cam){
		this();
		camera = cam;
	}
	
	public void setPause(Callback pause){
		this.pause = pause;
	}
	
	public void setUnpause(Callback unpause){
		this.unpause = unpause;
	}
	
	public void draw(SpriteBatch batch){
		
//		Iterator<String> itr = list.iterator();
//		while(itr.hasNext()){
//			String key = itr.next();
//			Overlay o = overlays.get(key);
//			if(o.isVisible()){
//				o.draw(batch);
//			} else {
////				o.onHide();
//				hide(key);
//				itr.remove();
//			}
//		}
		iter = 0;
		while(iter<list.size()){
			String key = list.get(iter);
			if(key==null)
				continue;
			
			Overlay o = overlays.get(key);
			if(o.isVisible()){
				o.draw(batch);
			} else {
//				o.onHide();
				hide(key);
				list.remove(iter);
			}
			
			iter++;
		}
	}
	
	public void addOverlay(String key, Overlay o){
		overlays.put(key, o);
	}
	
	public void show(String key){
		show(key,null);
	}
	
	public void show(String key, OverlayParam p){
		if(!overlays.get(key).isVisible())
			list.add(key);
		if(overlays.get(key).shouldPause()){
//			Gdx.app.log(tag, "should pause");
			if(pauseCount==0)
				pause.call(null);
			pauseCount++;
		}
		overlays.get(key).onShow(p);
	}
	
	public void hide(String key){
//		list.remove(key);
		if(overlays.get(key).shouldPause()){
//			Gdx.app.log(tag, "should unpause");
			pauseCount--;
			if(pauseCount==0)
				unpause.call(null);
		}
		overlays.get(key).onHide();
	}

	public static void begin(SpriteBatch batch) {
		batch.setProjectionMatrix(HUD.hudCam.combined);
		batch.begin();
	}

	public static void end(SpriteBatch batch) {
		batch.end();
		batch.setProjectionMatrix(HUD.camera.combined);
	}

	public void resize() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		x = 0;
		y = 0;
		if(hudCam==null)
			hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, width-x, height-y);
//		hudMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public static int width(){
		return Gdx.graphics.getWidth();
	}
	
	public static int height(){
		return Gdx.graphics.getHeight();
	}
}

