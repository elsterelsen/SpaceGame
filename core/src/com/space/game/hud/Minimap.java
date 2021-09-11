package com.space.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.space.game.SpaceGame;
import com.space.game.entities.Entity;
import com.space.game.playerClasses.Player;
import com.space.game.screens.GameScreen;
import com.space.game.utils.ExtendedShapeRenderer;

public class Minimap {
    Player player;
    float zoom;
    Texture background;
    Array<Entity> entities;
    Vector2 pos,size;

    public Minimap(Vector2 pos,Vector2 size) {
        this.size=size;
        this.pos=pos;
        entities=GameScreen.getEntities();
        player=GameScreen.getPlayer();
        background=new Texture("badlogic.jpg");
        zoom=Player.getMinimapZoom();
    }

    public void draw(){



        ScreenViewport viewport=GameScreen.getViewport();
        SpriteBatch batch=GameScreen.getBatch();
        ExtendedShapeRenderer sr=SpaceGame.getHudSr();
        OrthographicCamera camera=GameScreen.getCamera();
        Vector2 cameraTranslation=new Vector2((Gdx.graphics.getWidth()-SpaceGame.getINITIALSCREENHEIGHT())/2f,0).scl(-1);
        camera.translate(cameraTranslation);
        camera.zoom*=zoom;
        camera.update();

        Gdx.gl.glViewport(Math.round(pos.x),Math.round(pos.y),Math.round(size.x),Math.round(size.y));
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.NAVY);
        sr.rect(pos.x, pos.y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sr.setColor(Color.WHITE);
        sr.rect(pos.x, pos.y, Gdx.graphics.getWidth()*0.98f, Gdx.graphics.getHeight()*0.98f);
        sr.setColor(Color.NAVY);
        sr.rect(pos.x, pos.y, Gdx.graphics.getWidth()*0.96f, Gdx.graphics.getHeight()*0.96f);
        sr.end();



            System.out.println("clip worked");
            batch.begin();
            batch.setProjectionMatrix(camera.combined);
            player.drawMinimapSymbol(batch);
            if(entities.size!=0) {
                for (Entity e : entities) {
                    e.drawMinimapSymbol(batch);
                }
            }
            batch.end();


        Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(cameraTranslation.scl(-1));
        camera.zoom/=zoom;
        camera.update();

    }

}
