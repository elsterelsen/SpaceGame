package com.space.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.space.game.playerClasses.Player;

public class desktopHUD extends HUD{


    public desktopHUD(Player player) {
        super(player);
    }
    @Override
    public void controlls(float delta,Player player){
        //Movement Input Control
        if(Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.addVelocity(delta);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.addVelocity(-delta);

        }
        //Turning Input Controll
        if(Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.left(delta);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.right(delta);
        }
        //Shoot Input Control
        player.raiseAttackTimeCounter(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            player.shoot(delta);
        }
        player.move(delta);

    }

    @Override
    public void updateViewport(int width, int height) {
        super.updateViewport(width, height);

    }
}
