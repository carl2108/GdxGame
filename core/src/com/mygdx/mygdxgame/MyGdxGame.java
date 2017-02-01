package com.mygdx.mygdxgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	private final static int MAX_SPRITES_PER_BATCH = 16;
	private final static int DRAG_SENSITIVITY = 10;
	private final static int MOVE_SENSITIVITY = 40;

	SpriteBatch batch;
	Square activeSquare;

	int height, width, touchDownX, touchDownY;
	char dragOrientation;
	Board board;

	/* init */
	@Override
	public void create () {
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		Log("Height: " + height + "   Width: " + width);

		initialiseGame(width, height);

		batch = new SpriteBatch(MAX_SPRITES_PER_BATCH);


		Gdx.graphics.setContinuousRendering(true);
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
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				board.getBoard()[i][j].getSprite().draw(batch);
			}
		}
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

		touchDownX = screenX;
		touchDownY = screenY;
		activeSquare = board.getSquareAt(screenX, screenY);
		Log("touch square: " + activeSquare.getNumber());

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Log("touchUp   x: " + screenX + " y: " + screenY + " pointer: " + pointer);
		if(pointer > 0)
			return true;

		if(dragOrientation == 'h') {
			if(Math.abs(touchDownX - screenX) > MOVE_SENSITIVITY) {
				activeSquare.getSprite().setX(screenX);
				Gdx.graphics.requestRendering();
			} else {
				activeSquare.getSprite().setPosition(activeSquare.getPx(), activeSquare.getPy());
				Gdx.graphics.requestRendering();
			}
		} else if(dragOrientation == 'v') {
			if(Math.abs(touchDownY - screenY) > MOVE_SENSITIVITY) {
				activeSquare.getSprite().setY(screenY);
				Gdx.graphics.requestRendering();
			} else {
				activeSquare.getSprite().setPosition(activeSquare.getPx(), activeSquare.getPy());
				Gdx.graphics.requestRendering();
			}
		} else {
			activeSquare.getSprite().setPosition(activeSquare.getPx(), activeSquare.getPy());
			Gdx.graphics.requestRendering();
		}

		/* update active square px, py origin coordinates */

		touchDownX = -999;
		touchDownY = -999;
		activeSquare = null;

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Log("touchDragged   x: " + screenX + " y: " + screenY + " pointer: " + pointer);
		if(pointer > 0)																				/* disable multitouch */
			return true;

		if(dragOrientation == 'h') {																/* horizontal drag */
			activeSquare.getSprite().setX(screenX);
			Gdx.graphics.requestRendering();
		} else if(dragOrientation == 'v') {															/* vertical drag */
				activeSquare.getSprite().setY(screenY);
				Gdx.graphics.requestRendering();
		} else {																					/* no drag registered yet */
				if (Math.abs(touchDownX - screenX) > DRAG_SENSITIVITY)
					dragOrientation = 'h';
				else if (Math.abs(touchDownY - screenY) > DRAG_SENSITIVITY)
					dragOrientation = 'v';
				else
					dragOrientation = 'x';
			}
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

	void initialiseGame(int width, int height) {

		board = new Board(width, height);

		//int i = (int) Math.random() * 15;
		Tile t = new Tile(1, "numbers/1.png");

	}

}
