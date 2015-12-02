package ml.dpgames.infinite.stone.main.screens.game;

import ml.dpgames.infinite.stone.main.Graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Area {

	public static BitmapFont font = null;
	
	public abstract void render(SpriteBatch batch, int areaX);

	public static int draw(TextureRegion tex, int areaX, float x, float y, float width, float height) {
		return Graphics.draw(GameScreen.batch, tex, x + areaX * GameScreen.getSeparation(), y, width, height, GameScreen.camera);
	}

	public static int draw(Texture tex, int areaX, float x, float y, float width, float height) {
		return Graphics.draw(GameScreen.batch, new TextureRegion(tex), x + areaX * GameScreen.getSeparation(), y, width, height, GameScreen.camera);
	}
	
	public static int drawString(Batch batch, String str, Color color, int areaX, float x, float y, float size) {
		return Graphics.drawString(batch, str, color, x + areaX * GameScreen.getSeparation(), y, size, GameScreen.camera);
	}
}
