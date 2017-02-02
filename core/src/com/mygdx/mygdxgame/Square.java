package com.mygdx.mygdxgame;

import com.badlogic.gdx.Gdx;

/**
 * Created by carloconnor on 19/01/17.
 */

public class Square {
    private int squareWidth = Gdx.graphics.getWidth()/4;
    private int x, y, px, py;
    private Tile tile;
    private boolean canMoveLeft, canMoveRight, canMoveUp, canMoveDown, hasTile;

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
        this.tile.getSprite().setPosition(px, py+squareWidth);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
    }

    public boolean isCanMoveLeft() {
        return canMoveLeft;
    }

    public void setCanMoveLeft(boolean canMoveLeft) {
        this.canMoveLeft = canMoveLeft;
    }

    public boolean isCanMoveRight() {
        return canMoveRight;
    }

    public void setCanMoveRight(boolean canMoveRight) {
        this.canMoveRight = canMoveRight;
    }

    public boolean isCanMoveUp() {
        return canMoveUp;
    }

    public void setCanMoveUp(boolean canMoveUp) {
        this.canMoveUp = canMoveUp;
    }

    public boolean isCanMoveDown() {
        return canMoveDown;
    }

    public void setCanMoveDown(boolean canMoveDown) {
        this.canMoveDown = canMoveDown;
    }

    public boolean isHasTile() {
        return hasTile;
    }

    public void setHasTile(boolean hasTile) {
        this.hasTile = hasTile;
    }
}
