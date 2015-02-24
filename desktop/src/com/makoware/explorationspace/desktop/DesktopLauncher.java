package com.makoware.explorationspace.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.makoware.explorationspace.ESgame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Exploration: Space v"+ESgame.version;

        config.width = 1280;
        config.height = 720;

		new LwjglApplication(new ESgame(), config);
	}
}
