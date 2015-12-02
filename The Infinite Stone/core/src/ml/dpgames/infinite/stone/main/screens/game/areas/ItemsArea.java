package ml.dpgames.infinite.stone.main.screens.game.areas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ml.dpgames.infinite.stone.main.Graphics;
import ml.dpgames.infinite.stone.main.IStoneMain;
import ml.dpgames.infinite.stone.main.Input;
import ml.dpgames.infinite.stone.main.screens.game.Area;
import ml.dpgames.infinite.stone.main.screens.game.GameScreen;

public class ItemsArea extends Area {

	public float scroll = 0;

	@Override
	public void render(SpriteBatch batch, int areaX) {
		for (int i = 0; i < Catalog.itemNames.length; i++) {
			if (GameScreen.gems[i] > 0) {
				GameScreen.unlockedGems[i] = true;
			}
			boolean unlocked = GameScreen.unlockedGems[i] || GameScreen.gems[i] > 0;
			Vector2 coords = unlocked ? Graphics.getItemCoords(i) : Graphics.getItemCoords(Catalog.itemNames.length);
			Vector2 coords1 = Graphics.getItemCoords(Catalog.itemNames.length);
			if (unlocked) {
				int pointer = Catalog.draw(batch, new TextureRegion(Graphics.spriteSheet, (int) coords.x, (int) coords.y, 32, 32), 1, new TextureRegion(
						Graphics.spriteSheet, (int) coords1.x, (int) coords1.y, 32, 32), 1, String.valueOf(GameScreen.gems[i]), areaX,
						-IStoneMain.minCamWidth * (1.5f / 4f), -i * 96 + scroll, IStoneMain.minCamWidth * (3f / 4f));
				if (pointer == 3) {
					if (GameScreen.gems[i - 1] >= 100) {
						GameScreen.gems[i]++;
						GameScreen.gems[i - 1] -= 100;
					}
				}
			}
		}
		if (Gdx.input.isTouched()) {
			scroll -= Input.getDeltaY(GameScreen.camera) * 2;
		}
		scroll += Input.scroll * 64;
		if (scroll < 2 * 64)
			scroll = 2 * 64;
		if (scroll > (Catalog.itemNames.length - 3) * 96)
			scroll = (Catalog.itemNames.length - 3) * 96;
	}

}
