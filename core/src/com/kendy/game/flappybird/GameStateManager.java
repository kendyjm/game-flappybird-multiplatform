package com.kendy.game.flappybird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kendy.game.flappybird.states.State;

import java.util.Stack;

/**
 * Created by kendy on 27/11/16.
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop();
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
