package com.makoware.explorationspace.Input;

public interface TouchControllerService extends ControllerService {

	public void touchDown(int screenX, int screenY, int pointer, int button);

	public void touchUp(int screenX, int screenY, int pointer, int button);

	public void touchDragged(int screenX, int screenY, int pointer);
}
