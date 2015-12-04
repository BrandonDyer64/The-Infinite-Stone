package ml.dpgames.infinite.stone.main.screens.game.areas;

import ml.dpgames.infinite.stone.main.screens.game.Area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Catalog {

	public static final Texture slot = new Texture("catSlot.png");
	public static final String itemNameOrig = Gdx.files.internal("item_names.txt").readString();
	public static final String[] itemNames = itemNameOrig.split("\n");
	public static final float sensitivity = 1.3f;

	public static final int draw(Batch batch, TextureRegion item, int amount, TextureRegion costItem, int cost, String name, int areaX, float x, float y,
			float width) {
		float height = (slot.getHeight() * width) / slot.getWidth();
		int pointer = Area.draw(slot, areaX, x, y, width, height);

		Area.draw(item, areaX, x + width * 0.06f, y + height * 0.25f, height * 0.5f, height * 0.5f);
		if (amount != 1)
			Area.drawString(batch, String.valueOf(amount), Color.WHITE, areaX, x + width * 0.07f, y + height * 0.39f, 1.3f);
		Area.draw(costItem, areaX, x + width * 0.81f, y + height * 0.25f, height * 0.5f, height * 0.5f);
		if (cost != 1)
			Area.drawString(batch, String.valueOf(cost), Color.WHITE, areaX, x + width * 0.82f, y + height * 0.39f, 1.3f);
		/*GlyphLayout layout = new GlyphLayout();
		layout.setText(TitleScreen.font, name);
		TitleScreen.font.draw(batch, name, x + areaX * GameScreen.getSeparation() + width * 0.06f -layout.width / 2, y + height * 0.6f);
		*/
		Area.drawString(batch, name, Color.WHITE, areaX, x + width * 0.26f, y + height * 0.6f, 2);
		return pointer;
	}

}
