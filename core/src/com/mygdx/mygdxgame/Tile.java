package com.mygdx.mygdxgame;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by carloconnor on 19/01/17.
 */

public class Tile {
    private int number;
    private Texture texture;
    private Sprite sprite;

    public Tile(int num, String imgFile) {
        this.number = num;
        this.texture = new Texture(imgFile);
        sprite = new Sprite(texture);
    }

    public int getNumber() {
        return number;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
