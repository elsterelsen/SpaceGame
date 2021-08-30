package com.space.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.space.game.SpaceGame;
import com.space.game.entities.Entity;
import com.space.game.entities.Meteor;
import com.space.game.obstacles.Border;
import com.space.game.playerClasses.Player;
import com.space.game.utils.ExtendedShapeRenderer;

public class GameScreen extends ScreenAdapter {
    private static final float pixelPerMeter=Gdx.graphics.getHeight()/10f/5f;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private SpriteBatch HUDbatch;
    private ExtendedShapeRenderer SR;
    private Player player;
    private Border border;
    private Array<Entity> entities;
    public GameScreen() {
        HUDbatch=SpaceGame.getHUDbatch();
        batch= SpaceGame.getBatch();
        camera=new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player=new Player();
        SR=SpaceGame.getSR();
        border=new Border(Math.round(2000*pixelPerMeter),Math.round(1000*pixelPerMeter));
        entities=new Array<>();
        entities.add(new Meteor(100,100));
    }

    @Override
    public void render(float delta) {

        controlls(delta);
        collisionCheck();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        draw(delta);
        drawSR();
        drawHUD(delta);
        updateCamera();
    }
    public void drawSR(){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        SR.begin(ShapeRenderer.ShapeType.Filled);
        SR.setProjectionMatrix(camera.combined);
        border.draw();
        SR.end();
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
        camera.position.x=player.getCenter().x+player.getWidth();
        camera.position.y=player.getCenter().y+player.getHeight();
        camera.update();
    }
    public void collisionCheck(){
        border.collision(player);
    }
    public void controlls(float delta){
        boolean f=true;
        if(Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.addVelocity(delta);
            f=false;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.addVelocity(-delta);
            f=false;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.left(delta);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.right(delta);
        }
        player.move(delta,f);
    }

    public static float getPixelsPerMeter(){
        return pixelPerMeter;
    }
}
