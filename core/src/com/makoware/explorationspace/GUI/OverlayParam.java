package com.makoware.explorationspace.GUI;

import java.util.HashMap;

public class OverlayParam{
	
	private HashMap<String,Object> params = new HashMap<String,Object>();
	
	public OverlayParam put(String key, Object obj){ 
		params.put(key, obj);
		return this;
	}
	
	public boolean contains(String key){ 
		return params.containsKey(key); 
	}
	
	public Object get(String key){ 
		return params.get(key); 
	}
	
	public <T> T get(String key, Class<T> c){ 
		return (T)params.get(key); 
	}
}
