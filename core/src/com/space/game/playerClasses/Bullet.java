package com.space.game.playerClasses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.space.game.entities.Entity;
import com.space.game.screens.GameScreen;
import com.space.game.utils.ExtendedShapeRenderer;


public class Bullet {
    Vector2 position;
    float length,thickness;
    Vector2 movement;
    float distanceTraveled;
    float range;
    float damage;
    Color color;

    public Bullet(Vector2 position, Vector2 movement, float damage,Color color) {
        this.position = new Vector2(position);
        this.movement = new Vector2(movement);
        this.damage = damage;
        this.color=color;
        distanceTraveled=0;
        range= 100*GameScreen.getPixelsPerMeter();
        length=2*GameScreen.getPixelsPerMeter();
        thickness=2*GameScreen.getPixelsPerMeter();
    }
    public void collision(Player player){
        if(player.getHitbox().contains(position)){
            player.damage(damage);
            onHitEffect();
        }
    }

    public void collision(Entity entity){
        if(entity.getHitbox().contains(position)){
            entity.damage(damage);
            onHitEffect();
        }

    }
    private void adjustMovement(float delta){
        Vector2 m=new Vector2(movement);
        position.add(m.scl(delta));
        distanceTraveled+=m.len2()*delta;
    }
    public void onHitEffect(){
        death();
    }
    public void death(){
        GameScreen.bullets.removeValue(this,true);
    }
    public void draw(ExtendedShapeRenderer SR,float delta){
        //Act
        adjustMovement(delta);

        //Draw
        Vector2 length=new Vector2(movement).nor().scl(this.length);
        SR.drawBlurredLine(color,new Vector2(position),new Vector2(position).add(length),Math.round(thickness));
    }
}
