package ml.dpgames.infinite.stone.main.screens.game;

import java.util.LinkedList;

import ml.dpgames.infinite.stone.main.Graphics;
import ml.dpgames.infinite.stone.main.IStoneMain;
import ml.dpgames.infinite.stone.main.Input;
import ml.dpgames.infinite.stone.main.pretty.stuff.Clicksplosion;
import ml.dpgames.infinite.stone.main.screens.game.areas.CatItemsArea;
import ml.dpgames.infinite.stone.main.screens.game.areas.CatWorksArea;
import ml.dpgames.infinite.stone.main.screens.game.areas.ItemsArea;
import ml.dpgames.infinite.stone.main.screens.game.areas.StoneArea;
import ml.dpgames.infinite.stone.main.screens.title.TitleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {

	public static final SpriteBatch batch = new SpriteBatch();
	public static final OrthographicCamera camera = new OrthographicCamera(TitleScreen.getCamWidth(IStoneMain.scaling), IStoneMain.scaling);
	public static int[] gems;
	public static final int numGems = 17;
	public static boolean[] unlockedGems;
	public static int[] dwarfs;
	public static float dwarfTimeDelta = 0;
	public static int dwarfUpdateDelta = 0;
	public static Area[] areas;
	public static int currentArea = 0;
	public static int transitionSpeed = 10;
	public static final LinkedList<Clicksplosion> splodes = new LinkedList<Clicksplosion>();

	@Override
	public void show() {
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.x = -getSeparation() * 20;
	}

	public static void init() {
		areas = new Area[] { new StoneArea(), new ItemsArea(), new CatItemsArea(), new CatWorksArea(), };
		gems = new int[numGems];
		unlockedGems = new boolean[numGems];
		for (int i = 0; i < numGems; i++) {
			gems[i] = 0;
			unlockedGems[i] = false;
		}
		unlockedGems[0] = true;
		dwarfs = new int[numGems];
		for (int i = 0; i < numGems; i++) {
			dwarfs[i] = 0;
		}
	}

	@Override
	public void render(float delta) {
		/*Update*/{
			if (Gdx.input.isKeyPressed(Keys.SPACE)) {
				gems[0] += 1000;
				splodes.add(new Clicksplosion(0, 0, 1));
			}
			dwarfTimeDelta += delta;
			if (dwarfTimeDelta > 0.5f) {
				dwarfTimeDelta = 0;
				updateDwarfs();
				checkAchievements();
			}
		}
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
			for (int i = 0; i < splodes.size(); i++) {
				splodes.get(i).render(batch);
			}
		}
		batch.end();
	}

	public static int getTier() {
		for (int i = 0; i < unlockedGems.length; i++) {
			if (!unlockedGems[i]) {
				return i - 3 > 0 ? i - 3 : 0;
			}
		}
		return 0;
	}
	
	/**
	 * Checks if achievements have been acquired and notifies Google
	 */
	
	public static void checkAchievements() {
		// TODO: Achievements : Austin this is for you.
	}

	public static void updateDwarfs() {
		dwarfUpdateDelta++;
		for (int i = 0; i < dwarfs.length - 1; i++) {
			if (gems[i] < 1000000) {
				gems[i] += dwarfs[i];
				if (dwarfUpdateDelta >= 5 && gems[i + 1] < 100) {
					if (gems[i] > 100 && (dwarfs[i] > 1)) {
						gems[i] -= 100;
						gems[i < numGems - 1 ? i + 1 : i]++;
					}
				}
			} else {
				gems[i] = 1000000000;
			}
		}
		if (dwarfUpdateDelta >= 5) {
			dwarfUpdateDelta = 0;
			save();
		}
	}
	
	public static void save() {
		String data = "";
		for (int i = 0; i < gems.length; i++) {
			data += String.valueOf(gems[i]) + "\n";
		}
		Gdx.files.local("gems.dat").writeString(data, false);
		data = "";
		for (int i = 0; i < unlockedGems.length; i++) {
			data += String.valueOf(unlockedGems[i]) + "\n";
		}
		Gdx.files.local("unlockedGems.dat").writeString(data, false);
		data = "";
		for (int i = 0; i < dwarfs.length; i++) {
			data += String.valueOf(dwarfs[i]) + "\n";
		}
		Gdx.files.local("dwarfs.dat").writeString(data, false);
	}
	
	public static void load() {
		String data = Gdx.files.local("gems.dat").readString();
		String[] datas = data.split("\n");
		for (int i = 0; i < Math.min(datas.length, gems.length); i++) {
			gems[i] = Integer.parseInt(datas[i]);
		}
		data = Gdx.files.local("unlockedGems.dat").readString();
		datas = data.split("\n");
		for (int i = 0; i < Math.min(datas.length, unlockedGems.length); i++) {
			unlockedGems[i] = Boolean.getBoolean(datas[i]);
		}
		data = Gdx.files.local("dwarfs.dat").readString();
		datas = data.split("\n");
		for (int i = 0; i < Math.min(datas.length, dwarfs.length); i++) {
			dwarfs[i] = Integer.parseInt(datas[i]);
		}
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
