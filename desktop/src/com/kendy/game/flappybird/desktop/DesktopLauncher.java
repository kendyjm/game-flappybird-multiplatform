package com.kendy.game.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kendy.game.flappybird.Constants;
import com.kendy.game.flappybird.FlappyBrid;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WIDTH;
		config.height=Constants.HEIGHT;
		config.title=Constants.TITLE;
		new LwjglApplication(new FlappyBrid(), config);
	}
}
