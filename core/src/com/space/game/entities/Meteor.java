package com.space.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.space.game.screens.GameScreen;

import java.util.Random;

public class Meteor extends Entity{
private static TextureAtlas atlas=new TextureAtlas("KennyAssets/Default/meteor/atlas.atlas");
    public Meteor(float x,float y) {
        super();
        setCenter(x,y);
        Random random=new Random();
        setTexture(atlas.getRegions().get(random.nextInt(atlas.getRegions().size-1)).getTexture());
        getHitbox().setRadius((random.nextFloat()*10+10)* GameScreen.getPixelsPerMeter());
        setSize(getHitbox().radius*2f,getHitbox().radius*2f);
        rotate(random.nextFloat()*360);
        updateHitbox();
    }


}
