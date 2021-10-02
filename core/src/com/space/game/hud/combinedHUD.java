package com.space.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.space.game.SpaceGame;
import com.space.game.playerClasses.Player;
import com.space.game.utils.ExtendedShapeRenderer;

public class combinedHUD extends HUD{
    private ExtendedShapeRenderer SR= SpaceGame.getSR();
    private Circle steeringWheel;
    private Circle steeringCircle;
    private Circle shootButton;
    private short steeringPointer;
    public combinedHUD(Player player) {
        super(player);

        steeringWheel=new Circle(Gdx.graphics.getHeight()/5f-Gdx.graphics.getWidth()/2.1f,Gdx.graphics.getHeight()/5f-Gdx.graphics.getHeight()/2.1f,Gdx.graphics.getHeight()/5f);
        shootButton=new Circle(-steeringWheel.x,steeringWheel.y,steeringWheel.radius/4f);
        steeringCircle=new Circle(steeringWheel);
        steeringCircle.setRadius(shootButton.radius);
        steeringPointer=-1;
    }

    @Override
    public void draw() {

        //Draw Background of steeringwheel
        Gdx.gl.glEnable(GL20.GL_BLEND);
        SR.setProjectionMatrix(camera.combined);
        SR.setColor(0.7f,0.7f,0.7f,0.5f);
        SR.begin(ShapeRenderer.ShapeType.Filled);
        SR.circle(steeringWheel.x,steeringWheel.y,steeringWheel.radius);
        SR.circle(shootButton.x,shootButton.y,shootButton.radius);
        SR.setColor(0.9f,0.9f,0.9f,1);
        SR.circle(steeringCircle.x,steeringCircle.y,steeringCircle.radius);
        SR.end();
        super.draw();
    }
    public void controlls(float delta,Player player){
        Vector3 c=camera.unproject(new Vector3(Gdx.input.getX(0),Gdx.input.getY(0),0));
        Vector2 pointer0=new Vector2(c.x,c.y);
        c=camera.unproject(new Vector3(Gdx.input.getX(1),Gdx.input.getY(1),1));
        Vector2 pointer1=new Vector2(c.x,c.y);
        if(steeringPointer!=-1) {
            c = camera.unproject(new Vector3(Gdx.input.getX(steeringPointer), Gdx.input.getY(steeringPointer), 1));
            Vector2 pointer = new Vector2(c.x, c.y);
        }


        if(shootButton.contains(pointer1.x,pointer1.y)&&Gdx.input.isTouched(1)||shootButton.contains(pointer0.x,pointer0.y)&&Gdx.input.isTouched(0)){
            player.shoot(delta);
        }
        player.raiseAttackTimeCounter(delta);



        if(steeringPointer<0){
            if(Gdx.input.isTouched(0)&&steeringWheel.contains(new Vector2(pointer0.x,pointer0.y))) {
                steeringPointer=0;
                c = camera.unproject(new Vector3(Gdx.input.getX(steeringPointer), Gdx.input.getY(steeringPointer), 1));
                Vector2 pointer = new Vector2(c.x, c.y);
                player.setRotation(new Vector2(steeringWheel.x,steeringWheel.y).sub(pointer.x,pointer.y).angleDeg()+90);
            }
            else if(Gdx.input.isTouched(1)&&steeringWheel.contains(new Vector2(pointer1.x,pointer1.y))){
                steeringPointer=1;
                c = camera.unproject(new Vector3(Gdx.input.getX(steeringPointer), Gdx.input.getY(steeringPointer), 1));
                Vector2 pointer = new Vector2(c.x, c.y);
                player.setRotation(new Vector2(steeringWheel.x,steeringWheel.y).sub(pointer.x,pointer.y).angleDeg()+90);
            }
        }
        else if(Gdx.input.isTouched(steeringPointer)){
            c = camera.unproject(new Vector3(Gdx.input.getX(steeringPointer), Gdx.input.getY(steeringPointer), 1));
            Vector2 pointer = new Vector2(c.x, c.y);
            Vector2 direction=new Vector2(steeringWheel.x,steeringWheel.y).sub(pointer.x,pointer.y);
            player.setRotation(direction.angleDeg()+90);
            if(direction.len2()>steeringWheel.radius/0.5f){
                player.addVelocity(delta);
            }
            direction.scl(-1);
            if(direction.len2()>steeringWheel.radius*100){
                direction.nor().scl(steeringWheel.radius);
            }
            steeringCircle.setPosition(direction.add(steeringWheel.x,steeringWheel.y));
        }
        else{
            steeringPointer=-1;
            steeringCircle.setPosition(steeringWheel.x,steeringWheel.y);
        }
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
        steeringWheel=new Circle(Gdx.graphics.getHeight()/5f-Gdx.graphics.getWidth()/2.1f,Gdx.graphics.getHeight()/5f-Gdx.graphics.getHeight()/2.1f,Gdx.graphics.getHeight()/5f);
        shootButton=new Circle(-steeringWheel.x,steeringWheel.y,steeringWheel.radius/4f);
    }
}
