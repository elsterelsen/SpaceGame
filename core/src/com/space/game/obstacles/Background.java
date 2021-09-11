package com.space.game.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.space.game.screens.GameScreen;
import java.util.Random;


public class Background {
    private Array<TextureAtlas.AtlasRegion> textureRegionArray;
    //x=x,y=y;z=radius
    private Array<Vector3> stars;
    private Array<Float> starTextureIndexes;
    private Random random;

    public Background (){
        random=new Random();
        textureRegionArray=new TextureAtlas("KennyAssets/Default/star/atlas.atlas").getRegions();
        stars=new Array<>();
        starTextureIndexes= new Array<>();
    }
    public void initialize(int starNumber,float borderX, float borderY,float borderWidth,float borderHeight){
        for(int i = starNumber;i>0;i--){

            if(!secureAddStar(random.nextFloat()*borderWidth+borderX,random.nextFloat()*borderHeight+borderY,(random.nextFloat()+3)* GameScreen.getPixelsPerMeter()))
            {
                i++;
            }
        }
    }
    public boolean secureAddStar(float x, float y,float radiusX2){
        Vector2 newPos=new Vector2(x,y);
        boolean b=false;
        if(!stars.isEmpty()){
            for (Vector3 v:stars){
                if(newPos.dst(new Vector2(v.x,v.y))<radiusX2/2f+v.z){
                    return false;
                }
            }
        }

        stars.add(new Vector3(x,y,radiusX2));
        starTextureIndexes.add(new Float(random.nextInt(4)));
        return true;
    }
    public void draw(SpriteBatch batch) {
        for (int i = 0; i < stars.size; i++) {
            batch.draw(textureRegionArray.get(Math.round(starTextureIndexes.get(i))),stars.get(i).x,stars.get(i).y,stars.get(i).z,stars.get(i).z);
        }
    }
}
