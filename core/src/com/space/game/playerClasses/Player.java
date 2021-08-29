package com.space.game.playerClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.space.game.entities.Entity;

public class Player extends Sprite {
    private Texture img = new Texture("KennyAssets/Default/enemy_A.png");
    private Sprite minimapImg;
    private float speed;
    private float maxSpeed;
    private Vector2 currentMovement;
    private float turnSpeed;
    private float friction;
    private float hp;
    private float maxHp;
    private Circle hitbox;

    public Player() {
        super(new Texture("KennyAssets/Default/enemy_A.png"));
        turnSpeed=1;
        maxSpeed=100;
        currentMovement=new Vector2(0,0);
        friction=1.1f;
        setTexture(img);
        this.minimapImg = new Sprite();
        speed=3f;
        setSize(Gdx.graphics.getHeight()/10f,Gdx.graphics.getHeight()/10f );
        setCenter(0,0);
        setOrigin(getWidth()/2f,getHeight()/2f);
        hitbox=new Circle(getCenter().x,getCenter().y,getHeight()/2f);
    }

    public void drawMinimapSymbol(SpriteBatch batch){
    }

    public void addVelocity(float delta){
        System.out.println(currentMovement.len());
        if(currentMovement.len2()<maxSpeed){
        Vector2 movement=new Vector2(1,0);
        movement.setAngleDeg(getRotation()+90);
        movement.scl(speed*delta*getHeight());
        currentMovement.add(movement);
        }
    }
    public void move(float delta){
        translate(currentMovement.x,currentMovement.y);
        currentMovement.scl(1/ friction);
        currentMovement.scl(delta);
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
    public void collisionCheck(Entity e){
        if(hitbox.overlaps(e.getHitbox())){
            e.hitEffect();
        }
    }
    public void updateHitbox(){
        hitbox.setPosition(getCenter().x,getCenter().y);
    }
}
