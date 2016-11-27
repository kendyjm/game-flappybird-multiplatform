package com.kendy.game.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kendy.game.flappybird.Constants;
import com.kendy.game.flappybird.GameStateManager;
import com.kendy.game.flappybird.sprites.Bird;
import com.kendy.game.flappybird.sprites.Tube;

/**
 * Created by kendy on 27/11/16.
 */

public class PlayState extends State {
    private Bird bird;
    private Texture bg;
    private Tube tube;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, Constants.WIDTH/2, Constants.HEIGHT/2);
        bg = new Texture("bg.png");
        tube = new Tube(100);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            bird.jump();
        }
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
        sb.draw(bg, cam.position.x - cam.viewportWidth/2, 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
        sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        sb.end();
    }

    /**
     * TODO ressemble à une bêtise, utiliser le design pattern Singleton pour instancier une unique fois les objets, et ne pas avoir à allouer/dispose constamment
     */
    @Override
    public void dispose() {

    }
}
