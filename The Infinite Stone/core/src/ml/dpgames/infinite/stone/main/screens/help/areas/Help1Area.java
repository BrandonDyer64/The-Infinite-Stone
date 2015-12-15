package ml.dpgames.infinite.stone.main.screens.help.areas;

import ml.dpgames.infinite.stone.main.screens.game.Area;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Help1Area extends Area {

	@Override
	public void render(SpriteBatch batch, int areaX) {
		Area.drawString(batch, "THIS IS A HELP SCREEN\nNEW LINE", Color.WHITE, areaX, 0, 0, 2f);
	}

}
