package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Scenes.GameDraw;

public class MyGdxGame extends Game {

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new GameDraw(this));

    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {

    }

    public SpriteBatch getBatch(){
        return this.batch;
    }
}
