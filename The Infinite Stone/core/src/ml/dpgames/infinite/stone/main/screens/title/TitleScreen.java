package ml.dpgames.infinite.stone.main.screens.title;

import ml.dpgames.infinite.stone.main.Graphics;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleScreen implements Screen {

	private SpriteBatch batch;

	@Override
	public void show() {
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Graphics.clear(0, 0, 0);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
