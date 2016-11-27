package com.kendy.game.flappybird.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.kendy.game.flappybird.GameStateManager;

/**
 * Created by kendy on 27/11/16.
 */

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm) {
        // TODO unneeded param, use Singleton for GameStateManager
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();

    /**
     * @param dt delta time
     */
    public abstract void update(float dt);

    /**
     * @param sb all that need to be rendered on screen
     */
    public abstract void render(SpriteBatch sb);

    /**
     * TODO ressemble à une bêtise, utiliser le design pattern Singleton pour instancier une unique fois les objets, et ne pas avoir à allouer/dispose constamment
     */
    public abstract void dispose();
}
