package com.kendy.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kendy.game.flappybird.states.MenuState;

public class FlappyBird extends ApplicationAdapter {

	// one instance during application life
	private SpriteBatch batch;
    private Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        Gdx.gl.glClearColor(1, 0, 0, 1); // red background
		GameStateManager.getInstance().push(MenuState.getInstance());
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		GameStateManager.getInstance().update(Gdx.graphics.getDeltaTime());
		GameStateManager.getInstance().render(batch);
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
        music.dispose();
    }
}
