package com.mygdx.mygdxgame;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by carloconnor on 19/01/17.
 */

public class Tile {

    public int number;
    public Texture img;

    public Tile(int num, String imgFile) {
        this.number = num;
        this.img = new Texture(imgFile);
    }


    private Texture getImg(char l){
        return new Texture("img//letters//");
    }

}
