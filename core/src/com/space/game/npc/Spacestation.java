package com.space.game.npc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.space.game.playerClasses.Player;
import com.space.game.screens.GameScreen;

public class Spacestation extends Sprite {
    public float hp,maxHp,shield,maxShield
            ,selfHealing,playerHealing;//inPercent
    public float opacity,minOpacity;
    public Circle shieldHitbox;
    public boolean hostile;
    public Spacestation(float x, float y){
        super(new Texture("KennyAssets/Default/spaceStation/station_B.png"));
        setCenter(x,y);
        float size=(float)Math.random()*300f+200f;
        setSize(size* GameScreen.getPixelsPerMeter(),size*GameScreen.getPixelsPerMeter());
        hostile=false;
        maxHp=size*5;
        hp=maxHp;
        maxShield=size*10;
        shield=maxShield;
        shieldHitbox=new Circle();
        opacity=1;
        minOpacity=0.2f;
    }
    public void heal(float percent){
        if(hp+hp*percent>maxHp){
            shield+=(percent-1-hp/maxHp)*shield;
            hp=maxHp;
            if(shield>maxShield){
                shield=maxShield;
            }
        }
    }

    @Override
    public void draw(Batch batch) {
        draw(batch,opacity);
        opacity+=0.05f;
    }
    public Circle getShieldHitbox(){
        return shieldHitbox;
    }
    public void hoverEffect(Player player){
        if (opacity>minOpacity) {
            opacity-=0.1f;
        }
    }
}
