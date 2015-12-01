package ml.dpgames.infinite.stone.main;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import com.badlogic.gdx.Gdx;

public class Lang {

	public static final Properties prop = parsePropertiesString(Gdx.files.internal("lang/" + System.getProperty("user.language") + ".txt").readString());

	private static final Properties parsePropertiesString(String s) {
		// grr at load() returning void rather than the Properties object
		// so this takes 3 lines instead of "return new Properties().load(...);"
		System.out.println("Loading language file: " + System.getProperty("user.language") + ".txt");
		final Properties p = new Properties();
		try {
			p.load(new StringReader(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
}
