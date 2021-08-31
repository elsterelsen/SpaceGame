package com.space.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class HPBar {
    float counter;
    float refreshingTime;
    Vector2 position;
    Vector2 size;
    Vector2 adjectedCenter;
    float hpPercent;
    float formerHpPercent;
    public HPBar(float refreshingTime, Vector2 position, Vector2 size,Vector2 adjectedCenter) {
        this.counter = 0;
        this.adjectedCenter=adjectedCenter;
        this.refreshingTime = refreshingTime;
        this.position = position;
        this.size = size;
        hpPercent=1;
        formerHpPercent=1;
    }
    public void updatePosition(Vector2 center){
        position.add(center.sub(adjectedCenter));
    }
    public void draw(ShapeRenderer SR,float delta){
        if (hpPercent<1) {
            SR.setColor(1,1,1,0.1f);
            SR.rect(position.x,position.y,size.x,size.y);
            if(counter>0) {
                counter-=delta;
                SR.setColor(Color.FIREBRICK);
                SR.rect(position.x + size.x * 0.05f, position.y + size.y * 0.05f, size.x * 0.9f * formerHpPercent, size.y * 0.9f);
            }
            else{
                formerHpPercent=hpPercent;
            }
            SR.setColor(Color.RED);
            SR.rect(position.x+size.x*0.05f,position.y+size.y*0.05f,size.x*0.9f*hpPercent,size.y*0.9f);
        }
    }
    public void setHpPercent(float newHpPercent){
        hpPercent=newHpPercent;
        counter=refreshingTime;
    }
}
