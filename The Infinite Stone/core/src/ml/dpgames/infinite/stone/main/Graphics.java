package ml.dpgames.infinite.stone.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class Graphics {
	
	public static final Texture stoneBackground = new Texture("stone_background.png");

	/**
	 * Clears the screen with the specified color.
	 * 
	 * @param r
	 *            red
	 * @param g
	 *            green
	 * @param b
	 *            blue
	 */
	public static void clear(float r, float g, float b) {
		Gdx.gl.glClearColor(r, g, b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * Clears the screen with the specified color.
	 * 
	 * @param hexcode
	 */
	public static void clear(int hexcode) {
		Color c = new Color(hexcode);
		clear(c.r, c.g, c.b);
	}
}
