package com.makoware.explorationspace.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.ControllerListener;

public interface InputMethod extends InputProcessor, ControllerListener {
	
	public void setService(ControllerService cont);
	
	public ControllerService getService();

	public void cycle();
	
}
