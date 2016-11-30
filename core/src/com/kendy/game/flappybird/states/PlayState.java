package com.kendy.game.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
    private static final int GROUND_Y_OFFSET = -50;

    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, Constants.WIDTH/2, Constants.HEIGHT/2);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(cam.position.x - cam.viewportWidth / 2 + ground.getWidth(), GROUND_Y_OFFSET);
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
        updateGround();
        bird.update(dt);

        // follow our bird
        cam.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes) {
            // collides ?
            if (tube.collides(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
                return;
            }

            // tube is get out the cam position
            if (cam.position.x - cam.viewportWidth / 2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }
        }

        // bird is on the ground ?
        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            System.out.println("PlayState, fall on the ground!");
            gsm.set(new PlayState(gsm));
            return;
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
        sb.draw(background, cam.position.x - cam.viewportWidth / 2, 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    /**
     * check if the ground has disappeared, and update its position
     */
    private void updateGround() {
        if (cam.position.x - cam.viewportWidth / 2 > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (cam.position.x - cam.viewportWidth / 2 > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }

    /**
     * TODO ressemble à une bêtise, utiliser le design pattern Singleton pour instancier une unique fois les objets, et ne pas avoir à allouer/dispose constamment
     */
    @Override
    public void dispose() {
        background.dispose();
        ground.dispose();
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("PlayState, dispose!");
    }
}
