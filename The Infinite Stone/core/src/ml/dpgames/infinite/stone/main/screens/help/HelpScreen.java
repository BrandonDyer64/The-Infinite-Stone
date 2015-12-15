package ml.dpgames.infinite.stone.main.screens.help;

import ml.dpgames.infinite.stone.main.Graphics;
import ml.dpgames.infinite.stone.main.IStoneMain;
import ml.dpgames.infinite.stone.main.Input;
import ml.dpgames.infinite.stone.main.screens.game.Area;
import ml.dpgames.infinite.stone.main.screens.game.GameScreen;
import ml.dpgames.infinite.stone.main.screens.help.areas.Help1Area;
import ml.dpgames.infinite.stone.main.screens.title.TitleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HelpScreen implements Screen {

	public static final SpriteBatch batch = new SpriteBatch();
	public static final OrthographicCamera camera = new OrthographicCamera(TitleScreen.getCamWidth(IStoneMain.scaling), IStoneMain.scaling);
	public static Area[] areas;
	public static int currentArea = 0;
	public static int transitionSpeed = 10;

	@Override
	public void show() {
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.x = -getSeparation() * 20;
	}

	public static void init() {
		areas = new Area[] { new Help1Area() };
	}

	@Override
	public void render(float delta) {
		Graphics.clear(0, 0, 0);
		camera.position.y = 0;
		camera.position.x = getSeparation() * currentArea - (getSeparation() * currentArea - camera.position.x) / (transitionSpeed * delta + 1f);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		{
			for (int x = -1; x < camera.viewportWidth / Graphics.stoneBackground.getWidth() + 1; x++) {
				for (int y = -1; y < camera.viewportHeight / Graphics.stoneBackground.getHeight() + 1; y++) {
					batch.draw(Graphics.stoneBackground, camera.position.x + -camera.viewportWidth / 2 + x * Graphics.stoneBackground.getWidth()
							- (camera.position.x / 2f) % Graphics.stoneBackground.getWidth(),
							-camera.viewportHeight / 2 + y * Graphics.stoneBackground.getHeight());
				}
			}
			batch.draw(Graphics.vignette, -camera.viewportWidth / 2 + camera.position.x, -camera.viewportHeight / 2, camera.viewportWidth,
					camera.viewportHeight);
			for (int i = Math.max(0, currentArea - 1); i < Math.min(areas.length, currentArea + 2); i++) {
				areas[i].render(batch, i);
			}
			int tabWidth = 6 * 8;
			int tabHeight = 32 * 8;
			if (Graphics
					.draw(batch, new TextureRegion(Graphics.tab), camera.position.x - camera.viewportWidth / 2, -tabHeight / 2, tabWidth, tabHeight, camera) == 3) {
				if (currentArea != 0)
					currentArea--;
				else
					IStoneMain.setTheScreen(new TitleScreen());
			}
			if (currentArea != areas.length - 1)
				if (Graphics.draw(batch, new TextureRegion(Graphics.tab), camera.position.x + camera.viewportWidth / 2, -tabHeight / 2, -tabWidth, tabHeight,
						camera) == 3) {
					currentArea++;
				}
			if (Gdx.input.isKeyJustPressed(Keys.A))
				currentArea--;
			if (Gdx.input.isKeyJustPressed(Keys.D))
				currentArea++;
			float threshold = Gdx.graphics.getWidth() * 2f;
			if (Gdx.input.getDeltaX() / delta > threshold && Gdx.input.isTouched() && camIsPlaced() && Input.getDir(camera) == Input.Dir.HORIZONTAL)
				currentArea--;
			if (Gdx.input.getDeltaX() / delta < -threshold && Gdx.input.isTouched() && camIsPlaced() && Input.getDir(camera) == Input.Dir.HORIZONTAL)
				currentArea++;
			if (currentArea < 0)
				currentArea = 0;
			if (currentArea >= areas.length)
				currentArea = areas.length - 1;
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
		// camera.position.x = getSeparation() * currentArea;
	}

	public static boolean camIsPlaced() {
		int threshold = 10;
		return camera.position.x > currentArea * getSeparation() - threshold && camera.position.x < currentArea * getSeparation() + threshold;
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
