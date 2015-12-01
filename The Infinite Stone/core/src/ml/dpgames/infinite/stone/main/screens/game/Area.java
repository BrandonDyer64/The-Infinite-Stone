package ml.dpgames.infinite.stone.main.screens.game;

import ml.dpgames.infinite.stone.main.Graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Area {
	
	protected int x;
	
	public Area(int x) {
		this.x = x;
	}
	
	public abstract void render(SpriteBatch batch);
	
	public static int draw(TextureRegion tex, float x, float y, float width, float height) {
		return Graphics.draw(GameScreen.batch, tex, x, y, width, height, GameScreen.camera);
	}
}
