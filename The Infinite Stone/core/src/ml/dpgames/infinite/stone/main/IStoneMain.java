package ml.dpgames.infinite.stone.main;

import ml.dpgames.infinite.stone.main.screens.title.TitleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class IStoneMain extends Game {

	@Override
	public void create() {
		setScreen(new TitleScreen());
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	public static final void setTheScreen(Screen screen) {
		((Game)Gdx.app.getApplicationListener()).setScreen(screen);
	}
	
}
