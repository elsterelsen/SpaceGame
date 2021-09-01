package com.space.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.space.game.playerClasses.Player;
import com.space.game.screens.GameScreen;

import java.awt.*;
import java.util.Random;

public class Meteor extends Entity{
private static TextureAtlas atlas=new TextureAtlas("KennyAssets/Default/meteor/atlas.atlas");
    public Meteor(float x,float y) {
        super();
        Random random=new Random();
        setRegion(atlas.getRegions().get(random.nextInt(atlas.getRegions().size-1)));
        float radius=(random.nextFloat()*30+10)* GameScreen.getPixelsPerMeter()/2f;
        setSize(radius,radius);

        getHitbox().setRadius(radius/4f);
        maxHp=getHitbox().radius/GameScreen.getPixelsPerMeter()*100;
        hp=maxHp;

        setCenter(x,y);
        getHitbox().setPosition(getCenter().x,getCenter().y);
        innitializeHpBar();
    }

    @Override
    public void hitEffect(Player player) {
            Vector2 newCenterPosition=getCenter().add(
                    player.getCenter().sub(getCenter())
                            .nor().scl(player.getHitbox().radius+getHitbox().radius));
            player.setCenter(newCenterPosition.x,newCenterPosition.y);

        damage(player.crashDamage());

    }
}
