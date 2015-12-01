package ml.dpgames.infinite.stone.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Input {

	public static float getX(Camera camera) {
		return camera.position.x + (Gdx.input.getX() - Gdx.graphics.getWidth() / 2) * (camera.viewportWidth / Gdx.graphics.getWidth());
	}

	public static float getY(Camera camera) {
		return camera.position.y + (Gdx.graphics.getHeight() - Gdx.input.getY() - Gdx.graphics.getHeight() / 2)
				* (camera.viewportHeight / Gdx.graphics.getHeight());
	}

	public static int touchGlyph(GlyphLayout layout, Vector2 position, Camera camera) {
		int pointer = 0;
		Rectangle rect = new Rectangle(position.x, position.y - layout.height, layout.width, layout.height);
		Vector2 in = new Vector2(getX(camera), getY(camera));
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