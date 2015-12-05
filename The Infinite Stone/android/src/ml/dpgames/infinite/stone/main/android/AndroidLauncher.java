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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.plus.Plus;

public class AndroidLauncher extends AndroidApplication {
	private static String TAG = "AndroidLauncher";
	protected AdView adView;
	public GoogleApiClient apiClient = new GoogleApiClient.Builder(this).addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN).build();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		apiClient.connect(GoogleApiClient.SIGN_IN_MODE_OPTIONAL);

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
				Games.Achievements.unlock(apiClient, id);
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
