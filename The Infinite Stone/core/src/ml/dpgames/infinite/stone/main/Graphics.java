package ml.dpgames.infinite.stone.main;

import ml.dpgames.infinite.stone.main.pretty.stuff.Clicksplosion;
import ml.dpgames.infinite.stone.main.screens.game.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Graphics {

	public static final Texture stoneBackground = new Texture("stone_background.png");
	public static final Texture vignette = new Texture("vignette.png");
	public static final Texture spriteSheet = new Texture("sprite_sheet.png");
	public static final Texture tab = new Texture("tab.png");
	private static BitmapFont font = null;
	private static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("DroidSans-Bold.ttf"));
	private static FreeTypeFontParameter parameter = new FreeTypeFontParameter();

	/**
	 * Clears the screen with the specified color.
	 * 
	 * @param r
	 *            red
	 * @param g
	 *            green
	 * @param b
	 *            blue
	 */
	public static void clear(float r, float g, float b) {
		Gdx.gl.glClearColor(r, g, b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * Clears the screen with the specified color.
	 * 
	 * @param hexcode
	 */
	public static void clear(int hexcode) {
		Color c = new Color(hexcode);
		clear(c.r, c.g, c.b);
	}

	public static final int draw(Batch batch, TextureRegion tex, float x, float y, float width, float height, Camera camera) {
		batch.draw(tex, x, y, width, height);
		int pointer = 0;
		Rectangle rect;
		if (width > 0)
			rect = new Rectangle(x, y, width, height);
		else
			rect = new Rectangle(x + width, y, -width, height);

		Vector2 in = new Vector2(Input.getX(camera), Input.getY(camera));
		// pointer = 0
		if (rect.contains(in)) {
			pointer++; // pointer = 1
			if (Gdx.input.isTouched()) {
				pointer++; // pointer = 2
			}
			if (Input.justReleased() && !Input.touchExceded(camera)) {
				pointer += 2; // pointer = 3;
				GameScreen.splodes.add(new Clicksplosion(Input.getX(camera),Input.getY(camera),5));
			}
		}
		return pointer;
	}

	public static int drawString(Batch batch, String str, Color color, float x, float y, float size, Camera camera) {
		if (font == null) {
			parameter.size = 50;
			font = generator.generateFont(parameter);
		}
		font.setColor(color);
		font.getData().setScale((size / 50 * 12));
		GlyphLayout layout = new GlyphLayout();
		layout.setText(font, str);
		font.draw(batch, str, x, y);
		return Input.touchGlyph(layout, new Vector2(x, y), camera);
	}
	
	public static final Vector2 getItemCoords(int item) {
		return new Vector2((item % 8) * 32, (item / 8) * 32);
	}
	
}
