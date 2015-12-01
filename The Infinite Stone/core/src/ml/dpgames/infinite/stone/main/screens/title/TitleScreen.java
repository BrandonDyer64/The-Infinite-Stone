package ml.dpgames.infinite.stone.main.screens.title;

import ml.dpgames.infinite.stone.main.Graphics;
import ml.dpgames.infinite.stone.main.IStoneMain;
import ml.dpgames.infinite.stone.main.Input;
import ml.dpgames.infinite.stone.main.Lang;
import ml.dpgames.infinite.stone.main.pretty.stuff.Flame;
import ml.dpgames.infinite.stone.main.screens.game.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;

public class TitleScreen implements Screen {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Flame torchFlame;
	public static final Texture tex = new Texture("title.png");
	public static final Texture rope = new Texture("title_rope.png");
	BitmapFont font;
	FreeTypeFontGenerator generator;
	FreeTypeFontParameter parameter;

	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(getCamWidth(IStoneMain.scaling), IStoneMain.scaling);
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		torchFlame = new Flame(0, 0);
		Lang.prop.getProperty("");
		System.out.println("RUN");
		generator = new FreeTypeFontGenerator(Gdx.files.internal("DroidSans-Bold.ttf"));
		parameter = new FreeTypeFontParameter();
	}

	@Override
	public void render(float delta) {
		Graphics.clear(0, 0, 0);
		/*Update*/{
			camera.update();
		}
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		/*Render*/{
			for (int x = -1; x < camera.viewportWidth / Graphics.stoneBackground.getWidth() + 1; x++) {
				for (int y = -1; y < camera.viewportHeight / Graphics.stoneBackground.getHeight() + 1; y++) {
					batch.draw(Graphics.stoneBackground, -camera.viewportWidth / 2 + x * Graphics.stoneBackground.getWidth(), -camera.viewportHeight / 2 + y
							* Graphics.stoneBackground.getHeight());
				}
			}
			batch.draw(new TextureRegion(rope), -tex.getWidth() / 2 + (float) Math.sin(IStoneMain.gameTime) * 64,
					720 / 4 - tex.getHeight() / 2 - (float) Math.cos(IStoneMain.gameTime * 2) * 2, tex.getWidth() / 2, tex.getHeight() / 2, rope.getWidth(),
					rope.getHeight(), 1, 1, (float) Math.sin(IStoneMain.gameTime) * 3);
			batch.draw(new TextureRegion(tex), -tex.getWidth() / 2 + (float) Math.sin(IStoneMain.gameTime) * 64,
					720 / 4 - tex.getHeight() / 2 - (float) Math.cos(IStoneMain.gameTime * 2) * 2, tex.getWidth() / 2, tex.getHeight() / 2, tex.getWidth(),
					tex.getHeight(), 1, 1, (float) Math.sin(IStoneMain.gameTime) * 3);

			torchFlame.x = (float) Math.sin(IStoneMain.gameTime) * 64 - 64;
			torchFlame.y = 720 / 4 - (float) Math.cos(IStoneMain.gameTime * 2) * 2 + 64;
			batch.draw(Graphics.vignette, -camera.viewportWidth / 2, -camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight);
			torchFlame.render(batch);
			parameter.size = 40 * Gdx.graphics.getHeight() / IStoneMain.scaling;
			if (font != null)
				font.dispose();
			font = generator.generateFont(parameter);
			font.getData().setScale(camera.viewportHeight / Gdx.graphics.getHeight());
			GlyphLayout layout = new GlyphLayout();
			// Play
			String play = Lang.prop.getProperty("play");
			layout.setText(font, play);
			font.draw(batch, play, -layout.width / 2, 0);
			if (Input.touchGlyph(layout, new Vector2(-layout.width / 2, 0), camera) == 3) {
				IStoneMain.setTheScreen(new GameScreen());
			}
			// Options
			String options = Lang.prop.getProperty("options");
			layout.setText(font, options);
			font.draw(batch, options, -layout.width / 2, -IStoneMain.scaling / 10);
			// Help
			String help = Lang.prop.getProperty("help");
			layout.setText(font, help);
			font.draw(batch, help, -layout.width / 2, -IStoneMain.scaling / 10 * 2);
			// Credits
			String credits = Lang.prop.getProperty("credits");
			layout.setText(font, credits);
			font.draw(batch, credits, -layout.width / 2, -IStoneMain.scaling / 10 * 3);
		}
		batch.end();
	}

	/**
	 * Gets the width of a camera with specified height
	 * 
	 * @param camHeight
	 *            The height of the camera.
	 * @return The width of the camera.
	 */
	public static int getCamWidth(int camHeight) {
		// cam.height / cam.width = width / height
		// cam.width = (width * cam.height) / height
		int camWidth = (Gdx.graphics.getWidth() * camHeight) / Gdx.graphics.getHeight();
		return camWidth;
	}

	/**
	 * Gets the height of a camera with specified height
	 * 
	 * @param camWidth
	 *            The width of the camera.
	 * @return The height of the camera.
	 */
	public static int getCamHeight(int camWidth) {
		// cam.height / cam.width = width / height
		// cam.width = (width * cam.height) / height
		int camHeight = (Gdx.graphics.getHeight() * camWidth) / Gdx.graphics.getWidth();
		return camHeight;
	}

	@Override
	public void resize(int width, int height) {
		if ((float) IStoneMain.minCamWidth / IStoneMain.scaling < (float) width / height) {
			camera.viewportHeight = IStoneMain.scaling;
			camera.viewportWidth = getCamWidth(IStoneMain.scaling);
		} else {
			camera.viewportHeight = getCamHeight(IStoneMain.minCamWidth);
			camera.viewportWidth = IStoneMain.minCamWidth;
		}
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
