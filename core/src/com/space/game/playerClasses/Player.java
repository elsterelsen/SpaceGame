package com.space.game.playerClasses;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Rectangle {
    private Sprite img;
    private Sprite minimapImg;

    public Player() {
        this.img = new Sprite();
        this.minimapImg = new Sprite();

    }

}
