package ml.dpgames.infinite.stone.main.screens.game.areas;

import ml.dpgames.infinite.stone.main.Graphics;
import ml.dpgames.infinite.stone.main.IStoneMain;
import ml.dpgames.infinite.stone.main.Lang;
import ml.dpgames.infinite.stone.main.screens.game.Area;
import ml.dpgames.infinite.stone.main.screens.game.GameScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StoneArea extends Area {

	public static final TextureRegion stone = new TextureRegion(Graphics.spriteSheet, 0, 0, 32, 32);

	@Override
	public void render(SpriteBatch batch, int areaX) {
		float scroll = -96 * 4.5f;
		Catalog.draw(batch, new TextureRegion(Graphics.spriteSheet, (int) Graphics.getItemCoords(GameScreen.numGems+1).x, (int) Graphics.getItemCoords(GameScreen.numGems+1).y, 32, 32), 1, new TextureRegion(
				Graphics.spriteSheet, (int) Graphics.getItemCoords(GameScreen.numGems+1).x, (int) Graphics.getItemCoords(GameScreen.numGems+1).y, 32, 32), 1, Lang.prop.getProperty("the_stone"), areaX, -IStoneMain.minCamWidth
				* (1.5f / 4f), 1 * 96 + scroll, IStoneMain.minCamWidth * (3f / 4f));
		if (draw(stone, areaX, -128,-128,256,256) == 3) {
			GameScreen.gems[0]++;
		}
		Area.drawString(batch, "Stones: " + String.valueOf(GameScreen.gems[0]), Color.WHITE, areaX, -GameScreen.camera.viewportWidth / 2, -GameScreen.camera.viewportHeight/2 + 22, 2);
	}

}
