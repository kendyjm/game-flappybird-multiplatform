package com.kendy.game.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kendy.game.flappybird.GameStateManager;

/**
 * Created by kendy on 27/11/16.
 */

public abstract class State {
    //protected Vector3 mouse;
    final GameStateManager gsm;

    State() {
        this.gsm = GameStateManager.getInstance();
        //mouse = new Vector3();
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
     * TODO appel en quittant le jeu
     */
    public abstract void dispose();
}
