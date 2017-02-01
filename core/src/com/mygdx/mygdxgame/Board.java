package com.mygdx.mygdxgame;

/**
 * Created by carloconnor on 19/01/17.
 */

public class Board {

    private Square[][] board;
    private int squareHeight, squareWidth;

    public Board(int width, int height) {
        squareHeight = height/5;
        squareWidth = width/4;

        board = new Square[4][4];

        for(int j=0; j<4; j++) {
            for(int i=0; i<4; i++) {
                board[i][j] = new Square(i, j, i*squareWidth, (height - (j+1)*squareHeight), j*4 + i + 1, squareWidth, squareHeight);
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

    public int getSquareWidth() {
        return squareWidth;
    }

    public void setSquareWidth(int squarewidth) {
        this.squareWidth = squarewidth;
    }

    public void drawBoard() {

    }

    public Square getSquareAt(int x, int y) {
        for(int i=0 ; i<4; i++) {
            for(int j=0; j<4; j++) {
                Square s = board[i][j];
                if(s.getSprite().getX() <= x && s.getSprite().getX()+squareWidth > x && s.getSprite().getY() <= y + squareHeight && s.getSprite().getY()+squareHeight > y)
                    return s;
            }
        }
        return null;
    }

}
