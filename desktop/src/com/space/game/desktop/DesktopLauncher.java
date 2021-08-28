package com.space.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.space.game.SpaceGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.pauseWhenBackground=true;
		config.pauseWhenMinimized=true;
		config.width=500;
		config.height=500;
		new LwjglApplication(new SpaceGame(), config);
	}
}
