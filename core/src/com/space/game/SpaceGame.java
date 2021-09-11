package com.space.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.space.game.screens.GameScreen;
import com.space.game.utils.ExtendedShapeRenderer;

public class SpaceGame extends Game {
	private static int INITIALSCREENHEIGHT;
	private static ExtendedShapeRenderer SR;
	private static ExtendedShapeRenderer hudSr;
	private static SpriteBatch batch;
	private static SpriteBatch HUDbatch;
	private static SpaceGame INSTANCE;
	
	@Override
	public void create () {
		batch=new SpriteBatch();
		HUDbatch=new SpriteBatch();
		INSTANCE=this;
		hudSr=new ExtendedShapeRenderer();
		SR=new ExtendedShapeRenderer();
			setScreen(new GameScreen());
			INITIALSCREENHEIGHT= Gdx.graphics.getHeight();

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

	public static ExtendedShapeRenderer getSR() {
		return SR;
	}

	public static ExtendedShapeRenderer getHudSr() {
		return hudSr;
	}

	public static int getINITIALSCREENHEIGHT() {
		return INITIALSCREENHEIGHT;
	}
}
