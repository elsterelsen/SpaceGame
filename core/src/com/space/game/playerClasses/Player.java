package com.space.game.playerClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.space.game.entities.Entity;
import com.space.game.npc.Spacestation;
import com.space.game.screens.GameScreen;
import org.graalvm.compiler.core.common.SpeculativeExecutionAttacksMitigations;

public class Player extends Sprite {
    private Texture img = new Texture("KennyAssets/Default/enemy_A.png");
    private Sprite minimapImg;
    private float acceleration;
    private float speed;
    private float maxSpeed;
    private Vector2 currentMovement;
    private float turnSpeed;
    private float friction;
    private float hp;
    private float maxHp;
    private Circle hitbox;
    private float bulletDamage;
    private float attackSpeed;
    private float attackTimeCounter;
    private final float bulletSpeed;
    Color bulletColor;

    public Player() {
        super(new Texture("KennyAssets/Default/enemy_A.png"));
        turnSpeed=1;
        currentMovement=new Vector2(0,0);
        friction=0.5f*GameScreen.getPixelsPerMeter();
        acceleration=8f* GameScreen.getPixelsPerMeter();
        maxSpeed=acceleration/friction;
        bulletDamage=100;
        bulletSpeed=50f*GameScreen.getPixelsPerMeter();

        attackSpeed=2;
        attackTimeCounter=attackSpeed;
        bulletColor= Color.BLUE;
        setTexture(img);
        this.minimapImg = new Sprite();
        maxHp=1000f;
        fillUpHp();
        setSize(Gdx.graphics.getHeight()/10f,Gdx.graphics.getHeight()/10f );
        setCenter(0,0);
        setOrigin(getWidth()/2f,getHeight()/2f);
        hitbox=new Circle(getCenter().x,getCenter().y,getHeight()/2f);
    }

    public void drawMinimapSymbol(SpriteBatch batch){
    }

    public void addVelocity(float delta){
        //System.out.println(currentMovement.len2()/GameScreen.getPixelsPerMeter());
        Vector2 movement=new Vector2(1,0);
        movement.setAngleDeg(getRotation()+90);
        movement.nor();
        movement.scl(acceleration*delta);
        currentMovement.add(movement.x,movement.y);

    }
    public void move(float delta){
        translate(currentMovement.x,currentMovement.y);

            Vector2 newMovement=new Vector2(currentMovement);
            Vector2 m =new Vector2(currentMovement);
        newMovement.mulAdd(m,-(friction*delta));
        if(newMovement.hasOppositeDirection(currentMovement)){

            currentMovement.scl(0f);
        }
        else if(newMovement.len2()<currentMovement.len2()){
            currentMovement=new Vector2(newMovement);
        }
            //    currentMovement.scl(0);

        updateHitbox();
    }
    public Vector2 getPosition(){
        return new Vector2(getX(),getY());
    }
    public Vector2 getCenter(){
        return new Vector2(getX()+getWidth()/2f,getY()+getHeight()/2f);
    }
    public void left(float delta){
        rotate(delta*(180)*turnSpeed);
    }

    public void right(float delta){
        rotate(delta*-180*turnSpeed);
    }
    public void collisionCheck(Entity e){
        if(hitbox.overlaps(e.getHitbox())){
            e.hitEffect(this);
        }
    }
    public void collisionCheck(Spacestation spacestation){
        if(hitbox.overlaps(spacestation.getShieldHitbox())){
            spacestation.hoverEffect(this);
        }
    }
    public void updateHitbox(){
        hitbox.setPosition(getCenter().x,getCenter().y);
    }
    public void fillUpHp(){
        hp=maxHp;
    }

    public Circle getHitbox() {
        return hitbox;
    }
    public boolean isSmaller(Entity entity){
        return entity.getHitbox().radius>hitbox.radius;
    }
    public void damage(float damage){
        hp-=damage;
        checkDeath();
    }
    public float crashDamage(){
        hp-=currentMovement.len2()/GameScreen.getPixelsPerMeter();
        return currentMovement.len2()/GameScreen.getPixelsPerMeter();
    }
    public void checkDeath(){

    }
    public void shoot(float delta){
        if(attackTimeCounter>=1/attackSpeed){
            Vector2 bulletAccelaration=new Vector2(1,0).nor().setAngleDeg(getRotation()+90).scl(bulletSpeed);
            Vector2 bulletMovement=new Vector2(currentMovement);
            bulletMovement.add(bulletAccelaration);
        GameScreen.bullets.add(
                new Bullet(getCenter(),bulletMovement,bulletDamage,bulletColor)
        );
        attackTimeCounter=0;
        }
    }
    public void raiseAttackTimeCounter(float delta){
        attackTimeCounter+=delta;
    }
    public float getHpPercent(){
        return hp/maxHp;
    }
    public void heal(float percent){
        hp+=hp*percent;
        if(hp>maxHp){
            hp=maxHp;
        }
    }
}
