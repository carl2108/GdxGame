package com.mygdx.mygdxgame;

/**
 * Created by carloconnor on 19/01/17.
 */

public class Board {

    private Square[][] board;
    private int squareHeight, squareWidth;

    public Board(int width) {
        //squareHeight = height/5;
        squareWidth = width/4;

        board = new Square[4][4];

        for(int j=0; j<4; j++) {
            for(int i=0; i<4; i++) {
                board[i][j] = new Square(i, j, i*squareWidth, j*squareWidth);
                Square s = board[i][j];
                int num = j*4 + i + 1;

                if(num == 16)
                    s.setHasTile(false);
                else
                    s.setHasTile(true);

                s.setTile(new Tile(num, "numbers/" + num + ".png"));
                s.getTile().getSprite().setSize(squareWidth, squareWidth);
                s.getTile().getSprite().setPosition(s.getPx(), s.getPy()+squareWidth);          /* fix this */
            }
        }

    }

    public int getSquareHeight() {
        return squareHeight;
    }

    public Square[][] getBoard() {
        return board;
    }

    public int getSquareWidth() {
        return squareWidth;
    }

    public Square getSquareAt(int x, int y) {
        for(int i=0 ; i<4; i++) {
            for(int j=0; j<4; j++) {
                Square s = board[i][j];
                if(s.getPx() <= x && s.getPx()+squareWidth > x && s.getPy() <= y && s.getPy()+squareWidth > y)
                    return s;
            }
        }
        return null;
    }

    public void computeLegalMoves() {
        Log("Computing legal moves");
        Square anchor = null;
        Square currSquare;
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                currSquare = board[i][j];
                currSquare.setHasTile(true);
                if(currSquare.getTile().getNumber() == 16) {
                    anchor = currSquare;
                    anchor.setHasTile(false);
                    Log("Anchor square found. x: " + anchor.getX() + " y: " + anchor.getY());
                }
                currSquare.setCanMoveDown(false);
                currSquare.setCanMoveUp(false);
                currSquare.setCanMoveLeft(false);
                currSquare.setCanMoveRight(false);
            }
        }

        for(int i=anchor.getX()+1; i<4; i++)
            board[i][anchor.getY()].setCanMoveLeft(true);

        for(int i=anchor.getX()-1; i>=0; i--)
            board[i][anchor.getY()].setCanMoveRight(true);

        for(int i=anchor.getY()+1; i<4; i++)
            board[anchor.getX()][i].setCanMoveUp(true);

        for(int i=anchor.getX()-1; i>=0; i--)
            board[anchor.getX()][i].setCanMoveDown(true);

    }

    void Log(String logMessage) {
        System.out.println(logMessage);
    }

}
