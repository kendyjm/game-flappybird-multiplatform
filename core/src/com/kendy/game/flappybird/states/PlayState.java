package com.kendy.game.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.kendy.game.flappybird.Constants;
import com.kendy.game.flappybird.GameStateManager;
import com.kendy.game.flappybird.sprites.Bird;
import com.kendy.game.flappybird.sprites.Tube;

/**
 * Created by kendy on 27/11/16.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;

    private Array<Tube> tubes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, Constants.WIDTH/2, Constants.HEIGHT/2);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();

        for (int i = 0; i < TUBE_COUNT; i++) {
            Tube tube = new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH));
            tubes.add(tube);
        }
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

        // follow our bird
        cam.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes) {
            // tube is get out the cam position
            if (cam.position.x - cam.viewportWidth / 2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }
        }

        // needed to tell libgdx the cam position has changed
        cam.update();
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
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.end();
    }

    /**
     * TODO ressemble à une bêtise, utiliser le design pattern Singleton pour instancier une unique fois les objets, et ne pas avoir à allouer/dispose constamment
     */
    @Override
    public void dispose() {

    }
}
