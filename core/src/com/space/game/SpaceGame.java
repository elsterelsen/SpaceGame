package com.space.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.game.screens.GameScreen;

public class SpaceGame extends Game {
	private static SpriteBatch batch;
	private static SpriteBatch HUDbatch;
	private static SpaceGame INSTANCE;
	
	@Override
	public void create () {
		batch=new SpriteBatch();
		HUDbatch=new SpriteBatch();
		INSTANCE=this;
		setScreen(new GameScreen());
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public static SpaceGame getINSTANCE() {
		return INSTANCE;
	}

	public static SpriteBatch getHUDbatch() {
		return HUDbatch;
	}

	public static void setHUDbatch(SpriteBatch HUDbatch) {
		SpaceGame.HUDbatch = HUDbatch;
	}

	public static SpriteBatch getBatch() {
		return batch;
	}

	public static void setBatch(SpriteBatch batch) {
		SpaceGame.batch = batch;
	}
}
