package com.space.game.playerClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {
    private Texture img = new Texture("KennyAssets/Default/enemy_A.png");
    private Sprite minimapImg;
    private float speed;
    private float turnSpeed;
    private Vector2 position;

    public Player() {
        super(new Texture("KennyAssets/Default/enemy_A.png"));
        turnSpeed=1;
        setTexture(img);

        this.minimapImg = new Sprite();
        speed=1.5f;
        setSize(Gdx.graphics.getHeight()/10f,Gdx.graphics.getHeight()/10f );
        setCenter(0,0);
        setOrigin(getWidth()/2f,getHeight()/2f);
    }

    public void drawMinimapSymbol(SpriteBatch batch){
    }

    public void move(float delta){
        Vector2 movement=new Vector2(1,0);
        movement.setAngleDeg(getRotation()+90);
        movement.scl(speed*delta*getHeight());
        translate(movement.x,movement.y);
    }
    public Vector2 getPosition(){
        return new Vector2(getX(),getY());
    }
    public Vector2 getCenter(){
        return new Vector2(getX()-getWidth()/2f,getY()-getHeight()/2f);
    }
    public void left(float delta){
        rotate(delta*(-180)*turnSpeed);
    }

    public void right(float delta){
        rotate(delta*180*turnSpeed);
    }
}
