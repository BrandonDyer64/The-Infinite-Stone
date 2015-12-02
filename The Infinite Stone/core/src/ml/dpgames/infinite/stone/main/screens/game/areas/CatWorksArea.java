package ml.dpgames.infinite.stone.main.screens.game.areas;

import ml.dpgames.infinite.stone.main.IStoneMain;
import ml.dpgames.infinite.stone.main.screens.game.Area;
import ml.dpgames.infinite.stone.main.screens.game.GameScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CatWorksArea extends Area {
	
	@Override
	public void render(SpriteBatch batch, int areaX) {
		Catalog.draw(batch, StoneArea.stone, 2, StoneArea.stone, 2, "Stone", areaX, -IStoneMain.minCamWidth*(1.5f/4f),0, IStoneMain.minCamWidth * (3f/4f));
	}
	
}
