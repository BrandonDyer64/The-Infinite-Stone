package ml.dpgames.infinite.stone.main;

import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;
import java.util.Properties;

import com.badlogic.gdx.Gdx;

public class Lang {

	public static final Properties prop = parsePropertiesString();

	private static final Properties parsePropertiesString() {
		String s = "";
		try {
			s = Gdx.files.internal("lang/" + IStoneMain.handler.getLanguage() + ".txt").readString();
		} catch (Exception e) {
			System.out.println("FAILED TO LOAD LANGUAGE!");
			try {
				s = Gdx.files.internal("lang/" + "en" + ".txt").readString();
			} catch (Exception e1) {
				e1.printStackTrace();
				System.exit(1);
			}
		}
		// grr at load() returning void rather than the Properties object
		// so this takes 3 lines instead of "return new Properties().load(...);"
		System.out.println("Loading language file: " + IStoneMain.handler.getLanguage() + ".txt");
		final Properties p = new Properties();
		try {
			p.load(new StringReader(s));
		} catch (IOException e) {

		}
		return p;
	}
	
	public static final String getHelp(int screen) {
		return prop.getProperty("help" + screen).replaceAll("\\n", "\n");
	}
}
