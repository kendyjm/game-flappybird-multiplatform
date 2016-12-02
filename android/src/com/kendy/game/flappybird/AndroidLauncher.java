package com.kendy.game.flappybird;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication {
	private static final String TAG = AndroidLauncher.class.getName();
	protected AdView adView; // view containing ad

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		RelativeLayout layout = new RelativeLayout(this);



		adView = new AdView(this);
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				Log.i(TAG, "Ad loaded...");
			}
		});

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new FlappyBird(), config);
	}
}
