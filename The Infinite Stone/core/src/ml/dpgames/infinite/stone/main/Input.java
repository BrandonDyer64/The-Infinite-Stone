package ml.dpgames.infinite.stone.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Input implements InputProcessor {

	public static boolean wasTouched = false;
	public static Vector2 clickDelta = new Vector2(0, 0);
	public static Vector2 clickLast = new Vector2(0, 0);
	public static int scroll = 0;
	public static int pressSensitivity = 20;

	public static float getX(Camera camera) {
		return camera.position.x + (Gdx.input.getX() - Gdx.graphics.getWidth() / 2) * (camera.viewportWidth / Gdx.graphics.getWidth());
	}

	public static float getY(Camera camera) {
		return camera.position.y + (Gdx.graphics.getHeight() - Gdx.input.getY() - Gdx.graphics.getHeight() / 2)
				* (camera.viewportHeight / Gdx.graphics.getHeight());
	}

	// Broken!!!
	/*public static float getClickDeltaX(Camera camera) {
		return camera.position.x + (clickDelta.x - Gdx.graphics.getWidth() / 2) * (camera.viewportWidth / Gdx.graphics.getWidth()) + 480;
	}
	
	public static float getClickDeltaY(Camera camera) {
		return camera.position.y + (Gdx.graphics.getHeight() - clickDelta.y - Gdx.graphics.getHeight() / 2)
				* (camera.viewportHeight / Gdx.graphics.getHeight()) - 360;
	}*/

	public static float getClickDelta(Camera camera) {
		return (float) Math.sqrt(Math.pow(clickDelta.x, 2) + Math.pow(clickDelta.y, 2));
	}

	public static boolean touchExceded(Camera camera) {
		return getClickDelta(camera) > pressSensitivity * (Gdx.graphics.getHeight() / camera.viewportHeight);
	}

	public static float getDeltaX(Camera camera) {
		return Gdx.input.getDeltaX() * (camera.viewportWidth / Gdx.graphics.getWidth()) * 2;
	}

	public static float getDeltaY(Camera camera) {
		return Gdx.input.getDeltaY() * (camera.viewportHeight / Gdx.graphics.getHeight()) * 2;
	}

	public static Dir getDir(Camera camera) {
		if (Math.abs(getDeltaX(camera)) >= Math.abs(getDeltaY(camera))) {
			return Dir.HORIZONTAL;
		}
		return Dir.VERTICAL;
	}

	public static boolean justReleased() {
		boolean released = wasTouched && !Gdx.input.isTouched();
		return released;
	}

	public static void update() {
		justReleased();
		if (Gdx.input.justTouched()) {
			clickLast = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		}
		if (Gdx.input.isTouched()) {
			clickDelta = new Vector2(Gdx.input.getX() - clickLast.x, Gdx.input.getY() - clickLast.y);
		}
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
			}
			if (Input.justReleased() && !Input.touchExceded(camera)) {
				pointer += 2; // pointer = 3;
				// GameScreen.splodes.add(new
				// Clicksplosion(Input.getX(camera),Input.getY(camera),5));
			}
		}
		return pointer;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		scroll = amount;
		System.out.println("SCROLLED: " + scroll);
		return false;
	}

	public enum Dir {
		HORIZONTAL, VERTICAL
	}
}