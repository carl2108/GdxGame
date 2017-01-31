package com.mygdx.mygdxgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	Sprite s;

	int height, width;


	//Board board = new Board();


	/* init */
	@Override
	public void create () {

		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();

		Log("Height: " + height + "   Width: " + width);

		initialiseGame(height, width);


		batch = new SpriteBatch();
		img = new Texture("numbers/2.png");
		s = new Sprite(img);

		Gdx.input.setInputProcessor(this);
	}

	/* loop function */
	@Override
	public void render () {


		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(s, width/2, height/2, width/4, height/5);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
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
		Log("touchDown   x: " + screenX + " y: " + screenY);

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Log("touchUp");
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Log("touchDragged   x: " + screenX + " y: " + screenY + " pointer: " + pointer);
		if()
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

		Board board = new Board(width, height);


		//int i = (int) Math.random() * 15;
		Tile t = new Tile(1, "numbers/1.png");

	}

}
