package com.mygdx.mygdxgame;

/**
 * Created by carloconnor on 19/01/17.
 */

public class Square {

    private int x, y, px, py;
    private Tile tile;

    public Square(int x, int y, int px, int py) {
        this.x = x;
        this.y = y;
        this.px = px;
        this.py = py;

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
}
