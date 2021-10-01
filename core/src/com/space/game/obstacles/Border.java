package com.space.game.obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.space.game.SpaceGame;
import com.space.game.playerClasses.Player;
import com.space.game.screens.GameScreen;
import com.space.game.utils.ExtendedShapeRenderer;


public class Border extends Rectangle {
Vector2 v0,v1,v2,v3;
    public Border(int width, int height) {
        super(0,0,width, height);
        setCenter(0,0);
        updateVertecies();
    }
    public void updateVertecies(){
        v0=new Vector2(x,y+height);
        v1=new Vector2(x+width,y+height);
        v2=new Vector2(x+width,y);
        v3=new Vector2(x,y);
    }
    public void collision(Player p){
        Vector2 size=new Vector2(p.getHitbox().radius*2,p.getHitbox().radius*2);
        if(p.getPosition().x<x){
            p.setX(x);
        }
        if (p.getPosition().y<y){
            p.setY(y);
        }
        if(p.getPosition().x+size.x>x+width){
            p.setX(x+width-size.x);
        }

        if(p.getPosition().y+size.y>y+height){
            p.setY(y+height-size.y);
        }
    }
    public void draw(){
        ExtendedShapeRenderer SR= SpaceGame.getSR();
        SR.drawBlurredLine(com.badlogic.gdx.graphics.Color.CORAL,v0,v1,Math.round(5f* GameScreen.getPixelsPerMeter()));
        SR.drawBlurredLine(com.badlogic.gdx.graphics.Color.WHITE,v0,v1,Math.round(2.5f*GameScreen.getPixelsPerMeter()));
        SR.drawBlurredLine(com.badlogic.gdx.graphics.Color.CORAL,v1,v2,Math.round(5f* GameScreen.getPixelsPerMeter()));
        SR.drawBlurredLine(com.badlogic.gdx.graphics.Color.WHITE,v1,v2,Math.round(2.5f*GameScreen.getPixelsPerMeter()));
        SR.drawBlurredLine(com.badlogic.gdx.graphics.Color.CORAL,v2,v3,Math.round(5f* GameScreen.getPixelsPerMeter()));
        SR.drawBlurredLine(com.badlogic.gdx.graphics.Color.WHITE,v2,v3,Math.round(2.5f*GameScreen.getPixelsPerMeter()));
        SR.drawBlurredLine(com.badlogic.gdx.graphics.Color.CORAL,v3,v0,Math.round(5f* GameScreen.getPixelsPerMeter()));
        SR.drawBlurredLine(com.badlogic.gdx.graphics.Color.WHITE,v3,v0,Math.round(2.5f*GameScreen.getPixelsPerMeter()));


        SR.drawBlurredCircle(com.badlogic.gdx.graphics.Color.CORAL,v0,Math.round(5f*GameScreen.getPixelsPerMeter()));
        SR.drawBlurredCircle(Color.WHITE,v0,Math.round(2.5f*GameScreen.getPixelsPerMeter()));
        SR.drawBlurredCircle(com.badlogic.gdx.graphics.Color.CORAL,v1,Math.round(5f*GameScreen.getPixelsPerMeter()));
        SR.drawBlurredCircle(Color.WHITE,v1,Math.round(2.5f*GameScreen.getPixelsPerMeter()));
        SR.drawBlurredCircle(com.badlogic.gdx.graphics.Color.CORAL,v2,Math.round(5f*GameScreen.getPixelsPerMeter()));
        SR.drawBlurredCircle(Color.WHITE,v2,Math.round(2.5f*GameScreen.getPixelsPerMeter()));
        SR.drawBlurredCircle(com.badlogic.gdx.graphics.Color.CORAL,v3,Math.round(5f*GameScreen.getPixelsPerMeter()));
        SR.drawBlurredCircle(Color.WHITE,v3,Math.round(2.5f*GameScreen.getPixelsPerMeter()));

    }
}
