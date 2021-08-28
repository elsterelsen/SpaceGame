package com.space.game.playerClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Rectangle {
    private Sprite img;
    private Sprite minimapImg;
    private float speed;

    public Player() {
        this.img = new Sprite();
        this.minimapImg = new Sprite();
        speed=10;
        x=0;
        y=0;
        setCenter(0,0);
        height= Gdx.graphics.getHeight()/10f;
        width=height;
    }
    public void draw(SpriteBatch batch){
        batch.draw(img,x,y,width,height);
    }
    public void drawMinimapSymbol(SpriteBatch batch){
        batch.draw(minimapImg,x,y,width,height);
    }
    public void rotate(float degrees){
        img.rotate(degrees);
        minimapImg.rotate(degrees);
    }
    public void move(float delta){
        Vector2 pos=getPosition(new Vector2());
        pos.add(new Vector2().setAngleDeg(img.getRotation()).scl(speed*delta));
        x=pos.x;
        y=pos.y;
    }
}
