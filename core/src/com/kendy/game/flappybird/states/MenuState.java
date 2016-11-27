package com.kendy.game.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kendy.game.flappybird.Constants;
import com.kendy.game.flappybird.GameStateManager;

/**
 * Created by kendy on 27/11/16.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            /*
                on a touché l'écran, on passe à l'état suivant : le jeu!
                et on libère les ressources
             */
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    /**
     * @param dt delta time
     */
    @Override
    public void update(float dt) {
        // always check input to know if user do anything
        handleInput();
    }

    /**
     * @param sb all that need to be rendered on screen
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        // draw the background to fullsize on the screen
        sb.draw(background, 0, 0, Constants.WIDTH, Constants.HEIGHT);

        sb.draw(playBtn, Constants.WIDTH/2 - playBtn.getWidth()/2, Constants.HEIGHT/2 - playBtn.getHeight()/2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
