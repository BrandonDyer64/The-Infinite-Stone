package ml.dpgames.infinite.stone.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class Graphics {
	
	public static void clear(float r, float g, float b) {
		Gdx.gl.glClearColor(r, g, b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public static void clear(int hexcode) {
		Color c = new Color(hexcode);
		clear(c.r, c.g, c.b);
	}
}
