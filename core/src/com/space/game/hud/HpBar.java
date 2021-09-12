package com.space.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.space.game.playerClasses.Player;
import com.space.game.utils.ExtendedShapeRenderer;

public class HpBar {
    float playerHp;//in percent
    float formerPlayerHp;//in percent
    private Player player;
    Vector2 size,position;//in percent of the Screen Width/Height

    public HpBar(Player player) {
        this.player = player;
        size=new Vector2(0.7f,0.05f);
        position=new Vector2(-size.x/2f,-0.5f);
        formerPlayerHp=0;
        updateHpValues();
    }
    public void draw(ExtendedShapeRenderer extendedShapeRenderer){
        updateHpValues();

        extendedShapeRenderer.setColor(Color.WHITE);
        extendedShapeRenderer.rect(Gdx.graphics.getWidth()*position.x, Gdx.graphics.getHeight()*position.y, Gdx.graphics.getWidth()*size.x, Gdx.graphics.getHeight()*size.y);

        extendedShapeRenderer.setColor(Color.BLACK);
        extendedShapeRenderer.rect(Gdx.graphics.getWidth()*position.x +Gdx.graphics.getWidth()*size.x*0.01f, Gdx.graphics.getHeight()*position.y+Gdx.graphics.getHeight()*size.y*0.01f, Gdx.graphics.getWidth()*size.x*0.98f, Gdx.graphics.getHeight()*size.y*0.98f);

        extendedShapeRenderer.setColor(1,0,0,0.8f);
        extendedShapeRenderer.rect(Gdx.graphics.getWidth()*position.x +Gdx.graphics.getWidth()*size.x*0.01f, Gdx.graphics.getHeight()*position.y+Gdx.graphics.getHeight()*size.y*0.01f, Gdx.graphics.getWidth()*size.x*0.98f*formerPlayerHp, Gdx.graphics.getHeight()*size.y*0.98f);

        extendedShapeRenderer.setColor(1,0,0,1);
        extendedShapeRenderer.rect(Gdx.graphics.getWidth()*position.x +Gdx.graphics.getWidth()*size.x*0.01f, Gdx.graphics.getHeight()*position.y+Gdx.graphics.getHeight()*size.y*0.01f, Gdx.graphics.getWidth()*size.x*0.98f*playerHp, Gdx.graphics.getHeight()*size.y*0.98f);

    }
    public void updateHpValues(){
        formerPlayerHp=playerHp;
        playerHp=player.getHpPercent();
        if(playerHp<0){
            playerHp=0;
        }
    }
}
