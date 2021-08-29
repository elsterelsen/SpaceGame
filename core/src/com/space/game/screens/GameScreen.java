package com.space.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.space.game.SpaceGame;
import com.space.game.playerClasses.Player;

public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private SpriteBatch HUDbatch;
    private Player player;
    public GameScreen() {
        HUDbatch=SpaceGame.getHUDbatch();
        batch= SpaceGame.getBatch();
        camera=new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player=new Player();
    }

    @Override
    public void render(float delta) {
        controlls(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        draw(delta);
        drawHUD(delta);
        updateCamera();
    }
    public void draw(float delta){
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        player.draw(batch);
        batch.end();
    }
    public void drawHUD(float delta){
        HUDbatch.begin();
        HUDbatch.end();
    }
    public void updateCamera(){
        camera.translate(player.getCenter());
    }
    public void controlls(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.addVelocity(delta);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.addVelocity(-delta);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.left(delta);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.right(delta);
        }
        player.move(delta);
    }
}
