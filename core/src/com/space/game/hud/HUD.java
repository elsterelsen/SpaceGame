package com.space.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.space.game.SpaceGame;
import com.space.game.playerClasses.Player;
import com.space.game.utils.ExtendedShapeRenderer;

public class HUD {
    public float playerHp;
    public Minimap minimap;
    public HpBar hpBar;
    public Sprite pilot;
    public Player player;
    public OrthographicCamera camera;
    public ScreenViewport viewport;

    public HUD(Player player) {
        camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        viewport=new ScreenViewport(camera);
        viewport.apply();
        this.player = player;
        hpBar=new HpBar(player);
    }
    public void draw(){
        ExtendedShapeRenderer sr= SpaceGame.getSR();
        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        hpBar.draw(sr);
        sr.end();
    }
    public void updateViewport(int width,int height){
        viewport.update(width,height);
    }
    public void controlls(float delta,Player player){

    }
}
