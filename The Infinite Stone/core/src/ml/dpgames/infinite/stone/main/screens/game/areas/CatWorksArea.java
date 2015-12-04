package ml.dpgames.infinite.stone.main.screens.game.areas;

import ml.dpgames.infinite.stone.main.Graphics;
import ml.dpgames.infinite.stone.main.IStoneMain;
import ml.dpgames.infinite.stone.main.Input;
import ml.dpgames.infinite.stone.main.Lang;
import ml.dpgames.infinite.stone.main.pretty.stuff.Clicksplosion;
import ml.dpgames.infinite.stone.main.screens.game.Area;
import ml.dpgames.infinite.stone.main.screens.game.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class CatWorksArea extends Area {

	public float scroll = 0;

	@Override
	public void render(SpriteBatch batch, int areaX) {
		Catalog.draw(batch, new TextureRegion(Graphics.spriteSheet, (int) Graphics.getItemCoords(GameScreen.numGems+1).x, (int) Graphics.getItemCoords(GameScreen.numGems+1).y, 32, 32), 1, new TextureRegion(
				Graphics.spriteSheet, (int) Graphics.getItemCoords(GameScreen.numGems+1).x, (int) Graphics.getItemCoords(GameScreen.numGems+1).y, 32, 32), 1, Lang.prop.getProperty("dwarves"), areaX, -IStoneMain.minCamWidth
				* (1.5f / 4f), 1 * 96 + scroll, IStoneMain.minCamWidth * (3f / 4f));
		for (int i = 0; i < Catalog.itemNames.length; i++) {
			boolean unlocked = GameScreen.unlockedGems[i < GameScreen.gems.length - 2 ? i + 1 : i];
			Vector2 coords = unlocked ? Graphics.getItemCoords(i) : Graphics.getItemCoords(Catalog.itemNames.length);
			Vector2 coords1 = unlocked ? Graphics.getItemCoords(i + 1) : Graphics.getItemCoords(Catalog.itemNames.length);
			int cost = unlocked ? 3 : 1;
			String[] names = Catalog.itemNames[i].split(" ");
			String name = "";
			for (String s : names) {
				name += s.charAt(0);
			}
			int pointer = Catalog.draw(batch, new TextureRegion(Graphics.spriteSheet, (int) coords.x, (int) coords.y, 32, 32), 1, new TextureRegion(
					Graphics.spriteSheet, (int) coords1.x, (int) coords1.y, 32, 32), cost, unlocked ? name + " " + Lang.prop.getProperty("dwarf") : "???", areaX, -IStoneMain.minCamWidth
					* (1.5f / 4f), -i * 96 + scroll, IStoneMain.minCamWidth * (3f / 4f));
			if (pointer == 3) {
				if (GameScreen.gems[i + 1] >= 3) {
					GameScreen.dwarfs[i]++;
					GameScreen.gems[i + 1] -= 3;
					GameScreen.splodes.add(new Clicksplosion(areaX * GameScreen.getSeparation(), -i * 96 + scroll, 100));
				}
			}
		}
		if (GameScreen.currentArea != areaX)
			return;
		if (Gdx.input.isTouched() && Input.touchExceded(GameScreen.camera) && Input.getDir(GameScreen.camera) == Input.Dir.VERTICAL) {
			scroll -= Input.getDeltaY(GameScreen.camera) * Catalog.sensitivity;
		}
		scroll += Input.scroll * 64;
		if (scroll < 1 * 96)
			scroll = 1 * 96;
		if (scroll > (Catalog.itemNames.length - 3) * 96)
			scroll = (Catalog.itemNames.length - 3) * 96;
	}

}
