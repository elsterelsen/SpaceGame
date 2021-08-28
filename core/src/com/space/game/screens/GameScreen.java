package com.space.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.game.SpaceGame;
import com.space.game.playerClasses.Player;

public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;
    public GameScreen() {
        batch= SpaceGame.getBatch();
        camera=new OrthographicCamera();
        player=new Player();
    }

    @Override
    public void render(float delta) {

    }
}
