package com.space.game.playerClasses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.space.game.entities.Entity;
import com.space.game.screens.GameScreen;
import com.space.game.utils.ExtendedShapeRenderer;


public class Bullet {
    Vector2 position;
    Vector2 size;
    Vector2 movement;
    float range;
    float damage;
    Color color;

    public Bullet(Vector2 position, Vector2 movement, float damage,Color color) {
        this.position = position;
        size=new Vector2(1* GameScreen.getPixelsPerMeter(),0.2f*GameScreen.getPixelsPerMeter());
        this.movement = movement;
        this.damage = damage;
        this.color=color;
        range= 100*GameScreen.getPixelsPerMeter();
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
    public void onHitEffect(){
        death();
    }
    public void death(){
        GameScreen.bullets.removeValue(this,true);
    }
    public void draw(ExtendedShapeRenderer SR){
        SR.drawBlurredLine(color,position,position.add(movement.x,movement.y),Math.round(size.y));
    }
}
