package com.kendy.game.flappybird;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication {
	private static final String TAG = AndroidLauncher.class.getName();
    private static final String ADUNITID_MENU_BOTTOM_BANNER = "ca-app-pub-6717582752353185/7093837956";
    private static final String TESTDEVICE_LGG3 = "73DE83A773DEA703F9EBA40BE89A1E35";

	protected AdView adView; // view containing ad

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // on cree un layout, on y ajoute 2 vues (celle du jeu et celle de la pub
        RelativeLayout layout = new RelativeLayout(this);

        // vue du jeu
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        View gameView = initializeForView(new FlappyBird(), config);
        layout.addView(gameView);

        // vue de la pub
        adView = new AdView(this);
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				Log.i(TAG, "Ad loaded...");
			}
		});

        // width of the device and about 100px height
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(ADUNITID_MENU_BOTTOM_BANNER);

        // what is going to request the ad from google
        AdRequest.Builder builder = new AdRequest.Builder();
        builder.addTestDevice(TESTDEVICE_LGG3);
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        layout.addView(adView, adParams);
        adView.loadAd(builder.build());

        setContentView(layout);


	}
}
