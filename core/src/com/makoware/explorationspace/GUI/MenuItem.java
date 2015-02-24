package com.makoware.explorationspace.GUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.makoware.explorationspace.Framework.Callback;
import com.makoware.explorationspace.Framework.Message;

public abstract class MenuItem {

	protected float Fdx = 0.039f;
	protected float Tdx = 0.015f; 
	protected float dIndex = 0.04f;
	
	protected Callback cb;
	protected Message message;

	protected boolean selected;
	protected boolean selectable;
	protected int index;
	
	public abstract void draw(SpriteBatch batch);
	
	public MenuItem select(){
		this.selected = true;
		return this;
	}
	
	public MenuItem deselect(){
		this.selected = false;
		return this;
	}
	
	public MenuItem selectable(boolean b){
		this.selectable = b;
		return this;
	}

	public boolean isSelectable() {
		return this.selectable;
	}

	public void setIndex(int i) {
		index = i;
	}

	public void click(){
		if(cb!=null)
			cb.call(message);
	}

}
