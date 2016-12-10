package com.kendy.game.flappybird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kendy.game.flappybird.states.State;

import java.util.Stack;

/**
 * Created by kendy on 27/11/16.
 */

public class GameStateManager {
    private final Stack<State> states;

    private static final GameStateManager instance = new GameStateManager();

    private GameStateManager() {
        states = new Stack<State>();
    }

    public static GameStateManager getInstance() {
        return instance;
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    /**
     * pop and instantly push a new state
     * @param state new state to push
     */
    public void set(State state) {
        pop();
        push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
