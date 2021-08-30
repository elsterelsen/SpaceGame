package com.space.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class ExtendedShapeRenderer extends ShapeRenderer {


    public ExtendedShapeRenderer() {
        super();
    }
    public void drawBlurredCircle(Color color, Vector2 center,int radius){
        color.mul(1,1,1,0);
        setColor(color);

        float f=1f/radius/2f;

        for(float t=radius/2f;t>0;t--){

            circle(center.x,center.y,t);
            setColor(color.add(0,0,0,f));
        }

    }
    public void drawBlurredLine(Color color, Vector2 v0, Vector2 v1,int thickness){
        color.mul(1,1,1,0);
        setColor(color);

        float f=1f/thickness/2f;

        for(float t=thickness/2f;t>0;t--){

            rectLine(v0,v1,t);
            setColor(color.add(0,0,0,f));
        }

    }
}
