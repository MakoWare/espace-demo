package com.makoware.explorationspace.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

public class DesktopControllerInput implements InputMethod {
	public String tag = "DesktopControllerInput";
	private ControllerService cont;
	private Controller c;
	
	public enum Conts{
		Sony
	}

	public DesktopControllerInput(ControllerService cont){
		Gdx.app.log(tag, tag);
		this.cont = cont;
	}
	
	public DesktopControllerInput() {

		if(Controllers.getControllers().size>0){
			c = Controllers.getControllers().first();
			c.addListener(this);
		}
		
	}

	@Override
	public void setService(ControllerService cont) {
		this.cont = cont;
	}
	
	@Override
	public ControllerService getService() {
		return cont;
	}

	@Override
	public boolean keyDown(int keycode){
//        Gdx.app.log("controller","keydown: "+keycode);
		switch(keycode){
		case Keys.DPAD_RIGHT:
			cont.right(0);
			break;
		case Keys.DPAD_LEFT:
			cont.left(0);
			break;
		case Keys.DPAD_UP:
			cont.up(0);
			break;
		case Keys.DPAD_DOWN:
			cont.down(0);
			break;
		case Keys.SPACE:
			cont.cross(0);
			break;
		case Keys.S:
			cont.R2(0);
			break;
		case Keys.A:
			cont.L2(0);
			break;
		case Keys.X:
			cont.R1(0);
			break;
		case Keys.Z:
			cont.triangle(0);
			break;
		case Keys.SHIFT_LEFT:
			cont.square(0);
			break;
        case Keys.ESCAPE:
            cont.ps(0);
            break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode){
		switch(keycode){
		case Keys.DPAD_RIGHT:
			cont.right(1);
			break;
		case Keys.DPAD_LEFT:
			cont.left(1);
			break;
		case Keys.DPAD_UP:
			cont.up(1);
			break;
		case Keys.DPAD_DOWN:
			cont.down(1);
			break;
		case Keys.SPACE:
			cont.cross(1);
			break;
		case Keys.S:
			cont.R2(1);
			break;
		case Keys.A:
			cont.L2(1);
			break;
		case Keys.X:
			cont.R1(1);
			break;
		case Keys.Z:
			cont.triangle(1);
			break;
		case Keys.SHIFT_LEFT:
			cont.square(1);
			break;
        case Keys.ESCAPE:
            cont.ps(1);
            break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(cont instanceof TouchControllerService) ((TouchControllerService)cont).touchDown(screenX, screenY, pointer, button);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(cont instanceof TouchControllerService) ((TouchControllerService)cont).touchUp(screenX, screenY, pointer, button);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(cont instanceof TouchControllerService) ((TouchControllerService)cont).touchDragged(screenX, screenY, pointer);
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
		Gdx.app.log(tag, "Connected: "+controller.getName());
	}

	@Override
	public void disconnected(Controller controller) {
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
//		Gdx.app.log(tag, controller.getName() + " : down : " + buttonCode);
		switch(buttonCode){
		case 0:
			cont.select(0);
			break;
		case 1:
			cont.L3(0);
			break;
		case 2:
			cont.R3(0);
			break;
		case 3:
			cont.start(0);
			break;
		case 4:
			cont.up(0);
			break;			
		case 5:
			cont.right(0);
			break;
		case 6:
			cont.down(0);
			break;
		case 7:
			cont.left(0);
			break;
		case 8:
			cont.L2(0);
			break;
		case 9:
			cont.R2(0);
			break;
		case 10:
			cont.L1(0);
			break;
		case 11:
			cont.R1(0);
			break;
		case 12:
			cont.triangle(0);
			break;
		case 13:
			cont.circle(0);
			break;
		case 14:
			cont.cross(0);
			break;
		case 15:
			cont.square(0);
			break;
		case 16:
			cont.ps(0);
			break;
		}
		return true;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
//		Gdx.app.log(tag, controller.getName() + " :  up  : " + buttonCode);
		switch(buttonCode){
		case 0:
			cont.select(1);
			break;
		case 1:
			cont.L3(1);
			break;
		case 2:
			cont.R3(1);
			break;
		case 3:
			cont.start(1);
			break;
		case 4:
			cont.up(1);
			break;			
		case 5:
			cont.right(1);
			break;
		case 6:
			cont.down(1);
			break;
		case 7:
			cont.left(1);
			break;
		case 8:
			cont.L2(1);
			break;
		case 9:
			cont.R2(1);
			break;
		case 10:
			cont.L1(1);
			break;
		case 11:
			cont.R1(1);
			break;
		case 12:
			cont.triangle(1);
			break;
		case 13:
			cont.circle(1);
			break;
		case 14:
			cont.cross(1);
			break;
		case 15:
			cont.square(1);
			break;
		case 16:
			cont.ps(1);
			break;
		}
		return true;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
//		if(value < -0.1 || value > 0.1){
//			Gdx.app.log(tag, controller.getName() + " : axis : " + axisCode + ":"+ value);
		if(value<-0.1f || value>0.1f)
			Gdx.app.log(tag, "axis: "+axisCode+" val: "+value);
		switch(axisCode){
		case 0:
		case 1:
			cont.LAxis(axisCode, value);
			break;
		case 2:
			cont.RAxis(4, value);
		case 3:
			cont.RAxis(axisCode, value);
			break;
		}
//		}
//			Gdx.app.log(tag, controller.getName() + " : axis : " + axisCode + ":"+ value);
		return true;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		Gdx.app.log(tag, controller.getName() + " :  pov : " + povCode + ":"+ value);
		return true;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		Gdx.app.log(tag, controller.getName() + " :xslide: " + sliderCode + ":"+ value);
		return true;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		Gdx.app.log(tag, controller.getName() + " :yslide: " + sliderCode + ":"+ value);
		return true;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		Gdx.app.log(tag, controller.getName() + " : accel: " + accelerometerCode + ":"+ value.toString());
		return true;
	}

	@Override
	public void cycle() {
	}

}
