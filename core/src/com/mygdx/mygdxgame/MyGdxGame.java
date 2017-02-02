package com.mygdx.mygdxgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	private final static int MAX_SPRITES_PER_BATCH = 16;
	private final static int DRAG_SENSITIVITY = 10;
	private final static int MOVE_SENSITIVITY = 40;

	SpriteBatch batch;
	Square activeSquare;

	int height, width, touchDownX, touchDownY;
	Board board;
	char move;

	/* init */
	@Override
	public void create () {
		//height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		Log("Width: " + width);

		initialiseGame(width);

		batch = new SpriteBatch(MAX_SPRITES_PER_BATCH);

		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {

	}

	/* loop function */
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		drawBoard();
		batch.end();
	}


	/*** game inputs ***/
	@Override
	public boolean keyDown(int keycode) {
		Log("keyDown");
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Log("keyUp");
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		Log("keyTyped");
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Log("touchDown   x: " + screenX + " y: " + screenY + " pointer: " + pointer);
		if(pointer > 0)
			return true;

		move = 'x';
		touchDownX = screenX;
		touchDownY = screenY;
		activeSquare = board.getSquareAt(screenX, screenY);
		Log("activeSquare: " + activeSquare.getTile().getNumber());

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Log("touchUp   x: " + screenX + " y: " + screenY + " pointer: " + pointer);
		if(pointer > 0)
			return true;

		if(touchDownX - screenX > MOVE_SENSITIVITY)
			move = 'l';
		else if (screenX - touchDownX > MOVE_SENSITIVITY)
			move = 'r';
		else if (touchDownY - screenY > MOVE_SENSITIVITY)
			move = 'u';
		else if (screenY - touchDownY > MOVE_SENSITIVITY)
			move = 'd';
		else move = 'x';

		processMove(activeSquare, move);

		touchDownX = -999;
		touchDownY = -999;
		activeSquare = null;

		//board.computeLegalMoves();

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		//Log("touchDragged   x: " + screenX + " y: " + screenY + " pointer: " + pointer);
		if(pointer > 0)																				/* disable multitouch */
			return true;

		/*if(dragOrientation == 'h') {																*//* horizontal drag *//*
			activeSquare.getTile().getSprite().setX(screenX);
			Gdx.graphics.requestRendering();
		} else if(dragOrientation == 'v') {															*//* vertical drag *//*
				activeSquare.getTile().getSprite().setY(screenY);
				Gdx.graphics.requestRendering();
		} else {																					*//* no drag registered yet *//*
				if (Math.abs(touchDownX - screenX) > DRAG_SENSITIVITY)
					dragOrientation = 'h';
				else if (Math.abs(touchDownY - screenY) > DRAG_SENSITIVITY)
					dragOrientation = 'v';
				else
					dragOrientation = 'x';
			}*/
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Log("mouseMoved");
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		Log("scrolled");
		return false;
	}

	void Log(String logMessage) {
		System.out.println(logMessage);
	}

	void initialiseGame(int width) {
		board = new Board(width);
		board.computeLegalMoves();

	}

	public void drawBoard() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				board.getBoard()[i][j].getTile().getSprite().draw(batch);
			}
		}
	}

	public void processMove(Square activeSquare, char move) {
		Stack<Square> tilesToMove  = new Stack<Square>();
		int i;
		switch(move) {
			case 'l':
				if(!activeSquare.isCanMoveLeft()) {
					Log("Cannot move left");
					return;
				}

				i = activeSquare.getX();
				while(i>=0 && board.getBoard()[i][activeSquare.getY()].isHasTile()) {
					tilesToMove.push(board.getBoard()[i][activeSquare.getY()]);
					i--;
				}
				board.getBoard()[activeSquare.getX()][activeSquare.getY()].setTile(board.getBoard()[i][activeSquare.getY()].getTile());
				break;
			case 'r':
				if(!activeSquare.isCanMoveRight()) {
					Log("Cannot move right");
					return;
				}

				i = activeSquare.getX();
				while(i<4 && board.getBoard()[i][activeSquare.getY()].isHasTile()) {
					tilesToMove.push(board.getBoard()[i][activeSquare.getY()]);
					i++;
				}
				board.getBoard()[activeSquare.getX()][activeSquare.getY()].setTile(board.getBoard()[i][activeSquare.getY()].getTile());
				break;
			case 'd':
				if(!activeSquare.isCanMoveDown()) {
					Log("Cannot move down");
					return;
				}

				i = activeSquare.getY();
				while(i<4 && board.getBoard()[activeSquare.getX()][i].isHasTile()) {
					tilesToMove.push(board.getBoard()[activeSquare.getX()][i]);
					i++;
				}
				board.getBoard()[activeSquare.getX()][activeSquare.getY()].setTile(board.getBoard()[i][activeSquare.getY()].getTile());
				break;
			case 'u':
				if(!activeSquare.isCanMoveUp()) {
					Log("Cannot move up");
					return;
				}

				i = activeSquare.getY();
				while(i>=0 && board.getBoard()[activeSquare.getX()][i].isHasTile()) {
					tilesToMove.push(board.getBoard()[activeSquare.getX()][i]);
					i--;
				}
				board.getBoard()[activeSquare.getX()][activeSquare.getY()].setTile(board.getBoard()[i][activeSquare.getY()].getTile());
				break;
			default:
				Log("No move to process");
				return;
		}
		moveSquares(tilesToMove, move);
		Gdx.graphics.requestRendering();

		board.computeLegalMoves();
	}

	public void moveSquares(Stack<Square> tilesToMove, char move) {
		for(Square s : tilesToMove)
			moveSquare(s, move);

	}

	public void moveSquare(Square square, char move) {
		switch(move) {
			case 'u':
				board.getBoard()[square.getX()][square.getY()-1].setTile(square.getTile());
				break;
			case 'd':
				board.getBoard()[square.getX()][square.getY()+1].setTile(square.getTile());
				break;
			case 'l':
				board.getBoard()[square.getX()-1][square.getY()].setTile(square.getTile());
				break;
			case 'r':
				board.getBoard()[square.getX()+1][square.getY()].setTile(square.getTile());
				break;
			default:
				Log("No move");
				return;
		}
	}

	public boolean checkGameWin() {

		 return false;
	}


}