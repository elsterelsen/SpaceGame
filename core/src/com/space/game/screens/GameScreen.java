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
import com.space.game.playerClasses.Bullet;
import com.space.game.playerClasses.Player;
import com.space.game.utils.ExtendedShapeRenderer;

import java.util.Random;

public class GameScreen extends ScreenAdapter {
    private static final float pixelPerMeter=Gdx.graphics.getHeight()/10f/5f;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private SpriteBatch HUDbatch;
    private ExtendedShapeRenderer SR;
    private Player player;
    private Border border;
    public static Array<Entity> entities;
    public static Array<Bullet> bullets;
    public GameScreen() {
        HUDbatch=SpaceGame.getHUDbatch();
        batch= SpaceGame.getBatch();
        camera=new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player=new Player();
        SR=SpaceGame.getSR();
        border=new Border(Math.round(2000*pixelPerMeter),Math.round(1000*pixelPerMeter));
        entities=new Array<>();
        bullets=new Array<>();
        bullets.add(new Bullet(new Vector2(10000,10000),new Vector2(),1,Color.RED));
        initialAddEntities(400);

    }

    @Override
    public void render(float delta) {

        controlls(delta);
        collisionCheck();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawBullets(delta);
        draw(delta);
        drawBorder(delta);
        drawHUD(delta);
        updateCamera();
    }
    public void drawBorder(float delta){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        SR.begin(ShapeRenderer.ShapeType.Filled);
        SR.setProjectionMatrix(camera.combined);
        border.draw();

        SR.end();
    }
    public void drawBullets(float delta){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        SR.begin(ShapeRenderer.ShapeType.Filled);
        SR.setProjectionMatrix(camera.combined);
        for(Entity e:entities){
            e.drawHpBar(delta,SR);
        }
        for(Bullet b:bullets){
            b.draw(SR,delta);
        }
        SR.end();
    }
    public void draw(float delta){
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        player.draw(batch);
        for(Entity e:entities){
            e.draw(batch);
        }
        batch.end();
    }
    public void drawHUD(float delta){
        HUDbatch.begin();
        HUDbatch.end();
    }
    public void initialAddEntities(int number){
        entities.add(new Meteor(-1000,1000));
        Random random=new Random();
        for(int i=number;i>0;i--){
            Vector2 entityPosition=new Vector2(random.nextFloat()*border.width*0.9f-border.width/2f,random.nextFloat()*border.height*0.9f-border.height/2f);
            boolean repeat=false;
            for (Entity e:entities){
                if(entityPosition.dst(e.getCenter())/pixelPerMeter<40){
                    repeat=true;
                    break;
                }
            }
            if(repeat){i++;}
            else{entities.add(new Meteor(entityPosition.x,entityPosition.y));}
        }
    }
    public void invisibleAddEntity(){

    }
    public void updateCamera(){
        camera.position.x=player.getCenter().x;
        camera.position.y=player.getCenter().y;
        camera.update();
    }
    public void collisionCheck(){
        border.collision(player);

            for (Entity e:entities){
                player.collisionCheck(e);
                for(Bullet b:bullets){
                    b.collision(e);
                }
            }


    }
    public void controlls(float delta){
        boolean f=true;
        //Movement Input Control
        if(Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.addVelocity(delta);
            f=false;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.addVelocity(-delta);
            f=false;
        }
        //Turning Input Controll
        if(Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.left(delta);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.right(delta);
        }
        //Shoot Input Control
        player.raiseAttackTimeCounter(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            player.shoot(delta);
        }
        player.move(delta,f);
    }

    public static float getPixelsPerMeter(){
        return pixelPerMeter;
    }
}
