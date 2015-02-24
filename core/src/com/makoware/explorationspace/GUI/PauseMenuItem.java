package com.makoware.explorationspace.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.makoware.explorationspace.Framework.Callback;
import com.makoware.explorationspace.Framework.Message;

public class PauseMenuItem extends MenuItem {
	public static String tag = "PauseMenuItem";
	
	
	private String text;
	private boolean active;
	
	
	public PauseMenuItem(int index, String text, Callback cb, Message message){
		this.cb = cb;
		this.message = message;
		this.index = index;
		this.text = text;
		if(index == 0)
			active = true;
		else
			active = false;
		
		Fdx = 0.039f;
		Tdx = 0.015f; 
		dIndex = 0.04f;
	}
	
	public PauseMenuItem(int index, String text, Callback cb){
		this(index,text,cb,null);
	}
	
	public void activate(){
		this.active = true;
	}
	
	public void deactivate(){
		this.active = false;
	}
	
	public void action(){
		if(cb!=null)
			cb.call(message);
		else
			Gdx.app.log(tag, text+" action!");
	}
	
	@Override
	public void draw(SpriteBatch batch){
		HUD.font.setColor(Color.WHITE);
		float scale = (HUD.height/320f);
		
		if(active)
			batch.draw(HUD.arrow, (PauseMenu.X+Tdx)*HUD.width, (0.70f-(dIndex*index))*HUD.height, 10*scale,10*scale);
		
		HUD.font.setScale(scale*0.5f);
		HUD.font.drawWrapped(batch, text, (PauseMenu.X+Fdx)*HUD.width, (0.736f-(dIndex*index))*HUD.height, 0.4f*HUD.width);
//			HUD.font.drawWrapped(batch, text, 0.414f*Gdx.graphics.getWidth(), (0.736f-(0.04f*1))*Gdx.graphics.getHeight(), 0.4f*Gdx.graphics.getWidth());
		
		
	}
	
}
