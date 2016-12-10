package com.kendy.game.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.kendy.game.flappybird.Constants;
import com.kendy.game.flappybird.sprites.Bird;
import com.kendy.game.flappybird.sprites.Tube;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kendy on 27/11/16.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private static final OrthographicCamera cam = new OrthographicCamera();

    private static final Bird bird = new Bird(50, 300); // TODO singleton
    private static final Texture background = new Texture("bg.png");
    private static final Texture ground = new Texture("ground.png");
    private static final Vector2 groundPos1 = new Vector2(0, 0);
    private static final Vector2 groundPos2 = new Vector2(0, 0);
    private static final List<Tube> tubes = new ArrayList<Tube>(TUBE_COUNT);

    static {
        for (int i = 0; i < TUBE_COUNT; i++) {
            Tube tube = new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH));
            tubes.add(i, tube);
            System.out.println("PlayState, static, tubes=" + tubes);
        }
    }

    private static final PlayState instance = new PlayState();

    public static PlayState getInstance(boolean reset) {
        if (reset) {
            init();
        }
        return instance;
    }

    private PlayState() {
        super();
        //init();
    }

    private static void init() {
        bird.init(50, 300);

        cam.setToOrtho(false, Constants.WIDTH/2, Constants.HEIGHT/2);
        cam.position.x = bird.getPosition().x + 80;

        groundPos1.set(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2.set(cam.position.x - cam.viewportWidth / 2 + ground.getWidth(), GROUND_Y_OFFSET);

        for (int i = 0; i < TUBE_COUNT; i++) {
            Tube tube = tubes.get(i);
            tube.reposition(i * (TUBE_SPACING + Tube.TUBE_WIDTH));
        }
        System.out.println("PlayState, init, tubes=" + tubes);
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
                //gsm.set(PlayState.getInstance());
                //dispose();
                init();
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
            //gsm.set(PlayState.getInstance());
            //dispose();
            init();
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

    @Override
    public void dispose() {
        System.out.println("PlayState, dispose!");
        //background.dispose();
        //ground.dispose();
        //bird.dispose();
    }
}
