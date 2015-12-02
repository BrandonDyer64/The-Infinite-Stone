package ml.dpgames.infinite.stone.main.screens.game.areas;

import ml.dpgames.infinite.stone.main.screens.game.Area;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Catalog {

	public static final Texture slot = new Texture("catSlot.png");

	public static final int draw(Batch batch, TextureRegion item, int amount, TextureRegion costItem, int cost, String name, int areaX, float x, float y,
			float width) {
		float height = (slot.getHeight() * width) / slot.getWidth();
		int pointer = Area.draw(slot, areaX, x, y, width, height);

		Area.draw(item, areaX, x + width * 0.06f, y + height * 0.25f, height * 0.5f, height * 0.5f);
		Area.drawString(batch, String.valueOf(amount), Color.WHITE, areaX, x + width * 0.07f, y + height * 0.39f, 1.3f);
		Area.draw(item, areaX, x + width * 0.81f, y + height * 0.25f, height * 0.5f, height * 0.5f);
		Area.drawString(batch, String.valueOf(cost), Color.WHITE, areaX, x + width * 0.82f, y + height * 0.39f, 1.3f);
		
		return pointer;
	}

}
