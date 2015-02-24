package com.makoware.explorationspace.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

public class AndroidControllerInput implements InputMethod {
	public static String tag = "AndroidControllerInput";
	private ControllerService cont; 
	
	public AndroidControllerInput() {
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Keys.BACK){
			cont.ps(0);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode==Keys.BACK){
			cont.ps(1);
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(cont instanceof TouchControllerService)
			((TouchControllerService)cont).touchDown(screenX, screenY, pointer, button);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(cont instanceof TouchControllerService)
			((TouchControllerService)cont).touchUp(screenX, screenY, pointer, button);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(cont instanceof TouchControllerService)
			((TouchControllerService)cont).touchDragged(screenX, screenY, pointer);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void connected(Controller controller) {
	}

	@Override
	public void disconnected(Controller controller) {
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode,
			PovDirection value) {
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode,
			boolean value) {
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode,
			boolean value) {
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller,
			int accelerometerCode, Vector3 value) {
		return false;
	}

	@Override
	public void setService(ControllerService cont) {
		this.cont = cont;
	}

	@Override
	public ControllerService getService() {
		return this.cont;
	}

	@Override
	public void cycle() {
	}

}
