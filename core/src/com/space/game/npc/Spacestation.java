package com.space.game.npc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.space.game.screens.GameScreen;

public class Spacestation extends Sprite {
    public float hp,shield;
    public Circle shieldHitbox;
    public boolean hostile;
    public Spacestation(float x, float y){
        super(new Texture("KennyAssets/Default/spaceStation/station_B.png"));
        setCenter(x,y);
        float size=(float)Math.random()*300f+200f;
        setSize(size* GameScreen.getPixelsPerMeter(),size*GameScreen.getPixelsPerMeter());
        hostile=false;
        hp=size*5;
        shield=size*10;
        shieldHitbox=new Circle();
    }

}
