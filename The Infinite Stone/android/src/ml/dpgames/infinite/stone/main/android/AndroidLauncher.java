package ml.dpgames.infinite.stone.main.android;

import ml.dpgames.infinite.stone.main.Handler;
import ml.dpgames.infinite.stone.main.IStoneMain;
import android.content.pm.PackageManager.NameNotFoundException;
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
	private static String TAG = "AndroidLauncher";
	protected AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout layout = new RelativeLayout(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		try {
			String app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			String app_ver = "Failed to load version name";
		}
		View gameView = initializeForView(new IStoneMain(new Handler(){
			@Override
			public void achievement(String name) {
				// TODO: Call to Google for achievement
			}
		}), config);
		layout.addView(gameView);

		adView = new AdView(this);
		// Listen for ads and print when they appear
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				Log.i(TAG, "Ad Loaded!");
			}
		});

		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-1192993843080120/4947427094");

		AdRequest.Builder builder = new AdRequest.Builder();
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		layout.addView(adView, adParams);
		adView.loadAd(builder.build());

		setContentView(layout);

	}
}
