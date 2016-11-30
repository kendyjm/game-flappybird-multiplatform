package com.kendy.game.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Set the position of our bird texture on the screen
 * Velocity determines how the bird changes position in time.
 * His velocity.y determines if he is flying up or down and how fast.
 * When you set velocity.y to positive 250, you know he will be flying up.
 * Quickly initially, but as the GRAVITY constant decreases his velocity with each tick by 15,
 * his rising slows down and he eventually starts falling again when his velocity.y gets to negative values.
 * Basically velocity says how much you add to his position with each tick as you can see in the update() method.﻿
 */

public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT_HORIZONTAL = 100;
    private static final int FLY = 250;

    private Vector3 position;
    private Vector3 velocity;

    private Texture bird;

    private Rectangle bounds;

    public Bird(int x, int y) {
        position = new Vector3(x, y , 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
    }

    public void update(float dt) {
        // s'il est tombé plus besoin de gravité...
        if(position.y>0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(dt);
        position.add(MOVEMENT_HORIZONTAL * dt, velocity.y, 0);

        // on ne veut pas qu'il disparaisse, on le met au sol
        if(position.y<0) {
            position.y = 0;
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.y = FLY;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
