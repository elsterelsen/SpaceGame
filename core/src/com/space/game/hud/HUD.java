package com.space.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.space.game.SpaceGame;
import com.space.game.screens.GameScreen;
import com.space.game.utils.ExtendedShapeRenderer;

public class HUD {
    private Minimap minimap;
    private Vector2 minimapPos,minimapSize,hpBarPos,hpBarSize,pilotPos,pilotSize;
    private final SpriteBatch batch= SpaceGame.getHUDbatch();
    private Texture pilotTexture;

    public HUD() {
        loadHudSettings();
        pilotTexture=new Texture("badlogic.jpg");
        minimap=new Minimap(minimapPos,minimapSize);
    }
    public void loadHudSettings(){
        minimapPos=new Vector2(0,0);
        minimapSize=new Vector2(Gdx.graphics.getHeight()/5f*2,Gdx.graphics.getHeight()/5f*2);
        pilotSize=new Vector2(Gdx.graphics.getHeight()/7f,Gdx.graphics.getHeight()/7f);
        pilotPos=new Vector2(Gdx.graphics.getWidth(),0).add(-pilotSize.x,0);
        hpBarPos=new Vector2(minimapSize.x,0);
        hpBarSize=new Vector2(Gdx.graphics.getWidth()-pilotSize.x,Gdx.graphics.getHeight()/10f);
    }
    public void draw(){

        batch.begin();
        batch.end();
    }
    public void renewMinimap(int newWidth,int newHeight){
        minimap=new Minimap(new Vector2(minimapPos.x-(newWidth- SpaceGame.getINITIALSCREENHEIGHT())/5f*2,0),new Vector2(newWidth/5f*2,newHeight/5f*2));
    }

}
