package ml.dpgames.infinite.stone.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Graphics {

	public static final Texture stoneBackground = new Texture("stone_background.png");
	public static final Texture vignette = new Texture("vignette.png");

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

	public static final int draw(Batch batch, TextureRegion tex, float x, float y, float width, float height, Camera camera) {
		batch.draw(tex, x, y, width, height);
		int pointer = 0;
		Rectangle rect = new Rectangle(x, y, width, height);
		Vector2 in = new Vector2(Input.getX(camera), Input.getY(camera));
		// pointer = 0
		if (rect.contains(in)) {
			pointer++; // pointer = 1
			if (Gdx.input.isTouched()) {
				pointer++; // pointer = 2
				if (Gdx.input.justTouched()) {
					pointer++; // pointer = 3
				}
			}
		}
		return pointer;
	}
}
