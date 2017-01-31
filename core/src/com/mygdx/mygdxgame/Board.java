package com.mygdx.mygdxgame;

import static com.badlogic.gdx.graphics.g2d.ParticleEmitter.SpawnShape.square;

/**
 * Created by carloconnor on 19/01/17.
 */

public class Board {

    private Square[][] board;
    private int squareHeight, squarewidth;

    public Board(int width, int height) {
        squareHeight = height/5;
        squarewidth = width/4;

        board = new Square[4][4];

        for(int j=0; j<4; j++) {
            for(int i=0; i<4; i++) {
                board[i][j] = new Square(i, j, i*squarewidth, j*squareHeight);
            }
        }

    }

    public int getSquareHeight() {
        return squareHeight;
    }

    public void setSquareHeight(int squareHeight) {
        this.squareHeight = squareHeight;
    }

    public Square[][] getBoard() {
        return board;
    }

    public void setBoard(Square[][] square) {
        this.board = square;
    }

    public int getSquarewidth() {
        return squarewidth;
    }

    public void setSquarewidth(int squarewidth) {
        this.squarewidth = squarewidth;
    }

    public void drawBoard() {

    }

}
