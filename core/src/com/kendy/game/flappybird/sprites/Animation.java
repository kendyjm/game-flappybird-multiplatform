package com.kendy.game.flappybird.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.logging.Logger;

/**
 * Created by kendy on 30/11/16.
 */

class Animation {
    private final static Logger LOGGER = Logger.getLogger(Animation.class.getName());

    private final Array<TextureRegion> frames;
    // how long before we switch to the next frame
    private final float maxFrameTime;
    private float currentFrameTime;
    // number of frames in our animation
    private final int frameCount;
    private int currentFrame;


    /**
     * TODO use a Texture as 1st parameter instead of a region?
     *
     * @param region,     animation containing all frames
     * @param frameCount, number of frames in our region
     * @param cycleTime
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(
                            region, i * frameWidth, 0, frameWidth, region.getRegionHeight()
                    )
            );
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        currentFrame = 0;
    }


    /**
     * update our animation, moving from frame to frame
     *
     * @param dt
     */
    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            currentFrame++;
            currentFrameTime = 0;
        }
        if (currentFrame >= frameCount) {
            currentFrame = 0;
        }
    }

    public TextureRegion getCurrentFrame() {
        return frames.get(currentFrame);
    }

    public void dispose() {
        LOGGER.info("Animation, dispose!");
        // TODO dispose frames
    }
}
