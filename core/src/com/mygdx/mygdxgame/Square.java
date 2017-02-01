package com.mygdx.mygdxgame;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by carloconnor on 19/01/17.
 */

public class Square {

    private int x, y, px, py;
    private Tile tile;
    private Texture texture;
    private Sprite sprite;
    private int number;

    public Square(int x, int y, int px, int py, int num, int width, int height) {
        this.x = x;
        this.y = y;
        this.px = px;
        this.py = py;
        this.number = num;

        texture = new Texture("numbers/" + num + ".png");
        sprite = new Sprite(texture);
        sprite.setPosition(this.px, this.py);
        sprite.setSize(width, height);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
