package com.space.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.space.game.SpaceGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.pauseWhenBackground=true;
		config.pauseWhenMinimized=true;
		config.width=500;
		config.height=500;
		//config.fullscreen=true;
		//processTextures();
		new LwjglApplication(new SpaceGame(), config);
	}
	public static void processTextures(){
		TexturePacker.process("KennyAssets/Default/meteor","KennyAssets/Default/meteor","atlas");
	}
}
