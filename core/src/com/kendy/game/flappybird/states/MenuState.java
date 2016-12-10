package com.kendy.game.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kendy.game.flappybird.Constants;

/**
 * Created by kendy on 27/11/16.
 */

public class MenuState extends State {
    private static final Texture background = new Texture("bg.png");
    private static final Texture playBtn = new Texture("playBtn.png");

    private static final OrthographicCamera cam = new OrthographicCamera();
    private static final MenuState instance = new MenuState();

    public static MenuState getInstance() {
        return instance;
    }

    private MenuState() {
        super();
        cam.setToOrtho(false, Constants.WIDTH / 2, Constants.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            /*
             * on a touché l'écran, on passe à l'état suivant : le jeu!
             */
            gsm.set(PlayState.getInstance(true));
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
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        // draw the background to fullsize on the screen
        sb.draw(background, 0, 0, Constants.WIDTH, Constants.HEIGHT);

        // TODO doesn't seem to be centered
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        System.out.println("MenuState, dispose!");
        //background.dispose();
        //playBtn.dispose();
    }
}
