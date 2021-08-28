package com.space.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.game.screens.GameScreen;

public class SpaceGame extends Game {
	private static SpriteBatch batch;
	private static SpaceGame INSTANCE;
	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public static SpaceGame getINSTANCE() {
		return INSTANCE;
	}

	public static SpriteBatch getBatch() {
		return batch;
	}

	public static void setBatch(SpriteBatch batch) {
		SpaceGame.batch = batch;
	}
}
