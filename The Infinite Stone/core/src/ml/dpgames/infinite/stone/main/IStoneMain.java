package ml.dpgames.infinite.stone.main;

import ml.dpgames.infinite.stone.main.screens.title.TitleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class IStoneMain extends Game {
	
	public static float gameTime = 0;
	public static int scaling = 720;
	public static int minCamWidth = 480;
	
	@Override
	public void create() {
		gameTime = 0;
		Gdx.input.setInputProcessor(new Input());
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
