package ml.dpgames.infinite.stone.main.screens.game;

import java.util.LinkedList;

import ml.dpgames.infinite.stone.main.Graphics;
import ml.dpgames.infinite.stone.main.IStoneMain;
import ml.dpgames.infinite.stone.main.screens.title.TitleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

	public static final SpriteBatch batch = new SpriteBatch();
	public static final OrthographicCamera camera = new OrthographicCamera(TitleScreen.getCamWidth(IStoneMain.scaling), IStoneMain.scaling);
	public static final LinkedList<Integer> numGems = new LinkedList<Integer>();

	@Override
	public void show() {
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		Graphics.clear(0, 0, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		{

		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if ((float) IStoneMain.minCamWidth / IStoneMain.scaling < (float) width / height) {
			camera.viewportHeight = IStoneMain.scaling;
			camera.viewportWidth = TitleScreen.getCamWidth(IStoneMain.scaling);
		} else {
			camera.viewportHeight = TitleScreen.getCamHeight(IStoneMain.minCamWidth);
			camera.viewportWidth = IStoneMain.minCamWidth;
		}
	}

	public static final float getSeparation() {
		return camera.viewportWidth;
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
