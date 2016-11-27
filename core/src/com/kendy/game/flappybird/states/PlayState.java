package com.kendy.game.flappybird.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kendy.game.flappybird.Constants;
import com.kendy.game.flappybird.GameStateManager;
import com.kendy.game.flappybird.sprites.Bird;

/**
 * Created by kendy on 27/11/16.
 */

public class PlayState extends State {
    private Bird bird;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,100);
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
        handleInput();
        bird.update(dt);
    }

    /**
     * @param sb all that need to be rendered on screen
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        sb.end();
    }

    /**
     * TODO ressemble à une bêtise, utiliser le design pattern Singleton pour instancier une unique fois les objets, et ne pas avoir à allouer/dispose constamment
     */
    @Override
    public void dispose() {

    }
}
