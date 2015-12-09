package ml.dpgames.infinite.stone.main.android;

import ml.dpgames.infinite.stone.main.Handler;
import ml.dpgames.infinite.stone.main.IStoneMain;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.provider.Settings.Secure;
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

	private static String TAG = "InfiniteStone";
	protected AdView adView;

	// public GoogleApiClient apiClient = new
	// GoogleApiClient.Builder(this).addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN).build();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// apiClient.connect(GoogleApiClient.SIGN_IN_MODE_OPTIONAL);

		RelativeLayout layout = new RelativeLayout(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		try {
			String app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			String app_ver = "Failed to load version name";
		}
		View gameView = initializeForView(new IStoneMain(new Handler() {
			@Override
			public void achievement(String name) {
				String id = "";
				switch (name) {
				case "It's not a Boulder. It's a Rock!":
					id = "CgkIj9nCoLUQEAIQBA";
					break;
				case "Getting Stoned":
					id = "CgkIj9nCoLUQEAIQAQ";
					break;
				case "How Ironic":
					id = "CgkIj9nCoLUQEAIQAg";
					break;
				case "Realization":
					id = "CgkIj9nCoLUQEAIQAw";
					break;
				case "We've come far":
					id = "CgkIj9nCoLUQEAIQBQ";
					break;
				}
				// Games.Achievements.unlock(apiClient, id);
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

			@Override
			public void onAdFailedToLoad(int errorCode) {
				Log.i(TAG, "Ad failed!");
			}
		});

		Log.i(TAG, "Device id: " + Secure.getString(getContext().getContentResolver(), Secure.ANDROID_ID));

		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-7345229224654271/4800313744");

		AdRequest.Builder builder = new AdRequest.Builder();
		builder.addTestDevice("637F5950F4CB830A87224FF619278AD4");
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		layout.addView(adView, adParams);
		adView.loadAd(builder.build());

		setContentView(layout);

	}
}
