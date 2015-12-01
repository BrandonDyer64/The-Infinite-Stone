package ml.dpgames.infinite.stone.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ml.dpgames.infinite.stone.main.IStoneMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "The Infinite Stone";
		new LwjglApplication(new IStoneMain(), config);
	}
}
