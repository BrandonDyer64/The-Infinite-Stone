package ml.dpgames.infinite.stone.main;

import ml.dpgames.infinite.stone.main.screens.game.GameScreen;
import ml.dpgames.infinite.stone.main.screens.help.HelpScreen;
import ml.dpgames.infinite.stone.main.screens.title.TitleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class IStoneMain extends Game {
	
	public static float gameTime = 0;
	public static int scaling = 720;
	public static int minCamWidth = 480;
	public static Handler handler;
	
	public IStoneMain(Handler handler) {
		IStoneMain.handler = handler;
	}
	
	@Override
	public void create() {
		gameTime = 0;
		Gdx.input.setInputProcessor(new Input());
		GameScreen.init();
		HelpScreen.init();
		try {
			GameScreen.load();
		} catch (Exception e) {
			System.out.println("Game files not found. Creating them.");
		}
		setScreen(new TitleScreen());
	}

	@Override
	public void render() {
		gameTime += Gdx.graphics.getDeltaTime();
		Input.update();
		super.render();
		Input.wasTouched = Gdx.input.isTouched();
		Input.scroll = 0;
	}

	/**
	 * Sets the current game screen.
	 * 
	 * @param screen
	 */
	public static final void setTheScreen(Screen screen) {
		((Game) Gdx.app.getApplicationListener()).setScreen(screen);
	}

}
