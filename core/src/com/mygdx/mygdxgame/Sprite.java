package com.mygdx.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by carloconnor on 01/02/17.
 */

public class Sprite extends com.badlogic.gdx.graphics.g2d.Sprite{
    float screenHeight = Gdx.graphics.getHeight();

    public Sprite() {
        super();
    }

    public Sprite(Texture texture) {
        super(texture);
    }

    public float invert(float y) {
        return screenHeight - y;
    }

    public float getY () {
        return invert(super.getY());
    }

    public void setPosition (float x, float y) {
        super.setPosition(x, invert(y));
    }

    public void setY (float y) {
        super.setY(invert(y));
    }
}
