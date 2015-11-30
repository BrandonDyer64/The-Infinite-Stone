package ml.dpgames.infinite.stone.main.screens.test;

import ml.dpgames.infinite.stone.main.Graphics;
import ml.dpgames.infinite.stone.main.pretty.stuff.Flame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FireScreen implements Screen {

	private SpriteBatch batch;
	private static final Flame flame = new Flame(0,0);

	@Override
	public void show() {
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Graphics.clear(0, 0, 0);
		batch.begin();
		{
			flame.x = Gdx.input.getX();
			flame.y = Gdx.graphics.getHeight() - Gdx.input.getY();
			flame.render(batch);
		}
		batch.end();
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
	}

}
