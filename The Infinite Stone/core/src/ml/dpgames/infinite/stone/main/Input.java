package ml.dpgames.infinite.stone.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

public class Input {

	public static float getX(Camera camera) {
		return camera.position.x + (Gdx.input.getX() - Gdx.graphics.getWidth()/2) * (camera.viewportWidth / Gdx.graphics.getWidth());
	}
	
	public static float getY(Camera camera) {
		return camera.position.y + (Gdx.graphics.getHeight() - Gdx.input.getY() - Gdx.graphics.getHeight()/2) * (camera.viewportHeight / Gdx.graphics.getHeight());
	}

}
