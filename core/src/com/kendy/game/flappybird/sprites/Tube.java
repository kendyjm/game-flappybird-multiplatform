package com.kendy.game.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by kendy on 27/11/16.
 */

public class Tube {
    // this is the real WIDTH of toptube.png and bottomtube.png
    public static final int TUBE_WIDTH = 52;

    // height variation of the tube
    private static final int FLUCTUATION = 130;

    // minimal distance between 2 tubes openings
    private static final int TUBE_GAP = 100;

    // minimal distance between top tube opening and ground
    private static final int LOWEST_OPENING = 120;

    // TODO make static for performance sake
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;

    // 2D rectangle defining the bounds of the tubes, used for collision detection
    private Rectangle boundsTop, boundsBottom;


    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();
        posTopTube = new Vector2();
        posBotTube = new Vector2();

        // bounds
        boundsTop = new Rectangle();
        boundsBottom = new Rectangle();

        reposition(x);
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Texture getTopTube() {
        return topTube;
    }

    /**
     * place the tube at init
     * and reposition it when the tubes leave the screen
     *
     * @param x
     */
    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        // bounds
        boundsTop.set(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom.set(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    /**
     * Check if the bird collides with on the obstacles (tubes, their bounds exactly)
     *
     * @param playerBird, rectangle bounding the bird
     * @return
     */
    public boolean collides(Rectangle playerBird) {
        return playerBird.overlaps(boundsTop) || playerBird.overlaps(boundsBottom);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
