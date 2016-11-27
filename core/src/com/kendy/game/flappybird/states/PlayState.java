package com.kendy.game.flappybird.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kendy.game.flappybird.Constants;
import com.kendy.game.flappybird.GameStateManager;

/**
 * Created by kendy on 27/11/16.
 */

public class PlayState extends State {
    private Texture bird;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
        cam.setToOrtho(false, Constants.WIDTH/2, Constants.HEIGHT/2);
    }

    @Override
    protected void handleInput() {

    }

    /**
     * @param dt delta time
     */
    @Override
    public void update(float dt) {

    }

    /**
     * @param sb all that need to be rendered on screen
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bird, 50, 50);
        sb.end();
    }

    /**
     * TODO ressemble à une bêtise, utiliser le design pattern Singleton pour instancier une unique fois les objets, et ne pas avoir à allouer/dispose constamment
     */
    @Override
    public void dispose() {

    }
}
