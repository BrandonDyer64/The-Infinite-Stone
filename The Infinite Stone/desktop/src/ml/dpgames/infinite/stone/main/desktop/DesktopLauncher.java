package ml.dpgames.infinite.stone.main.desktop;

import javax.swing.JDialog;

import ml.dpgames.infinite.stone.main.Handler;
import ml.dpgames.infinite.stone.main.IStoneMain;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "The Infinite Stone";
		config.vSyncEnabled = true;
		new LwjglApplication(new IStoneMain(new Handler(){
			@Override
			public void achievement(String name) {
				Achievement dialog = new Achievement(name);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		}), config);
	}
}
