package com.space.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.space.game.playerClasses.Player;

public class Entity extends Sprite {

    private Texture img = new Texture("KennyAssets/Default/enemy_A.png");
    private Sprite minimapImg;
    private float speed;
    private float maxSpeed;
    private Vector2 currentMovement;
    private float turnSpeed;
    private float friction;
    public float hp;
    public float maxHp;
    private Circle hitbox;

    public Entity() {
        super(new Texture("KennyAssets/Default/meteor_large.png"));
        turnSpeed=1;
        maxSpeed=100;
        currentMovement=new Vector2(0,0);
        friction=1.5f;
        setTexture(img);
        this.minimapImg = new Sprite();
        speed=1.5f;
        setOrigin(getCenter().x,getCenter().y);
        hitbox=new Circle(getCenter().x,getCenter().y,getHeight()/2f);
    }
    public void drawMinimapSymbol(SpriteBatch batch){
    }

    public void addVelocity(float delta){
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
        return new Vector2(getX()+getWidth()/2f,getY()+getHeight()/2f);
    }
    public void hitEffect(Player player){}
    public void collisionCheck(){}
    public void updateHitbox(){
        hitbox.setPosition(getCenter().x,getCenter().y);
    }
    public com.badlogic.gdx.math.Circle getHitbox() {
        return hitbox;
    }
    public void death(){}
}
