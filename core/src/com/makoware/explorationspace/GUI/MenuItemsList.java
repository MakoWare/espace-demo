package com.makoware.explorationspace.GUI;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Iterator;

public class MenuItemsList {
	
	private ArrayList<MenuItem> items;
	private boolean cycle;
	private int itemIndex;
	private int minSelectable;
	private int maxSelectable;
	
	public MenuItemsList(){
		this(false);
	}
	
	public MenuItemsList(boolean cycle){
		this.cycle = cycle;
		items = new ArrayList<MenuItem>();
		this.itemIndex = 0;
		this.minSelectable = 9999;
		this.maxSelectable = -2;
	}
	
	public void add(MenuItem i){
		items.add(i);
		i.setIndex(items.size());
		if(i.isSelectable()){
			minSelectable = Math.min(items.size()-1, minSelectable);
			maxSelectable = Math.max(items.size()-1, maxSelectable);
		}
	}
	
	public void clear(){
		items.clear();
		this.itemIndex = 0;
		this.minSelectable = 9999;
		this.maxSelectable = -2;
	}
	
	public Iterator<MenuItem> iterator(){
		return items.iterator();
	}
	
	public ArrayList<MenuItem> items(){
		return this.items;
	}
	
	public void up(){
		if(itemIndex>0 && itemIndex<items.size())
			items.get(itemIndex ).deselect();
		this.itemIndex--;
		for(int i=0; i<items.size(); i++){
			
			if(this.itemIndex<0){
				if(cycle)
					this.itemIndex = items.size()-1;
				else
					this.itemIndex = minSelectable;
			}
			Gdx.app.log("MenuItemList", "up index: "+itemIndex + " selectable: "+items.get(itemIndex).isSelectable());
			if(items.get(itemIndex).isSelectable())
				break;
			else
				this.itemIndex--;
		}	
		if(itemIndex>0 && itemIndex<items.size())
			items.get(itemIndex).select();
	}

	public void down() {
		if(itemIndex>0 && itemIndex<items.size())
			items.get(itemIndex ).deselect();
		this.itemIndex++;
		for(int i=0; i<items.size(); i++){
			
			if(this.itemIndex>=items.size()){
				if(cycle)
					this.itemIndex = 0;
				else
					this.itemIndex = maxSelectable;
			}
			Gdx.app.log("MenuItemList", "down index: "+itemIndex + " selectable: "+items.get(itemIndex).isSelectable());
			if(items.get(itemIndex).isSelectable())
				break;
			else
				this.itemIndex++;
		}
		if(itemIndex>0 && itemIndex<items.size())
			items.get(itemIndex).select();
	}

	public void select() {
		for(int i=0; i<items.size(); i++){
			if(items.get(i).isSelectable()){
				items.get(i).select();
				this.itemIndex = i;
				break;
			}
		}
	}

	public void click() {
		if(itemIndex>0 && itemIndex<items.size())
			if(items.get(itemIndex).isSelectable())
				items.get(itemIndex).click();
	}
	

}
