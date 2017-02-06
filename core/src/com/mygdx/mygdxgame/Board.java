package com.mygdx.mygdxgame;

import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 * Created by carloconnor on 19/01/17.
 */

public class Board {

    private Square[][] board;
    private int squareHeight, squareWidth;
    private Stack<Integer> stack;

    public Board(int width) {
        //squareHeight = height/5;
        squareWidth = width/4;

        board = new Square[4][4];

        stack = initStack();
        stack = randomiseStack(stack);
        makeBoard(squareWidth, stack);
        while(!isSolvable(stack)) {
            stack = randomiseStack(stack);
            makeBoard(squareWidth, stack);
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
        //Log("Computing legal moves");
        Square anchor = null;
        Square currSquare;
        for(int j=0; j<4; j++) {
            for(int i=0; i<4; i++) {
                currSquare = board[i][j];
                currSquare.setHasTile(true);
                if(currSquare.getTile().getNumber() == 16) {
                    anchor = currSquare;
                    anchor.setHasTile(false);
                    //Log("Anchor square found. x: " + anchor.getX() + " y: " + anchor.getY());
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

        for(int i=anchor.getY()-1; i>=0; i--)
            board[anchor.getX()][i].setCanMoveDown(true);

    }

    void Log(String logMessage) {
        System.out.println(logMessage);
    }

    private void makeBoard(int width, Stack<Integer> stack) {
        for(int j=0; j<4; j++) {
            for(int i=0; i<4; i++) {
                board[i][j] = new Square(i, j, i*squareWidth, j*squareWidth);
                Square s = board[i][j];
                int index = j*4 + i;
                int num = stack.get(index);

                if(num == 16)
                    s.setHasTile(false);
                else
                    s.setHasTile(true);

                s.setTile(new Tile(num, "numbers/" + num + ".png"));
                s.getTile().getSprite().setSize(squareWidth, squareWidth);
                s.getTile().getSprite().setPosition(s.getPx(), s.getPy()+squareWidth);          /* fix this - caused by inverting using height - y, should be (height - squareWidth) - y*/
            }
        }
    }

    private Stack<Integer> randomiseStack(Stack<Integer> stackIn) {
        Stack<Integer> stackOut = new Stack<Integer>();
        Random rand;
        int r, i;
        while(!stackIn.isEmpty()) {
            rand = new Random();
            r = rand.nextInt(stackIn.size());
            i = stackIn.get(r);
            stackIn.removeElementAt(r);
            stackOut.push(i);
        }
        return stackOut;
    }

    private Stack<Integer> initStack() {
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=1; i<=16; i++)
            stack.push(i);
        return stack;
    }

    private int countInversions(Stack<Integer> stack) {
        int count = 0;
        int curr;
        int x = 0;
        Iterator<Integer> iterator = stack.iterator();
        while(iterator.hasNext()) {
            x++;
            curr = iterator.next();
            for(int i=x; i<stack.size(); i++) {
                if(curr > stack.get(i) && curr != 16 && stack.get(i) != 16)
                    count++;
            }
        }
        return count;
    }

    private int getAnchorRow() {
        for(int j=0; j<4; j++) {
            for(int i=0; i<4; i++) {
               Square curr = board[i][j];
                if(curr.getTile().getNumber() == 16) {
                    return j+1;
                }
            }
        }
        Log("Error finding anchor row");
        return 0;
    }

    private boolean isEven(int i) {
        return i%2 == 0 ? true : false;
    }

    private boolean isSolvable(Stack<Integer> s) {      /* extend for odd number grid width */
        int inversions = countInversions(s);
        int anchorRow = getAnchorRow();
        return (isEven(4 - anchorRow+1) && !isEven(inversions) ||
                !isEven(4 - anchorRow+1) && isEven(inversions)) ? true : false;
    }

}
